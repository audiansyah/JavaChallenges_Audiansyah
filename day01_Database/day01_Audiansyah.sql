ALTER ROLE postgres SET search_path TO oe;

/* 1. Tampilkan data category & total product-nya.*/
select c.category_id, category_name, count (p.quantity_per_unit) as total_product
from categories c  
join products p on c.category_id = p.category_id
group by c.category_id
order by category_name;

/*2. Tampilkan data supplier & total product-nya.*/
select s.supplier_id, company_name, count (p.quantity_per_unit) as total_product
from suppliers s
join products p on s.supplier_id = p.supplier_id
group by s.supplier_id, company_name
order by total_product desc;

/*3. Tampilkan data supplier, total product dan harga rata-rata tiap product (gunakaan to_char() untuk
menampilkan format avg_unit_price)*/
select s.supplier_id, company_name, count (p.quantity_per_unit) as total_product,
to_char(avg (p.unit_price),'9,999,999.99') as avg_unit_price
from suppliers s
join products p on s.supplier_id = p.supplier_id
group by s.supplier_id, company_name
order by total_product desc;

/*4. Tampilkan data product yang harus pesan lagi ke supplier sesuai reorder-level nya.*/
select p.product_id, product_name, s.supplier_id, s.company_name, unit_price, units_in_stock, units_on_order, reorder_level
from products p
join suppliers s on p.supplier_id = s.supplier_id
where p.units_in_stock <= p.reorder_level
order by product_name asc;

/*5. Tampilkan data customer dan total order-nya*/
select c.customer_id, company_name, count(o.order_id) as total_order
from customers c
join orders o on c.customer_id = o.customer_id
group by c.customer_id, company_name
order by company_name asc;

/*6. Tampilkan data order yang melebihi delivery time lebih dari 7 hari.*/
select o.order_id, c.customer_id, order_date, required_date, shipped_date, shipped_date - order_date as delivery_time
from orders o
join customers c on o.customer_id = c.customer_id
where shipped_date - order_date > 7;

with delivery_data as (select o.order_id, c.customer_id, order_date, required_date, shipped_date, shipped_date - order_date as delivery_time
from orders o
join customers c on o.customer_id = c.customer_id)
select *
from delivery_data
where delivery_time > 7;

/*7. Tampilkan total product yang sudah di order dan urut berdasarkan total_quantity terbesar.*/
select p.product_id, product_name, sum(od.quantity) as total_qty
from products p
join order_details od on p.product_id = od.product_id
join orders o on o.order_id = od.order_id
where shipped_date is not NULL
group by p.product_id, product_name
order by total_qty desc;

/*8. Tampilkan total product yang sudah di order berdasarkan category*/
select c.category_id, category_name, sum(od.quantity) as total_qty_ordered
from categories c
join products p on p.category_id = c.category_id
join order_details od on od.product_id = p.product_id
group by c.category_id, category_name
order by total_qty_ordered desc;

/*9. Mengacu ke soal no 8, tampilkan data category yang memiliki min & max total_qty_ordered*/
-- Subquery untuk ambil total_qty_ordered per kategori
with total_per_category AS (
  select c.category_id, c.category_name, sum(od.quantity) as total_qty_ordered
  from
    categories c
  join 
    products p on c.category_id = p.category_id
  join
    order_details od ON p.product_id = od.product_id
  group by 
    c.category_id, c.category_name
)

select * from total_per_category
where total_qty_ordered = (
    select max(total_qty_ordered) from total_per_category
)
union
select * from total_per_category
where total_qty_ordered = (
    select min(total_qty_ordered) from total_per_category
);

--bisa juga pakai 
with total_per_category as (
  select c.category_id, c.category_name, sum(od.quantity) AS total_qty_ordered
  from
    categories c
  join 
    products p ON c.category_id = p.category_id
  join 
    order_details od on p.product_id = od.product_id
  group by 
    c.category_id, c.category_name
),

a as (
  select *, rank() over (order by total_qty_ordered desc) as x,
         rank() over (order by total_qty_ordered asc) as y
  from total_per_category
)
select category_id, category_name, total_qty_ordered
from a
where x = 1 or y = 1
order by total_qty_ordered desc;

/*10. Tampilkan data shipper dan total qty product yang dikirim*/
select s.shipper_id, s.company_name, p.product_id, p.product_name, sum(od.quantity) AS total_qty_ordered
from shippers s
join orders o on s.shipper_id = o.ship_via
join order_details od on o.order_id = od.order_id
join products p on od.product_id = p.product_id
group by s.shipper_id, s.company_name, p.product_id, p.product_name
order by s.company_name, p.product_name;

-- CTE
with cte1 as(
select s.shipper_id, s.company_name, o.order_id
from shippers s 
join orders o on s.shipper_id= o.ship_via),
cte2 as (
select p.product_id, p.product_name, od.order_id,
sum(od.quantity)as total_qty_ordered
from products p
join order_details od on p.product_id=od.product_id
group by 1, 2, 3)
select c1.shipper_id, c1.company_name, c2.product_id,
c2.product_name,sum(c2.total_qty_ordered) as total_qty_ordered
from cte1 c1 
join cte2 c2 on c1.order_id=c2.order_id
group by 1, 2, 3, 4
order by c1.company_name, c2.product_name

/*11. Tampilkan data shipper dengan product yang paling sering dikirim dan paling minim dikirim*/
with cte3 as (
    select s.shipper_id, s.company_name, p.product_id, p.product_name, sum(od.quantity) as total_qty_ordered
    from shippers s
    join orders o on s.shipper_id = o.ship_via
    join order_details od on o.order_id = od.order_id
    join products p on od.product_id = p.product_id
    group by s.shipper_id, s.company_name, p.product_id, p.product_name
    order by s.company_name, p.product_name
),
min_max_per_shipper as (
    select
        shipper_id,
        min(total_qty_ordered) as min_qty,
        max(total_qty_ordered) as max_qty
    from cte3
    group by shipper_id
)
select c.shipper_id, c.company_name, c.product_id, c.product_name, c.total_qty_ordered
from cte3 c
join min_max_per_shipper m on c.shipper_id = m.shipper_id
where c.total_qty_ordered = m.min_qty or c.total_qty_ordered = m.max_qty
order by c.shipper_id asc, c.total_qty_ordered asc; 

---- RANK
with total_per_category AS (
select s.shipper_id, s.company_name, p.product_id, p.product_name, sum(od.quantity) AS total_qty_ordered
from shippers s
join orders o on s.shipper_id = o.ship_via
join order_details od on o.order_id = od.order_id
join products p on od.product_id = p.product_id
group by p.product_name, s.shipper_id, s.company_name, p.product_id),

a as (
  select *, rank() over (partition by company_name order by total_qty_ordered desc) as x,
         rank() over (partition by company_name order by total_qty_ordered asc) as y
  from total_per_category
)
select shipper_id, company_name, product_id, product_name, total_qty_ordered
from a
where x = 1 or y = 1
order by shipper_id, total_qty_ordered;

-- 12. Tampilkan hirarki level employee seperti gambar dibawah ini
SET search_path TO hr;
ALTER ROLE postgres SET search_path TO hr;
with recursive hirarki as (
	select
	e.employee_id,
	e.first_name||' '||last_name as full_name,
	e.manager_id,
	d.department_name,
	1 as level
	from employees e join departments d on e.department_id=d.department_id 
	where manager_id is null
	
	union all
	
	select
	p.employee_id,
	p.first_name||' '||last_name as full_name,
	p.manager_id,
	d.department_name,
	h.level + 1
	from employees p
	join departments d on p.department_id = d.department_id
	join hirarki h on p.manager_id=h.employee_id
)
select * from hirarki
order by employee_id
DROP TABLE IF EXISTS oe.order_details;
DROP TABLE IF EXISTS oe.orders;
DROP TABLE IF EXISTS oe.products;
DROP TABLE IF EXISTS oe.shippers;
DROP TABLE IF EXISTS oe.suppliers;
DROP TABLE IF EXISTS oe.categories;

create table fintech.bank(
    bank_code varchar(4),
    bank_name varchar(55),
    PRIMARY KEY(bank_code)
);

CREATE EXTENSION IF NOT EXISTS pgcrypto;
CREATE TABLE person.users(
    user_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_name VARCHAR(15),
    user_email VARCHAR(80) UNIQUE,
    user_password VARCHAR(125),
    user_handphone VARCHAR(25)
);

-- Function hash otomatis
CREATE OR REPLACE FUNCTION encrypt_user_password()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' OR NEW.user_password IS DISTINCT FROM OLD.user_password THEN
        NEW.user_password := crypt(NEW.user_password, gen_salt('bf'));
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger
CREATE TRIGGER trg_encrypt_user_password
BEFORE INSERT OR UPDATE ON person.users
FOR EACH ROW
EXECUTE FUNCTION encrypt_user_password();

INSERT INTO person.users (user_name, user_email, user_password, user_handphone)
VALUES ('Andi', 'andi@example.com', 'abc123', '08123456789');

CREATE TABLE oe.categories (
    category_id smallint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    category_name varchar(15) NOT NULL,
    description text,
    picture varchar (225),
	created_date  timestamp default current_timestamp,
    modified_date timestamp
);


--
-- Name: suppliers; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE oe.suppliers (
    supplier_id smallint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    company_name varchar(40) NOT NULL,
	created_date  timestamp default current_timestamp,
    modified_date timestamp
);

CREATE TABLE oe.shippers (
    shipper_id smallint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    company_name varchar(40) NOT NULL,
    phone varchar(24),
	created_date  timestamp default current_timestamp,
    modified_date timestamp
);

--
-- Name: products; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE oe.products (
    product_id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    product_name VARCHAR(40) NOT NULL,
    supplier_id SMALLINT,
    category_id SMALLINT,
    unit_price REAL,
    units_in_stock SMALLINT,
    picture varchar (225),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_date TIMESTAMP,

    CONSTRAINT fk_products_supplier
        FOREIGN KEY (supplier_id)
        REFERENCES oe.suppliers(supplier_id),
    CONSTRAINT fk_products_category
        FOREIGN KEY (category_id)
        REFERENCES oe.categories(category_id)
);

--
-- Name: orders; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE IF NOT EXISTS oe.orders
(
    order_id smallint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_no date,
    order_date date,
    required_date date,
    shipped_date date,
    freight real,
    ship_name varchar(40),
    total_discount decimal(5,2),
    total_amount decimal(8,2),
    payment_type varchar(15),
    card_no varchar(25),
    transac_no varchar(25),
    transac_date timestamp without time zone,
    ref_no varchar(25),
    location_id integer,
    user_id integer,
    employee_id integer,
    bank_code varchar(4),
	created_date  timestamp default current_timestamp,
    modified_date timestamp,
    CONSTRAINT fk_order_location FOREIGN KEY (location_id)
        REFERENCES hr.locations (location_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_orders_hr_employee FOREIGN KEY (employee_id)
        REFERENCES hr.employees (employee_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_orders_person_user FOREIGN KEY (user_id)
        REFERENCES person.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_orders_fintech_bank FOREIGN KEY (bank_code)
        REFERENCES fintech.bank (bank_code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT orders_payment_type_check CHECK (payment_type::text = ANY (ARRAY['DEBIT'::varchar::text, 'CREDIT'::varchar::text, 'QRIS'::varchar::text, 'TRANSFER'::varchar::text]))
);



--
-- Name: order_details; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE oe.order_details (
    order_id SMALLINT NOT NULL,
    product_id SMALLINT NOT NULL,
    unit_price REAL NOT NULL,
    quantity SMALLINT NOT NULL,
    discount REAL,
    voucher REAL,
    
    CONSTRAINT pk_order_details PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk_orderdetails_order FOREIGN KEY (order_id)
        REFERENCES oe.orders(order_id),
    CONSTRAINT fk_orderdetails_product FOREIGN KEY (product_id)
        REFERENCES oe.products(product_id)
);



CREATE SEQUENCE IF NOT EXISTS oe.carts_cart_id_seq;
CREATE TABLE IF NOT EXISTS oe.carts
(
    cart_id integer NOT NULL DEFAULT nextval('oe.carts_cart_id_seq'::regclass),
    created_on timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    user_id integer,
    CONSTRAINT pk_carts PRIMARY KEY (cart_id),
    CONSTRAINT uq_carts_user UNIQUE (user_id),
    created_date  timestamp default current_timestamp,
    modified_date timestamp,
	CONSTRAINT fk_carts_user FOREIGN KEY (user_id)
        REFERENCES person.users(user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


CREATE TABLE IF NOT EXISTS oe.cart_items
(
    cart_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity smallint,
    unit_price real,
    discount decimal(2,2),
	created_date  timestamp default current_timestamp,
    modified_date timestamp,
    CONSTRAINT pk_cart_items PRIMARY KEY (cart_id, product_id),
    CONSTRAINT fk_cart_cart_items FOREIGN KEY (cart_id)
        REFERENCES oe.carts (cart_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
	CONSTRAINT fk_products_cart_items FOREIGN KEY (product_id)
        REFERENCES oe.products (product_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);



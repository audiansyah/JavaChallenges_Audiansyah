package com.eshopper_backend_final.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshopper_backend_final.model.entity.CartItems;
import com.eshopper_backend_final.model.entity.CartItemsId;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, CartItemsId>{
List<CartItems> findByCarts_CartId(Long cartId);
}

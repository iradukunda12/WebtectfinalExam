package com.ecommerce.finalexam.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartOrderRepo extends JpaRepository<CartOrder, Integer> {
}

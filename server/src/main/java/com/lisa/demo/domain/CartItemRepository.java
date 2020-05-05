package com.lisa.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("select c from CartItem c where c.item = ?1")
    public CartItem findOneByItem(Item item);
}

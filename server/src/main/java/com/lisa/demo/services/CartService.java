package com.lisa.demo.services;

import com.lisa.demo.domain.CartItem;
import com.lisa.demo.domain.CartItemRepository;
import com.lisa.demo.domain.Item;
import com.lisa.demo.domain.ItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Accessors(prefix = "m")
@AllArgsConstructor
public class CartService {
    final CartItemRepository mCartItemRepository;
    final ItemRepository mItemRepository;

    public Optional<CartItem> add(final String jsonString) throws JsonProcessingException {
        System.out.println("*************** " +jsonString);
        Item itemToAdd = new ObjectMapper().readValue(jsonString, Item.class);

        CartItem itemInCart = mCartItemRepository.findOneByItem(itemToAdd);  // should only return 1

        if(null != itemInCart){ // item in shopping cart
            itemInCart.setQuantity(itemInCart.getQuantity() + 1);
        } else {
            itemInCart = new CartItem(itemToAdd, 1);
        }
        itemInCart = mCartItemRepository.save(itemInCart);

        return Optional.of(itemInCart);
    }

    public Optional<List<CartItem>> getItems() {
        List<CartItem> cart = mCartItemRepository.findAll();
        if(cart.size() == 0){
            return Optional.empty();
        } else {
            return Optional.of(cart);
        }
    }

    public void update(final String jsonString) throws JsonProcessingException {
        List<CartItem> cartItems = Arrays.asList(new ObjectMapper().readValue(jsonString, CartItem[].class));
        cartItems.stream().forEach(mCartItemRepository::save);
    }

    public void clear() {
        mCartItemRepository.deleteAll();
    }
}

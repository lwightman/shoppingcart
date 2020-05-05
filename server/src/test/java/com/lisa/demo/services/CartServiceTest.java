package com.lisa.demo.services;

import com.lisa.demo.domain.CartItem;
import com.lisa.demo.domain.CartItemRepository;
import com.lisa.demo.domain.Item;
import com.lisa.demo.domain.ItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class CartServiceTest {
    @Mock
    private CartItemRepository mockCartItemRepository;
    @Mock
    private ItemRepository mockItemRepository;

    private CartService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        service = Mockito.spy(new CartService(mockCartItemRepository, mockItemRepository));
    }

    @Test
    void getItems() {
        //given
        final Item item1 = new Item(1L, "item 1", "description 1");
        final Item item2 = new Item(2L, "item 2", "description 2");
        final CartItem cartItem1 = new CartItem(42L, item1, 12);
        final CartItem cartItem2 = new CartItem(43L, item2, 13);
        final List<CartItem> cartItems = ImmutableList.of(cartItem1, cartItem2);

        when(mockCartItemRepository.findAll()).thenReturn(cartItems);

        //when
        Optional<List<CartItem>> results = service.getItems();

        //then
        assertTrue(results.isPresent());
        final List<CartItem> resultItems = results.get();
        assertEquals(item1, resultItems.get(0).getItem());
        assertEquals(item2, resultItems.get(1).getItem());
        assertEquals(12, resultItems.get(0).getQuantity());
        assertEquals(13, resultItems.get(1).getQuantity());
    }

    @Test
    void getItemsNoItems() {
        //given
        final List<Item> items = new ArrayList<>();
        when(mockItemRepository.findAll()).thenReturn(items);

        //when
        Optional<List<CartItem>> results = service.getItems();

        //then
        assertFalse(results.isPresent());
    }

    @Test
    void add() throws JsonProcessingException {
        //given
        final String json = "{\"id\":5,\"name\":\"Soccer ball\",\"description\":\"Would you like to run for 90 minutes?\"}";
        final Item item = new ObjectMapper().readValue(json, Item.class);
        final CartItem cartItem= new CartItem(123L, item, 1);
        final CartItem updatedCartItem = new CartItem(123L, item, 2);
        when(mockCartItemRepository.findOneByItem(any())).thenReturn(cartItem);
        when(mockCartItemRepository.save(any())).thenReturn(updatedCartItem);

        //when
        Optional<CartItem> results = service.add(json);

        //then
        assertTrue(results.isPresent());
        assertEquals(2, results.get().getQuantity());
    }

    @Test
    void addNewItemToCart() throws JsonProcessingException {
        //given
        final String json = "{\"id\":5,\"name\":\"Soccer ball\",\"description\":\"Would you like to run for 90 minutes?\"}";
        final Item item = new ObjectMapper().readValue(json, Item.class);
        final CartItem cartItem= new CartItem(123L, item, 1);
        final CartItem updatedCartItem = new CartItem(123L, item, 2);
        when(mockCartItemRepository.findOneByItem(any())).thenReturn(null);
        when(mockCartItemRepository.save(any())).thenReturn(cartItem);

        //when
        Optional<CartItem> results = service.add(json);

        //then
        assertTrue(results.isPresent());
        assertEquals(1, results.get().getQuantity());
    }
}

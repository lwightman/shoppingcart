package com.lisa.demo.services;

import com.lisa.demo.domain.Item;
import com.lisa.demo.domain.ItemRepository;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class ItemServiceTest {
    @Mock
    private ItemRepository mockItemRepository;

    private ItemService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        service = spy(new ItemService(mockItemRepository));
    }

    @Test
    void getItems() {
        //given
        final List<Item> items = ImmutableList.of(new Item(1L, "item 1", "description 1"),
                                            new Item(2L, "item 2", "description 2"));
        when(mockItemRepository.findAll()).thenReturn(items);

        //when
        Optional<List<Item>> results = service.getItems();

        //then
        assertTrue(results.isPresent());
        final List<Item> resultItems = results.get();
        assertEquals("item 1", resultItems.get(0).getName());
        assertEquals("item 2", resultItems.get(1).getName());
        assertEquals("description 1", resultItems.get(0).getDescription());
        assertEquals("description 2", resultItems.get(1).getDescription());
    }

    @Test
    void getItemsNoItems() {
        //given
        final List<Item> items = new ArrayList<>();
        when(mockItemRepository.findAll()).thenReturn(items);

        //when
        Optional<List<Item>> results = service.getItems();

        //then
        assertFalse(results.isPresent());
    }
}

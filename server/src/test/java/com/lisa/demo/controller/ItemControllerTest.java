package com.lisa.demo.controller;

import com.lisa.demo.domain.Item;
import com.lisa.demo.services.ItemService;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class ItemControllerTest {

    @Mock
    private ItemService mockItemService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        ItemController controller = spy(new ItemController(mockItemService));
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    void getItems() throws Exception {
        //given
        final Item item1 = new Item(1L, "item 1", "description 1");
        final Item item2 = new Item(2L, "item 2", "description 2");

        final Optional<List<Item>> items = Optional.of(ImmutableList.of(item1, item2));
        when(mockItemService.getItems()).thenReturn(items);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/item/list"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name").value("item 1"))
               .andExpect(jsonPath("$[0].description").value("description 1"))
               .andExpect(jsonPath("$[1].name").value("item 2"))
               .andExpect(jsonPath("$[1].description").value("description 2"));;

        //then
    }

    @Test
    void getItemsNoItems() throws Exception {
        //given
        when(mockItemService.getItems()).thenReturn(Optional.empty());

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/item/list"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").value("There are no items loaded in the database."));

        //then
    }
}

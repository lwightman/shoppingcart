package com.lisa.demo.controller;

import com.google.common.collect.ImmutableList;
import com.lisa.demo.domain.CartItem;
import com.lisa.demo.domain.Item;
import com.lisa.demo.services.CartService;
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

class CartControllerTest {

    @Mock
    private CartService mockCartService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        CartController controller = spy(new CartController(mockCartService));
        mockMvc = standaloneSetup(controller).build();
    }

//    @Test
//    void add() throws Exception {
//        //given
//        String jsonString = "this is my json string";
//        final Item item = new Item(4L, "this is my item", "this is the description");
//        Optional<CartItem> results = Optional.of(new CartItem(42L, item, 12));
//        when(mockCartService.add(eq(jsonString))).thenReturn(results);
//
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.get("/cart/add")
//                                              .param("jsonString", jsonString))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$.item.name").value("this is my item"))
//               .andExpect(jsonPath("$.quantity").value(12));
//
//        //then
//    }
//
//    @Test
//    void addWithNoResults() throws Exception {
//        //given
//        String jsonString = "this is my json string";
//        when(mockCartService.add(eq(jsonString))).thenReturn(Optional.empty());
//
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.get("/cart/add")
//                                              .param("jsonString", jsonString))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$").value("I've got issues."));
//
//        //then
//    }

    @Test
    void getItems() throws Exception {
        //given
        final Item item1 = new Item(1L, "item 1", "description 1");
        final Item item2 = new Item(2L, "item 2", "description 2");
        final CartItem cartItem1 = new CartItem(42L, item1, 12);
        final CartItem cartItem2 = new CartItem(43L, item2, 13);
        final Optional<List<CartItem>> cartItems = Optional.of(ImmutableList.of(cartItem1, cartItem2));
        when(mockCartService.getItems()).thenReturn(cartItems);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/cart/getItems"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].item.name").value("item 1"))
               .andExpect(jsonPath("$[0].quantity").value(12))
               .andExpect(jsonPath("$[1].item.name").value("item 2"))
               .andExpect(jsonPath("$[1].quantity").value(13));;

        //then
    }

    @Test
    void getItemsEmptyCart() throws Exception {
        //given
        when(mockCartService.getItems()).thenReturn(Optional.empty());


        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/cart/getItems"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").value("Cart is empty."));

        //then
    }

    @Test
    void update() throws Exception {
        //given
        String jsonString = "this is my json string";

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/cart/update")
               .param("jsonString", jsonString))
               .andExpect(status().isOk());
        //then
    }

    @Test
    void clear() throws Exception {
        //given

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/cart/clear"))
               .andExpect(status().isOk());
        //then

    }
}

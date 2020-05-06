package com.lisa.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lisa.demo.domain.CartItem;
import com.lisa.demo.domain.Item;
import com.lisa.demo.services.CartService;
import com.lisa.demo.services.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/item")
@CrossOrigin(origins = {"http://shoppingcart-server:4200"})
public class ItemController {
    private final ItemService mItemService;
    private final CartService mCartService;

    public ItemController(final ItemService itemService, final CartService cartService) {
        mItemService = itemService;
        mCartService = cartService;
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getItems(){
        Optional<List<Item>> results = mItemService.getItems();

        if(results.isPresent()){
            return new ResponseEntity<>(results.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There are no items loaded in the database.", HttpStatus.OK);
        }
    }

    @GetMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add( final String jsonString) throws JsonProcessingException {
        Optional<CartItem> results = mCartService.add(jsonString);

        if(results.isPresent()){
            return new ResponseEntity<>(results.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("I've got issues.", HttpStatus.OK);
        }
    }
}

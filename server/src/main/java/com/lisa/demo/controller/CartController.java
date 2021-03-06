package com.lisa.demo.controller;

import com.lisa.demo.domain.CartItem;
import com.lisa.demo.services.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/cart")
@CrossOrigin(origins = {"http://shoppingcart-server:4200"})
public class CartController {
    private final CartService mCartService;

    public CartController(final CartService cartService){
        mCartService = cartService;
    }

    @GetMapping(value = "/getItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getItems() {
        Optional<List<CartItem>> results = mCartService.getItems();

        if(results.isPresent()){
            return new ResponseEntity<>(results.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cart is empty.", HttpStatus.OK);
        }
    }

    @GetMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestParam(required = true) final String jsonString) throws JsonProcessingException {
        mCartService.update(jsonString);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/clear", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> clear() {
        mCartService.clear();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

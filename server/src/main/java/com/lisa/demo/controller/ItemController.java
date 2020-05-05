package com.lisa.demo.controller;

import com.lisa.demo.domain.Item;
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
@CrossOrigin(origins = {"http://shoppingcart-server:4200", "http://shoppingcart-server:4201"})
public class ItemController {
    private final ItemService mItemService;

    public ItemController(final ItemService itemService) {
        mItemService = itemService;
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getItems(){
        log.debug("getItems()");
        Optional<List<Item>> results = mItemService.getItems();

        if(results.isPresent()){
            return new ResponseEntity<>(results.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There are no items loaded in the database.", HttpStatus.OK);
        }
    }
}

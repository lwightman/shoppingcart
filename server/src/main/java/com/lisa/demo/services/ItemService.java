package com.lisa.demo.services;

import com.lisa.demo.domain.Item;
import com.lisa.demo.domain.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Accessors(prefix = "m")
@AllArgsConstructor
public class ItemService {
    private final ItemRepository mItemRepository;

    public Optional<List<Item>> getItems() {
        List<Item> items = mItemRepository.findAll();

        if(items.size() > 0){
            return Optional.of(items);
        } else {
            return Optional.empty();
        }
    }
}

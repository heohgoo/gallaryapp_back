package com.example.backend.controller;

import com.example.backend.dto.ItemDto;
import com.example.backend.entity.Item;
import com.example.backend.repository.ItemRepository;
import com.example.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    JwtService jwtService;

    @GetMapping("/api/items")
    public List<Item> getItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }

    @PostMapping("/api/sell")
    public ResponseEntity sellItem(@RequestBody ItemDto dto,
                                   @CookieValue(value="token", required = false) String token) {
        if(!jwtService.isValid(token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        Item newitem = new Item();
        newitem.setName(dto.getName());
        newitem.setImgPath(dto.getImgPath());
        newitem.setPrice(Integer.parseInt(dto.getPrice()));
        newitem.setDiscountPer(Integer.parseInt(dto.getDiscountPer()));

        itemRepository.save(newitem);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

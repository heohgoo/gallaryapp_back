package com.example.backend.controller;

import com.example.backend.dto.OrderDto;
import com.example.backend.entity.Cart;
import com.example.backend.entity.Item;
import com.example.backend.entity.Order;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.ItemRepository;
import com.example.backend.repository.OrderRepository;
import com.example.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    JwtService jwtService;


    @PostMapping("/api/orders")
    public ResponseEntity pushOrder(
            @RequestBody OrderDto dto,
            @CookieValue(value = "token", required = false) String token){

        if(!jwtService.isValid(token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        Order newOrder = new Order();
        newOrder.setMemberId(jwtService.getId(token));
        newOrder.setName(dto.getName());
        newOrder.setAddress(dto.getAddress());
        newOrder.setCardNumber(dto.getCardNumber());
        newOrder.setItems(dto.getItems());

        orderRepository.save(newOrder);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

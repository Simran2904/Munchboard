package com.munchboard.app.controller;

import com.munchboard.app.model.Order;
import com.munchboard.app.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createOrder(
            @RequestBody Order order
    ) {

        try {

            Long orderId =
                    orderService.createOrder(order);

            Map<String, Object> response =
                    new HashMap<>();

            response.put("success", true);
            response.put("orderId", orderId);
            response.put(
                    "message",
                    "Order placed successfully"
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            e.printStackTrace();

            Map<String, Object> response =
                    new HashMap<>();

            response.put("success", false);
            response.put(
                    "message",
                    "Failed to place order"
            );

            return ResponseEntity
                    .internalServerError()
                    .body(response);
        }
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Order> getOrder(
            @PathVariable Long id
    ) {

        Order order =
                orderService.getOrder(id);

        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order);
    }
}
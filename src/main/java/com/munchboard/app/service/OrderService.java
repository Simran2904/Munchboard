package com.munchboard.app.service;

import com.munchboard.app.model.Order;
import com.munchboard.app.model.OrderItem;
import com.munchboard.app.repository.OrderItemRepository;
import com.munchboard.app.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository
    ) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Long createOrder(Order order) {

        // Save main order first
        Long orderId = orderRepository.save(order);

        // Save each food item
        if (order.getItems() != null) {

            for (OrderItem item : order.getItems()) {

                item.setOrderId(orderId);

                orderItemRepository.save(item);
            }
        }

        return orderId;
    }

    public List<Order> getAllOrders() {

        List<Order> orders = orderRepository.findAll();

        for (Order order : orders) {

            List<OrderItem> items =
                    orderItemRepository.findByOrderId(order.getId());

            order.setItems(items);
        }

        return orders;
    }

    public Order getOrder(Long id) {

        Order order = orderRepository.findById(id);

        if (order != null) {

            List<OrderItem> items =
                    orderItemRepository.findByOrderId(id);

            order.setItems(items);
        }

        return order;
    }

    public void updateStatus(Long id, String status) {

        orderRepository.updateStatus(id, status);
    }
}
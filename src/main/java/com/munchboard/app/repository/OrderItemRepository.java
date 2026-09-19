package com.munchboard.app.repository;

import com.munchboard.app.model.OrderItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(OrderItem item) {

        String sql = """
                INSERT INTO order_items
                (order_id, product_name, quantity, price, subtotal)
                VALUES (?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                item.getOrderId(),
                item.getProductName(),
                item.getQuantity(),
                item.getPrice(),
                item.getSubtotal()
        );
    }

    public List<OrderItem> findByOrderId(Long orderId) {

        String sql = """
                SELECT id, order_id, product_name,
                       quantity, price, subtotal
                FROM order_items
                WHERE order_id = ?
                """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {

                    OrderItem item = new OrderItem();

                    item.setId(rs.getLong("id"));
                    item.setOrderId(rs.getLong("order_id"));
                    item.setProductName(rs.getString("product_name"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setPrice(rs.getDouble("price"));
                    item.setSubtotal(rs.getDouble("subtotal"));

                    return item;
                },
                orderId
        );
    }
}
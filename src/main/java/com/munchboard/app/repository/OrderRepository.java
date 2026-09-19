package com.munchboard.app.repository;

import com.munchboard.app.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(Order order) {

        String sql = """
                INSERT INTO orders
                (
                    customer_name,
                    phone,
                    email,
                    address,
                    payment_method,
                    total_amount,
                    order_status
                )
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement ps =
                    connection.prepareStatement(
                            sql,
                            Statement.RETURN_GENERATED_KEYS
                    );

            ps.setString(1, order.getCustomerName());
            ps.setString(2, order.getPhone());
            ps.setString(3, order.getEmail());
            ps.setString(4, order.getAddress());
            ps.setString(5, order.getPaymentMethod());
            ps.setDouble(6, order.getTotalAmount());
            ps.setString(7, order.getOrderStatus());

            return ps;

        }, keyHolder);

        return keyHolder.getKey().longValue();
    }


    public List<Order> findAll() {

        String sql = """
                SELECT
                    id,
                    customer_name,
                    phone,
                    email,
                    address,
                    payment_method,
                    total_amount,
                    order_status,
                    order_date
                FROM orders
                ORDER BY order_date DESC
                """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {

                    Order order = new Order();

                    order.setId(rs.getLong("id"));
                    order.setCustomerName(
                            rs.getString("customer_name")
                    );
                    order.setPhone(
                            rs.getString("phone")
                    );
                    order.setEmail(
                            rs.getString("email")
                    );
                    order.setAddress(
                            rs.getString("address")
                    );
                    order.setPaymentMethod(
                            rs.getString("payment_method")
                    );
                    order.setTotalAmount(
                            rs.getDouble("total_amount")
                    );
                    order.setOrderStatus(
                            rs.getString("order_status")
                    );
                    order.setOrderDate(
                            rs.getTimestamp("order_date")
                                    .toLocalDateTime()
                    );

                    return order;
                }
        );
    }


    public Order findById(Long id) {

        String sql = """
                SELECT
                    id,
                    customer_name,
                    phone,
                    email,
                    address,
                    payment_method,
                    total_amount,
                    order_status,
                    order_date
                FROM orders
                WHERE id = ?
                """;

        List<Order> orders = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {

                    Order order = new Order();

                    order.setId(rs.getLong("id"));
                    order.setCustomerName(
                            rs.getString("customer_name")
                    );
                    order.setPhone(
                            rs.getString("phone")
                    );
                    order.setEmail(
                            rs.getString("email")
                    );
                    order.setAddress(
                            rs.getString("address")
                    );
                    order.setPaymentMethod(
                            rs.getString("payment_method")
                    );
                    order.setTotalAmount(
                            rs.getDouble("total_amount")
                    );
                    order.setOrderStatus(
                            rs.getString("order_status")
                    );
                    order.setOrderDate(
                            rs.getTimestamp("order_date")
                                    .toLocalDateTime()
                    );

                    return order;
                },
                id
        );

        if (orders.isEmpty()) {
            return null;
        }

        return orders.get(0);
    }


    public void updateStatus(Long id, String status) {

        String sql = """
                UPDATE orders
                SET order_status = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(
                sql,
                status,
                id
        );
    }
}
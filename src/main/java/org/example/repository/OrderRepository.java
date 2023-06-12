package org.example.repository;

import org.example.model.Order;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends Repository {
    private static final String SELECT_ORDER_BY_ID_QUERY = "select * from shop_order where id = %s;";
    private static final String SELECT_ALL_ORDERS_BY_USER_ID_QUERY = "select * from shop_order where user_id = %s;";
    private static final String CREATE_ORDER = "insert into shop_order" +
            "(id, user_id, total) values(nextval('order_seq'), %s, %s);";

    public Order findOrderById(int id) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement(String.format(SELECT_ORDER_BY_ID_QUERY, id));
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }

            int orderId = resultSet.getInt(1);
            int userId = resultSet.getInt(2);
            float total = resultSet.getInt(3);


            return new Order(orderId, userId, total);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Order> findAllOrdersByUserId(int userId) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement(String.format(SELECT_ALL_ORDERS_BY_USER_ID_QUERY, userId));
            ResultSet resultSet = statement.executeQuery();

            List<Order> orderList = new ArrayList<>();
            while (resultSet.next()) {
                int oderId = resultSet.getInt(1);
                int userID = resultSet.getInt(2);
                float total = resultSet.getFloat(3);


                Order order = new Order(oderId, userID, total);
                orderList.add(order);

            }
            return orderList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int createOrder(int userId, double total) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement(String.format(CREATE_ORDER, userId, total), Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

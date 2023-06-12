package org.example.repository;

import org.example.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends Repository{
    private static final String SELECT_PRODUCT_BY_ID_QUERY = "select * from product where id = %s;";
    private static final String SELECT_ALL_PRODUCTS_BY_ORDER_ID = "select * from product where id in " +
            "(select product_id from product_order where order_id = %s);";
    private static final String INSERT_PRODUCT_TO_ORDER = "insert " +
            "into product_order(id, order_id, product_id) " +
            "values (nextval('product_order_seq'), %s, %s);";

    private static final String SELECT_ALL_PRODUCTS = "select * from product;";



    public Product findProductById(int id) {
        try(Connection conn = connect()){
            PreparedStatement statement = conn.prepareStatement(String.format(SELECT_PRODUCT_BY_ID_QUERY,id));
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) {
                return null;
            }

            int productId = resultSet.getInt(1);
            String nameProduct = resultSet.getString(2);
            String descriptionproduct = resultSet.getString(3);
            float price = resultSet.getInt(4);


            return new Product(productId,nameProduct,descriptionproduct,price);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Product> getAllProductsByOrderId(int orderId) {
        try(Connection conn = connect()){
            PreparedStatement statement = conn.prepareStatement(String.format(SELECT_ALL_PRODUCTS_BY_ORDER_ID,orderId));
            ResultSet resultSet = statement.executeQuery();

            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                int productId = resultSet.getInt(1);
                String nameProduct = resultSet.getString(2);
                String descriptionproduct = resultSet.getString(3);
                float price = resultSet.getInt(4);

                Product product = new Product(productId,nameProduct,descriptionproduct,price);
                productList.add(product);

            }
            return productList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void addProductsToOrder(List<Product> productList, int orderId) {
        try(Connection conn = connect()){
            for (Product p: productList) {
                PreparedStatement statement = conn.prepareStatement(String.format(INSERT_PRODUCT_TO_ORDER, orderId, p.getId()));
                statement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Product> getAllProducts() {
        try(Connection conn = connect()){
            PreparedStatement statement = conn.prepareStatement(String.format(SELECT_ALL_PRODUCTS));
            ResultSet resultSet = statement.executeQuery();

            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                int productId = resultSet.getInt(1);
                String nameProduct = resultSet.getString(2);
                String descriptionproduct = resultSet.getString(3);
                float price = resultSet.getInt(4);

                Product product = new Product(productId,nameProduct,descriptionproduct,price);
                productList.add(product);

            }
            return productList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

package org.example.view;

import org.example.model.Product;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ViewMyCart extends View{

    static List<Product> productList = new ArrayList<>();


    private int userId;

    private ProductRepository productRepository;

    private OrderRepository orderRepository;

    public ViewMyCart(int userId) {
        this.userId = userId;
        productRepository = new ProductRepository();
        orderRepository = new OrderRepository();
    }

    @Override
    void show() {
        System.out.println("My cart");
        productList.stream().forEach(p -> System.out.println(p));
        System.out.println("0. Back to my account");
        System.out.println("1. Place order");

        int i = getUserInputInt();
        if(i == 0) {
            MyAccount myAccount = new MyAccount(userId);
            myAccount.show();
            myAccount.goToSelectedView();
        } else {
            Double d = productList.stream().mapToDouble(p -> p.getPrice()).sum();
            int orderId = orderRepository.createOrder(userId,d);
            productRepository.addProductsToOrder(productList,orderId);
            System.out.println("Order is placed!");
            productList = new ArrayList<>();
            MyAccount myAccount = new MyAccount(userId);
            myAccount.show();
            myAccount.goToSelectedView();

        }
    }
}

package org.example.view;

import org.example.model.Order;
import org.example.model.Product;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;

import java.util.List;

public class OrderDetails extends View{
    private int orderId;

    private ProductRepository productRepository;

    private MyAccount myAccount;

    public OrderDetails(int orderId, int userId) {
        this.orderId = orderId;
        productRepository = new ProductRepository();
        myAccount = new MyAccount(orderId);
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    void show() {
        System.out.println("Order details.");
        System.out.println("0. Back to my account");
        showOrderDetails();

        int i = getUserInputInt();
        if(i == 0) {
            myAccount.show();
            myAccount.goToSelectedView();
        }

    }

    public void showOrderDetails() {
            List<Product> productList = productRepository.getAllProductsByOrderId(orderId);
            productList.stream().forEach(p -> System.out.println(p.getNameProduct() +
                    p.getDescriptionProduct() + p.getPrice()));
    }
}

package org.example.view;

import org.example.model.Order;
import org.example.repository.OrderRepository;

import java.util.List;

public class OrdersHistory extends View{
    private int userId;
    private OrderRepository orderRepository;

    public OrdersHistory(int userId) {
        this.userId = userId;
        orderRepository = new OrderRepository();
    }



    void show() {
        System.out.println("Orders history");
        System.out.println("0. Back to my account");
        showAllOrders();


        int i = getUserInputInt();
        if(i != 0) {
            OrderDetails orderDetails = new OrderDetails(i);
            orderDetails.show();
        } else {
            MyAccount myAccount = new MyAccount(userId);
            myAccount.show();
            myAccount.goToSelectedView();
        }
    }


    public void showAllOrders() {
        List<Order> orderList = orderRepository.findAllOrdersByUserId(userId);
      orderList.stream().forEach(o -> System.out.println(o.getId() + ". " + o.getTotal() + " lei"));
    }


}

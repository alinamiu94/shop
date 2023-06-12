package org.example.view;

public class MyAccount extends View{
    private OrdersHistory ordersHistory;
    private ViewAllProducts viewAllProducts;
    private ViewMyCart viewMyCart;
    MyAccount(int userId) {
        ordersHistory = new OrdersHistory(userId);
        viewAllProducts = new ViewAllProducts(userId);
        viewMyCart = new ViewMyCart(userId);
    }

    public void show() {
        System.out.println("My account");
        System.out.println("1. View orders history");
        System.out.println("2. View all products");
        System.out.println("3. View my cart");
        System.out.println("4. Go to home view");
    }
    public void goToSelectedView() {
        int i = getUserInputInt();
        if(i == 1) {
            ordersHistory.show();
        } else if (i == 2) {
           viewAllProducts.show();
        } else if(i == 3) {
           viewMyCart.show();
        } else {
            HomeView homeView = new HomeView();
            homeView.show();
            homeView.goToSelectedView();
        }
    }

}

package org.example.view;

import org.example.model.Product;
import org.example.repository.ProductRepository;

public class ViewProductDetails extends View{
    private int productId;
    private int userId;
    private ProductRepository productRepository;
    private MyAccount myAccount;


    public ViewProductDetails(int productId, int userId) {
        this.productId = productId;
        this.userId = userId;
        productRepository = new ProductRepository();
        myAccount = new MyAccount(userId);
    }

    public int getProductId() {
        return productId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    void show() {
        System.out.println("View product details");
        showProductDetails();
        System.out.println("0. Back to my account");
        System.out.println("1. Add to my cart");

        int i = getUserInputInt();

        if(i == 0) {
            myAccount.show();
            myAccount.goToSelectedView();
        } else {
            Product product = productRepository.findProductById(productId);
            ViewMyCart.productList.add(product);
            myAccount.show();
            myAccount.goToSelectedView();
        }


    }

    private void showProductDetails() {
       Product product = productRepository.findProductById(productId);
        System.out.println(product);
    }
}

package org.example.view;

import org.example.model.Product;
import org.example.repository.ProductRepository;

import java.util.List;

public class ViewAllProducts extends View{

    private ProductRepository productRepository;
    private int userId;

    public ViewAllProducts(int userId) {
        this.userId = userId;
        productRepository = new ProductRepository();

    }

    @Override
    void show() {
        System.out.println("View all shop products");
        System.out.println("0. Back to my account");
        showAllProducts();
        System.out.println("Select product id to view details: ");

        int i = getUserInputInt();
        if(i == 0) {
            MyAccount myAccount = new MyAccount(userId);
            myAccount.show();
            myAccount.goToSelectedView();
        } else  {
            ViewProductDetails viewProductDetails = new ViewProductDetails(i,userId);
            viewProductDetails.show();
        }

    }
    public void showAllProducts() {
        List<Product> productList = productRepository.getAllProducts();
        productList.stream().forEach(p -> System.out.println(p.getId() + ". " + p.getNameProduct()));

    }

}

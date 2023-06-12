# Shop App

## Tech stack: Java & PostgreSql

## 1. Database design
![db-diagram.png](photos%2Fdb-diagram.png)

## DB Scripts:


```
create Table app_user(
	ID INT PRIMARY KEY,
	NAME VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(100) NOT NULL,
	CREATED_AT TIMESTAMP
);
```
```
create Table product(
	ID INT Primary key,
	NAME VARCHAR(50) NOT NULL,
	DESCRIPTION VARCHAR(2000),
	PRICE FLOAT(2)
);
```
```
create Table shop_order(
	ID INT Primary key,
	USER_ID INT,
	TOTAL FLOAT(2),
	FOREIGN KEY(USER_ID) REFERENCES APP_USER(ID)
);
```
```
create Table Product_Order(
	ID INT Primary key,
	ORDER_ID INT,
	PRODUCT_ID INT,
	FOREIGN KEY(ORDER_ID) REFERENCES SHOP_ORDER(ID),
	FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT(ID)
);
```

## Views:

### 1. Home View
![home-view.png](photos%2Fhome-view.png)

User can Log in, Sign up or exit from app

### 2. Sign up View
![sign-up.png](photos%2Fsign-up.png)

In the sign up view, user can sign up. The app will ask for a name and password, and then the user will be saved in the "USER" table. The password will be stored encrypted with the BCrypt algorithm.

### 3. Log in View
![log-in.png](photos%2Flog-in.png)
In the log in view, users can log in into the app. The password entered by the user will be checked if matched the one in the database using BCrypt.checkpw(plainPassword, dbPassword);

If users doesn't exist it will be shown "User doesn't exist!". If the password is not correct it will be shown "Password is not correct".

### 4. My account
![my-account.png](photos%2Fmy-account.png)

The "My Account" view is where users can access and manage their account-related features and information.

### 5. View all products
![view-all-products.png](photos%2Fview-all-products.png)

Users can explore the full range of available products. Detailed information such as descriptions and prices.

### 6. View product details
![view-products-details.png](photos%2Fview-products-details.png)

Users can see product details and can choose to go back to "My account" or to add the product to cart.

### 7. View my cart
![view-my-cart.png](photos%2Fview-my-cart.png)

Users can access the contents of their shopping cart, and they can place an order.

### 8. View orders history
![view-orders-history.png](photos%2Fview-orders-history.png)

Users can review their previous orders.

### 9. Order details
![view-order-details.png](photos%2Fview-order-details.png)

The "Order Details" view gives you all the important information about a specific order. 

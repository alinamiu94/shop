create Table app_user(
	ID INT PRIMARY KEY,
	NAME VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(100) NOT NULL,
	CREATED_AT TIMESTAMP
);

create Table product(
	ID INT Primary key,
	NAME VARCHAR(50) NOT NULL,
	DESCRIPTION VARCHAR(2000),
	PRICE FLOAT(2)
);

create Table shop_order(
	ID INT Primary key,
	USER_ID INT,
	TOTAL FLOAT(2),
	FOREIGN KEY(USER_ID) REFERENCES APP_USER(ID)
);

create Table Product_Order(
	ID INT Primary key,
	ORDER_ID INT,
	PRODUCT_ID INT,
	FOREIGN KEY(ORDER_ID) REFERENCES SHOP_ORDER(ID),
	FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT(ID)
);

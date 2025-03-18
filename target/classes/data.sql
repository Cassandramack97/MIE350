-- PRODUCTS
-- 1. A normal product (not running low, not expiring soon)
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Tomatoes', 50, 2.99, '2025-03-15');

-- 2. Out of Stock (quantity = 0)
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Lettuce', 0, 1.99, '2025-02-20');

-- 3. Running Low & Expiring Soon (expiry date close to today, quantity between 1 and 10)
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Milk', 5, 1.50, '2025-02-18');

-- 4. Running Low & Expiring Soon
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Eggs', 8, 3.00, '2025-02-19');

-- 5. Running Low (quantity exactly 10, not expiring soon)
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Cheese', 10, 4.50, '2025-03-05');

-- 6. Running Low & Expiring Very Soon (e.g., tomorrow)
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Bread', 2, 1.00, '2025-02-16');

-- 7. Plenty in stock (not running low, far expiry)
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Chicken', 20, 7.00, '2025-04-01');

-- 8. Out of Stock (negative quantity)
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Beef', -5, 10.00, '2025-03-01');

-- 9. Bulk item (non-perishable or far future expiry)
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Rice', 100, 2.00, '2030-01-01');

-- 10. Running Low & Expiring Soon
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Pasta', 7, 1.50, '2025-02-21');

--Suppliers
INSERT INTO SUPPLIERS (NAME, CONTACT_INFO, MINIMUM_ORDER, DELIVERY_TIME, DESCRIPTION)
VALUES ('Butcher 1', 'butcher1@gmail.com, 937-855-0524', 10, 3, 'butcher specialized in lamb meat');

--Suppliers products
INSERT INTO SUPPLIERS_PRODUCTS (SUPPLIER_ID, PRODUCT_ID)
VALUES (1, 1);

--SUPPLIER_ORDER
INSERT INTO SUPPLIER_ORDER (SUPPLIER_ID, PRODUCT_ID, QUANTITY, STATUS, DATE_PLACED, DATE_DELIVERED)
VALUES (1, 1, 20, 'Pending', '2025-02-14', '2025-02-17');

-- DISHES Table
CREATE TABLE DISH (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- DISH_PRODUCTS (Many-to-Many with Quantity)
CREATE TABLE DISH_PRODUCTS (
    id SERIAL PRIMARY KEY,
    dish_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (dish_id) REFERENCES DISH(id),
    FOREIGN KEY (product_id) REFERENCES PRODUCTS(id)
);

-- Insert a dish (e.g., a Salad containing Tomatoes and Lettuce)
INSERT INTO DISH (id, name) VALUES (1, 'Salad');

-- Link the dish to its products with quantities
INSERT INTO DISH_PRODUCTS (dish_id, product_id, quantity) VALUES (1, 1, 2); -- 2 Tomatoes
INSERT INTO DISH_PRODUCTS (dish_id, product_id, quantity) VALUES (1, 2, 1); -- 1 Lettuce

-- Insert a Customer Order for this dish
INSERT INTO CustomerOrder (id, dish_id, DATE) 
VALUES (1, 1, '2025-02-14');
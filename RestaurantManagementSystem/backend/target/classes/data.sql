--INGREDIENTS
INSERT INTO INGREDIENTS (INGREDIENT_CODE, NAME)
VALUES ('Tomatoe', 'Tomatoe');

INSERT INTO INGREDIENTS (INGREDIENT_CODE, NAME)
VALUES ('Lettuce', 'Lettuce');

INSERT INTO INGREDIENTS (INGREDIENT_CODE, NAME)
VALUES ('Milk', 'Milk');

INSERT INTO INGREDIENTS (INGREDIENT_CODE, NAME)
VALUES ('Eggs', 'Eggs');

INSERT INTO INGREDIENTS (INGREDIENT_CODE, NAME)
VALUES ('Cheese', 'Cheese');

INSERT INTO INGREDIENTS (INGREDIENT_CODE, NAME)
VALUES ('Bread', 'Bread');

INSERT INTO INGREDIENTS (INGREDIENT_CODE, NAME)
VALUES ('Chicken', 'Chicken');

INSERT INTO INGREDIENTS (INGREDIENT_CODE, NAME)
VALUES ('Beef', 'Beef');

INSERT INTO INGREDIENTS (INGREDIENT_CODE, NAME)
VALUES ('Rice', 'Rice');

INSERT INTO INGREDIENTS (INGREDIENT_CODE, NAME)
VALUES ('Pasta', 'Pasta');

-- Menu Items
INSERT INTO menu_items (id, name, description, price) VALUES
(1111, 'Grilled Chicken Salad', 'Healthy grilled chicken with fresh greens and dressing', 12.99),
(2222, 'Classic Pasta', 'Pasta with meat and tomatoe sauce', 9.50),
(3333, 'Fried Egg', 'Fried egg with a glass of milk', 8.00);

-- Menu Item Ingredients (Join Table)
INSERT INTO menu_item_ingredients (menu_item_id, ingredient_code, quantity, unit) VALUES
-- Grilled Chicken Salad
(1111, 'Chicken', 200, 'grams'),
(1111, 'Tomatoe', 150, 'grams'),
(1111, 'Lettuce', 75, 'grams'),
(1111, 'Eggs', 2, 'whole'),
-- Meat Pasta
(2222, 'Tomatoe', 2, 'whole'),
(2222, 'Pasta', 300, 'grams'),
(2222, 'Beef', 200, 'grams'),
-- Fried Egg
(3333, 'Eggs', 1, 'whole'),
(3333, 'Milk', 1, 'whole');

-- PRODUCTS
-- 1. A normal product (not running low, not expiring soon)
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE, INGREDIENT_CODE)
VALUES ('Tomatoes', 50, 2.99, '2025-03-15', 'Tomatoe');

-- 2. Out of Stock (quantity = 0)
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE, INGREDIENT_CODE)
VALUES ('Lettuce', 0, 1.99, '2025-02-20', 'Lettuce');

-- 3. Running Low & Expiring Soon (expiry date close to today, quantity between 1 and 10)
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE, INGREDIENT_CODE)
VALUES ('Milk', 5, 1.50, '2025-02-18', 'Milk');

-- 4. Running Low & Expiring Soon
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE, INGREDIENT_CODE)
VALUES ('Eggs', 8, 3.00, '2025-02-19', 'Eggs');

-- 5. Running Low (quantity exactly 10, not expiring soon)
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE, INGREDIENT_CODE)
VALUES ('Cheese', 10, 4.50, '2025-03-05', 'Cheese');

-- 6. Running Low & Expiring Very Soon (e.g., tomorrow)
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE, INGREDIENT_CODE)
VALUES ('Bread', 2, 1.00, '2025-02-16', 'Bread');

-- 7. Plenty in stock (not running low, far expiry)
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE, INGREDIENT_CODE)
VALUES ('Chicken', 20, 7.00, '2025-04-01', 'Chicken');

-- 8. Out of Stock (negative quantity)
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE, INGREDIENT_CODE)
VALUES ('Beef', -5, 10.00, '2025-03-01', 'Beef');

-- 9. Bulk item (non-perishable or far future expiry)
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE, INGREDIENT_CODE)
VALUES ('Rice', 100, 2.00, '2030-01-01', 'Rice');

-- 10. Running Low & Expiring Soon
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE, INGREDIENT_CODE)
VALUES ('Pasta', 7, 1.50, '2025-02-21', 'Pasta');

--Suppliers
INSERT INTO SUPPLIERS (NAME, CONTACT_INFO, MINIMUM_ORDER, DELIVERY_TIME, DESCRIPTION)
VALUES ('Butcher 1', 'butcher1@gmail.com, 937-855-0524', 10, 3, 'butcher specialized in lamb meat');

--Suppliers products
INSERT INTO SUPPLIERS_INGREDIENTS(SUPPLIER_ID, INGREDIENT_CODE)
VALUES (1, 'Beef');

--SUPPLIER_ORDER
INSERT INTO SUPPLIER_ORDER (SUPPLIER_ID, PRODUCT_ID, QUANTITY, STATUS, DATE_PLACED, DATE_DELIVERED)
VALUES (1, 1, 20, 'Pending', '2025-02-14', '2025-02-17');

--CUSTOMER_ORDER
INSERT INTO CUSTOMER_ORDER(MENU_ITEM_ID, DATE, QUANTITY)
VALUES(1111, '2025-02-17', 3)



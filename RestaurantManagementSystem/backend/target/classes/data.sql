-- INGREDIENTS
INSERT INTO INGREDIENTS (INGREDIENT_CODE, NAME)
VALUES
(1001, 'Tomatoe'),
(1002, 'Lettuce'),
(1003, 'Milk'),
(1004, 'Eggs'),
(1005, 'Cheese'),
(1006, 'Bread'),
(1007, 'Chicken'),
(1008, 'Beef'),
(1009, 'Rice'),
(1010, 'Pasta'),
(1011, 'Onion'),
(1012, 'Garlic'),
(1013, 'Pepper'),
(1014, 'Salt'),
(1015, 'Butter'),
(1016, 'Olive Oil'),
(1017, 'Carrot'),
(1018, 'Spinach'),
(1019, 'Potato'),
(1020, 'Cucumber'),
(1021, 'Fish'),
(1022, 'Pork'),
(1023, 'Flour'),
(1024, 'Sugar'),
(1025, 'Cream');

-- Menu Items
INSERT INTO menu_items (id, name, description, price) VALUES
(1111, 'Grilled Chicken Salad', 'Healthy grilled chicken with fresh greens and dressing', 12.99),
(2222, 'Classic Pasta', 'Pasta with meat and tomatoe sauce', 9.50),
(3333, 'Fried Egg', 'Fried egg with a glass of milk', 8.00),
(4444, 'Vegetable Stir Fry', 'Mixed vegetables with rice and olive oil', 10.75),
(5555, 'Cheese Omelet', 'Fluffy omelet with melted cheese', 9.25),
(6666, 'Beef Burger', 'Classic beef burger with lettuce and cheese', 11.50),
(7777, 'Fish and Chips', 'Crispy fish with potato fries', 13.99),
(8888, 'Chicken Pasta', 'Creamy chicken pasta with garlic and cheese', 12.50),
(9999, 'Veggie Salad', 'Fresh mixed vegetable salad with olive oil dressing', 8.75);

-- Menu Item Ingredients
INSERT INTO menu_item_ingredients (menu_item_id, ingredient_code, quantity, unit) VALUES
(1111, 1007, 0.2, 'whole'),
(1111, 1001, 1.5, 'whole'),
(1111, 1002, 0.75, 'whole'),
(1111, 1004, 2, 'whole'),
(2222, 1001, 2, 'whole'),
(2222, 1010, 1.5, 'whole'),
(2222, 1008, 1, 'whole'),
(3333, 1004, 1, 'whole'),
(3333, 1003, 1, 'whole'),
(4444, 1017, 5, 'whole'),
(4444, 1011, 1, 'whole'),
(4444, 1013, 0.75, 'whole'),
(4444, 1009, 0.250, 'whole'),
(4444, 1016, 0.02, 'whole'),
(5555, 1004, 2, 'whole'),
(5555, 1005, 1, 'whole'),
(5555, 1015, 10, 'whole'),
(6666, 1008, 0.5, 'whole'),
(6666, 1006, 1, 'whole'),
(6666, 1002, 3, 'whole'),
(6666, 1005, 2, 'whole'),
(7777, 1021, 3, 'whole'),
(7777, 1019, 2, 'whole'),
(7777, 1023, 0.5, 'whole'),
(8888, 1007, 0.8, 'whole'),
(8888, 1010, 1.5, 'whole'),
(8888, 1005, 3, 'whole'),
(8888, 1012, 2, 'whole'),
(8888, 1025, 1.5, 'whole'),
(9999, 1002, 1, 'whole'),
(9999, 1020, 1, 'whole'),
(9999, 1018, 2, 'whole'),
(9999, 1016, 1, 'whole'),
(9999, 1014, 0.02, 'whole');

-- PRODUCTS
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE, INGREDIENT_CODE) VALUES
('Tomatoes', 45, 2.99, '2025-04-15', 1001),
('Tomatoes', 30, 2.79, '2025-04-28', 1001),
('Tomatoes', 22, 2.59, '2025-04-20', 1001),
('Lettuce', 25, 1.99, '2025-04-25', 1002),
('Lettuce', 15, 1.89, '2025-04-18', 1002),
('Milk', 20, 3.50, '2025-04-22', 1003),
('Milk', 15, 3.25, '2025-04-18', 1003),
('Milk', 35, 3.75, '2025-05-01', 1003),
('Milk', 10, 3.00, '2025-04-15', 1003),
('Eggs', 40, 3.00, '2025-05-01', 1004),
('Eggs', 25, 2.75, '2025-04-25', 1004),
('Cheese', 25, 4.50, '2025-04-10', 1005),
('Cheese', 18, 4.25, '2025-04-28', 1005),
('Cheese', 35, 4.75, '2025-04-15', 1005),
('Bread', 30, 2.50, '2025-04-25', 1006),
('Bread', 20, 2.25, '2025-04-20', 1006),
('Chicken', 40, 7.00, '2025-04-15', 1007),
('Beef', 35, 10.00, '2025-05-02', 1008),
('Beef', 20, 9.75, '2025-04-25', 1008),
('Beef', 45, 10.25, '2025-04-20', 1008),
('Rice', 60, 2.00, '2025-05-15', 1009),
('Rice', 40, 1.90, '2025-04-28', 1009),
('Pasta', 45, 1.50, '2025-04-30', 1010),
('Pasta', 30, 1.40, '2025-04-25', 1010),
('Pasta', 55, 1.60, '2025-05-15', 1010),
('Onion', 35, 2.50, '2025-04-28', 1011),
('Onion', 25, 2.25, '2025-04-22', 1011),
('Garlic', 30, 3.00, '2025-04-15', 1012),
('Garlic', 20, 2.75, '2025-04-25', 1012),
('Garlic', 40, 3.25, '2025-05-10', 1012),
('Pepper', 45, 4.50, '2025-05-15', 1013),
('Pepper', 30, 4.25, '2025-04-21', 1013),
('Salt', 50, 2.00, '2030-01-01', 1014),
('Butter', 35, 4.50, '2025-04-10', 1015),
('Butter', 25, 4.25, '2025-04-28', 1015),
('Butter', 45, 4.75, '2025-05-15', 1015),
('Olive Oil', 40, 5.50, '2025-05-20', 1016),
('Olive Oil', 25, 5.25, '2025-04-25', 1016),
('Carrot', 35, 2.25, '2025-04-28', 1017),
('Carrot', 25, 2.00, '2025-04-22', 1017),
('Carrot', 45, 2.50, '2025-05-05', 1017),
('Spinach', 30, 3.00, '2025-04-25', 1018),
('Spinach', 20, 2.75, '2025-04-20', 1018),
('Cucumber', 35, 2.25, '2025-04-28', 1020),
('Fish', 40, 8.50, '2025-04-15', 1021),
('Fish', 25, 8.25, '2025-04-28', 1021),
('Fish', 50, 8.75, '2025-05-10', 1021),
('Pork', 35, 7.50, '2025-04-22', 1022),
('Pork', 25, 7.25, '2025-04-25', 1022),
('Flour', 45, 2.50, '2025-04-15', 1023),
('Flour', 30, 2.25, '2025-05-01', 1023),
('Flour', 55, 2.75, '2025-05-20', 1023),
('Sugar', 40, 2.00, '2025-04-10', 1024),
('Sugar', 30, 1.90, '2025-04-28', 1024),
('Cream', 35, 3.50, '2025-04-15', 1025),
('Cream', 25, 3.25, '2025-04-28', 1025);

-- SUPPLIERS
INSERT INTO SUPPLIERS (NAME, CONTACT_INFO, MINIMUM_ORDER, DELIVERY_TIME, DESCRIPTION) VALUES
('Butcher Bros', 'john@butcherbros.com, 937-855-0524', 10, 3, 'Specializing in premium meats and protein'),
('Green Harvest Farms', 'sales@greenharvest.com, 614-222-7890', 20, 5, 'Organic vegetables and fresh produce'),
('Dairy Delights', 'info@dairydelights.com, 800-555-1234', 15, 2, 'High-quality dairy products and eggs'),
('Pantry Essentials', 'orders@pantryessentials.com, 555-123-4567', 25, 4, 'Bulk dry goods and staple ingredients'),
('Ocean Fresh Seafood', 'sales@oceanfresh.com, 941-678-9012', 5, 3, 'Fresh fish and seafood distributor'),
('Global Spice Traders', 'contact@globalspices.com, 212-987-6543', 30, 7, 'Specialty spices and cooking ingredients'),
('Farm to Table Organics', 'orders@farmtotable.com, 503-456-7890', 15, 4, 'Organic and locally sourced ingredients');

-- SUPPLIERS INGREDIENTS
INSERT INTO SUPPLIERS_INGREDIENTS (SUPPLIER_ID, INGREDIENT_CODE) VALUES
(1, 1008),
(1, 1007),
(1, 1022),
(2, 1001),
(2, 1002),
(2, 1011),
(2, 1017),
(2, 1020),
(2, 1018),
(2, 1019),
(2, 1013),
(3, 1003),
(3, 1004),
(3, 1005),
(3, 1025),
(3, 1015),
(4, 1009),
(4, 1010),
(4, 1023),
(4, 1024),
(4, 1014),
(5, 1021),
(6, 1012),
(6, 1013),
(6, 1014),
(7, 1016),
(7, 1006);

-- SUPPLIER ORDER
INSERT INTO SUPPLIER_ORDER (SUPPLIER_ID, INGREDIENT_CODE, QUANTITY, STATUS, DATE_PLACED, DATE_DELIVERED, PRODUCT_NAME, price, product_expiry_date) VALUES
(1, 1008, 50, 'Delivered', '2025-02-10', '2025-02-15', 'Beef', 500.0, '2025-05-02'),
(1, 1007, 60, 'Pending', '2025-05-20', '2025-05-25', 'Chicken', 600.0, '2025-04-15'),
(1, 1022, 40, 'In Transit', '2025-05-15', '2025-05-22', 'Pork', 400.0, '2025-04-22'),
(2, 1001, 100, 'Delivered', '2025-02-12', '2025-02-16', 'Tomatoes', 100.0, '2025-04-15'),
(2, 1002, 50, 'Pending', '2025-06-01', '2025-06-06', 'Lettuce', 50.0, '2025-04-18'),
(2, 1017, 75, 'In Transit', '2025-06-05', '2025-06-10', 'Carrot', 75.0, '2025-04-22'),
(3, 1003, 40, 'Delivered', '2025-02-15', '2025-02-19', 'Milk', 40.0, '2025-04-22'),
(3, 1004, 80, 'Pending', '2025-06-10', '2025-06-15', 'Eggs', 80.0, '2025-04-25'),
(3, 1005, 30, 'In Transit', '2025-06-12', '2025-06-17', 'Cheese', 30.0, '2025-04-28'),
(4, 1009, 120, 'Delivered', '2025-02-10', '2025-02-14', 'Rice', 120.0, '2025-04-28'),
(4, 1010, 60, 'Pending', '2025-06-20', '2025-06-25', 'Pasta', 60.0, '2025-04-30'),
(4, 1023, 50, 'In Transit', '2025-06-18', '2025-06-23', 'Flour', 50.0, '2025-05-01'),
(5, 1021, 40, 'Delivered', '2025-02-12', '2025-02-16', 'Fish', 40.0, '2025-04-15'),
(5, 1021, 35, 'Pending', '2025-06-25', '2025-06-30', 'Fish', 35.0, '2025-05-10'),
(6, 1012, 50, 'Delivered', '2025-02-15', '2025-02-19', 'Garlic', 50.0, '2025-04-15'),
(6, 1013, 30, 'Pending', '2025-06-28', '2025-07-03', 'Pepper', 30.0, '2025-04-21'),
(7, 1016, 25, 'Delivered', '2025-02-10', '2025-02-14', 'Olive Oil', 25.0, '2025-05-20'),
(7, 1006, 40, 'Pending', '2025-07-01', '2025-07-06', 'Bread', 40.0, '2025-04-25');

-- CUSTOMER_ORDER
INSERT INTO CUSTOMER_ORDER (MENU_ITEM_ID, DATE, QUANTITY) VALUES
(1111, '2025-03-30', 5),
(1111, '2025-03-04', 3),
(1111, '2025-03-23', 1),
(1111, '2025-03-31', 5),
(1111, '2025-03-22', 1),
(1111, '2025-03-11', 3),
(1111, '2025-03-12', 6),
(1111, '2025-03-14', 6),
(1111, '2025-03-15', 6),
(1111, '2025-03-11', 5),
(2222, '2025-03-07', 6),
(2222, '2025-03-14', 2),
(2222, '2025-03-20', 4),
(2222, '2025-03-20', 5),
(2222, '2025-04-01', 5),
(2222, '2025-03-04', 1),
(2222, '2025-03-04', 2),
(2222, '2025-03-12', 6),
(2222, '2025-03-23', 6),
(2222, '2025-03-30', 6),
(3333, '2025-03-07', 6),
(3333, '2025-03-11', 5),
(3333, '2025-03-20', 2),
(3333, '2025-03-23', 6),
(3333, '2025-03-12', 1),
(3333, '2025-03-19', 3),
(3333, '2025-03-14', 4),
(3333, '2025-03-08', 2),
(3333, '2025-03-10', 4),
(3333, '2025-03-10', 5),
(4444, '2025-03-31', 4),
(4444, '2025-03-30', 2),
(4444, '2025-03-25', 1),
(4444, '2025-03-25', 3),
(4444, '2025-03-11', 4),
(4444, '2025-03-08', 2),
(4444, '2025-03-10', 1),
(4444, '2025-03-15', 3),
(4444, '2025-03-27', 3),
(4444, '2025-03-21', 5),
(5555, '2025-03-05', 2),
(5555, '2025-03-07', 4),
(5555, '2025-03-14', 3),
(5555, '2025-03-15', 3),
(5555, '2025-03-16', 2),
(5555, '2025-03-22', 2),
(5555, '2025-03-28', 4),
(5555, '2025-03-29', 3),
(5555, '2025-03-25', 3),
(5555, '2025-03-19', 2),
(6666, '2025-03-05', 6),
(6666, '2025-03-06', 4),
(6666, '2025-03-10', 2),
(6666, '2025-03-11', 3),
(6666, '2025-03-15', 6),
(6666, '2025-03-18', 4),
(6666, '2025-03-22', 5),
(6666, '2025-03-27', 6),
(6666, '2025-03-29', 3),
(6666, '2025-03-31', 5),
(7777, '2025-03-05', 4),
(7777, '2025-03-08', 3),
(7777, '2025-03-12', 4),
(7777, '2025-03-14', 2),
(7777, '2025-03-17', 5),
(7777, '2025-03-20', 3),
(7777, '2025-03-24', 3),
(7777, '2025-03-28', 4),
(7777, '2025-03-30', 2),
(7777, '2025-04-01', 2),
(8888, '2025-03-04', 5),
(8888, '2025-03-07', 3),
(8888, '2025-03-11', 6),
(8888, '2025-03-15', 3),
(8888, '2025-03-19', 4),
(8888, '2025-03-21', 2),
(8888, '2025-03-27', 5),
(8888, '2025-03-29', 3),
(8888, '2025-03-30', 5),
(8888, '2025-04-01', 4),
(9999, '2025-03-03', 3),
(9999, '2025-03-07', 2),
(9999, '2025-03-12', 3),
(9999, '2025-03-15', 2),
(9999, '2025-03-18', 1),
(9999, '2025-03-22', 2),
(9999, '2025-03-25', 3),
(9999, '2025-03-28', 4),
(9999, '2025-03-30', 1),
(9999, '2025-04-01', 2);

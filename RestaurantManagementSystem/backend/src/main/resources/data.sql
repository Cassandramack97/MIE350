-- INGREDIENTS
INSERT INTO INGREDIENTS (INGREDIENT_CODE, NAME)
VALUES
(1, 'Tomatoe'),
(2, 'Lettuce'),
(3, 'Milk'),
(4, 'Eggs'),
(5, 'Cheese'),
(6, 'Bread'),
(7, 'Chicken'),
(8, 'Beef'),
(9, 'Rice'),
(10, 'Pasta'),
(11, 'Onion'),
(12, 'Garlic'),
(13, 'Pepper'),
(14, 'Salt'),
(15, 'Butter'),
(16, 'Olive Oil'),
(17, 'Carrot'),
(18, 'Spinach'),
(19, 'Potato'),
(20, 'Cucumber'),
(21, 'Fish'),
(22, 'Pork'),
(23, 'Flour'),
(24, 'Sugar'),
(25, 'Cream');

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

-- Menu Item Ingredients (Join Table)
INSERT INTO menu_item_ingredients (menu_item_id, ingredient_code, quantity, unit) VALUES
-- Grilled Chicken Salad
(1111, 7, 200, 'grams'),
(1111, 1, 150, 'grams'),
(1111, 2, 75, 'grams'),
(1111, 4, 2, 'whole'),

-- Classic Pasta
(2222, 1, 2, 'whole'),
(2222, 10, 300, 'grams'),
(2222, 8, 200, 'grams'),

-- Fried Egg
(3333, 4, 1, 'whole'),
(3333, 3, 1, 'whole'),

-- Vegetable Stir Fry
(4444, 17, 100, 'grams'),
(4444, 11, 50, 'grams'),
(4444, 13, 75, 'grams'),
(4444, 9, 250, 'grams'),
(4444, 16, 20, 'ml'),

-- Cheese Omelet
(5555, 4, 2, 'whole'),
(5555, 5, 50, 'grams'),
(5555, 15, 10, 'grams'),

-- Beef Burger
(6666, 8, 200, 'grams'),
(6666, 6, 1, 'whole'),
(6666, 2, 25, 'grams'),
(6666, 5, 30, 'grams'),

-- Fish and Chips
(7777, 21, 250, 'grams'),
(7777, 19, 200, 'grams'),
(7777, 23, 50, 'grams'),

-- Chicken Pasta
(8888, 7, 180, 'grams'),
(8888, 10, 250, 'grams'),
(8888, 5, 40, 'grams'),
(8888, 12, 10, 'grams'),
(8888, 25, 50, 'ml'),

-- Veggie Salad
(9999, 2, 100, 'grams'),
(9999, 20, 75, 'grams'),
(9999, 18, 50, 'grams'),
(9999, 16, 15, 'ml'),
(9999, 14, 1, 'pinch');

-- PRODUCTS
INSERT INTO PRODUCTS (NAME, QUANTITY, PRICE, EXPIRY_DATE, INGREDIENT_CODE) VALUES
-- Tomatoes (3 entries)
('Tomatoes', 45, 2.99, '2025-04-15', 1),
('Tomatoes', 30, 2.79, '2025-04-28', 1),
('Tomatoes', 22, 2.59, '2025-04-20', 1),

-- Lettuce (2 entries)
('Lettuce', 25, 1.99, '2025-04-25', 2),
('Lettuce', 15, 1.89, '2025-04-18', 2),

-- Milk (4 entries)
('Milk', 20, 3.50, '2025-04-22', 3),
('Milk', 15, 3.25, '2025-04-18', 3),
('Milk', 35, 3.75, '2025-05-01', 3),
('Milk', 10, 3.00, '2025-04-15', 3),

-- Eggs (2 entries)
('Eggs', 40, 3.00, '2025-05-01', 4),
('Eggs', 25, 2.75, '2025-04-25', 4),

-- Cheese (3 entries)
('Cheese', 25, 4.50, '2025-04-10', 5),
('Cheese', 18, 4.25, '2025-04-28', 5),
('Cheese', 35, 4.75, '2025-04-15', 5),

-- Bread (2 entries)
('Bread', 30, 2.50, '2025-04-25', 6),
('Bread', 20, 2.25, '2025-04-20', 6),

-- Chicken (1 entry)
('Chicken', 40, 7.00, '2025-04-15', 7),

-- Beef (3 entries)
('Beef', 35, 10.00, '2025-05-02', 8),
('Beef', 20, 9.75, '2025-04-25', 8),
('Beef', 45, 10.25, '2025-04-20', 8),

-- Rice (2 entries)
('Rice', 60, 2.00, '2025-05-15', 9),
('Rice', 40, 1.90, '2025-04-28', 9),

-- Pasta (3 entries)
('Pasta', 45, 1.50, '2025-04-30', 10),
('Pasta', 30, 1.40, '2025-04-25', 10),
('Pasta', 55, 1.60, '2025-05-15', 10),

-- Onion (2 entries)
('Onion', 35, 2.50, '2025-04-28', 11),
('Onion', 25, 2.25, '2025-04-22', 11),

-- Garlic (3 entries)
('Garlic', 30, 3.00, '2025-04-15', 12),
('Garlic', 20, 2.75, '2025-04-25', 12),
('Garlic', 40, 3.25, '2025-05-10', 12),

-- Pepper (2 entries)
('Pepper', 45, 4.50, '2025-05-15', 13),
('Pepper', 30, 4.25, '2025-04-21', 13),

-- Salt (1 entry)
('Salt', 50, 2.00, '2030-01-01', 14),

-- Butter (3 entries)
('Butter', 35, 4.50, '2025-04-10', 15),
('Butter', 25, 4.25, '2025-04-28', 15),
('Butter', 45, 4.75, '2025-05-15', 15),

-- Olive Oil (2 entries)
('Olive Oil', 40, 5.50, '2025-05-20', 16),
('Olive Oil', 25, 5.25, '2025-04-25', 16),

-- Carrot (3 entries)
('Carrot', 35, 2.25, '2025-04-28', 17),
('Carrot', 25, 2.00, '2025-04-22', 17),
('Carrot', 45, 2.50, '2025-05-05', 17),

-- Spinach (2 entries)
('Spinach', 30, 3.00, '2025-04-25', 18),
('Spinach', 20, 2.75, '2025-04-20', 18),

-- Cucumber (1 entry)
('Cucumber', 35, 2.25, '2025-04-28', 20),

-- Fish (3 entries)
('Fish', 40, 8.50, '2025-04-15', 21),
('Fish', 25, 8.25, '2025-04-28', 21),
('Fish', 50, 8.75, '2025-05-10', 21),

-- Pork (2 entries)
('Pork', 35, 7.50, '2025-04-22', 22),
('Pork', 25, 7.25, '2025-04-25', 22),

-- Flour (3 entries)
('Flour', 45, 2.50, '2025-04-15', 23),
('Flour', 30, 2.25, '2025-05-01', 23),
('Flour', 55, 2.75, '2025-05-20', 23),

-- Sugar (2 entries)
('Sugar', 40, 2.00, '2025-04-10', 24),
('Sugar', 30, 1.90, '2025-04-28', 24),

-- Cream (2 entries)
('Cream', 35, 3.50, '2025-04-15', 25),
('Cream', 25, 3.25, '2025-04-28', 25);

-- Suppliers
INSERT INTO SUPPLIERS (NAME, CONTACT_INFO, MINIMUM_ORDER, DELIVERY_TIME, DESCRIPTION) VALUES
('Butcher Bros', 'john@butcherbros.com, 937-855-0524', 10, 3, 'Specializing in premium meats and protein'),
('Green Harvest Farms', 'sales@greenharvest.com, 614-222-7890', 20, 5, 'Organic vegetables and fresh produce'),
('Dairy Delights', 'info@dairydelights.com, 800-555-1234', 15, 2, 'High-quality dairy products and eggs'),
('Pantry Essentials', 'orders@pantryessentials.com, 555-123-4567', 25, 4, 'Bulk dry goods and staple ingredients'),
('Ocean Fresh Seafood', 'sales@oceanfresh.com, 941-678-9012', 5, 3, 'Fresh fish and seafood distributor'),
('Global Spice Traders', 'contact@globalspices.com, 212-987-6543', 30, 7, 'Specialty spices and cooking ingredients'),
('Farm to Table Organics', 'orders@farmtotable.com, 503-456-7890', 15, 4, 'Organic and locally sourced ingredients');

-- Suppliers Ingredients
INSERT INTO SUPPLIERS_INGREDIENTS (SUPPLIER_ID, INGREDIENT_CODE) VALUES
-- Butcher Bros (Meats)
(1, 8),
(1, 7),
(1, 22),

-- Green Harvest Farms (Vegetables)
(2, 1),
(2, 2),
(2, 11),
(2, 17),
(2, 20),
(2, 18),
(2, 19),
(2, 13),

-- Dairy Delights (Dairy Products)
(3, 3),
(3, 4),
(3, 5),
(3, 25),
(3, 15),

-- Pantry Essentials (Dry Goods)
(4, 9),
(4, 10),
(4, 23),
(4, 24),
(4, 14),

-- Ocean Fresh Seafood
(5, 21),

-- Global Spice Traders
(6, 12),
(6, 13),
(6, 14),

-- Farm to Table Organics
(7, 16),
(7, 6);

-- SUPPLIER_ORDER
INSERT INTO SUPPLIER_ORDER (SUPPLIER_ID, PRODUCT_ID, QUANTITY, STATUS, DATE_PLACED, DATE_DELIVERED) VALUES
-- Butcher Bros Orders (Supplier ID 1)
(1, (SELECT id FROM PRODUCTS WHERE NAME = 'Beef' AND EXPIRY_DATE = '2025-05-02'), 50, 'Delivered', '2025-02-10', '2025-02-15'),
(1, (SELECT id FROM PRODUCTS WHERE NAME = 'Chicken' AND EXPIRY_DATE = '2025-04-15'), 60, 'Pending', '2025-02-20', '2025-02-25'),
(1, (SELECT id FROM PRODUCTS WHERE NAME = 'Pork' AND EXPIRY_DATE = '2025-04-22'), 40, 'In Transit', '2025-02-15', '2025-02-22'),

-- Green Harvest Farms Orders (Supplier ID 2)
(2, (SELECT id FROM PRODUCTS WHERE NAME = 'Tomatoes' AND EXPIRY_DATE = '2025-04-15'), 100, 'Delivered', '2025-02-12', '2025-02-16'),
(2, (SELECT id FROM PRODUCTS WHERE NAME = 'Lettuce' AND EXPIRY_DATE = '2025-04-18'), 50, 'Pending', '2025-02-22', '2025-02-27'),
(2, (SELECT id FROM PRODUCTS WHERE NAME = 'Carrot' AND EXPIRY_DATE = '2025-04-22'), 75, 'In Transit', '2025-02-18', '2025-02-23'),

-- Dairy Delights Orders (Supplier ID 3)
(3, (SELECT id FROM PRODUCTS WHERE NAME = 'Milk' AND EXPIRY_DATE = '2025-04-22'), 40, 'Delivered', '2025-02-15', '2025-02-19'),
(3, (SELECT id FROM PRODUCTS WHERE NAME = 'Eggs' AND EXPIRY_DATE = '2025-04-25'), 80, 'Pending', '2025-02-25', '2025-02-28'),
(3, (SELECT id FROM PRODUCTS WHERE NAME = 'Cheese' AND EXPIRY_DATE = '2025-04-28'), 30, 'In Transit', '2025-02-20', '2025-02-25'),

-- Pantry Essentials Orders (Supplier ID 4)
(4, (SELECT id FROM PRODUCTS WHERE NAME = 'Rice' AND EXPIRY_DATE = '2025-04-28'), 120, 'Delivered', '2025-02-10', '2025-02-14'),
(4, (SELECT id FROM PRODUCTS WHERE NAME = 'Pasta' AND EXPIRY_DATE = '2025-04-30'), 60, 'Pending', '2025-02-22', '2025-02-27'),
(4, (SELECT id FROM PRODUCTS WHERE NAME = 'Flour' AND EXPIRY_DATE = '2025-05-01'), 50, 'In Transit', '2025-02-18', '2025-02-23'),

-- Ocean Fresh Seafood Orders (Supplier ID 5)
(5, (SELECT id FROM PRODUCTS WHERE NAME = 'Fish' AND EXPIRY_DATE = '2025-04-15'), 40, 'Delivered', '2025-02-12', '2025-02-16'),
(5, (SELECT id FROM PRODUCTS WHERE NAME = 'Fish' AND EXPIRY_DATE = '2025-05-10'), 35, 'Pending', '2025-02-25', '2025-02-28'),

-- Global Spice Traders Orders (Supplier ID 6)
(6, (SELECT id FROM PRODUCTS WHERE NAME = 'Garlic' AND EXPIRY_DATE = '2025-04-15'), 50, 'Delivered', '2025-02-15', '2025-02-19'),
(6, (SELECT id FROM PRODUCTS WHERE NAME = 'Pepper' AND EXPIRY_DATE = '2025-04-21'), 30, 'Pending', '2025-02-22', '2025-02-27'),

-- Farm to Table Organics Orders (Supplier ID 7)
(7, (SELECT id FROM PRODUCTS WHERE NAME = 'Olive Oil' AND EXPIRY_DATE = '2025-05-20'), 25, 'Delivered', '2025-02-10', '2025-02-14'),
(7, (SELECT id FROM PRODUCTS WHERE NAME = 'Bread' AND EXPIRY_DATE = '2025-04-25'), 40, 'Pending', '2025-02-25', '2025-02-28');

-- Customer Orders
INSERT INTO CUSTOMER_ORDER (MENU_ITEM_ID, DATE, QUANTITY) VALUES
-- Orders for Grilled Chicken Salad
(1111, '2025-02-17', 3),
(1111, '2025-02-18', 5),
(1111, '2025-02-19', 2),

-- Orders for Classic Pasta
(2222, '2025-02-17', 4),
(2222, '2025-02-18', 6),
(2222, '2025-02-19', 3),

-- Orders for Fried Egg
(3333, '2025-02-17', 2),
(3333, '2025-02-18', 4),
(3333, '2025-02-19', 1),

-- Orders for Vegetable Stir Fry
(4444, '2025-02-17', 3),
(4444, '2025-02-18', 5),
(4444, '2025-02-19', 4),

-- Orders for Cheese Omelet
(5555, '2025-02-17', 2),
(5555, '2025-02-18', 3),
(5555, '2025-02-19', 2),

-- Orders for Beef Burger
(6666, '2025-02-17', 5),
(6666, '2025-02-18', 7),
(6666, '2025-02-19', 4),

-- Orders for Fish and Chips
(7777, '2025-02-17', 3),
(7777, '2025-02-18', 4),
(7777, '2025-02-19', 2),

-- Orders for Chicken Pasta
(8888, '2025-02-17', 4),
(8888, '2025-02-18', 6),
(8888, '2025-02-19', 3),

-- Orders for Veggie Salad
(9999, '2025-02-17', 2),
(9999, '2025-02-18', 3),
(9999, '2025-02-19', 1);

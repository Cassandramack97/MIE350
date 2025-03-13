-- PRODUCTS
-- 1. A normal product (not running low, not expiring soon)
INSERT INTO PRODUCT (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Tomatoes', 50, 2.99, '2025-03-15');

-- 2. Out of Stock (quantity = 0)
INSERT INTO PRODUCT (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Lettuce', 0, 1.99, '2025-02-20');

-- 3. Running Low & Expiring Soon (expiry date close to today, quantity between 1 and 10)
INSERT INTO PRODUCT (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Milk', 5, 1.50, '2025-02-18');

-- 4. Running Low & Expiring Soon
INSERT INTO PRODUCT (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Eggs', 8, 3.00, '2025-02-19');

-- 5. Running Low (quantity exactly 10, not expiring soon)
INSERT INTO PRODUCT (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Cheese', 10, 4.50, '2025-03-05');

-- 6. Running Low & Expiring Very Soon (e.g., tomorrow)
INSERT INTO PRODUCT (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Bread', 2, 1.00, '2025-02-16');

-- 7. Plenty in stock (not running low, far expiry)
INSERT INTO PRODUCT (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Chicken', 20, 7.00, '2025-04-01');

-- 8. Out of Stock (negative quantity)
INSERT INTO PRODUCT (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Beef', -5, 10.00, '2025-03-01');

-- 9. Bulk item (non-perishable or far future expiry)
INSERT INTO PRODUCT (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Rice', 100, 2.00, '2030-01-01');

-- 10. Running Low & Expiring Soon
INSERT INTO PRODUCT (NAME, QUANTITY, PRICE, EXPIRY_DATE)
VALUES ('Pasta', 7, 1.50, '2025-02-21');

-- SUPPLIERS
INSERT INTO SUPPLIER (NAME, CONTACT_INFO)
VALUES ('Fresh Farm Inc.', 'fresh@farm.com');

INSERT INTO SUPPLIER (NAME, CONTACT_INFO)
VALUES ('Organic Valley', 'contact@organicvalley.com');

-- MENU ITEMS
INSERT INTO MENUITEM (ITEM_NAME, DESCRIPTION)
VALUES ('Margherita Pizza', 'Tomato sauce, mozzarella, basil');

INSERT INTO MENUITEM (ITEM_NAME, DESCRIPTION)
VALUES ('Chicken Salad', 'Mixed greens, grilled chicken, vinaigrette');
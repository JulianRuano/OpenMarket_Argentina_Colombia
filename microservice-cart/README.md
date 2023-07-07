###

Crear en el root del proyecto el archivo .env con las siguientes variables

``` sh
DELIVERY_URL=http://delivery:8080
PRODUCT_URL=http://product:8080
PERSON_URL=http://person:8080
ENVIRONMENT=dev
SECRET_KEY=secret
```



-- Insert data into the "ADDRESS" table
INSERT INTO ADDRESS (ID, CITY, COUNTRY, DEPARTMENT, FLOOR_NUMBER, HOUSE_NUMBER, PROVINCE, STREET)
VALUES
  (1, 'City1', 'Country1', 1, 3, '123', 'Province1', 'Street1'),
  (2, 'City2', 'Country2', 1, 5, '456', 'Province2', 'Street2'),
  (3, 'City3', 'Country3', 1, 2, '789', 'Province3', 'Street3');

-- Insert data into the "PRODUCTS" table
INSERT INTO PRODUCTS (ID, CATEGORY, DESCRIPTION, NAME, PERSON_ID, PRICE, ADDRESS_ID)
VALUES
  (1, 'Category1', 'Description1', 'Product1', 1, 9.99, 1),
  (2, 'Category2', 'Description2', 'Product2', 2, 19.99, 2),
  (3, 'Category3', 'Description3', 'Product3', 3, 29.99, 3);

-- Insert data into the "PUBLICATIONS" table
INSERT INTO PUBLICATIONS (ID, IS_ACTIVE, MAX_PRICE, MIN_PRICE, SELL_PRICE, STOCK, PRODUCT_ID)
VALUES
  (1, true, 100, 50, 75, 10, 1),
  (2, true, 200, 100, 150, 20, 2),
  (3, true, 300, 150, 225, 5, 3);

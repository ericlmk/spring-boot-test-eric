# Purchase Order Service - Spring Java Test - Solution

## Project Modules

1. purchase-order-service
   * The main SpringBoot project to handle order CRUD actions
   

2. purchase-order-service-api
   * The interface project for purchase-order-service
   

3. purchase-order-service-client
   * It includes OpenFeign Client w/ Demo SpringBoot application

## Test Data
### User
* User 1: Peter
  * Username: peter
  * Password: 123456
  * role: USER


* User 2: Paul
   * Username: paul
   * Password: 123456
   * Role: ADMIN

### Product Item
* ID: 1
   * PI_NAME: Burger

* ID: 2
   * PI_NAME: Coke

## Module - purchase-order-service

### Authentication
* Basic auth is applied with using spring-security-web
  * Header: 'Authorization: Basic cGV0ZXI6MTIzNDU2' 

### REST API
* Port: 8080
* Context Path: /

1. Get ALL purchase orders (status <> 'Deleted')
   * GET /orders
```
curl --location --request GET 'http://localhost:8080/orders' \
--header 'Authorization: Basic cGF1bDoxMjM0NTY='
```

2. Get ONE purchase order by ID
   * GET /orders/{id}
```
curl --location --request GET 'http://localhost:8080/orders/1' \
--header 'Authorization: Basic cGF1bDoxMjM0NTY='
```

3. Get list of purchase orders for a given date
   * GET /orders?date={YYYY-MM-DD}
```
curl --location --request GET 'http://localhost:8080/orders?date=2022-02-12' \
--header 'Authorization: Basic cGF1bDoxMjM0NTY='
```

4. Create a new purchase order
   * POST /orders
```
curl --location --request POST 'http://localhost:8080/orders' \
--header 'Authorization: Basic cGF1bDoxMjM0NTY=' \
--header 'Content-Type: application/json' \
--data-raw '{
    "status": "ACTIVE",
    "productItemIdList": [1, 2]
}'
```

```
curl --location --request POST 'http://localhost:8080/orders' \
--header 'Authorization: Basic cGF1bDoxMjM0NTY=' \
--header 'Content-Type: application/xml' \
--data-raw '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<purchaseOrder>
    <status>ACTIVE</status>
    <productItemIdList>
        <productItemIdList>1</productItemIdList>
        <productItemIdList>2</productItemIdList>
    </productItemIdList>
</purchaseOrder>
'
```

5. Add and remove product line items to/from the purchase order
   * PUT /orders/{id}
```
curl --location --request PUT 'http://localhost:8080/orders/1' \
--header 'Authorization: Basic cGF1bDoxMjM0NTY=' \
--header 'Content-Type: application/json' \
--data-raw '{
    "status": "ACTIVE",
    "productItemIdList": [1]
}'
```

```
curl --location --request PUT 'http://localhost:8080/orders/1' \
--header 'Authorization: Basic cGF1bDoxMjM0NTY=' \
--header 'Content-Type: application/xml' \
--data-raw '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<purchaseOrder>
    <status>ACTIVE</status>
    <productItemIdList>
        <productItemIdList>1</productItemIdList>
        <productItemIdList>2</productItemIdList>
    </productItemIdList>
</purchaseOrder>
'
```

6. Delete a purchase order
   * Change the status field to 'Deleted'
   * DELETE /orders/{id}
```
curl --location --request DELETE 'http://localhost:8080/orders/1' \
--header 'Authorization: Basic cGF1bDoxMjM0NTY='
```

7. Extra: Create User
   * POST /clients
```
curl --location --request POST 'http://localhost:8080/clients' \
--header 'Authorization: Basic cGF1bDoxMjM0NTY=' \
--header 'Content-Type: application/json' \
--data-raw '{
    "clientName": "mary",
    "clientAddress1": "Addr1",
    "clientAddress2": "Addr2",
    "clientAddressCity": "Tuen Mun",
    "clientAddressPostCode": "852",
    "clientCountry": "Hong Kong"
}'
```

### Encryption
* In client DB table, all data fields are encrypted by AES except primary key "clientId"

### h2-console
* Excluded from Auth checking
* The module should be removed before production launch

### Dockerize
```
cd ./purchase-order-service
./mvnw spring-boot:build-image -D spring-boot.build-image.imageName=ericlmk/purchase-order-service:0.0.1
docker run -p 8080:8080 --name poservice -d ericlmk/purchase-order-service:0.0.1 
```

## Module - purchase-order-service-client

### Authentication
* Basic Auth is used
  * pass through to purchase-order-service
  * No validation is implemented in this demo project
  * Header: 'Authorization: Basic cGF1bDoxMjM0NTY='

### REST API
* Port: 8081
* Context Path: /client

1. Get ALL purchase orders (status <> 'Deleted')
   * GET /client/orders

2. Get ONE purchase order by ID
   * GET /client/orders/{id}

3. Get list of purchase orders for a given date
   * GET /client/orders?date={YYYY-MM-DD}

4. Create a new purchase order
   * POST /client/orders

5. Add and remove product line items to/from the purchase order
   * PUT /client/orders/{id}

6. Delete a purchase order
   * DELETE /client/orders/{id}

### Dockerize
* Assuming that the name of docker image purchase-order-service is **poservice**
* Use docker link to connect 2 docker instances for demo ONLY. It is not a good approach for real implementation.
```
cd ./purchase-order-service-client
./mvnw spring-boot:build-image -D spring-boot.build-image.imageName=ericlmk/purchase-order-service-client:0.0.1
docker run -p 8081:8081 --name poserviceclient --link poservice -v C:/Projects/spring-boot-test-eric/docker/client-application.properties:/workspace/BOOT-INF/classes/application.properties -d ericlmk/purchase-order-service-client:0.0.1 
```

### Fallback (Hystrix)
* PurchaseOrderServiceFallbackClient.java
  * Not implemented the fallback functions, return 501 Not Implemented for demo
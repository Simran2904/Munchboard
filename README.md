
# Munchboard

Munchboard is a food ordering web application developed using Java and Spring Boot. It provides users with an interactive platform to explore food items, browse categories, search for products, manage their shopping cart, and proceed through the checkout process.

The project also includes a Spring Boot backend with REST APIs for managing customer orders and storing order information in a MySQL database.

## Features

- Interactive food ordering website.
- Food categories and menu sections.
- Search and filter food items.
- Add food items to the shopping cart.
- Remove items from the cart.
- Shopping cart with quantity and total price calculation.
- Checkout form for customer details and delivery address.
- Payment method selection (Cash on Delivery and demo UPI option).
- Spring Boot backend integration.
- REST APIs for creating and retrieving orders.
- MySQL database connectivity.

## Technologies Used

- Java 21
- Spring Boot 3.4.5
- Spring Web
- Thymeleaf
- Spring JDBC
- MySQL
- HTML
- CSS
- JavaScript
- Maven

## Project Structure

```text
Munchboard-main/
│
├── pom.xml
│
└── src/
    └── main/
        ├── java/com/munchboard/app/
        │   ├── AppApplication.java
        │   │
        │   ├── controller/
        │   │   ├── HomeController.java
        │   │   └── OrderController.java
        │   │
        │   ├── model/
        │   │   ├── Order.java
        │   │   └── OrderItem.java
        │   │
        │   ├── repository/
        │   │   ├── OrderRepository.java
        │   │   └── OrderItemRepository.java
        │   │
        │   └── service/
        │       └── OrderService.java
        │
        └── resources/
            ├── application.properties
            ├── templates/
            │   └── index.html
            │
            └── static/
                ├── script.js
                ├── style.css
                └── images/
```

## Requirements

Before running the project, make sure you have installed:

- JDK 21
- Apache Maven
- MySQL Server
- IntelliJ IDEA or Eclipse (optional)

## Installation and Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
```

Navigate to the project directory:

```bash
cd Munchboard-main
```

### 2. Create the Database

Open MySQL and execute:

```sql
CREATE DATABASE munchboard;
```

### 3. Configure Database Connection

Open:

`src/main/resources/application.properties`

Configure your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/munchboard
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

server.port=8081
```

Make sure the required `orders` and `order_items` tables are created in MySQL according to the repository SQL queries.

### 4. Run the Application

Open a terminal in the project directory and execute:

```bash
mvn spring-boot:run
```

Alternatively, build the project:

```bash
mvn clean package
```

### 5. Open the Application

Once the application starts, open your browser and visit:

http://localhost:8081/

## REST API Endpoints

The backend provides the following REST APIs:

| HTTP Method | Endpoint | Description |
|-------------|----------|-------------|
| POST | /api/orders | Create a new order |
| GET | /api/orders/{id} | Retrieve an order using its ID |

### Create Order

Endpoint:

`POST /api/orders`

This API accepts customer details, payment information, total amount, and ordered items.

### Retrieve Order

Endpoint:

`GET /api/orders/{id}`

This API retrieves order details and associated items using the order ID.

## Future Enhancements

- Connect the frontend checkout process with the backend order API.
- Implement real-time order tracking.
- Add user registration and login.
- Integrate a secure online payment gateway.
- Add order history.
- Improve backend validation and error handling.
- Implement an admin dashboard for managing orders.
- Add database schema and migration scripts.

## Important Note

The current shopping cart and checkout interactions are handled on the frontend using JavaScript. The checkout confirmation does not yet submit orders to the backend REST API.

The UPI payment option is a demonstration feature and does not process actual payments.

## License

Refer to the LICENSE file included in the project for licensing information.

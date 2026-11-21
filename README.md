**Munchboard**

Munchboard is a food-ordering website built with Java 21 and Spring Boot. It provides a restaurant-style interface where visitors can browse food categories and products, search the menu, add items to a cart, and proceed through a checkout form.

The project also contains a Spring Boot REST API for creating and retrieving orders, with MySQL access through JDBC.
Features
Website
- Responsive food-ordering landing page.
- Food category and menu sections.
- Product search and category filtering.
- Shopping cart with item count and total calculation.
- Add products to the cart and remove them.
- Checkout form for customer contact and delivery details.
- Cash on Delivery and demo UPI payment-method selection.
- Food images and a styled interface.
Backend
- Spring Boot application.
- Home page served using Thymeleaf.
- REST endpoint to create an order.
- REST endpoint to retrieve an order by its ID.
- JDBC repositories for saving orders and order items in MySQL.
Technology Stack
Area	Technology
Programming language	Java 21
Backend framework	Spring Boot 3.4.5
Web	Spring Web
Template engine	Thymeleaf
Database	MySQL
Database access	Spring JDBC
Frontend	HTML, CSS, JavaScript
Build tool	Maven


Project Structure
Munchboard-main/
├── pom.xml
└── src/
    └── main/
        ├── java/com/munchboard/app/
        │   ├── AppApplication.java
        │   ├── controller/
        │   │   ├── HomeController.java
        │   │   └── OrderController.java
        │   ├── model/
        │   │   ├── Order.java
        │   │   └── OrderItem.java
        │   ├── repository/
        │   │   ├── OrderRepository.java
        │   │   └── OrderItemRepository.java
        │   └── service/
        │       └── OrderService.java
        └── resources/
            ├── application.properties
            ├── templates/
            │   └── index.html
            └── static/
                ├── script.js
                ├── style.css
                └── images/
Requirements
- JDK 21
- Apache Maven 3.6.3 or later
- MySQL Server
- An IDE such as IntelliJ IDEA or Eclipse (optional)
Spring Boot 3.4.5 supports Java 17 through Java 24. The project itself sets the Java version to 21 in pom.xml. See the Spring Boot 3.4 system requirements.
Setup and Installation
1. Clone or extract the project
git clone <repository-url>
cd Munchboard-main
If you downloaded the ZIP, extract it and open a terminal in the Munchboard-main folder.
2. Create the MySQL database
Start your MySQL server and create the database:
CREATE DATABASE munchboard;
3. Configure the database connection
Open:
src/main/resources/application.properties
Update the connection properties to match your local MySQL setup:
spring.datasource.url=jdbc:mysql://localhost:3306/munchboard
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

server.port=8081
The supplied project uses the orders and order_items tables through JDBC. The ZIP does not include a SQL schema file for these tables, so create them before using the order API. The columns used by the repository code are:
- orders: id, customer_name, phone, email, address, payment_method, total_amount, order_status, order_date
- order_items: id, order_id, product_name, quantity, price, subtotal
The order_date column should be populated by the database when a new order is inserted.
Configuration note: The archive contains local database credentials. Do not publish real passwords in source control; use environment variables or a separate local configuration file.

4. Run the application
From the project root, run:
mvn spring-boot:run
Or build the project:
mvn clean package
Then run the generated JAR from the target directory:
java -jar target/Munchboard-0.0.1-SNAPSHOT.jar
5. Open the website
Visit:
http://localhost:8081/
REST API
The backend currently defines these order endpoints:
HTTP method	Endpoint	Description
POST	/api/orders	Creates an order and saves its order items
GET	/api/orders/{id}	Retrieves an order and its items using the order ID


Create an order
Example request body for POST /api/orders:
{
  "customerName": "Alex",
  "phone": "9876543210",
  "email": "alex@example.com",
  "address": "123 Example Street",
  "paymentMethod": "Cash on Delivery",
  "totalAmount": 250.00,
  "orderStatus": "PLACED",
  "items": [
    {
      "productName": "Burger",
      "quantity": 2,
      "price": 125.00,
      "subtotal": 250.00
    }
  ]
}
A successful response includes a success flag, the generated order ID, and a message.
Retrieve an order
GET /api/orders/1
The response contains the order details and its associated items. If the requested order ID does not exist, the endpoint returns 404 Not Found.
Current Implementation Notes
- The cart and checkout interactions in static/script.js are handled in the browser.
- The current placeOrder() JavaScript function displays a confirmation message and clears the client-side cart; it does not send the checkout data to POST /api/orders.
- UPI is presented as a demo selection. No live payment gateway integration is included.
- The supplied archive does not contain a database schema script.
- application.properties includes spring.jpa.hibernate.ddl-auto=update, but the project declares Spring JDBC rather than Spring Data JPA entities. Do not rely on that setting to create the JDBC order tables.
Possible Future Improvements
- Connect the checkout form to the order creation API.
- Add a database schema or migration script.
- Add server-side validation for customer and order details.
- Add order history and order-status tracking.
- Integrate a real payment provider if online payments are required.
- Move database credentials out of the committed configuration file.
- Add automated tests for the order service and API.
License
This project includes a LICENSE file. Refer to that file for the applicable license terms.

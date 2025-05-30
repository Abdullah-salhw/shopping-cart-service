# The Shopping Cart Service Project – Final Project

This project presents a straightforward Shopping Cart REST API crafted using Java version 21, Spring Boot framework, and MySQL database (used instead of PostgreSQL). The system provides fundamental functionalities such as adding/removing items to/from the cart, displaying products and carts, and processing cart payments inclusive of tax.

##  Technologies Utilized
- Java 21
- Spring Boot (version 3.3.12)
- Spring Data JPA
- MySQL 8.3.0
- Maven
- Embedded Tomcat
- Postman tool for testing

##  Database Setup
- Database Schema: `ShoppingCartDB`
- User: `postgres`
- No password required
- JPA automatic creation activated
- Table prefix set as `abdullah_alhawas_`
- Initial dataset populated upon system startup

##  Key Functionalities Implemented
- Display a list of all available products
- Search for products by ID or keyword
- View detailed information about a specific product
- Add a product to the shopping cart
- Remove a product from the shopping cart
- View the contents of the shopping cart
- Clear the shopping cart
- Process payment for the shopping cart, considering tax charges

##  Endpoints for the REST API
###  Product Operations
| Method | Endpoint              | Description                      |
|--------|-----------------------|----------------------------------|
| GET    | `/api/products`       | Retrieve all products            |
| GET    | `/api/products/{id}`  | Retrieve a product by ID         |
| GET    | `/api/products/search?query=word` | Search for products by keyword |

###  Shopping Cart Operations
| Method | Endpoint                        | Description                           |
|--------|----------------------------------|---------------------------------------|
| POST   | `/api/carts`                    | Create a new empty cart                |
| GET    | `/api/carts/{cartId}`           | Retrieve cart information by ID        |
| GET    | `/api/carts/{cartId}/items`     | Retrieve all items in a cart           |
| POST   | `/api/carts/{cartId}/items`     | Add an item to the cart                |
| DELETE | `/api/carts/{cartId}/items/{productId}` | Remove a specific product from the cart |
| DELETE | `/api/carts/{cartId}/items`     | Clear the entire cart                  |
| POST   | `/api/carts/{cartId}/pay`       | Process payment for the cart           |
> **Note**: An HTTP 409 (Conflict) status will be returned if a cart has already been paid for.

##  Assumptions
- Each `Product` comprises attributes such as `id`, `name`, `description`, `price`, `taxRate`, `stockQty`.
- `CartItem` stores snapshots of `price` and `tax` at the time of addition.
- The system does not involve authentication mechanisms or customer-related logic as they are not mandatory.
- The system initializes with predefined sample products.

##  Running the Project
1. Clone the repository.
2. Ensure MySQL is up and running.
3. Utilize the schema named `ShoppingCartDB`.
4. Execute the project using the following command:
   ```bash
   .

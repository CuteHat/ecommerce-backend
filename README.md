## The following microservices are included in this project:

    IAM (Identity and Access Management) service
    Product service
    Notification service
    Pad service
    Order service

## Running the Microservices

To run the microservices, make sure you have Docker and Docker Compose installed on your machine. Then, navigate to the
root directory of the project and run the following command:

docker-compose up

This will start all the microservices and their dependencies (i.e., RabbitMQ and PostgreSQL).

## Swagger URLs

You can access the Swagger UI for each microservice at the following URLs:

    IAM service: http://localhost:8080/swagger-ui/index.html
    Product service: http://localhost:8081/swagger-ui/index.html
    Notification service: http://localhost:8082/swagger-ui/index.html
    Pad service: http://localhost:8083/swagger-ui/index.html
    Order service: http://localhost:8084/swagger-ui/index.html

## Acknowledgements

This project was created using the following technologies:

    Docker
    Docker Compose
    Java Spring Boot
    PostgreSQL
    RabbitMQ

### IAM SERVICE

#### User endpoints:

    GET /api/v1/user: Returns the current user's details.
    PUT /api/v1/user: Updates the current user's details.
    DELETE /api/v1/user: Deactivates the current user's account.

#### Admin user endpoints:

    GET /api/v1/admin/user/{id}: Returns the user with the specified ID.
    GET /api/v1/admin/user/filter: Filters users based on the specified criteria.
    PUT /api/v1/admin/user/{id}: Updates the user with the specified ID.
    PATCH /api/v1/admin/user/{id}/role: Updates the role of the user with the specified ID.
    PATCH /api/v1/admin/user/{id}/deactivate: Deactivates the user with the specified ID.
    PATCH /api/v1/admin/user/{id}/activate: Activates the user with the specified ID.

#### Auth endpoints:

    POST /api/v1/auth/register: Registers a new user.
    POST /api/v1/auth/login: Logs in a user.

#### Prefilled users

    Admin: admin@example.com, password: qQ!12345678
    Seller: seller@example.com, password: qQ!12345678
    Client: client@example.com, password: qQ!12345678

#### Roles

    The API uses Flyway, which is a database migration tool, to manage and version database changes. Flyway is used to
    initialize the database with predefined roles, which are inserted during the migration process. The roles are '
    ADMINISTRATOR', 'SELLER', and 'CLIENT', which are predefined and inserted into the 'roles' table. These roles can be
    assigned to users later in the application, depending on the access level required.

### Notification service

    The Notification service allows sending notifications to users via email or SMS. The service
    is implemented using Spring Framework, and the primary functionality is provided by the NotificationServiceFacadeImpl
    class.
    
    The NotificationServiceFacadeImpl class is responsible for sending notifications by creating a batch of notifications,
    then sending each notification to the appropriate service. The batch creation is done via the createBatch() method of
    the NotificationService class, which is responsible for persisting the notifications in the database.
    
    Once the notifications are created, they are sent to the SMSService and MailService classes. These classes are mocked,
    meaning that they do not send actual emails or SMS messages, but rather log a message to indicate that the notification
    was sent.
    
    The NotificationServiceFacadeImpl class is also transactional, which means that if an error occurs during the sending of
    the notifications, the entire operation will be rolled back to ensure data consistency.
    
    Overall, the Notification service provides a straightforward way to send notifications to users via email or SMS, with a
    clear separation of concerns between creating and sending notifications, and with the added benefit of transactional
    support to ensure data consistency.

    This project also includes a NotificationQueueListener component which listens to the RabbitMQ notification queue for
    incoming messages. When a message is received, it parses the message body and converts it to a list of NotificationDto
    objects using Gson. It then passes the list to the NotificationServiceFacade to send the notifications via SMS and
    email. The listener component is annotated with @Component and @RequiredArgsConstructor and uses the @RabbitListener
    annotation to specify the queue it should listen to.

### Product service

    The Product Service has several endpoints for managing and retrieving product and category information. The Category
    Controller handles requests related to categories, while the Product Controller manages product information.
    GET /api/category: Retrieves a list of all categories.
    GET /api/category/{id}: Retrieves a category by ID.
    GET /api/product: Retrieves a list of all products.
    GET /api/product/{id}: Retrieves a product by ID.
    GET /api/product/{id}/stock/available: Retrieves whether the specified quantity of a product is available.
    POST /api/product/stock/available/batch: Retrieves whether the specified quantities of a batch of products are available.
    GET /api/product/filter: Retrieves a list of products based on the given list of IDs.

    This is a listener component in the Product Service that listens to the "product.queue" RabbitMQ queue for incoming
    messages. Once a message is received, the "ProductQueueListener" processes the message by converting it from a JSON
    string to a list of "DecrementStockDto" objects using the Gson library. The converted list is then passed to the "
    ProductServiceFacade" to update the stock levels of the corresponding products. This listener helps to keep the stock
    levels of the products in sync with the orders that have been placed.

### Packaging and delivery service

    The PAD (Pending Approval and Delivery) service consists of several controllers and a listener. The OrderController and
    DeliveryController are both used by administrators to manage orders and deliveries, respectively. The OrderController
    has a single endpoint that retrieves a list of all orders. The DeliveryController has two endpoints: one for filtering
    deliveries by various criteria, and one for updating the status of a delivery.

    GET /api/v1/admin/order: Retrieves a list of all orders.
    GET /api/v1/admin/delivery/filter: Retrieves a list of deliveries based on the given filters.
    POST /api/v1/admin/delivery/{id}/status: Updates a delivery's status by ID.

    Additionally, the PAD service includes a PADQueueListener, which listens for messages from a RabbitMQ queue. When a
    message is received, the listener deserializes it into an OrderCreateRequest object and passes it to the
    OrderServiceFacadeImpl to create a new pending order.

### Order service

The Order service consists of several classes that work together to create an order. The OrderController class is a REST
controller that provides an endpoint to create a new order. The endpoint accepts an OrderCreateRequest object in the
request body, validates the input, and passes the request to the OrderServiceFacade for processing.

The OrderServiceFacade is the main service class responsible for creating the order. It is composed of several other
service classes and APIs, including OrderService, NotificationQueueService, NotificationService, PadQueueService,
ProductQueueService, and ProductApiService.

    When a new order is received by the OrderServiceFacade, it performs the following steps:

    1) Validates the stock availability of the products in the order.
    2) Fetches the product details from the ProductApiService using the provided product IDs.
    3) Persists the order to the database using the OrderService.
    4) Sends a message to the ProductQueueService to decrement the stock of the ordered products.
    5) Sends a message to the PadQueueService to create a delivery request for the order.
    6) Generates notifications to the seller and client using the NotificationService.
    7) Sends a message to the NotificationQueueService to send the notifications.

    The OrderServiceFacade class also provides several helper methods to perform the above tasks. The checkStock() method
    validates the stock availability of the ordered products, while the getProducts() method fetches the product details
    from the ProductApiService. The persistOrder() method persists the order to the database using the OrderService.
    
    Overall, the OrderService provides a complete order creation solution that interacts with various other services and
    APIs to fulfill the order requirements.
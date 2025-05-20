Role	Can View Books	Can Place Orders	Can Manage Books	Can View Users/Orders
User	✅	✅	❌	❌
Manager	✅	❌	✅	✅
				
📚 Book Service Endpoints				
Endpoint	Method	Description	Access	
/books	GET	Get list of all available books	User & Manager	server-port is 8083
/books/{id}	GET	Get book details by ID	User & Manager	
/books	POST	Add a new book	Manager	
/books/{id}	PUT	Update book info	Manager	
/books/{id}	DELETE	Delete a book	Manager	
				
🛒 Order Service Endpoints				
Endpoint	Method	Description	Access	server-port is 8086
/orders	POST	Place a new order	User	
/orders/{id}	GET	Get order details by order ID	User	
/orders/user/{userId}	GET	Get all orders by user ID	User	
/orders/all	GET	Get all orders in the system	Manager	
/orders/{id}	PATCH	Update/Cancel order status (optional)	Manager	
				
👤 User Service Endpoints				
Endpoint	Method	Description	Access	server-port is 8082
/users	POST	Register a new user	Public	
/users/login	POST	Login and return token	Public	
/users/{id}	GET	Get user profile by ID	User	
/users	GET	Get list of all users	Manager	
/users/{id}	DELETE	Delete a user (optional)	Manager	
				
📂 Manager Service API Endpoints				
Endpoint	Method	Description		
/manager/login	POST	Admin login and get token		server-port is 8084
/manager/books	POST	Add a new book		
/manager/books/{id}	PUT	Update book details		
/manager/books/{id}	DELETE	Delete a book		
/manager/orders	GET	View all orders		
/manager/orders/{id}	PATCH	Update order status (approve/cancel)		
/manager/users	GET	View all users		
![image](https://github.com/user-attachments/assets/0b640902-d7d5-4af1-ad43-98af1a1044b5)
![Microservices Flowchart for Online Bookstore](https://github.com/user-attachments/assets/d2043358-7a8b-45ed-842b-954001d10c92)

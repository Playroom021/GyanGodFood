# 🍔 FoodyWoody

FoodyWoody is a full-stack food ordering web application built with **Java 17, Spring Boot, Spring MVC, Thymeleaf, Spring Data JPA, Spring Security, and H2/MySQL**.

The application provides a complete basic ordering flow:

**Browse Menu → Add to Cart → Checkout → Place Order → Track Orders**

It also includes an **admin panel** for managing products and updating order statuses.

---

## ✨ Features

### Customer Features

- 🏠 Landing/home page
- 🍕 Food menu with category filtering
- 🛒 Session-based shopping cart
- ➕ Add products with quantity
- 🔄 Update cart quantities
- ❌ Remove cart items
- 👤 User registration and login
- 🔐 BCrypt password hashing
- 📦 Checkout and order placement
- 📋 View previous orders
- 🔎 View order details and status
- 📱 Responsive UI
- 🎨 Lightweight modern UI using a single CSS design system

### Admin Features

- 🔐 Role-based admin authentication
- 📊 Admin dashboard
- 📦 Product management
  - Add products
  - Edit products
  - Delete products
  - Mark products available/unavailable
- 🧾 View all customer orders
- 🔄 Update order status
- 👥 View registered-user count

### Developer Features

- Layered Spring architecture
- Spring Data JPA repositories
- DTO for registration
- Session-scoped cart
- Bean Validation
- Thymeleaf reusable fragments
- H2 database for zero-setup development
- MySQL configuration ready for local/production-style deployment
- Automatic sample-data initialization

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Backend | Spring Boot 3.3.4 |
| Web | Spring MVC |
| Template Engine | Thymeleaf |
| ORM | Spring Data JPA / Hibernate |
| Security | Spring Security 6 |
| Validation | Jakarta Bean Validation |
| Database | H2 / MySQL |
| Build Tool | Maven |
| Frontend | HTML5, CSS3, Thymeleaf |
| Password Hashing | BCrypt |

---

## 🏗️ Architecture

The application follows a conventional layered architecture:

```text
Browser
   │
   ▼
Controller Layer
   │
   ▼
Service Layer
   │
   ▼
Repository Layer
   │
   ▼
Database
```

### Main packages

```text
src/main/java/com/example/foodywoody/
│
├── cart/
│   ├── Cart.java
│   └── CartItem.java
│
├── config/
│   ├── DataInitializer.java
│   ├── GlobalModelAttributes.java
│   └── SecurityConfig.java
│
├── controller/
│   ├── AdminController.java
│   ├── AuthController.java
│   ├── CartController.java
│   ├── HomeController.java
│   ├── OrderController.java
│   └── ProductController.java
│
├── dto/
│   └── RegisterForm.java
│
├── entity/
│   ├── Category.java
│   ├── Order.java
│   ├── OrderItem.java
│   ├── OrderStatus.java
│   ├── Product.java
│   ├── Role.java
│   └── User.java
│
├── repository/
│   ├── OrderRepository.java
│   ├── ProductRepository.java
│   └── UserRepository.java
│
├── service/
│   ├── CustomUserDetailsService.java
│   ├── OrderService.java
│   ├── OrderServiceImpl.java
│   ├── ProductService.java
│   ├── ProductServiceImpl.java
│   ├── UserService.java
│   └── UserServiceImpl.java
│
└── FoodyWoodyApplication.java
```

Frontend:

```text
src/main/resources/
│
├── templates/
│   ├── admin/
│   ├── auth/
│   ├── cart.html
│   ├── checkout.html
│   ├── fragments.html
│   ├── index.html
│   ├── menu.html
│   ├── order-confirmation.html
│   └── orders.html
│
└── static/
    └── css/
        └── style.css
```

---

## 🔐 Authentication & Authorization

FoodyWoody uses Spring Security.

### Roles

```text
USER
ADMIN
```

Regular users can:

- Browse products
- Manage their cart
- Checkout
- View their orders

Admins can additionally access:

```text
/admin/**
```

and manage products and orders.

Passwords are stored using **BCrypt hashing** rather than plain text.

---

## 🗄️ Database

### Development

The project uses an in-memory H2 database by default:

```properties
spring.datasource.url=jdbc:h2:mem:foodywoody;DB_CLOSE_DELAY=-1
spring.datasource.username=sa
spring.datasource.password=
```

This makes the project easy to run without installing MySQL.

### H2 Console

During development, the H2 console is available at:

```text
http://localhost:8080/h2-console
```

JDBC URL:

```text
jdbc:h2:mem:foodywoody
```

Username:

```text
sa
```

Password:

```text
(empty)
```

> The H2 console should be disabled or protected before deploying the application publicly.

---

## 🧪 Sample Data

On startup, `DataInitializer` creates:

### Admin

```text
Email: admin@foodywoody.com
Password: Admin@123
```

### Sample products

- Margherita Pizza
- Pepperoni Pizza
- Farmhouse Pizza
- Classic Cheeseburger
- Veggie Burger
- Sparkling Lemonade
- Cold Coffee
- Chocolate Brownie
- New York Cheesecake
- Garden Salad

> Change/remove the seeded admin password before using this project outside a development/demo environment.

---

## 🚀 Getting Started

### Prerequisites

Install:

- Java 17+
- Maven 3.8+

Verify:

```bash
java -version
mvn -version
```

### Clone the project

```bash
git clone <your-repository-url>
cd GyanGodFood/foodywoody
```

### Run the application

```bash
mvn spring-boot:run
```

Open:

```text
http://localhost:8080
```

---

## 📌 Important Routes

| Route | Purpose | Access |
|---|---|---|
| `/` | Home page | Public |
| `/menu` | Browse menu | Public |
| `/cart` | Shopping cart | Public |
| `/register` | Registration | Public |
| `/login` | Login | Public |
| `/checkout` | Checkout | Authenticated |
| `/orders` | Customer orders | Authenticated |
| `/orders/{id}` | Order details | Authenticated |
| `/admin` | Admin dashboard | ADMIN |
| `/admin/products` | Product management | ADMIN |
| `/admin/orders` | Order management | ADMIN |
| `/h2-console` | H2 database console | Development only |

---

# 🔄 Application Flow

## Customer Flow

```text
Home
  ↓
Menu
  ↓
Select Food
  ↓
Add to Cart
  ↓
Review Cart
  ↓
Checkout
  ↓
Enter Address + Payment Method
  ↓
Place Order
  ↓
Order Confirmation
  ↓
My Orders
```

## Admin Flow

```text
Login
  ↓
Admin Dashboard
  ├── Manage Products
  │     ├── Add
  │     ├── Edit
  │     └── Delete
  │
  └── Manage Orders
        └── Update Order Status
```

---

# 📊 Current Order Lifecycle

The application currently supports:

```text
PLACED
   ↓
PREPARING
   ↓
OUT_FOR_DELIVERY
   ↓
DELIVERED
```

It also supports:

```text
CANCELLED
```

A future improvement would be to enforce valid status transitions instead of allowing an administrator to select any status at any time.

---




A project with these capabilities can be presented as a complete **e-commerce/food-delivery platform**, rather than only a CRUD Spring Boot application.

---

# 🤝 Contributing

1. Fork the repository.
2. Create a feature branch:

```bash
git checkout -b feature/your-feature
```

3. Commit your changes:

```bash
git commit -m "feat: add product search"
```

4. Push the branch:

```bash
git push origin feature/your-feature
```

5. Open a Pull Request.

---

# 📄 License

Add the license that applies to your project.

For an open-source project, MIT is a simple option:

```text
MIT License
```

---

# 👨‍💻 Project Summary

**FoodyWoody** demonstrates:

- Java backend development
- Spring Boot
- MVC architecture
- Spring Security
- JPA/Hibernate
- Database integration
- Authentication and authorization
- Session management
- CRUD operations
- Server-side validation
- Thymeleaf frontend development
- E-commerce ordering logic

The current version is a solid foundation. The most important next step is to focus on **security correctness, real product data, persistent order/cart functionality, testing, and deployment**.

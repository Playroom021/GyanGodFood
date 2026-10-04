# FoodyWoody 🍔

A light-UI Spring Boot + Thymeleaf food-ordering app, built with a clean, idiomatic
Spring architecture.

## What changed vs. the original
- **Light, modern UI**: single CSS design system (colors/spacing as CSS variables), rounded
  cards, pill buttons/filters, emoji-based food icons instead of bundled images — fast to load
  and easy to re-skin.
- **Cleaner architecture**: proper `entity / repository / service (+ impl) / controller / dto`
  layering, session-scoped `Cart` bean instead of ad-hoc state, DTOs for forms, and snapshot
  fields on `OrderItem` so historic orders stay correct even if a product's price changes later.
- **Real Spring Security**: BCrypt-hashed passwords, form login, role-based authorization
  (`/admin/**` requires `ROLE_ADMIN`), CSRF protection on by default.
- **Zero-setup run**: uses an in-memory H2 database by default, so `mvn spring-boot:run` works
  immediately with no MySQL install. A commented MySQL config is included in
  `application.properties` for production use.
- Sample menu (pizzas, burgers, beverages, desserts, salads) and an admin account are seeded
  automatically on first run.

## Run it
```bash
mvn spring-boot:run
```
Then open http://localhost:8080

- Sign up as a customer at `/register`, or
- Log in as the seeded admin: **admin@foodywoody.com / Admin@123** to manage products and orders
  at `/admin`.

The H2 console (for peeking at the seeded data) is at `/h2-console`
(JDBC URL `jdbc:h2:mem:foodywoody`, user `sa`, no password).

## Project layout
```
src/main/java/com/example/foodywoody/
├── entity/        JPA entities (User, Product, Order, OrderItem, enums)
├── repository/    Spring Data JPA repositories
├── service/       Business logic (interfaces + impl)
├── controller/    MVC controllers
├── cart/          Session-scoped shopping cart
├── dto/           Form-backing objects
└── config/        Security config + startup data seeding
src/main/resources/
├── templates/     Thymeleaf views (fragments.html holds the shared navbar/footer)
└── static/css/    Single stylesheet driving the light theme
```

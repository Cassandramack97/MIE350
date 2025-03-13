# Restaurant Management System (Backend + Brief Frontend Overview)

## Table of Contents

1. [Project Overview](#project-overview)
2. [File & Directory Structure](#file--directory-structure)
3. [Key Dependencies](#key-dependencies)
4. [Installation & Setup](#installation--setup)
5. [Running the Application (Backend)](#running-the-application-backend)
6. [H2 Database Access](#h2-database-access)
7. [REST Endpoints](#rest-endpoints)
8. [Example Queries](#example-queries)
9. [Notes & Future Steps](#notes--future-steps)
10. [Frontend Setup (Brief)](#frontend-setup-brief)

---

## 1. Project Overview

This repository contains:

- **Backend (Spring Boot 3.x, H2 DB)**
  - Exposes REST endpoints for a Restaurant Management System (inventory, suppliers, menu items).
- **Frontend (React)**
  - Currently minimal: set up with basic pages and placeholder components.

---

## 2. File & Directory Structure

```
RestaurantManagementSystem/
├── backend/
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com/example/restaurant
│   │   │   │       ├── RestaurantManagementApplication.java
│   │   │   │       ├── config/
│   │   │   │       ├── controller/
│   │   │   │       ├── model/
│   │   │   │       ├── repository/
│   │   │   │       └── service/
│   │   │   └── resources
│   │   │       ├── application.properties
│   │   │       ├── schema.sql
│   │   │       └── data.sql
│   │   └── test/java
│   └── README.md
└── frontend/
    ├── package.json
    ├── yarn.lock (or package-lock.json)
    ├── public/
    └── src/
        ├── pages/
        ├── components/
        ├── services/
        └── App.js
```

### Key Backend Files
- **`pom.xml`**: Maven build config & dependencies
- **`application.properties`**: Database & server config
- **`schema.sql` / `data.sql`**: Manual table creation and data insert scripts
- **`controller/`**: REST endpoints
- **`service/`**: Business logic (e.g., InventoryService)
- **`repository/`**: Data access via Spring Data JPA
- **`model/`**: Entity classes (e.g., Product, Supplier)

### Frontend Directory (Brief)
- **`package.json`**: Lists React, React Router, and other libraries
- **`src/pages/`**: Top-level pages (Dashboard, Inventory, etc.)
- **`src/components/`**: Reusable UI components
- **`src/services/`**: API calls (e.g., Axios services)
- **`App.js`**: Main application entry
- **`public/index.html`**: Single Page App entry

---

## 3. Key Dependencies

### Backend Dependencies
- **Spring Boot Starter Web** (Tomcat, REST APIs)
- **Spring Boot Starter Data JPA** (ORM/Hibernate)
- **H2** (In-memory DB)
- **Spring Boot DevTools** (Hot reloading)
- **Maven** (Build tool)

### Frontend Dependencies (Minimal)
- **React / React-DOM**
- **React Router** (if you handle routing)
- **Axios** (for API calls)
- *(Optional)* Possibly Recharts or Chart.js for analytics

---

## 4. Installation & Setup

### Prerequisites
- Java 17+ (`java -version`)
- Maven 3.8+ (`mvn -v`)
- Node.js & npm or yarn (for the frontend)

---

## 5. Running the Application (Backend)

1. Clone or download this repository.
2. Navigate to the backend folder:
   ```sh
   cd RestaurantManagementSystem/backend
   ```
3. Build and run:
   ```sh
   mvn clean install
   ```
4. The server starts on `http://localhost:8080`. Logs will show:
   ```
   H2 console available at '/h2-console'.
   Database available at 'jdbc:h2:mem:myrestaurantdb'.
   Started RestaurantManagementApplication ...
   ```

---

## 6. H2 Database Access

- Visit: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL**: `jdbc:h2:mem:myrestaurantdb`
- **User**: `sa`, **Password**: (none)
- Click **Connect** to run SQL queries.
- Tables: `PRODUCT`, `SUPPLIER`, `MENUITEM`
- Preloaded Data: From `data.sql` (e.g., “Tomatoes” in `PRODUCT`).

---

## 7. REST Endpoints

### Inventory
- `GET /api/inventory` → Lists all products
- `GET /api/inventory/{id}` → Fetch product by ID
- `POST /api/inventory` → Create product
- `PUT /api/inventory/{id}` → Update product
- `DELETE /api/inventory/{id}` → Delete product

---

## 8. Example Queries

### Create new product
```sh
curl -X POST http://localhost:8080/api/inventory \
  -H "Content-Type: application/json" \
  -d '{
        "name": "Flour",
        "quantity": 100,
        "price": 1.5
      }'
```

---

## 9. Notes & Future Steps

- **Database**: Currently in-memory H2, resets on restart.
- **Security**: No authentication yet. Could add Spring Security.
- **Error Handling**: Minimal runtime exceptions; a custom `@ControllerAdvice` would improve it.

---

## 10. Frontend Setup (Brief)

- appsmith
- ill write more later, work in progress
# Cozy Glow Inventory – Spring Boot Web App

Inventory management web application for a Candle Shop, built with Spring Boot and a simple HTML/Thymeleaf front end.  
It manages parts (wax, jars, wicks, fragrances) and products (finished candles), with validation, sample data, and a “Buy Now” flow.

This project started from a generic inventory template and was customized end-to-end for a specific retail shop.

---

## Features

- **Custom shop UI**
  - Rebranded as Candle Shop.
  - Product and part tables use real candle inventory names instead of placeholder data.
  - Clean, minimal HTML layout styled for readability.

- **Dedicated “About” page**
  - `/about` page that describes the shop and its focus on small-batch scented candles.
  - Navigation links wired up between the main inventory screen and the About page.

- **Sample inventory bootstrap**
  - On startup, the app checks if the database is empty.
  - If there are no parts or products yet, it seeds the app with:
    - 5 **parts** (e.g., jars, wax, wicks, fragrance oils).
    - 5 **products** (e.g., *Lavender Candle*, *Vanilla Candle*, *Citrus Candle*).
  - Seed logic only runs when both part *and* product tables are empty, so it does not overwrite existing data.

- **“Buy Now” product flow**
  - Each product row on the main screen has a **Buy Now** button next to Update/Delete.
  - Clicking **Buy Now**:
    - Decrements that product’s inventory by 1.
    - Does **not** change the inventory of associated parts.
    - Shows a success message if the purchase goes through.
    - Shows a failure message if there is not enough product inventory.

- **Min/Max inventory tracking**
  - Parts include **min** and **max** inventory fields.
  - Sample data sets realistic min/max values for each part.
  - Part forms (in-house and outsourced) include inputs for min, max, and inventory.
  - Persistent storage / configuration updated so new fields are saved and loaded correctly.
  - Business rule enforced: min ≤ inventory ≤ max.

- **Validation with user-friendly error messages**
  - When adding or updating a **part**:
    - Error if inventory < min.
    - Error if inventory > max.
  - When adding or updating a **product**:
    - Error if the product’s required part quantities would push any part below its min stock level.
  - Errors are displayed directly in the UI so the user can correct the values.

- **Unit tests for inventory rules**
  - `PartTest` in the test package includes unit tests that:
    - Verify parts cannot be created with inventory less than min.
    - Verify parts cannot be created with inventory greater than max.
  - Tests focus on the core business rules around minimum and maximum stock.

- **Cleaned validators**
  - Unused validator classes were removed to keep the codebase small and easier to maintain.

---

## Tech Stack

- **Language:** Java 17  
- **Framework:** Spring Boot 2.6.x  
- **View layer:** Thymeleaf + HTML templates  
- **Persistence:** Spring Data JPA with H2 (in-memory)  
- **Build tool:** Maven  

---

## Getting Started

### Prerequisites

- Java 17 installed and on your PATH  
- Maven (if you want to run with `mvn`)  

### Run the application

From the project root (where `pom.xml` lives):

```bash
# Run with Maven
mvn spring-boot:run

# or build a jar and run it
mvn clean package
java -jar target/*.jar

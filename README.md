# FinalProject - Magento Automated Testing

## 📋 Project Description

This project contains automated test cases for the e-commerce site [Magento Software Testing Board](https://magento.softwaretestingboard.com/).  
It was developed using **Java**, **Selenium WebDriver**, and **TestNG** as part of a QA automation training or evaluation project.

---

## 🛠️ Tools & Technologies

- Java 17+
- Selenium WebDriver
- TestNG
- ChromeDriver
- Maven (optional)
- Eclipse / IntelliJ IDEA

---

## 📁 Project Structure

FinalProject/
│
├── src/test/java/
│ ├── FinalProject/
│ │ ├── AppTest.java # Main test class with all test cases
│ │ └── TestData.java # Contains test data and setup configurations
│
├── screenshots/
│ ├── ProductDetails.png
│ └── ContactUsPage.png

yaml
Copy
Edit

---

## 🚀 How to Run

1. Clone or download this project.
2. Open the project in your preferred Java IDE (e.g. Eclipse, IntelliJ).
3. Make sure `chromedriver` is correctly installed and available in your system `PATH`.
4. Run the file `AppTest.java` as a **TestNG Test**.

---

## 🧪 Test Scenarios

| Test # | Scenario                            | Description                                                    |
|--------|-------------------------------------|----------------------------------------------------------------|
| 1      | GetUrl                              | Navigates to the homepage and asserts URL                      |
| 2      | User Registration                   | Registers a new user                                           |
| 3      | Sign In                             | Logs in with registered user                                   |
| 4      | Product Details                     | Views a specific product's detail page                         |
| 5      | Add Products to Cart                | Adds two random pants to the cart                              |
| 6      | View & Modify Cart                  | Edits quantity and deletes one item from cart                  |
| 7      | Proceed to Checkout                 | Asserts navigation to checkout page                            |
| 8      | Privacy Policy Accessibility        | Verifies privacy policy link in footer                         |
| 9      | Contact Us Page                     | Validates "Contact Us" page load and takes screenshot          |
| 10     | Log Out                             | Logs the user out and validates successful logout              |

---

## 📝 Notes

- Screenshots are saved under `screenshot/` directory.
- All assertions are written using TestNG's `Assert`.
- Implicit and explicit waits are used to ensure elements are interactable.
- `Random` is used to select two different products.

---

## 📧 Author

- **Husam Zyoud**  
  QA Engineer | Software Tester  
  📍 Irbid, Jordan  
  📧 [your.email@example.com]


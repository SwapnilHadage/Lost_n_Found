# 🧩 Lost and Found – Full Stack Java Web Application

A full-stack **Java web application** designed to simplify the process of managing lost and found items within a college campus.  
It allows students to report lost or found items, while staff members can review, approve, or remove entries — helping return lost items to their rightful owners efficiently.

---

## 📚 Project Overview

The idea for **Lost and Found** came from a real college scenario — items often get misplaced, and there’s no centralized system to report or recover them.  
This application bridges that gap by enabling users to upload details of found items, while staff members moderate and manage the process through a simple, intuitive interface.

---

## 🚀 Features

- 👤 **User Features**
  - Upload details of found items (title, description, image, location)
  - Browse lost and found listings
  - Report lost items for visibility

- 🧑‍💼 **Staff Features**
  - Approve or delete found item submissions
  - Post or manage items directly without needing approval
  - Update item status (returned, pending, found)

- ⚙️ **General**
  - Clean and responsive user interface
  - Local database integration using MySQL
  - Runs on Apache Tomcat local server
  - Persistent data storage and easy management

---

## 🛠️ Tech Stack

| Layer | Technology Used |
|--------|------------------|
| **Frontend** | HTML, CSS |
| **Backend** | JSP, Servlets (Java) |
| **Database** | MySQL |
| **Server** | Apache Tomcat |
| **IDE** | Eclipse / IntelliJ IDEA |

---

## ⚙️ Installation & Setup

### 1️⃣ Clone the repository
git clone https://github.com/your-username/Lost-and-Found-WebApp.git

### 2️⃣ Import into your IDE
Open Eclipse or IntelliJ IDEA
Choose Import > Existing Project
Select the cloned repository folder

### 3️⃣ Configure Tomcat Server
Add Apache Tomcat (v9 or higher)
Configure your project as a Dynamic Web Project

### 4️⃣ Set up MySQL Database
Create a new database named lostandfound
Update database credentials in your connection file (e.g., DBConnection.java)

### 5️⃣ Run the Project
Deploy the project on Tomcat
Access it locally via
👉 http://localhost:8080/LostAndFound

📸 Screenshots / Demo

(Add screenshots or a short demo video here)
Example:

Homepage

Upload Found Item Page

Staff Dashboard

Database Table View

### 🧠 Learning Highlights

Working on this project helped me strengthen:

Integration of JSP and Servlets with backend logic

Performing CRUD operations via JDBC and MySQL

Session management and request-response handling

Understanding full-stack architecture and component interaction

### Future Enhancements
📧 Email notifications for matched items
☁️ Cloud deployment (e.g., AWS / Render / Railway)
🎨 UI/UX improvements for better accessibility

 ### 🧑‍💻 Author
   Swapnil Hadage
📍 Yaswantrao Bhonsale Institute of Technology
💼 Aspiring Full Stack Java Developer

### 🏷️ License

This project is licensed under the MIT License.
You’re free to use, modify, and distribute it with proper credit.

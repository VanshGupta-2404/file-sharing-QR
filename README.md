---

# 🌐 File Sharing Web App using QR Code & Cloudinary

A modern web-based file-sharing application that lets you securely upload files to the cloud (Cloudinary) and share them globally using **QR codes** and **clickable links**. Just upload, share, and download—no signup required!

---
Live Demo: file-sharing-qr-production.up.railway.app

## 🚀 Features

* 📤 Upload files directly to **Cloudinary**
* 🔐 Files expire after a fixed time (default: 10 minutes)
* 🔑 Auto-generated **secure access key**
* 📱 Share via **QR code** (easily scannable on mobile)
* 🔗 Share via **clickable download link**
* ⚙️ Built using **Spring Boot**, **Java**, and **Thymeleaf**

---

## 📸 Demo
![image](https://github.com/user-attachments/assets/5048dcaa-83b6-4b5b-9151-fc5e1532b595)
![image](https://github.com/user-attachments/assets/0cfe6f50-4ead-4477-9874-7f9b723ca911)



---

## 🛠️ Technologies Used

* **Java** (Spring Boot)
* **Spring MVC** & **Thymeleaf**
* **Spring Data JPA**
* **Cloudinary API** for file storage
* **H2 Database** (in-memory)
* **QRGen** or **ZXing** for QR code generation (depending on your setup)

---

## 📂 Project Structure

```
file-sharing-QR/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── Controller/
│   │   │   ├── Entity/
│   │   │   ├── Repository/
│   │   │   └── Service/
│   ├── resources/
│   │   ├── templates/
│   │   │   ├── index.html
│   │   │   ├── upload.html
│   │   │   └── access.html
│   │   └── application.properties
├── pom.xml
└── README.md
```

---

## 📦 Setup & Run

### Prerequisites

* Java 11+
* Maven
* Cloudinary Account (API credentials)

### Installation Steps

1. **Clone the repo**

   ```bash
   git clone https://github.com/VanshGupta-2404/file-sharing-QR.git
   cd file-sharing-QR
   ```

2. **Configure Cloudinary**

   In `application.properties`, add:

   ```
   cloudinary.cloud_name=your_cloud_name
   cloudinary.api_key=your_api_key
   cloudinary.api_secret=your_api_secret
   ```

3. **Build & Run the app**

   ```bash
   mvn spring-boot:run
   ```

4. **Visit in Browser**

   ```
   http://localhost:8080
   ```

---

## 🔄 Workflow

1. Upload a file → stored in Cloudinary
2. App generates:

   * A unique **access key**
   * A **QR code** with download link
3. File expires after a set time (default: 10 mins)
4. Access link auto-deletes file after expiry

---

## 📱 Sharing Options

* **QR Code**: Scan with any mobile camera to download
* **Link**: Click or share via WhatsApp, email, etc.

---

## ❗Security & Expiry

* Files are temporary (configurable)
* Each upload has a unique `accessKey`
* No account required—quick and secure!

---

## 📬 Contact

For suggestions or contributions, feel free to reach out:

* Email: [guptavansh2404@gmail.com](mailto:guptavansh2404@gmail.com)
* GitHub: [@VanshGupta-2404](https://github.com/VanshGupta-2404)

---

## 📄 License

This project is open-source under the [MIT License](LICENSE).

---

package com.example.Controller;

import com.example.Service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/")
public class FileUploadController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadWithQR(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("<h2>No file uploaded.</h2>");
            }

            Map<String, String> result = cloudinaryService.uploadWithQR(file);

            String qrUrl = result.get("qrCodeUrl");
            String fileUrl = result.get("fileUrl");
            String html = "<!DOCTYPE html>\n" +
        "<html lang=\"en\">\n" +
        "<head>\n" +
        "    <meta charset=\"UTF-8\">\n" +
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    <title>File Uploaded Successfully</title>\n" +
        "    <style>\n" +
        "        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap');\n" +
        "        \n" +
        "        :root {\n" +
        "            --primary: #4361ee;\n" +
        "            --secondary: #3a0ca3;\n" +
        "            --success: #10b981;\n" +
        "            --danger: #ef4444;\n" +
        "            --background: #f9fafb;\n" +
        "            --card: #ffffff;\n" +
        "            --text: #1f2937;\n" +
        "        }\n" +
        "        \n" +
        "        * {\n" +
        "            margin: 0;\n" +
        "            padding: 0;\n" +
        "            box-sizing: border-box;\n" +
        "        }\n" +
        "        \n" +
        "        body {\n" +
        "            background: var(--background);\n" +
        "            font-family: 'Poppins', sans-serif;\n" +
        "            color: var(--text);\n" +
        "            display: flex;\n" +
        "            justify-content: center;\n" +
        "            align-items: center;\n" +
        "            min-height: 100vh;\n" +
        "            overflow-x: hidden;\n" +
        "            padding: 20px;\n" +
        "            background: linear-gradient(135deg, #4361ee 0%, #3a0ca3 100%);\n" +
        "        }\n" +
        "        \n" +
        "        .container {\n" +
        "            background: var(--card);\n" +
        "            padding: 40px;\n" +
        "            max-width: 600px;\n" +
        "            width: 100%;\n" +
        "            margin: auto;\n" +
        "            box-shadow: 0 10px 30px rgba(0,0,0,0.15);\n" +
        "            border-radius: 16px;\n" +
        "            text-align: center;\n" +
        "            animation: fadeIn 0.8s ease-out;\n" +
        "            position: relative;\n" +
        "            overflow: hidden;\n" +
        "        }\n" +
        "        \n" +
        "        .container::before {\n" +
        "            content: '';\n" +
        "            position: absolute;\n" +
        "            width: 200px;\n" +
        "            height: 200px;\n" +
        "            background: linear-gradient(135deg, rgba(67, 97, 238, 0.1), rgba(58, 12, 163, 0.1));\n" +
        "            border-radius: 50%;\n" +
        "            top: -100px;\n" +
        "            left: -100px;\n" +
        "            z-index: 0;\n" +
        "        }\n" +
        "        \n" +
        "        .container::after {\n" +
        "            content: '';\n" +
        "            position: absolute;\n" +
        "            width: 150px;\n" +
        "            height: 150px;\n" +
        "            background: linear-gradient(135deg, rgba(67, 97, 238, 0.1), rgba(58, 12, 163, 0.1));\n" +
        "            border-radius: 50%;\n" +
        "            bottom: -50px;\n" +
        "            right: -50px;\n" +
        "            z-index: 0;\n" +
        "        }\n" +
        "        \n" +
        "        .content {\n" +
        "            position: relative;\n" +
        "            z-index: 1;\n" +
        "        }\n" +
        "        \n" +
        "        h2 {\n" +
        "            color: var(--primary);\n" +
        "            margin-bottom: 20px;\n" +
        "            font-size: 28px;\n" +
        "            font-weight: 600;\n" +
        "            opacity: 0;\n" +
        "            animation: slideUp 0.5s ease-out forwards;\n" +
        "            animation-delay: 0.2s;\n" +
        "        }\n" +
        "        \n" +
        "        .qr-container {\n" +
        "            background: #f8fafc;\n" +
        "            border-radius: 16px;\n" +
        "            padding: 20px;\n" +
        "            display: inline-block;\n" +
        "            margin: 20px auto;\n" +
        "            box-shadow: 0 4px 15px rgba(0,0,0,0.05);\n" +
        "            position: relative;\n" +
        "            opacity: 0;\n" +
        "            animation: zoomIn 0.6s ease-out forwards;\n" +
        "            animation-delay: 0.4s;\n" +
        "        }\n" +
        "        \n" +
        "        .qr-container::before {\n" +
        "            content: '';\n" +
        "            position: absolute;\n" +
        "            inset: 0;\n" +
        "            border-radius: 16px;\n" +
        "            padding: 2px;\n" +
        "            background: linear-gradient(135deg, #4361ee, #3a0ca3);\n" +
        "            mask: linear-gradient(#fff 0 0) content-box, linear-gradient(#fff 0 0);\n" +
        "            -webkit-mask: linear-gradient(#fff 0 0) content-box, linear-gradient(#fff 0 0);\n" +
        "            mask-composite: exclude;\n" +
        "            -webkit-mask-composite: xor;\n" +
        "        }\n" +
        "        \n" +
        "        .qr-img {\n" +
        "            width: 350px;\n" +
        "            height: 350px;\n" +
        "            object-fit: contain;\n" +
        "            border-radius: 8px;\n" +
        "            transition: transform 0.3s;\n" +
        "        }\n" +
        "        \n" +
        "        .qr-img:hover {\n" +
        "            transform: scale(1.03);\n" +
        "        }\n" +
        "        \n" +
        "        .btn-download {\n" +
        "            margin-top: 24px;\n" +
        "            padding: 12px 30px;\n" +
        "            font-size: 16px;\n" +
        "            font-weight: 500;\n" +
        "            background: linear-gradient(135deg, var(--primary), var(--secondary));\n" +
        "            color: white;\n" +
        "            border: none;\n" +
        "            border-radius: 8px;\n" +
        "            cursor: pointer;\n" +
        "            position: relative;\n" +
        "            overflow: hidden;\n" +
        "            transition: all 0.3s;\n" +
        "            box-shadow: 0 4px 15px rgba(67, 97, 238, 0.3);\n" +
        "            opacity: 0;\n" +
        "            animation: slideUp 0.5s ease-out forwards;\n" +
        "            animation-delay: 0.6s;\n" +
        "        }\n" +
        "        \n" +
        "        .btn-download:hover {\n" +
        "            transform: translateY(-3px);\n" +
        "            box-shadow: 0 7px 20px rgba(67, 97, 238, 0.4);\n" +
        "        }\n" +
        "        \n" +
        "        .btn-download:active {\n" +
        "            transform: translateY(1px);\n" +
        "        }\n" +
        "        \n" +
        "        .btn-download::before {\n" +
        "            content: \"\";\n" +
        "            position: absolute;\n" +
        "            top: 0;\n" +
        "            left: -100%;\n" +
        "            width: 100%;\n" +
        "            height: 100%;\n" +
        "            background: linear-gradient(\n" +
        "                90deg,\n" +
        "                transparent,\n" +
        "                rgba(255, 255, 255, 0.2),\n" +
        "                transparent\n" +
        "            );\n" +
        "            transition: 0.5s;\n" +
        "        }\n" +
        "        \n" +
        "        .btn-download:hover::before {\n" +
        "            left: 100%;\n" +
        "        }\n" +
        "        \n" +
        "        .btn-download[disabled] {\n" +
        "            background: #d1d5db;\n" +
        "            cursor: not-allowed;\n" +
        "            transform: none;\n" +
        "            box-shadow: none;\n" +
        "        }\n" +
        "        \n" +
        "        .btn-download[disabled]::before {\n" +
        "            display: none;\n" +
        "        }\n" +
        "        \n" +
        "        .timer {\n" +
        "            font-size: 18px;\n" +
        "            color: var(--danger);\n" +
        "            margin-top: 20px;\n" +
        "            font-weight: 500;\n" +
        "            opacity: 0;\n" +
        "            animation: slideUp 0.5s ease-out forwards;\n" +
        "            animation-delay: 0.8s;\n" +
        "            display: flex;\n" +
        "            align-items: center;\n" +
        "            justify-content: center;\n" +
        "            gap: 8px;\n" +
        "        }\n" +
        "        \n" +
        "        .timer-icon {\n" +
        "            display: inline-block;\n" +
        "            animation: pulse 1.5s infinite;\n" +
        "        }\n" +
        "        \n" +
        "        @keyframes fadeIn {\n" +
        "            from { opacity: 0; }\n" +
        "            to { opacity: 1; }\n" +
        "        }\n" +
        "        \n" +
        "        @keyframes slideUp {\n" +
        "            from { \n" +
        "                opacity: 0;\n" +
        "                transform: translateY(20px);\n" +
        "            }\n" +
        "            to { \n" +
        "                opacity: 1;\n" +
        "                transform: translateY(0);\n" +
        "            }\n" +
        "        }\n" +
        "        \n" +
        "        @keyframes zoomIn {\n" +
        "            from {\n" +
        "                opacity: 0;\n" +
        "                transform: scale(0.95);\n" +
        "            }\n" +
        "            to {\n" +
        "                opacity: 1;\n" +
        "                transform: scale(1);\n" +
        "            }\n" +
        "        }\n" +
        "        \n" +
        "        @keyframes pulse {\n" +
        "            0% { transform: scale(1); }\n" +
        "            50% { transform: scale(1.1); }\n" +
        "            100% { transform: scale(1); }\n" +
        "        }\n" +
        "        \n" +
        "        .success-message {\n" +
        "            background: #ecfdf5;\n" +
        "            color: #047857;\n" +
        "            padding: 12px;\n" +
        "            border-radius: 8px;\n" +
        "            margin-top: 20px;\n" +
        "            display: none;\n" +
        "            align-items: center;\n" +
        "            justify-content: center;\n" +
        "            gap: 8px;\n" +
        "            animation: slideUp 0.5s ease-out;\n" +
        "        }\n" +
        "        \n" +
        "        .error-message {\n" +
        "            background: #fef2f2;\n" +
        "            color: #b91c1c;\n" +
        "            padding: 12px;\n" +
        "            border-radius: 8px;\n" +
        "            margin-top: 20px;\n" +
        "            display: none;\n" +
        "            align-items: center;\n" +
        "            justify-content: center;\n" +
        "            gap: 8px;\n" +
        "            animation: slideUp 0.5s ease-out;\n" +
        "        }\n" +
        "    </style>\n" +
        "</head>\n" +
        "<body>\n" +
        "    <div class=\"container\">\n" +
        "        <div class=\"content\">\n" +
        "            <h2>QR Code Generated!</h2>\n" +
        "            \n" +
        "            <div class=\"qr-container\">\n" +
        "                <img class=\"qr-img\" src=\"" + qrUrl + "\" alt=\"QR Code\"/>\n" +
        "            </div>\n" +
        "            \n" +
        "            <button class=\"btn-download\" id=\"downloadBtn\" onclick=\"downloadFile()\">\n" +
        "                ⬇️ Download File\n" +
        "            </button>\n" +
        "            \n" +
        "            <div class=\"timer\" id=\"countdown\">\n" +
        "                <span class=\"timer-icon\">⏱️</span>\n" +
        "                <span id=\"timer-text\">10:00</span>\n" +
        "            </div>\n" +
        "            \n" +
        "            <div class=\"success-message\" id=\"successMessage\">\n" +
        "                ✅ File download started!\n" +
        "            </div>\n" +
        "            \n" +
        "            <div class=\"error-message\" id=\"errorMessage\">\n" +
        "                ❌ Download failed. Please try again.\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "\n" +
        "    <script>\n" +
        "        let seconds = 600;\n" +
        "        const countdownEl = document.getElementById(\"timer-text\");\n" +
        "        const fileUrl = \"" + fileUrl + "\";\n" +
        "        const successMsg = document.getElementById(\"successMessage\");\n" +
        "        const errorMsg = document.getElementById(\"errorMessage\");\n" +
        "        const downloadBtn = document.getElementById(\"downloadBtn\");\n" +
        "\n" +
        "        function updateTimer() {\n" +
        "            const minutes = Math.floor(seconds / 60);\n" +
        "            const secs = seconds % 60;\n" +
        "            countdownEl.textContent = `${minutes}:${secs < 10 ? '0' : ''}${secs}`;\n" +
        "            \n" +
        "            if (seconds > 0) {\n" +
        "                seconds--;\n" +
        "                setTimeout(updateTimer, 1000);\n" +
        "            } else {\n" +
        "                countdownEl.textContent = \"Link expired\";\n" +
        "                document.querySelector(\".timer-icon\").textContent = \"⛔\";\n" +
        "                downloadBtn.disabled = true;\n" +
        "                downloadBtn.innerHTML = \"Download Expired\";\n" +
        "            }\n" +
        "        }\n" +
        "\n" +
        "        function downloadFile() {\n" +
        "            try {\n" +
        "                const a = document.createElement(\"a\");\n" +
        "                a.href = fileUrl;\n" +
        "                a.target = \"_blank\";\n" +
        "                a.rel = \"noopener noreferrer\";\n" +
        "                // Important: this forces download instead of opening in browser\n" +
        "                a.setAttribute(\"download\", \"\");\n" +
        "                \n" +
        "                document.body.appendChild(a);\n" +
        "                a.click();\n" +
        "                document.body.removeChild(a);\n" +
        "                \n" +
        "                // Show success message\n" +
        "                successMsg.style.display = \"flex\";\n" +
        "                setTimeout(() => {\n" +
        "                    successMsg.style.display = \"none\";\n" +
        "                }, 3000);\n" +
        "            } catch (error) {\n" +
        "                console.error(\"Download error:\", error);\n" +
        "                // Show error message\n" +
        "                errorMsg.style.display = \"flex\";\n" +
        "                setTimeout(() => {\n" +
        "                    errorMsg.style.display = \"none\";\n" +
        "                }, 3000);\n" +
        "            }\n" +
        "        }\n" +
        "        \n" +
        "        // Start the timer when page loads\n" +
        "        window.onload = function() {\n" +
        "            updateTimer();\n" +
        "        };\n" +
        "    </script>\n" +
        "</body>\n" +
        "</html>";
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("<h2>Error uploading file: " + e.getMessage() + "</h2>");
        }
    }

   
}

package com.example.Service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public Map<String, String> uploadWithQR(MultipartFile file) throws IOException {
        // Extract original filename and strip extension for clean public_id
        String originalName = file.getOriginalFilename();
        String fileNameWithoutExtension = originalName != null
                ? originalName.replaceAll("\\.[^.]+$", "") // remove extension
                : "uploaded_file";
        String cleanFileName = fileNameWithoutExtension.replaceAll("[^a-zA-Z0-9.-]", "_");

        // Upload the original file (force auto for pdfs, docs, images, etc.)
        Map<String, Object> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "raw",
                        "public_id", cleanFileName
                )
        );

        String fileUrl = (String) uploadResult.get("secure_url");

        try {
            // Generate QR code for the uploaded file URL
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.MARGIN, 2);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(fileUrl, BarcodeFormat.QR_CODE, 400, 400, hints);

            ByteArrayOutputStream qrOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", qrOutputStream);

            // Upload the QR code image
            String qrPublicId = "qrcode_" + cleanFileName;
            Map<String, Object> qrCodeUploadResult = cloudinary.uploader().upload(
                    qrOutputStream.toByteArray(),
                    ObjectUtils.asMap(
                            "resource_type", "image",
                            "public_id", qrPublicId
                    )
            );

            // Final response map
            Map<String, String> result = new HashMap<>();
            result.put("fileUrl", fileUrl);
            result.put("qrCodeUrl", (String) qrCodeUploadResult.get("secure_url"));
            return result;

        } catch (WriterException e) {
            throw new IOException("Failed to generate QR code", e);
        }
    }
}

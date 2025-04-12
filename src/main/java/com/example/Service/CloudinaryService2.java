// package com.example.Service;

// import com.cloudinary.Cloudinary;
// import com.example.Entity.SharedFile;
// import com.example.Repository.SharedFileRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// import java.time.LocalDateTime;
// import java.time.ZoneOffset;
// import java.util.Map;
// import java.util.UUID;

// @Service
// public class CloudinaryService2 {

//     @Autowired
//     private Cloudinary cloudinary;

//     public Cloudinary getCloudinary() {
//         return cloudinary;
//     }
//     @Autowired
//     private SharedFileRepository sharedFileRepo;

//     public String upload(MultipartFile file) {
//         try {
//             Map uploadResult = cloudinary.uploader().upload(file.getBytes(), Map.of("resource_type", "auto"));

//             SharedFile sharedFile = new SharedFile();
//             sharedFile.setFileName(file.getOriginalFilename());
//             sharedFile.setFileType(file.getContentType());
//             sharedFile.setCloudinaryPublicId((String) uploadResult.get("public_id"));

//             // ✅ Set UTC timestamps
//             sharedFile.setUploadedAt(String.valueOf(LocalDateTime.now(ZoneOffset.UTC)));
//             sharedFile.setExpiresAt(LocalDateTime.now(ZoneOffset.UTC).plusMinutes(10));

//             sharedFile.setAccessKey(UUID.randomUUID().toString());

//             sharedFileRepo.save(sharedFile);

//             return sharedFile.getAccessKey(); // Return the access key
//         } catch (Exception e) {
//             e.printStackTrace();
//             return "Upload failed: " + e.getMessage();
//         }
//     }

// }

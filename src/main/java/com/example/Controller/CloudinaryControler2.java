// package com.example.Controller;

// import com.example.Entity.SharedFile;
// import com.example.Repository.SharedFileRepository;
// import com.example.Service.CloudinaryService2;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;

// import java.time.LocalDateTime;
// import java.time.ZoneOffset;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Optional;

// @RestController
// @RequestMapping("/cloudinaryf")
// public class CloudinaryControler2 {
//     @Autowired
//     private CloudinaryService2 cloudinaryService;
//     @Autowired
//     private CloudinaryService2 cloudinaryService2;
//     @Autowired
//     private SharedFileRepository sharedFileRepo;

//     @PostMapping("/upload2")
//     public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
//         try {
//             String accessKey = cloudinaryService2.upload(file); // assuming upload returns accessKey
//             return ResponseEntity.ok(Map.of("accessKey", accessKey));
//         } catch (Exception e) {
//             return ResponseEntity.internalServerError().body("Upload failed: " + e.getMessage());
//         }
//     }

//     @GetMapping("/getByAccessKey/{accessKey}")
//     public ResponseEntity<?> getFileByAccessKey(@PathVariable String accessKey) {
//         Optional<SharedFile> optionalFile = sharedFileRepo.findByAccessKey(accessKey);

//         if (!optionalFile.isPresent()) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid access key.");
//         }

//         SharedFile sharedFile = optionalFile.get();
//         if (sharedFile.getExpiresAt().isBefore(LocalDateTime.now(ZoneOffset.UTC))) {

//             return ResponseEntity.status(HttpStatus.GONE).body("Access key has expired.");
//         }

//         String fileUrl = cloudinaryService2
//                 .getCloudinary()
//                 .url()
//                 .publicId(sharedFile.getCloudinaryPublicId())
//                 .generate();


//         Map<String, String> response = new HashMap<>();
//         response.put("fileUrl", fileUrl);

//         return ResponseEntity.ok(response);
//     }
// }
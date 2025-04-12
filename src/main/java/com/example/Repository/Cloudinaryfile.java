package com.example.Repository;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface Cloudinaryfile {


    Map<String, Object> upload(MultipartFile file);
}

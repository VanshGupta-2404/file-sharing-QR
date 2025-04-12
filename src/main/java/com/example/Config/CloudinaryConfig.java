package com.example.Config;

import com.cloudinary.Cloudinary; // ✅ Import the correct Cloudinary class
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig { // ✅ Renamed class to avoid conflict

    @Bean
    public ModelMapper model() {
        return new ModelMapper();
    }

    @Bean
    public Cloudinary getCloudinary() {
        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", "dsbvyp4qg");
        config.put("api_secret", "reiMy6Q-tw6JaaKEsAmfsMRMidc");
        config.put("api_key", "775245479423287");
        config.put("secure", true);
        return new Cloudinary(config); // ✅ Using correct Cloudinary class
    }
}

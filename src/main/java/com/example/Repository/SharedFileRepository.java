package com.example.Repository;

import com.example.Entity.SharedFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SharedFileRepository extends MongoRepository<SharedFile, String> {
    Optional<SharedFile> findByAccessKey(String accessKey);
}

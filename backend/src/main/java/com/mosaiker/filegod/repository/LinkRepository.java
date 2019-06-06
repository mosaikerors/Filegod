package com.mosaiker.filegod.repository;

import com.mosaiker.filegod.entity.Link;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LinkRepository extends MongoRepository<Link, Long> {
}
package com.mcmanuel.UrlShortener.repository;

import com.mcmanuel.UrlShortener.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UrlShortenerRepository extends JpaRepository<UrlEntity, UUID> {
    Optional<UrlEntity> findByShortCode(String shortUrl);
    Optional<UrlEntity> findByOriginalUrl(String originalUrl);
}

package com.mcmanuel.UrlShortener.service;

import com.mcmanuel.UrlShortener.entity.UrlEntity;
import com.mcmanuel.UrlShortener.exception.CodeGenerationException;
import com.mcmanuel.UrlShortener.exception.OriginalUrlNotFoundException;
import com.mcmanuel.UrlShortener.exception.ShortCodeNotFoundException;
import com.mcmanuel.UrlShortener.repository.UrlShortenerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {
    private UrlShortenerRepository urlShortenerRepo;

    public String shortenUrl(String originalUrl) {
        Optional<UrlEntity> existingUrl = urlShortenerRepo.findByOriginalUrl(originalUrl);

        if (existingUrl.isPresent()) {
            return existingUrl.get().getShortCode();
        }

        String shortUrl ;
        int count=0;
        do{
            if (count >=5) {
                throw new CodeGenerationException("Failed to generate unique short code");
            }
            shortUrl =generateString(originalUrl);
            count++;

        }
        while (urlShortenerRepo.findByShortCode(shortUrl).isPresent());

        UrlEntity createdEntity =  UrlEntity.builder()
                .originalUrl(shortUrl)
                .shortCode(originalUrl)
                .createdAt(LocalDateTime.now())
                .build();

        return urlShortenerRepo.save(createdEntity).getShortCode();
    }

    private String generateString(String url) {
        String charSet = "QWERTYUIOPASDFGHJKLZXCVBNM";
        SecureRandom random = new SecureRandom();
        int count = (int)(10+Math.random()*5);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(charSet.charAt(random.nextInt(charSet.length())));
        }
        return builder.toString();
    }

    public String getShortCode(String originalUrl) {
        UrlEntity savedUrl =urlShortenerRepo.findByShortCode(originalUrl).orElseThrow(()-> new ShortCodeNotFoundException("Invalid url"));
        return savedUrl.getShortCode();
    }

    public String getOriginalUrl(String shortCode) {
        UrlEntity savedUrl =urlShortenerRepo.findByOriginalUrl(shortCode).orElseThrow(()-> new OriginalUrlNotFoundException("Invalid url"));
        return savedUrl.getOriginalUrl();
    }
}

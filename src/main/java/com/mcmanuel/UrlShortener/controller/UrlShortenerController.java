package com.mcmanuel.UrlShortener.controller;

import com.mcmanuel.UrlShortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1/url")
public class UrlShortenerController {
    private final UrlShortenerService shortenerService;

    @PostMapping("/")
    ResponseEntity<String> shortenUrl(String url){
        String shortUrl = shortenerService.createShortUrl(url);
        if (shortUrl == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
            return new ResponseEntity<>(shortUrl,HttpStatus.OK);
    }

    @GetMapping("/short-code")
    ResponseEntity<String> getShortCode(String originalUrl){
        String shortUrl = shortenerService.getShortCode(originalUrl);
        if (shortUrl == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(shortUrl,HttpStatus.OK);
    }


    @GetMapping("/original-url")
    ResponseEntity<String> redirectUrl(String shortCode){
        String shortUrl = shortenerService.getOriginalUrl(shortCode);
        if (shortUrl == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(shortUrl,HttpStatus.OK);
    }
}

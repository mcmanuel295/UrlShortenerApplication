package com.mcmanuel.UrlShortener.controller;

import com.mcmanuel.UrlShortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/url")
public class UrlShortenerController {
    private final UrlShortenerService shortenerService;

    @PostMapping()
    ResponseEntity<String> shsortenUrl(String url){
        String shortUrl = shortenerService.shortenUrl(url);
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

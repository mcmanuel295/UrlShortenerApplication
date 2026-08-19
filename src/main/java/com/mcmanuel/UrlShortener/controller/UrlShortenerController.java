package com.mcmanuel.UrlShortener.controller;

import com.mcmanuel.UrlShortener.pojo.UrlRequest;
import com.mcmanuel.UrlShortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
@RequestMapping("/api/v1/url")
public class UrlShortenerController {
    private final UrlShortenerService shortenerService;

    @GetMapping("/")
    public String home() {
        // Points to /src/main/resources/static/index.html
        // or /src/main/resources/templates/index.html
        return "forward:/index.html";
    }

    @PostMapping("/shorten")
    ResponseEntity<String> shortenUrl(@RequestBody UrlRequest request){
        log.info("incoming request {}",request.url());

        String shortUrl = shortenerService.createShortUrl(request.url());
        System.out.println("original url is "+request.url()+" short code is "+shortUrl);
        if (shortUrl == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
            return new ResponseEntity<>(shortUrl,HttpStatus.OK);
    }

    @GetMapping("/short-code")
    ResponseEntity<String> getShortCode(@RequestBody String originalUrl){
        String shortUrl = shortenerService.getShortCode(originalUrl);
        if (shortUrl == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(shortUrl,HttpStatus.OK);
    }


    @GetMapping("/original-url")
    ResponseEntity<String> redirectUrl(@PathVariable String shortCode){
        String shortUrl = shortenerService.getOriginalUrl(shortCode);
        if (shortUrl == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(shortUrl,HttpStatus.OK);
    }
}

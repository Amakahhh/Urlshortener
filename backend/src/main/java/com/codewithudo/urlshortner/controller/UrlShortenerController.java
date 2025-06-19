package com.codewithudo.urlshortner.controller;

import com.codewithudo.urlshortner.model.UrlMapping;
import com.codewithudo.urlshortner.service.UrlShortenerService;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:10001")  // 👈 Add this line
@RestController
@RequestMapping("/api/url")
public class UrlShortenerController {
    private final UrlShortenerService urlShortenerService;

    @Autowired
    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    // Endpoint to shorten a URL
    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam String longUrl) {
        UrlMapping urlMapping = urlShortenerService.shortenUrl(longUrl);
        String shortUrl = String.valueOf(urlMapping.getShortUrl());
        // String shortUrl = String.valueOf(urlShortenerService.shortenUrl(longUrl));
        // System.out.println("Shortened URL: " + shortUrl);
        return shortUrl;
    }
    // @PostMapping("/shorten")
    // public Map<String, String> shortenUrl(@RequestParam String longUrl) {
    //     UrlMapping urlMapping = urlShortenerService.shortenUrl(longUrl);
    //     return Map.of("shortUrl", urlMapping.getShortUrl());
    // }

    // @GetMapping("/shorten")
    // public ResponseEntity<String> createShortUrl(@RequestParam String longUrl) {
    //     UrlMapping urlMapping = urlShortenerService.shortenUrl(longUrl);
    //     return ResponseEntity.ok(urlMapping.getShortUrl());
    // }


    // Endpoint to retrieve the original long URL by short code
    @GetMapping("/{shortUrl}")
    public ResponseEntity<Object> getLongUrl(@PathVariable String shortUrl) {
        String longUrl = urlShortenerService.getLongUrl(shortUrl);
        if (longUrl == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Short URL not found");
        }
        if (!longUrl.startsWith("http://") && !longUrl.startsWith("https://")) {
            longUrl = "https://" + longUrl;
        }
    
        return ResponseEntity.status(HttpStatus.FOUND)
        .location(URI.create(longUrl))
        .build();
    }
}



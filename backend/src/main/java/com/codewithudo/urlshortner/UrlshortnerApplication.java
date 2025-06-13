package com.codewithudo.urlshortner;

import com.codewithudo.urlshortner.service.UrlShortenerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class UrlshortnerApplication {

    public static void main(String[] args) {
        // Start Spring and get the application context
        ApplicationContext context = SpringApplication.run(UrlshortnerApplication.class, args);

        // Get the UrlShortenerService bean from the context
        UrlShortenerService urlShortenerService = context.getBean(UrlShortenerService.class);

        // Call the shortenUrl method with a test URL
        String shortUrl = (urlShortenerService.shortenUrl("https://example.com").getShortUrl());

        // Print the result
        System.out.println("Shortened URL: " + shortUrl);
    }

}

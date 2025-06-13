package com.codewithudo.urlshortner.service;

import com.codewithudo.urlshortner.model.UrlMapping;
import com.codewithudo.urlshortner.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UrlShortenerService {
    private final UrlMappingRepository urlMappingRepository;

    @Autowired
    public UrlShortenerService(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    public UrlMapping shortenUrl(String longUrl) {
        // Check if long URL already exists
        Optional<UrlMapping> existingMapping = urlMappingRepository.findByLongUrl(longUrl);
        if (existingMapping.isPresent()) {
            return existingMapping.get();
        }

        // Generate a short code using UUID (first 8 characters)
        String shortCode = UUID.randomUUID().toString().substring(0, 8);

        // Create a new UrlMapping and save it
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setLongUrl(longUrl);
        urlMapping.setShortUrl(shortCode);

        return urlMappingRepository.save(urlMapping);
    }

    public String getLongUrl(String shortUrl) {
        Optional<UrlMapping> mapping = urlMappingRepository.findByShortUrl(shortUrl);
        return mapping.map(UrlMapping::getLongUrl).orElse(null);
    }

}

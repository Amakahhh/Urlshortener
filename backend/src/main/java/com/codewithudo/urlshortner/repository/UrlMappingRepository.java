package com.codewithudo.urlshortner.repository;

import com.codewithudo.urlshortner.model.UrlMapping;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UrlMappingRepository extends CrudRepository<UrlMapping, String> {
    Optional<UrlMapping> findByShortUrl(String shortUrl);
    Optional<UrlMapping> findByLongUrl(String longUrl);
}

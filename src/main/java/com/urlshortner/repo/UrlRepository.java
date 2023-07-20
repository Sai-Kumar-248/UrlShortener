package com.urlshortner.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.urlshortner.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {
	
	@Query(value = "select originalurl from Url u where shorturl = ?1", nativeQuery = true)
	String findByShortUrl(String id);
	
}

package com.urlshortner.service;


import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.urlshortner.model.Url;
import com.urlshortner.repo.UrlRepository;

@Service
public class UrlService {
	
	@Autowired
	private UrlRepository urlRepository;
	
	public String getOriginalUrl(String id) {
		return urlRepository.findByShortUrl(id);
	}
	
	public Url generateShortUrl(String url) {
		System.out.println(url);
		if (!isUrlValid(url)) {
			System.out.println("URL is not valid");
			return null;
		}
		
		Url urlObject = new Url();
		urlObject.setOriginalurl(url);
		urlObject.setShorturl(getShortUrl(url));
		return urlRepository.save(urlObject);
	}
		public static String getShortUrl(String url) {
		
		String shortUrl = encodeUrl(url);
		return shortUrl;
	}

	private static String encodeUrl(String url) {
		String encodedUrl = "";
		LocalDateTime time = LocalDateTime.now();
		encodedUrl = Hashing.murmur3_32_fixed().hashString(url.concat(time.toString()), StandardCharsets.UTF_8).toString();
		return encodedUrl;
	}

	public static boolean isUrlValid(String url) {
		UrlValidator urlValidator = new UrlValidator(new String[] { "http", "https" });
		boolean result = urlValidator.isValid(url);
		return result;
	}
}

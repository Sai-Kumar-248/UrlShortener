/**
 * 
 */
package com.urlshortner.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import com.url.app.urllinkerspringreactjs.model.Url;
//import com.url.app.urllinkerspringreactjs.service.UrlService;

import com.urlshortner.model.Url;
import com.urlshortner.service.UrlService;

import jakarta.servlet.http.HttpServletResponse;
/**
 * @author Sai
 *
 */

@RestController
@RequestMapping("/h")
////@CrossOrigin(origins = "http://localhost:3306")
//@CrossOrigin("*")
public class UrlController {
	
	@Autowired
	private UrlService urlService;
	
	@PostMapping
	public Url generateShortUrl(@RequestBody String url) {
		return urlService.generateShortUrl(url);
	}
	@RequestMapping(method = RequestMethod.GET)
	@GetMapping("/get")
	public String sayHello() {
		return "hello";
		
	}
	
	@GetMapping("/{shortLink}")
	public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortLink, HttpServletResponse response)
	        throws IOException {
		response.sendRedirect(urlService.getOriginalUrl(shortLink));
		return null;
	}
}

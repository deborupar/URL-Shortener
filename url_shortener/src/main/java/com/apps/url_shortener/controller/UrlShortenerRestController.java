package com.apps.url_shortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.url_shortener.entity.Url;
import com.apps.url_shortener.service.HandleLongUrl;
import com.apps.url_shortener.service.HandleShortUrl;

@RestController
@RequestMapping("/")
public class UrlShortenerRestController {
	
	@Autowired
	HandleShortUrl handleShortUrl;
	
	@Autowired
	HandleLongUrl handleLongUrl;

	@GetMapping("/short.ly/{shortUrl}")
	public ResponseEntity<String> getLongUrl(@PathVariable String shortUrl) {
		System.out.println(" ShortURL = "+shortUrl);
		String longUrl = handleShortUrl.getLongUrl(shortUrl);
		if (shortUrl != null)
			return new ResponseEntity<>("Long URL :" + longUrl, HttpStatus.OK);
		else
			return new ResponseEntity<>("Url doesn't exists ", HttpStatus.NOT_FOUND);
	}

	@PostMapping("/shorten")
	public ResponseEntity<String> generateShortUrl(@RequestBody Url url) {
		// 1. call api to store the url in db --> get the object id --> convert it to short url
		String shortUrl = handleLongUrl.getShortUrl(url);
		
		// 2. create a response body that contains the short url and the http code --> done
		if (shortUrl != null)
			return new ResponseEntity<>("Short URL :" + shortUrl, HttpStatus.OK);
		else
			return new ResponseEntity<>("Couldn't process request ", HttpStatus.BAD_REQUEST);

	}
	

}

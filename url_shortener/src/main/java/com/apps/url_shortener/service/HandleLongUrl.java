package com.apps.url_shortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.apps.url_shortener.dao.UrlLookup;
import com.apps.url_shortener.entity.Url;
import com.apps.url_shortener.proxy.UrlLookupProxy;

@Service
public class HandleLongUrl {
	
	
	private UrlService urlService;
	private UrlConversion urlConversion;
	private UrlLookupProxy urlLookupProxy;
	
	@Autowired
	public HandleLongUrl(UrlService urlService , UrlConversion urlConversion ,UrlLookupProxy urlLookupProxy ) {
		super();
		this.urlService = urlService;
		this.urlConversion = urlConversion;
		this.urlLookupProxy = urlLookupProxy;
	}
	
	
	//1. API to call db connect api to save the long url in db and return the object id 
	public Url saveLongUrl(Url url) {
		Url savedUrl = urlService.save(url);
		return savedUrl;
		
	}
	
	//2. API to store short URL in DB and in cache
	public void saveShortUrl(Url existingUrlObj, String shortUrl) {
		//i. update the existing db object with the shorturl value;
		urlService.updateShortUrl(existingUrlObj , shortUrl);
	}
	
	
	//3. Driving API 
	public String getShortUrl(Url longUrl) {

		Url savedLongUrl = saveLongUrl(longUrl);
		String shortUrl = urlConversion.generateShortUrl(savedLongUrl.getId());
		shortUrl = "http://localhost:8080/short.ly/"+shortUrl;
		saveShortUrl(savedLongUrl , shortUrl );
		//ii. store the updated object in cache
		urlLookupProxy.saveToCache(savedLongUrl.getId() , longUrl.getLongURL());
		return shortUrl;
		
		
	}
	 

}

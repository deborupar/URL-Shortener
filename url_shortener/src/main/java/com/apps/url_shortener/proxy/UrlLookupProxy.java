package com.apps.url_shortener.proxy;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.url_shortener.dao.UrlLookup;
import com.apps.url_shortener.service.UrlLookupImpl;

@Service("proxyService")
public class UrlLookupProxy implements UrlLookup {
	
	private UrlLookupImpl urlLookup;
	HashMap<Long , String> cache;
	
	@Autowired
	public UrlLookupProxy( UrlLookupImpl urlLookup) {
		this.urlLookup = urlLookup;
		cache = new HashMap<>();
	}

	@Override
	public String getLongURL(Long objectId) {
		//1. implement caching logic here first 
		String longUrl = lookupCache(objectId);
		if(longUrl != null)
		{
			System.out.println(" Returned from cache");
			return longUrl;
		}
		else //2. if not found in cache searches in the DB
		return urlLookup.getLongURL(objectId); 
		
	}
	
	public void saveToCache(Long id , String longUrl) {
		cache.put(id, longUrl);
	}
	
	public String lookupCache(Long id) {
		if(cache.containsKey(id))
			return cache.get(id);
		else
			return null;
	}

}

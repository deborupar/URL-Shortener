package com.apps.url_shortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.apps.url_shortener.proxy.UrlLookupProxy;

@Service
public class HandleShortUrl {
	
	
	private UrlConversion urlConversion;
	@Qualifier("proxyService") private UrlLookupProxy urlLookupProxy;
	
	@Autowired
	HandleShortUrl(UrlConversion urlConversion ,  UrlLookupProxy urlLookupProxy){
		this.urlConversion = urlConversion;
		this.urlLookupProxy = urlLookupProxy;
		
		
	}
	
	
	//2. Driving api
	
	public String getLongUrl(String shortUrl) {

		Long id = urlConversion.translateShortUrl(shortUrl);
		String url = urlLookupProxy.getLongURL(id);
		return url;
		
		
	}

}

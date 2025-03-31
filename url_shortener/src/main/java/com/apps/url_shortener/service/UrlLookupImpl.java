package com.apps.url_shortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.apps.url_shortener.dao.UrlLookup;

@Service
@Qualifier("urlLookup")
public class UrlLookupImpl implements UrlLookup {
	
	@Autowired
	private UrlService urlService;
	

	@Override
	public String getLongURL(Long objectId) {
		return urlService.findById(objectId).getLongURL();
	}

}

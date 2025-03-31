package com.apps.url_shortener.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.url_shortener.entity.Url;
import com.apps.url_shortener.repository.UrlRepository;

@Service
public class UrlServiceImpl implements UrlService {
	
	private UrlRepository repoObj;
	
	
	@Autowired
	public UrlServiceImpl(UrlRepository repoObj) {
		super();
		this.repoObj = repoObj;
		
		
	}
	@Override
	public Url save(Url url) {
		return repoObj.save(url) ;
	}
	@Override
	public Url findById(Long id) {
		Optional<Url> urlFetched = repoObj.findById(id);
		Url url = null;
		if(urlFetched.isPresent())
			url = urlFetched.get();
		return url;
	}
	@Override
	public Url updateShortUrl(Url url, String shortUrl) {
		url.setShortURL(shortUrl);
		repoObj.save(url);
		return url;
	}

}

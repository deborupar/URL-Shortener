package com.apps.url_shortener.service;

import com.apps.url_shortener.entity.Url;

public interface UrlService {
	public Url save(Url url);
	public Url findById(Long id);
	public Url updateShortUrl(Url url , String shortUrl);
}

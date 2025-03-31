package com.apps.url_shortener.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@ComponentScan
@Service
public class UrlConversion {

	public String generateShortUrl(Long id) {
		char ch[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		StringBuffer sb = new StringBuffer();
		while(id > 0) {
			int val = (int)(id%62);
			System.out.println(val);
			sb.append(ch[val]);
			id/=62;
			
		}
		return sb.reverse().toString();
	}

	public Long translateShortUrl(String shortUrl) {
		
		int len = shortUrl.length();
		long id=0;
		for(int i=0;i<len;i++) {
			char c= shortUrl.charAt(i);
			if('a' <= c && c<= 'z')
				id = id * 62 + c - 'a';
			else if('A' <= c && c<= 'Z')
				id = id * 62 + c - 'A' + 26;
			else
				id = id * 62 + c - '0' + 52;
		}
		System.out.println(" id = "+id);
		return id;
	}

}

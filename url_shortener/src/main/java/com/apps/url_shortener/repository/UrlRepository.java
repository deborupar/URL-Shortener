package com.apps.url_shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.url_shortener.entity.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {

}

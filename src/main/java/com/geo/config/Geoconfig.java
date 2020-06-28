package com.geo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.maps.GeoApiContext;

/**
*
* @author MELOIFI
*
*/
@Configuration
public class Geoconfig {
	
	@Bean
	public GeoApiContext getGeoApiContext() {
		System.out.println("in context bean ..........");
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey("NAIzaSyA2QqvK-05eRL0Nob3z5Rn7VD6j5wosuL8")
			    .build();
		System.out.println("after init context bean ..........");
			return context;
	}
}

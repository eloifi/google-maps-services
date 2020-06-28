package com.geo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geo.model.Point;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
/**
*
* @author MELOIFI
*
*/
@Service
public class GeoService {

    @Autowired
    private GeoApiContext context;
	public Point getLocation(String adress) {
		System.out.println(".....adress"+adress);

		GeocodingResult[] results=null;
		try {
			results = GeocodingApi
					      .geocode(context,adress)//using param
					      .await();
		} catch (ApiException e) {
			System.out.println("............ApiException probalbly because of the api key or the wright google maps product");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("............InterruptedException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("............IOException");
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("............Other Exceptions");
			e.printStackTrace();
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(results[0]));
			return null;
	}
}

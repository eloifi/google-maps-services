package com.geo.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geo.model.Direction;
import com.geo.model.Point;
import com.geo.model.Way;
import com.geo.services.GeoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest.Waypoint;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import reactor.core.publisher.Mono;

/**
*
* @author MELOIFI
*
*/
@RestController
@RequestMapping("/api/geo")
@CrossOrigin(origins="http://localhost:4200'")							// @CrossOrigin is used to handle the request from a difference origin.
public class GeocodingRestController {
    @Autowired
    private GeoApiContext context;
    @Autowired
    private GeoService geoService;
    
    @GetMapping(value = "/adress")
    public String getAdress(){
		System.out.println(".....");

			GeocodingResult[] results=null;
			try {
				results = GeocodingApi.geocode(context,
				    "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
			} catch (ApiException e) {
				System.out.println("............ApiException probalbly because of the api key or the wright google maps product");
				e.printStackTrace();
			} catch (InterruptedException e) {
				System.out.println("............InterruptedException");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("............IOException");
				e.printStackTrace();
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(results[0]));
        return context.toString();
    }
    
    @GetMapping(value = "/directionStream",headers="Accept=application/json")
   // public List<Point> getCoordinatesFromGoogle() {
    public List<Point> getCoordinatesFromGoogle(String from, String to) {
    	Way directionInput=new Way(from,to);
    	System.out.println(directionInput.toString());
    	DirectionsResult result = DirectionsApi.newRequest(context)
                .mode(TravelMode.WALKING)
                .origin(directionInput.from())
                // .waypoints(new LatLng(-7.272732, 110.508244), new LatLng(-7.172732, 110.508244))
                .optimizeWaypoints(true)
                .destination(directionInput.to())
                .awaitIgnoreError();
    	System.out.println("..........."+result);

    	List<Waypoint> wayPoints;
    	DirectionsRoute[] routes = result.routes;
    	List<LatLng> latlongList = null;

		try {

			for (DirectionsRoute route : routes) {
				latlongList = route.overviewPolyline.decodePath();
			}
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}

		final List<Point> points = new ArrayList<>(latlongList.size());

		for (LatLng latLng : latlongList) {
			points.add(new Point(latLng.lat, latLng.lng));
		}

		return points;
	}
    
    @GetMapping(value = "/direction",headers="application/json")
     public Direction getCoordinatesFromBackend(String from, String to) {
    	Point locationFrom=geoService.getLocation(from);
    	Point locationTo=geoService.getLocation(to);
		return new Direction(locationFrom,locationTo);
    }
}

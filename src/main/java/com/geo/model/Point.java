package com.geo.model;

/**
 *
 * @author MELOIFI
 *
 */
public class Point {
	private Double lat;
	private Double lng;
	private Double alt;
	
	public Point(Double lat, Double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	public Point(Double lat, Double lng, Double alt) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.alt = alt;
	}
	public Point() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getAlt() {
		return alt;
	}
	public void setAlt(Double alt) {
		this.alt = alt;
	}
	@Override
	public String toString() {
		return "Point [lat=" + lat + ", lng=" + lng + ", alt=" + alt + "]";
	}

}
package com.geo.model;

/**
 *
 * @author MELOIFI
 *
 */
public class Way {

	private String from;
	private String to;

	public Way() {
		super();
	}

	public Way(String from, String to) {
		super();
		this.from = from;
		this.to = to;
	}

	public String from() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String to() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "Way [from=" + from + ", to=" + to + "]";
	}

}
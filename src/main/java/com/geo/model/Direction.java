package com.geo.model;

/**
 *
 * @author MELOIFI
 *
 */
public class Direction {

	private Point from;
	private Point to;

	public Direction() {
		super();
	}

	public Direction(Point from, Point to) {
		super();
		this.from = from;
		this.to = to;
	}

	public Point from() {
		return from;
	}

	public void setFrom(Point from) {
		this.from = from;
	}

	public Point to() {
		return to;
	}

	public void setTo(Point to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "DirectionOutput [from=" + from + ", to=" + to + "]";
	}


}
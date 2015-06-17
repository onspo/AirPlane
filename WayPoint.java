package net.ukr.onspo;

public class WayPoint extends GeoPoint{
	
	private double altitude;
	private double speed;
	
	public WayPoint(GeoCoordinate longitude,  GeoCoordinate latitude, double altitude,  double speed) {
		super(longitude, latitude);
		this.altitude  = altitude;
		this.speed     = speed;
	}

	public WayPoint(GeoPoint gp,  double altitude,  double speed) {
		super(gp);
		this.altitude  = altitude;
		this.speed     = speed;
	}

	public WayPoint(WayPoint wp) {
		super(wp);
		this.altitude  = wp.getAltitude();
		this.speed     = wp.getSpeed();
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double distanceAltitudeTo(WayPoint wp){
		
		return wp.getAltitude() - this.altitude;
		
	}
	
}

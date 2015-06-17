package net.ukr.onspo;

public class GeoPoint {

	private GeoCoordinate longitude;
	private GeoCoordinate latitude;
	
	public GeoPoint(GeoCoordinate longitude, GeoCoordinate latitude) {
		super();
		this.longitude = new GeoCoordinate(longitude);
		this.latitude  = new GeoCoordinate(latitude);
	}

	public GeoPoint(GeoPoint gp) {
		super();
		this.longitude = gp.getLongitude();
		this.latitude  = gp.getLatitude();
	}

	public GeoCoordinate getLatitude() {
		return new GeoCoordinate(this.latitude);
	}

	public void setLatitude(GeoCoordinate latitude) {
		this.latitude = latitude;
	}

	public GeoCoordinate getLongitude() {
		return new GeoCoordinate(this.longitude);
	}

	public void setLongitude(GeoCoordinate longitude) {
		this.longitude = longitude;
	}
	
	public double distanceTo(GeoPoint gp){
		
		return Geodezy.simpleDistance(this, gp);
		
	}
	
	public double distanceLongitudeTo(GeoPoint gp){
		
		return Geodezy.simpleLongitudeDistance(this, gp);
		
	}
	
	public double distanceLatitudeTo(GeoPoint gp){
		
		return Geodezy.simpleLatitudeDistance(this, gp);
		
	}
	
	public double azimuthTo(GeoPoint gp){
		
		return Geodezy.simpleNorthAzimuth(this, gp);
		
	}
	
	public void moveLongitudeTo(double distance){
		
		this.longitude.setGeoCoordinatesByDegree(this.longitude.getGeoCoordinatesInDegree() + distance / Geodezy.getLongitudeBase(this.latitude));
		
	}
	
	public void moveLatitudeTo(double distance){
		
		this.latitude.setGeoCoordinatesByDegree(this.latitude.getGeoCoordinatesInDegree() + distance / Geodezy.getLatitudeBase(this.longitude));
		
	}
	
}

package net.ukr.onspo;

public class GeoCoordinate {
	
	private int degree;
	private int minutes;
	private double secundes;
	
	public GeoCoordinate(int degree, int minutes, double secundes) {
		super();
		this.degree = degree;
		this.minutes = minutes;
		this.secundes = secundes;
	}

	public GeoCoordinate(double degree) {
		super();
		this.setGeoCoordinatesByDegree(degree);
	}

	public GeoCoordinate(GeoCoordinate gc) {
		super();
		this.setDegree(gc.getDegree());
		this.setMinutes(gc.getMinutes());
		this.setSecundes(gc.getSecundes());
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public double getSecundes() {
		return secundes;
	}

	public void setSecundes(double secundes) {
		this.secundes = secundes;
	}
	
	public void setGeoCoordinatesByDegree(double degree){
		this.degree   = (int) degree;
		degree -= this.degree;
		this.minutes  = (int) (degree * 60.0);
		degree -= this.minutes / 60.0;
		this.secundes = degree * 3600.0; 
	}
	
	public double getGeoCoordinatesInDegree(){
		return this.degree + this.minutes / 60.0 + this.secundes / 3600.0;
	}

	@Override
	public String toString() {
		String sf = "%2d*%2d'%4.2f''";
		return String.format(sf, degree, minutes, secundes);
	}
	
}

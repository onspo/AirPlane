package net.ukr.onspo;

public class TemporaryPoint extends WayPoint{
	
	private double azimuth;
	
	public TemporaryPoint(GeoCoordinate longitude,  GeoCoordinate latitude, double altitude,  double speed, double azimuth) {
		super(longitude, latitude, altitude, speed);
		this.azimuth = azimuth;
	}

	public TemporaryPoint(GeoPoint gp,  double altitude,  double speed, double azimuth) {
		super(gp, altitude, speed);
		this.azimuth = azimuth;
	}
	
	public TemporaryPoint(WayPoint wp,  double azimuth) {
		super(wp);
		this.azimuth = azimuth;
	}
	
	public TemporaryPoint(TemporaryPoint tp) {
		super(tp);
	}

	public double getAzimuth() {
		return azimuth;
	}

	public void setAzimuth(double azimuth) {
		this.azimuth = azimuth;
	}

	@Override
	public String toString() {
		String sf = "TP(Lng = %s Lat = %s Alt = %4.2f V = %2.1f Az = %2.1f)";
		return String.format(sf, getLongitude(), getLatitude(), getAltitude(), getSpeed(), getAzimuth());
	}
	
}

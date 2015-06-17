package net.ukr.onspo;

public class AirplaneCharacteristics {

	private double maxSpeed;
	private double acceleration;
	private double altitudeSpeed;
	private double azimuthSpeed;
	private double courseSpeed;
	private double courseAltitude;
	
	public AirplaneCharacteristics(double maxSpeed, 
			double acceleration,
			double altitudeSpeed, 
			double azimuthSpeed,
			double courseSpeed,
			double courseAltitude) {
		super();
		this.maxSpeed       = maxSpeed;
		this.acceleration   = acceleration;
		this.altitudeSpeed  = altitudeSpeed;
		this.azimuthSpeed   = azimuthSpeed;
		this.courseSpeed    = courseSpeed;
		this.courseAltitude = courseAltitude;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public double getAltitudeSpeed() {
		return altitudeSpeed;
	}

	public void setAltitudeSpeed(double altitudeSpeed) {
		this.altitudeSpeed = altitudeSpeed;
	}

	public double getAzimuthSpeed() {
		return azimuthSpeed;
	}

	public void setAzimuthSpeed(double azimuthSpeed) {
		this.azimuthSpeed = azimuthSpeed;
	}

	public double getCourseSpeed() {
		return courseSpeed;
	}

	public void setCourseSpeed(double courseSpeed) {
		this.courseSpeed = courseSpeed;
	}

	public double getCourseAltitude() {
		return courseAltitude;
	}

	public void setCourseAltitude(double courseAltitude) {
		this.courseAltitude = courseAltitude;
	}
	
}

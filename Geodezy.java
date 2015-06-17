package net.ukr.onspo;

public class Geodezy {
	
	public static double getLatitudeBase(GeoCoordinate longitude){
		return 111.11;
	}
	
	public static double getLongitudeBase(GeoCoordinate latitude){
		return 111.11 * Math.cos(latitude.getGeoCoordinatesInDegree());
	}
	
	public static double simpleLatitudeDistance(GeoPoint gp1, GeoPoint gp2){
		
		return getLatitudeBase(gp1.getLongitude()) 
	             * (  gp2.getLatitude().getGeoCoordinatesInDegree()  - gp1.getLatitude().getGeoCoordinatesInDegree()  );
		
	}
	
	public static double simpleLongitudeDistance(GeoPoint gp1, GeoPoint gp2){
		
		return getLongitudeBase(gp1.getLatitude()) 
	             * (  gp2.getLongitude().getGeoCoordinatesInDegree() - gp1.getLongitude().getGeoCoordinatesInDegree() );
		
	}
	
	public static double simpleDistance(GeoPoint gp1, GeoPoint gp2){
		
		double distLatitude  = simpleLatitudeDistance(gp1, gp2);
		double distLongitude = simpleLongitudeDistance(gp1, gp2);
		
		return Math.sqrt(distLatitude * distLatitude + distLongitude * distLongitude);

	}
	
	public static double simpleNorthAzimuth(GeoPoint gp1, GeoPoint gp2){
		
		double distLongitude = simpleLongitudeDistance(gp1, gp2);
		double distLatitude  = simpleLatitudeDistance(gp1, gp2);
		
		return northAzimuth(distLongitude, distLatitude);

	}

	public static double northAzimuth(double distLongitude, double distLatitude){
		double angle   = Math.toDegrees( Math.atan2(distLongitude, distLatitude) );
		return angle > 0 ? angle : 360 + angle;
	}
	
	public static double northToEastAzimuth(double northAximuth){
		
		return northAximuth >= 90 ? northAximuth - 90 : 270 + northAximuth;

	}

	public static double eastToNorthAzimuth(double eastAximuth){
		
		return eastAximuth < 270 ? eastAximuth + 90 : eastAximuth - 270;

	}

}

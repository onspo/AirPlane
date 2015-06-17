package net.ukr.onspo;

import java.util.ArrayList;
import java.util.List;

public class planeCalculation {
	
	public static List<TemporaryPoint> calculateCruiseRoute(AirplaneCharacteristics characteristics,
			                                                 List<WayPoint> wayPoints){
		
		List<WayPoint> newWayPoints = new ArrayList<WayPoint>();
		if(wayPoints.size()<=1){
			return calculateRoute(characteristics, wayPoints);
		}
		WayPoint wpFrom = null;
		WayPoint wpTo   = wayPoints.get(0);
		for(int i = 1; i < wayPoints.size(); i++){
			wpFrom = wpTo;
			wpTo   = wayPoints.get(i);
			addMiddleWayPoint(newWayPoints, characteristics, wpFrom, wpTo);
		}
		
		newWayPoints.add(wpTo);
		
		return calculateRoute(characteristics, newWayPoints);
		
	}
	
	private static void addMiddleWayPoint(List<WayPoint> newWayPoints, AirplaneCharacteristics characteristics, WayPoint wp1, WayPoint wp2){
		
		newWayPoints.add(wp1);
		
		double distance = wp1.distanceTo(wp2);
		if (distance < 3){
			return;
		}
		
		double longDegree = (distance - 1) * wp1.getLongitude().getGeoCoordinatesInDegree() / distance + wp2.getLongitude().getGeoCoordinatesInDegree()/ distance;
		double latDegree  = (distance - 1) * wp1.getLatitude().getGeoCoordinatesInDegree()  / distance + wp2.getLatitude().getGeoCoordinatesInDegree() / distance;
		
		newWayPoints.add(new WayPoint(new GeoCoordinate(longDegree), new GeoCoordinate(latDegree), characteristics.getCourseAltitude(), characteristics.getCourseSpeed()));
		
		longDegree = wp1.getLongitude().getGeoCoordinatesInDegree() / distance + (distance - 1) * wp2.getLongitude().getGeoCoordinatesInDegree()/ distance;
		latDegree  = wp1.getLatitude().getGeoCoordinatesInDegree()  / distance + (distance - 1) * wp2.getLatitude().getGeoCoordinatesInDegree() / distance;
		
		newWayPoints.add(new WayPoint(new GeoCoordinate(longDegree), new GeoCoordinate(latDegree), characteristics.getCourseAltitude(), characteristics.getCourseSpeed()));
		
	}

	public static List<TemporaryPoint> calculateRoute(
			AirplaneCharacteristics characteristics, List<WayPoint> wayPoints) {

		List<TemporaryPoint> points = new ArrayList<TemporaryPoint>();
		if (wayPoints.size() <= 1) {
			for (WayPoint wp : wayPoints) {
				points.add(new TemporaryPoint(wp, 0));
			}
			return points;
		}
		WayPoint wpFrom = null;
		WayPoint wpTo = wayPoints.get(0);
		double   azFrom = 0;
		double   azTo   = 0;
		for (int i = 1; i < wayPoints.size(); i++) {
			wpFrom = wpTo;
			wpTo   = wayPoints.get(i);
			azFrom = azTo;
			azTo   = wpFrom.azimuthTo(wpTo);
			buildTemporaryPoint(points, characteristics, wpFrom, azFrom, wpTo, azTo);
		}
		return points;

	}

	private static void buildTemporaryPoint(List<TemporaryPoint> points, AirplaneCharacteristics characteristics, WayPoint wp1, double azimuth1, WayPoint wp2, double azimuth2){
		
		TemporaryPoint tp = new TemporaryPoint(wp1, azimuth1);
		points.add(tp);
		
		double distance = wp1.distanceTo(wp2);
		double azimRad1 = Math.toRadians(Geodezy.northToEastAzimuth(azimuth1));
		double azimRad2 = Math.toRadians(Geodezy.northToEastAzimuth(azimuth2));
		
		int time;
		if ((Math.abs(azimuth1-azimuth2) < 1) && (Math.abs(wp1.getSpeed()-characteristics.getCourseSpeed()) < 0.001) && (Math.abs(wp1.getSpeed()-characteristics.getCourseSpeed()) < 0.001)){
			time = (int) (Math.abs(distance) / characteristics.getCourseSpeed() + 1);
		}
		else{
			time = characteristics.getMaxSpeed() == 0 ? 0 : (int) (Math.abs(distance) / characteristics.getCourseSpeed() * 2);
		}
		
		if (time==0){
			tp = new TemporaryPoint(wp2, azimuth2);
			points.add(tp);
			return;
		}
		
		double[] koef1  = new double[6];
		double[] koef2  = new double[6];
		double[] koef3  = new double[6];
		
		double time_2   = time * time;
		double time_3   = time * time_2;
		double time_4   = time * time_3;
		double time_5   = time * time_4;
		
		double distAlt  = wp1.distanceAltitudeTo(wp2);
		koef1[0]        =  wp1.getAltitude();
		koef1[1]        =  0;
		koef1[2]        =  0;
		koef1[3]        =  10 * distAlt / time_3;
		koef1[4]        = -15 * distAlt / time_4;
		koef1[5]        =   6 * distAlt / time_5;
		
		double spLng1   = wp1.getSpeed() * Math.cos(azimRad1);
		double spLng2   = wp2.getSpeed() * Math.cos(azimRad2);
		double distLng  = wp1.distanceLongitudeTo(wp2);
		koef2[0]        =  0;
		koef2[1]        =  spLng1;
		koef2[2]        =  0;
		koef2[3]        =  10 * distLng / time_3 - (6 * spLng1 + 4 * spLng2) / time_2;
		koef2[4]        = -15 * distLng / time_4 + (8 * spLng1 + 7 * spLng2) / time_3;
		koef2[5]        =   6 * distLng / time_5 - (3 * spLng1 + 3 * spLng2) / time_4;
		
		double spLat1   = - wp1.getSpeed() * Math.sin(azimRad1);
		double spLat2   = - wp2.getSpeed() * Math.sin(azimRad2);
		double distLat  = wp1.distanceLatitudeTo(wp2);
		koef3[0]        =  0;
		koef3[1]        =  spLat1;
		koef3[2]        =  0;
		koef3[3]        =  10 * distLat / time_3 - (6 * spLat1 + 4 * spLat2) / time_2;
		koef3[4]        = -15 * distLat / time_4 + (8 * spLat1 + 7 * spLat2) / time_3;
		koef3[5]        =   6 * distLat / time_5 - (3 * spLat1 + 3 * spLat2) / time_4;
		
		double[] koef4  = koefPolinomDerivate(koef1);
		double[] koef5  = koefPolinomDerivate(koef2);
		double[] koef6  = koefPolinomDerivate(koef3);
		
		double y1, y2, y3, y4, y5, y6, v, az;
		GeoPoint gp;
		
		for(int i = 1; i<time; i++){
			y1  = polinomValue(i, koef1);
			y2  = polinomValue(i, koef2);
			y3  = polinomValue(i, koef3);
			y4  = polinomValue(i, koef4);
			y5  = polinomValue(i, koef5);
			y6  = polinomValue(i, koef6);
			v   = Math.sqrt(y4*y4 + y5*y5 + y6*y6);
			az  = Geodezy.eastToNorthAzimuth(Geodezy.northAzimuth(-y6, y5));
			gp  = new GeoPoint(wp1.getLongitude(), wp1.getLatitude());
			gp.moveLongitudeTo(y2);
			gp.moveLatitudeTo(y3);
			points.add(new TemporaryPoint(gp, y1, v, az));
		}
	}
	
	private static double polinomValue(double x, double[] koef){
		double val = 0;
		int len = koef.length;
		for(int j = 0; j < len; j++){
			val = val * x + koef[len-1-j];
		}
		return val;
	}

	private static double[] koefPolinomDerivate(double[] koef){
		double[] koefD = new double[koef.length-1];
		for(int j = 1; j < koef.length; j++){
			koefD[j-1] = j * koef[j]; 
		}
		return koefD;
	}

}

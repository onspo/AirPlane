package net.ukr.onspo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		AirplaneCharacteristics ap1 = new AirplaneCharacteristics(0.013, 0.001, 0.002, 10, 0.010, 3);
		
		WayPoint wp1 = new WayPoint(new GeoCoordinate(30, 31, 25), new GeoCoordinate(50, 27, 00), 1, 0.005);
		WayPoint wp2 = new WayPoint(new GeoCoordinate(30, 39, 24), new GeoCoordinate(50, 07, 48), 2, 0.005);
		WayPoint wp3 = new WayPoint(new GeoCoordinate(30, 19, 18), new GeoCoordinate(50, 10, 39), 1.5, 0.005);
		
		ArrayList<WayPoint> wpl = new ArrayList<WayPoint>();
		wpl.add(wp1);
		wpl.add(wp2);
		wpl.add(wp3);
		
		List<TemporaryPoint> points = planeCalculation.calculateCruiseRoute(ap1, wpl);
		
		System.out.println(points);
		
		String sf1 = "%12.8f\t%12.8f\t%12.8f\t%12.8f";
		for (WayPoint wp : wpl){
			System.out.println(String.format(sf1,
					wp.getLongitude().getGeoCoordinatesInDegree(),
					wp.getLatitude().getGeoCoordinatesInDegree(),
					wp.getAltitude(),
					wp.getSpeed()*1000));
		}
		
		try (PrintWriter pw = new PrintWriter("out.txt")){
			String sf2 = "%12.8f\t%12.8f\t%12.8f\t%12.8f\t%12.8f";
			for (TemporaryPoint tp : points){
				pw.println(String.format(sf2,
						tp.getLongitude().getGeoCoordinatesInDegree(),
						tp.getLatitude().getGeoCoordinatesInDegree(),
						tp.getAltitude(),
						tp.getSpeed()*1000,
						tp.getAzimuth()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

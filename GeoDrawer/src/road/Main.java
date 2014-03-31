package road;

import java.util.ArrayList;
import polygon2DDetection.NotPolygonException;
import polygon2DDetection.Polygon;
import polygon2DDetection.PolygonDetection;
import vector2D.Position;
import vector2D.Vector;

public class Main
{
	public static void main(String[] args)
	{
		Position A = new Position(0,0);
		Position B = new Position(0,3);
		Position C = new Position(3,1.5);
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(A);
		positions.add(C);
		positions.add(B);
		
		Vector AB = new Vector(A,B);
		Vector BC = new Vector(B,C);
		Vector CA = new Vector(C,A);
		ArrayList<Vector> vectorListTriangle = new ArrayList<Vector>();
		vectorListTriangle.add(AB);
		vectorListTriangle.add(BC);
		vectorListTriangle.add(CA);
		
		ArrayList<Position> rListAB = new ArrayList<Position>();
		ArrayList<Position> rListBC = new ArrayList<Position>();
		ArrayList<Position> rListCA = new ArrayList<Position>();
		rListAB.add(A);
		rListAB.add(B);
		rListBC.add(B);
		rListBC.add(C);
		rListCA.add(C);
		rListCA.add(A);
		
		Road rAB = new Road(1,rListAB);
		Road rBC = new Road(1,rListBC);
		Road rCA = new Road(1,rListCA);
		
		ArrayList<Road> roadList = new ArrayList<Road>();
		roadList.add(rAB);
		roadList.add(rBC);
		roadList.add(rCA);
		
		Polygon triangle = null;
		try
		{
			triangle = new Polygon("Triangle ABC", vectorListTriangle);
		}
		catch (NotPolygonException e)
		{
			System.out.println("Erreur lors de la création de la forme");
		}
		
		System.out.println("Contexte : ");
		System.out.println("Points :\n"+positions.toString());
		System.out.println("Vecteurs :\n"+vectorListTriangle.toString());
		System.out.println("Forme :\n"+triangle.toString());
		System.out.println("Routes :\n"+roadList.toString());
		ArrayList<ArrayList<Position>> detectedTriangle = PolygonDetection.isTherePolygon(
				positions, triangle, 0.0);
		System.out.println("Triangle(s) détecté(s) :\n"+detectedTriangle.toString());
		ArrayList<ArrayList<Position>> cleanDetectedPolygon = RoadCheckPolygon.
				getClearedListFromBadPolygonRoad(detectedTriangle,	roadList);
		System.out.println("Liste nettoyée :\n"+cleanDetectedPolygon);
	}
}

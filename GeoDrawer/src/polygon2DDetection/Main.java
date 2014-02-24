package polygon2DDetection;

import java.util.ArrayList;
import java.util.List;

import vector2D.Position;
import vector2D.Vector;

/**
 * Testing class
 * @author Quentin
 *
 */
public class Main 
{
	public final static double APROXIMATION = 0;
	
	public static void main(String[] args)
	{
		/* Hearth :
			 * 44.912198,4.916489
			 * 44.911712,4.916039
			 * 44.911666,4.915674
			 * 44.911119,4.915717
			 * 44.91045,4.916382
			 * 44.910314,4.916811
			 * 44.909675,4.917541
			 * 44.909691,4.91812
			 * 44.909128,4.918742
			 * 44.910131,4.919364
			 * 44.910291,4.919171
			 * 44.911423,4.920008
			 * 44.912145,4.918195
			 * 44.911537,4.917927
			 * 44.912198,4.916489
		 */
		
		Position a = new Position(1,1);
		Position b = new Position(4,1);
		Position c = new Position(2,4);
		Position d = new Position(2,2);
		Position e = new Position(5,2);
		Position f = new Position(3,5);
		
		Vector v1 = new Vector(a,b);
		Vector v2 = new Vector(b,c);
		Vector v3 = new Vector(c,a);
		
		ArrayList<Vector> l1 = new ArrayList<Vector>();
		List<Position> p = new ArrayList<Position>();
		
		Polygon triangle1 = null;
		
		l1.add(v1);
		l1.add(v2);
		l1.add(v3);
		
		p.add(a);
		p.add(b);
		p.add(c);
		p.add(d);
		p.add(e);
		p.add(f);
		
		try
		{
			triangle1 = new Polygon("triangle1", l1);
		}
		catch (NotPolygonException exception)
		{
			System.out.println("Erreur création du triangle");
		}
		
		System.out.println("Forme à détecter : \n\t"+triangle1.toString());
		System.out.println("Nuage de point : \n\t"+p.toString());
		System.out.println("Résultat : \n\t"+PolygonDetection.isTherePolygon(p, triangle1, APROXIMATION).toString());
	}
}

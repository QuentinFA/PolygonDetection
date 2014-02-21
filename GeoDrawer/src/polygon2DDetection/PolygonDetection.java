package polygon2DDetection;

import java.util.ArrayList;
import java.util.List;

import vector2D.NoPositionAvailableException;
import vector2D.Position;
import vector2D.PositionProportion;
import vector2D.Vector;
import vector2D.VectorDetection;

/**
 * Class for Polygon Detection
 * @author Quentin
 *
 */
public abstract class PolygonDetection 
{
	/**
	 * Detect if some points of the given Position collection form the given polygon
	 * @param pos the Position collection
	 * @param p the given Polygon to search
	 * @param APROX the value of approximation
	 * @return a list of list of points which forms the polygon
	 */
	public static ArrayList<ArrayList<Position>> isTherePolygon(List<Position> pos, Polygon p, double APROX)
	{
		ArrayList<ArrayList<Position>> result = new ArrayList<ArrayList<Position>>();
		
		//For each point in the list
		for(int i = 0; i < pos.size(); i++)
		{
			//List of point on the vector line applied on the current Position
			ArrayList<PositionProportion> app = VectorDetection.isOnVectorLine(pos.get(i), p.getVectorList().get(0), pos, APROX);
			System.out.println("Point : "+pos.get(i).toString()+"App : "+app.toString()+
					"\tPremier vecteur : "+p.getVectorList().get(0).toString());
			
			for(int j = 0; j < app.size(); j++)
			{
				ArrayList<Position> form = new ArrayList<Position>();
				form.add(pos.get(i));
				form.add(app.get(j).getPosition());
				
				Position temp = null;
				Position current = app.get(j).getPosition();
				
				for(int vect = 1; vect < p.getVectorList().size(); vect++)
				{
					Vector currentVector = p.getVectorList().get(vect);
					System.out.println("Vecteur courant : "+currentVector.toString());
					try 
					{
						temp = VectorDetection.bestAproxPositionOf(pos,
								currentVector.multiplied(app.get(j).getProportion()).application(current),
								APROX);
						System.out.println(temp.toString());
					} 
					catch (NoPositionAvailableException e) 
					{
						System.out.println("Break;");
						break;
					}
					form.add(temp);
					current = temp;
				}
				
				if(form.get(0).equals(form.get(form.size()-1)))
				{
					form.remove(form.size()-1);
					result.add(form);
				}
			}
		}
		return result;
	}
}

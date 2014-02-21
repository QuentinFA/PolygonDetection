package vector2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for Vector Detection
 * @author Quentin
 *
 */
public abstract class VectorDetection 
{
	/**
	 * Detect Positions of the given collection which are on the vector applyVector line, applied on the Position startPosition
	 * Return also Positions which are in the radius APROX from the line
	 * Create a new PositionProportion for each Positions on the line, containing the corresponding proportion
	 * 		applied on the vector to get the point (or its �quivalent)
	 * @param v the vector to apply
	 * @param a the position on which the vector will be applied
	 * @param pos the collection of Position for searching
	 * @param APROX the value of approximation (if 0, returns only Positions on the line)
	 * @return a collection of Positions
	 */
	public static ArrayList<PositionProportion> isOnVectorLine(Position startPosition, Vector applyVector, List<Position> pos, double APROX)
	{
		ArrayList<PositionProportion> result = new ArrayList<PositionProportion>();
		
		for (int i = 0; i < pos.size(); i++)
		{
			Position posTemp = pos.get(i);
			double a = applyVector.getY();
			double b = - applyVector.getX();
			double c = applyVector.getX() * startPosition.getY() - applyVector.getY() * startPosition.getX();
			
			double dist = Math.abs(a * posTemp.getX() + b * posTemp.getY() + c) / Math.sqrt( Math.pow(a,2) + Math.pow(b,2) );
			//System.out.println("Point : "+posTemp.toString()+"\tDistance : "+dist);
			if (dist <= APROX && !startPosition.equals(posTemp))
			{
				double k = Math.sqrt((Math.pow(startPosition.getX() - posTemp.getX(), 2) 
						+ Math.pow(startPosition.getX() - posTemp.getX(), 2)) - dist*dist) / applyVector.norm();
				Vector tempForScalaire = new Vector(startPosition, posTemp);
				double scalaire = applyVector.getX()*tempForScalaire.getX() + applyVector.getY()*tempForScalaire.getY();
				if (scalaire < 0)
					k = -k;
				result.add(new PositionProportion(posTemp, k));
			}
		}
		return result;
	}
	
	public static boolean nearOfThePointByAprox(Position pToTest, Position refPosition, double APROX)
	{
		return (new Vector(pToTest, refPosition).norm() <= APROX);
	}
	
	public static Position bestAproxPositionOf(List<Position> pos, Position refPosition, double APROX) 
			throws NoPositionAvailableException
	{
		Position best = pos.get(0);
		double normBest = (new Vector(refPosition, pos.get(0))).norm();
		for(int i = 1; i < pos.size(); i++)
		{
			double tempNorm = new Vector(refPosition, pos.get(i)).norm();
			if(tempNorm < normBest)
			{
				best = pos.get(i);
				normBest = tempNorm;
			}
		}
		
		if (normBest > APROX)
			throw new NoPositionAvailableException();
		return best;
	}
}
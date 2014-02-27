package getPatternFromDB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import polygon2DDetection.Polygon;
import vector2D.Vector;

/**
 * Used to get patterns from a database (a xml file)
 *  @author martinga
 */
public abstract class PatternFromDB
{
	/**
	 * @return the ArrayList of pattern (polygon) from the database  
	 */
   public static ArrayList<Polygon> getPolygonList()
   {
   try{
	   SAXBuilder sxb = new SAXBuilder();
	   try
      {
		   // File reading
            Document xmlFile = sxb.build("dbpattern.xml");
          	Element racine = xmlFile.getRootElement();
          	
    		String patternName, position;
    		
    		ArrayList<Vector> vectorList; 
    		ArrayList<Polygon> polygonList = new ArrayList<Polygon>();
    	
    		List<Element> listPatterns = racine.getChildren("pattern");
    		Iterator<Element> patternIterator = listPatterns.iterator();
    		
    	
    		while (patternIterator.hasNext()) {
    			
    			Element current = (Element) patternIterator.next();    	
    			patternName = current.getChild("name").getText();    			
    	
    			Element vectors = current.getChild("vectors");
    			List<Element> tmp_listVector = vectors.getChildren("vector");
    	
    			Iterator<Element> j = tmp_listVector.iterator();
    			
    			vectorList = new ArrayList<Vector>();
    	
    			while (j.hasNext()) {
    				Element currentVector = (Element) j.next();
    				
    				position = currentVector.getText();
    				// Conversion string to double    	
    				vectorList.add(new Vector(Double.parseDouble(position
    						.split(",")[0]),
    						Double.parseDouble(position.split(",")[1])));
    			}
    			polygonList.add(new Polygon(patternName, vectorList));
    		}
    		System.out.println(polygonList.toString());
    		return polygonList;
              
      }
      catch(Exception e)
      {
    	  e.printStackTrace();
      }
		}
   catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
return null;
   }
}

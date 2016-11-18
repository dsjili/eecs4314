import java.io.*;
import java.util.*;

public class Comparison
{
	
	public static List<String> otherList;
	public static List<String> understandList;
	public static List<String> otherSide;
	public static List<String> understandSide;
	public static List<String> overlap;
	
	//Change names if needed to suit your files
	public static String otherFileName = "ideaDependencies.txt";
	public static String understandFileName = "contain.txt";
	public static String outputName = "Venn_IdeaVSUnderstand.txt";
	
	public static void main(String[] args) throws IOException
	{
		File other = new File(otherFileName);
		File understand = new File(understandFileName);
		otherList = new ArrayList<String>();
		otherSide = new ArrayList<String>();
		understandSide = new ArrayList<String>();
		overlap = new ArrayList<String>();
		understandList = new ArrayList<String>();
		
		FileInputStream fisI = null;
		FileInputStream fisI2 = null;
		
		BufferedInputStream bis = null;
		BufferedInputStream bis2 = null;
		
		DataInputStream dis = null;
		DataInputStream dis2 = null;
	 
		try
		{
			fisI = new FileInputStream(other);
	 
			// Here BufferedInputStream is added for fast reading.
			bis = new BufferedInputStream(fisI);
			dis = new DataInputStream(bis);
	 
			// dis.available() returns 0 if the file does not have more lines.
			while (dis.available() != 0) 
			{	
				//Reads each line in the Other file and adds it to a List
				otherList.add(dis.readLine());
			}	 
		// dispose all the resources after using them.
		fisI.close();
		bis.close();
		dis.close();
		  
		fisI2 = new FileInputStream(understand);
	 
		// Here BufferedInputStream is added for fast reading.
		bis2 = new BufferedInputStream(fisI2);
		dis2 = new DataInputStream(bis2);
		  
		while (dis2.available() != 0) 
		{ 
			//Reads each line in the Understand file, and adds to the list
			//Also removes the reference numbers at the end
			understandList.add(dis2.readLine().replaceAll(",\\d+,\\d+,\\d+", ""));	
		}	 
		// dispose all the resources after using them.
		fisI2.close();
		bis2.close();
		dis2.close();
		
		//Creates a copy of the Understand List for overlap
		List<String> oU = new ArrayList<String>(understandList); 
		
		understandList.retainAll(otherList); // OVERLAP list
		oU.removeAll(understandList); // Understand list
		otherList.removeAll(understandList); // Other list
		  
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(outputName), "utf-8"));
			  
		//Writing the lists into a new file  
		writer.write("-----------------Overlap-------------------");		  
		  
		for (String e: understandList)
		{
			writer.newLine();
			writer.write(e);		  
		}
		writer.newLine();
		writer.write("---------------------Understand -------------------");
		for (String e: oU) //Writing 
		{
			writer.newLine();
			writer.write(e);		  
		}		  
		writer.newLine();
		writer.write("-----------------------"+ otherFileName +" -------------------");
		for (String e: otherList)
		{
			writer.newLine();
			writer.write(e);		  
		}
		  
		writer.flush();
		writer.close();
		  
		} catch (FileNotFoundException e) 
		{
		  e.printStackTrace();
		} catch (IOException e) 
		{
		  e.printStackTrace();
		}
	}	
}
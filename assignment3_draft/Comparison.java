import java.io.*;
import java.util.*;

public class Comparison
{
	
	public static List<String> includeList;
	public static List<String> understandList;
	public static List<String> includeSide;
	public static List<String> understandSide;
	public static List<String> overlap;
	
	public static void main(String[] args) throws IOException
	{
		File include = new File("filename2.txt");
		File understand = new File("contain.txt");
		includeList = new ArrayList<String>();
		includeSide = new ArrayList<String>();
		understandSide = new ArrayList<String>();
		overlap = new ArrayList<String>();
		understandList = new ArrayList<String>();
		
		FileInputStream fisI = null;
		
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		FileInputStream fisI2 = null;
		
		BufferedInputStream bis2 = null;
		DataInputStream dis2 = null;
	 
		try{
		  fisI = new FileInputStream(include);
	 
		  // Here BufferedInputStream is added for fast reading.
		  bis = new BufferedInputStream(fisI);
		  dis = new DataInputStream(bis);
	 
		  // dis.available() returns 0 if the file does not have more lines.
		  while (dis.available() != 0) 
		  {
	 
		  // this statement reads the line from the file and print it to
			// the console.
			includeList.add(dis.readLine());
			
		  }	 
		  // dispose all the resources after using them.
		  fisI.close();
		  bis.close();
		  dis.close();
		  
		  fisI2 = new FileInputStream(understand);
	 
		  // Here BufferedInputStream is added for fast reading.
		  bis2 = new BufferedInputStream(fisI2);
		  dis2 = new DataInputStream(bis2);
		  boolean match = false;
		  
		  while (dis2.available() != 0) 
		  {
	 
		  // this statement reads the line from the file and print it to
			// the console.
			
			understandList.add(dis2.readLine().replaceAll(",\\d+,\\d+,\\d+", ""));	
		  }	 
		  // dispose all the resources after using them.
		  fisI2.close();
		  bis2.close();
		  dis2.close();
		  
		  List<String> oU = new ArrayList<String>(understandList);
		  understandList.retainAll(includeList); // OVERLAP list
		  oU.removeAll(understandList); // Understand list
		  includeList.removeAll(understandList); // Include list
		  
		  BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream("Venn.txt"), "utf-8"));
		  
		  writer.write("-----------------Overlap-------------------");		  
		  
		  for (String e: understandList)
		  {
			  writer.newLine();
			  writer.write(e);		  
		  }
		  writer.newLine();
		  writer.write("---------------------Understand -------------------");
		  for (String e: oU)
		  {
			  writer.newLine();
			  writer.write(e);		  
		  }		  
		  writer.newLine();
		  writer.write("-----------------------Include -------------------");
		  for (String e: includeList)
		  {
			  writer.newLine();
			  writer.write(e);		  
		  }
		  
		  writer.flush();
		  writer.close();
		  
		  //System.out.println("Overlap is: " +understandList);
		  //System.out.println("Include is: " +includeList);
		  //System.out.println("Understand is: " + oU);
		  
		 } catch (FileNotFoundException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		}
	}
	
}
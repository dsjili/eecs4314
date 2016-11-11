import java.io.*;
import java.util.*;

public class Include
{
	
	public static BufferedWriter writer;
	
	public static void searchForDatFiles(File root, List<File> datOnly, 
		List<String> outputt) {
    if(root == null || datOnly == null) return; //just for safety   
    if(root.isDirectory()) {
		//System.out.println("is a directory");
        for(File file : root.listFiles()) {
            searchForDatFiles(file, datOnly, outputt);
        }
    } else if(root.isFile() && root.getName().endsWith(".java")) {
		//System.out.println("found: " + root.getPath());
		readFile(root, outputt);
        datOnly.add(root);
		
    }
	}
	
	public static void main(String[] args) throws IOException{
		//System.out.println("Hello World!");
		String hadoop = ("C:\\Users\\Commo\\Documents\\include\\hadoop-2.7.3-src");
		File root = new File(hadoop);
		File listFile = new File("C:\\Users\\Commo\\Documents\\include\\hadoop_FileDependencies.ls.ta");
		List<String> instances = new ArrayList<String>();
		List<String> outputt = new ArrayList<String>();
		List<File> javaFiles = new ArrayList<File>();
		
			writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream("filename2.txt"), "utf-8")); 
			//writer.write("AAAA");
			//writer.newLine();
			//writer.write("BBBBB");
			
		
		importList(listFile, instances, outputt);
		//System.out.println(outputt.get(5));
		searchForDatFiles(root, javaFiles, outputt);
		writer.flush();
		writer.close();
	}
	
	public static void importList(File file, List<String> instances,
			List<String> outputt)
	{
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
	 
		try{
		  fis = new FileInputStream(file);
	 
		  // Here BufferedInputStream is added for fast reading.
		  bis = new BufferedInputStream(fis);
		  dis = new DataInputStream(bis);
	 
		  // dis.available() returns 0 if the file does not have more lines.
		  while (dis.available() != 0) {
	 
		  // this statement reads the line from the file and print it to
			// the console.
			String line = dis.readLine();
			if (line.startsWith("$INSTANCE") && line.endsWith("cFile"))
			{
				line = line.replace("$INSTANCE ", "");
				line = line.replace(" cFile", "");
				outputt.add(line);
			}
		  }
	 
		  // dispose all the resources after using them.
		  fis.close();
		  bis.close();
		  dis.close();
		  
		 } catch (FileNotFoundException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		}
	
	}
	
	public static void readFile(File file, List<String> outputt)
	{
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    DataInputStream dis = null;
 
    try{
      fis = new FileInputStream(file);
 
      // Here BufferedInputStream is added for fast reading.
      bis = new BufferedInputStream(fis);
      dis = new DataInputStream(bis);
 
      // dis.available() returns 0 if the file does not have more lines.
      while (dis.available() != 0) {
 
      // this statement reads the line from the file and print it to
        // the console.
		String line = dis.readLine();
		if (line.startsWith("import"))
		{
			String[] two;
			line = line.replace("import ", "");
			line = line.replace(".", "/");
			two = line.split(";");
			//System.out.println(two[0]);
			for(String path : outputt) 
			{
				if (path.contains(two[0] + ".java"))
				{
					String toFileName = file.getPath();
					path = path.replace("/", "\\");
					toFileName = toFileName.replace("C:\\Users\\Commo\\Documents\\include\\", "");
					writer.write(toFileName + "," + path);
					writer.newLine();
				}
			}
        }
      }
 
      // dispose all the resources after using them.
      fis.close();
      bis.close();
      dis.close();
	  
	 } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
	
	}
}

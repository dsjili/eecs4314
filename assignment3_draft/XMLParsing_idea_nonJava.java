import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParsing_idea_nonJava {

	public static void run()  throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		String fileName = new String("C:\\Users\\dzhon\\Desktop\\Adv. Software Engineering EECS4314\\assignment3\\idea_dependecy.xml");
		PrintWriter writer = new PrintWriter("idea_dependency_nonJava.txt", "UTF-8");
		
        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        	DocumentBuilder db = dbf.newDocumentBuilder();
        	Document document = db.parse(fileName);
        	NodeList users = document.getChildNodes();   
       	 
        	 for(int i = 0; i < users.getLength(); i++){
        		 Node user = users.item(i);
        		 NodeList userInfo = user.getChildNodes();
        		 //System.out.println(users.item(i).getNodeName() + ":");
        		 
                 for (int j = 0; j < userInfo.getLength(); j++) {
                     Node node = userInfo.item(j);
                     NodeList userMeta = node.getChildNodes();
                     if(!userInfo.item(j).getNodeName().equals("#text")){                    

                    	 for (int k = 0; k < userMeta.getLength(); k++){
                    		 if(userMeta.item(k).getNodeName() != "#text"){
                    			 String dependee = new String(userInfo.item(j).getAttributes().getNamedItem("path").toString());
                    			 String dependent = new String(userMeta.item(k).getAttributes().getNamedItem("path").toString());
                    			 
                    			 dependee = dependee.replace("path=", "");
                    			 dependee = dependee.replace("\"", "");
                    			 dependent = dependent.replace("path=", "");
                    			 dependent = dependent.replace("\"", "");
                    			                    			                     			 
                    			 String output = new String(dependee + "," + dependent);   
                    			 
                    			 if(!dependee.matches(".+java$") || !dependent.matches(".+java$")){
                    				 System.out.println(output);
                    				 writer.println(output);   
                    			 }
                    			                  	                   		 
                    			 }

                    	 }
                     }
                 }
        	 }
        	 
     		writer.flush();
    		writer.close();

	}
}

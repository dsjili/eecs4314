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

		//set read file and write file
		String fileName = new String("C:\\Users\\dzhon\\Desktop\\Adv. Software Engineering EECS4314\\assignment3\\idea_dependecy.xml");
		PrintWriter writer = new PrintWriter("idea_dependency_nonJava.txt", "UTF-8");
		
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(fileName);
        NodeList users = document.getChildNodes();  
        
        //start traversing nodes 
        for(int i = 0; i < users.getLength(); i++){
        	Node user = users.item(i);
        	NodeList userInfo = user.getChildNodes();
        		 
            for (int j = 0; j < userInfo.getLength(); j++) {
                   Node node = userInfo.item(j);
                   NodeList userMeta = node.getChildNodes();
                   
                   if(!userInfo.item(j).getNodeName().equals("#text")){                    
                	   		for (int k = 0; k < userMeta.getLength(); k++){
                	   			
                	   				if(userMeta.item(k).getNodeName() != "#text"){
                	   						//get dependent and dependee info from path attribute
                	   						String dependent = new String(userInfo.item(j).getAttributes().getNamedItem("path").toString());
                	   						String dependee = new String(userMeta.item(k).getAttributes().getNamedItem("path").toString());
                	   						
                	   						//formatting the output
                	   						dependent = dependent.replace("path=", "");
                	   						dependent = dependent.replace("\"", "");
                	   						dependee = dependee.replace("path=", "");
                	   						dependee = dependee.replace("\"", "");
                    			                    			                     			 
                	   						String output = new String(dependent + "," + dependee);   
                	   								
                	   								//output only when it contains non .java file (either dependent or dependee)
                	   								if(!dependent.matches(".+java$") || !dependee.matches(".+java$")){
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

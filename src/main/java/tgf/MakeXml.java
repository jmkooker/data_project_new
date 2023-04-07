package tgf;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
    


public class MakeXml {

    final private ArrayList<MyPerson> MyPeople;
    final private String MyPath;

    public MakeXml(ArrayList<MyPerson> MyPeople, String MyPath)
    {
        this.MyPeople = MyPeople;
        this.MyPath = MyPath;


    }

    public ArrayList<MyPerson> getPeople()
    {
        return this.MyPeople;
    }

    public String getPath()
    {
        return this.MyPath;
    }

    //Method to Create an XML file using DocumentBuilderFactory.
    public static void toXml(ArrayList<MyPerson> People, String text) throws TransformerConfigurationException
    {
        

        try {

            //ArrayList<MyPerson> People = this.MyPeople;
            //String text = this.MyPath;    
            //Creates DocumentBuilderFactory and DocumentBuilder.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            
            //Creates root element for the document.
            Element root = doc.createElement("People");
            doc.appendChild(root);

            //Creating the values to go into XML.
            String Name = "";
            String Company = "";
            String MyDate = "";
            String MyColor = "";
            int id = 1;
            //Loops through the ArrayList<MyPerson> to extract all of the values that will go into XML.
            for(MyPerson x : People)
            {
                Name = x.getName();
                Company = x.getCompany();
                MyDate = x.getDateString(x.getDate());
                MyColor = x.colortoString(x.getColor());
                //Adds elements to the XML with method createUser.
                //createUser uses a method called createUserElement.
                //Appends to root.
                root.appendChild(createUser(doc, Integer.toString(id) ,Name, Company, MyDate, MyColor));
                id++;

            }

            //Creates the XML file 
            //Transforms the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            
            
            File file = new File(text);
            String fileSeparator = file.separator;
            Path path = Paths.get(text);
            String filepath = path.getParent().toString() + fileSeparator + "test.xml";
            
            
            
            
            
            /*
            String OS = System.getProperty("os.name").toLowerCase();          
            if(OS.contains("win"))
            {
                text = text.substring(0,text.lastIndexOf("\\"));
            }
            else if(OS.contains("nix") || OS.contains("nux") || OS.contains("aix"))
            {
                text = text.substring(0,text.lastIndexOf("/"));
            }
            */


            StreamResult streamResult = new StreamResult(new File(filepath));

            transformer.transform(domSource, streamResult);
 
            System.out.println("Done creating XML File");

            
        } catch (ParserConfigurationException | TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }







    //Creates a user with all values to be written to XML.
    //Uses createUserElement to create individual elements for the XML.
    private static Node createUser(Document doc, String id, String Name, String Company, String Date, String Color) {

        Element user = doc.createElement("user");

        user.setAttribute("id", id);
        user.appendChild(createUserElement(doc, "Name", Name));
        user.appendChild(createUserElement(doc, "Company", Company));
        user.appendChild(createUserElement(doc, "Date", Date));
        user.appendChild(createUserElement(doc, "Color", Color));

        return user;
    }

    private static Node createUserElement(Document doc, String name, String value) {

        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));

        return node;
    }





}

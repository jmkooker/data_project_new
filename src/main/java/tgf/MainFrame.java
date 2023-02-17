package tgf;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class MainFrame extends JFrame {

    private static Read_file read = new Read_file();
    private static JFrame mainPanel = new JFrame("Test");
    private static JButton b1 = new JButton("...");
    private static JButton b2 = new JButton("Get Data");
    private static JTextField t1=new JTextField("Click '...' to get file then 'Get Data' to retrieve file."); 
    static JRadioButton r1=new JRadioButton("Person");    
    static JRadioButton r2=new JRadioButton("Company");   
    static JRadioButton r3=new JRadioButton("Color");    
    static JRadioButton r4=new JRadioButton("Date");  
    private static ButtonGroup bg=new ButtonGroup();
    private static JTextArea ta = new JTextArea();
    private static JScrollPane sp = new JScrollPane(ta);


    public static void initialize(){
        //Creates Gui, buttons, and entries.
        //Scroll bar does not work.
 
        //Creates bounds and adds everything to the mainPanel

        r1.setBounds(870,50,100,30);    
        r2.setBounds(870,80,100,30);
        r3.setBounds(870,110,100,30);
        r4.setBounds(870,140,100,30);    
            
        bg.add(r1);
        bg.add(r2);  
        bg.add(r3);
        bg.add(r4);
        mainPanel.add(r1);
        mainPanel.add(r2);
        mainPanel.add(r3);
        mainPanel.add(r4);
        r1.setSelected(true);
        
        
        ta.setRows(4);
        ta.setColumns(38);
        
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        mainPanel.getContentPane().add(sp);
        sp.setBounds(10,50, 850,700);
        //ta.setBounds();
        b1.setBounds(820,10,35,30);  
        mainPanel.add(b1);
        b2.setBounds(870,10,100,30);  
        mainPanel.add(b2);
        t1.setBounds(10,10, 800,30);  
        mainPanel.add(t1);
        
        //Gives buttons actions

        setUpButtonDirectory(b1);
        getData(b2);



        //Creates Gui

        mainPanel.setName("Test");
        mainPanel.setSize(1000,800);
        mainPanel.setLayout(null); 
        mainPanel.setVisible(true);
        

  
 


    }

   
    //Creates the action for the "..." button which allows the user to pick a file to get data from.

    public static void setUpButtonDirectory(JButton button)
    {
        
        ActionListener buttonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    JFileChooser chooser = new JFileChooser();

            

                    int r = chooser.showSaveDialog(null);
 
                    // if the user selects a file
                    if (r == JFileChooser.APPROVE_OPTION)
                    {

                        String text = chooser.getSelectedFile().getAbsolutePath();
                        t1.setText(text);
                    }
                    
                    
                
            }

        };
        button.addActionListener(buttonListener);
    }

    //Controls the "Get Data" button

    public static void getData(JButton button)
    {

        ActionListener buttonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("getData Start");
                //ta.setText(" ");
                //Gets text from Jtextfield field.
                String text = t1.getText();
                //Sets the type to be sorted
                String type = " ";
                if(r1.isSelected()){    
                    type = "Name";
                }    
                else if(r2.isSelected()){    
                    type = "Company";    
                }
                else if(r3.isSelected()){    
                    type = "Color";    
                }
                else{    
                    type = "Date";    
                }           
                //Checks if the file selected is a file    
                File f = new File(text);
                if(f.isFile()){

                
                //Reads the data in from the file by using read_file from Read_file.java.
                read.read_file(text);
                //Gets the Arraylist Person that was read in from Read_file.java.
                ArrayList<Person> People = read.getPeopleArray();

                //Sorts the array based on the sort "type".
                Person sort = new Person(null,null,null,null);
                People = sort.sortPeopleArray(type ,People);

                //Appends the text from the array to the textarea.
                for(Person x : People)
                {   
                    ta.append(x.getInformation());

                }
                //Runs toxml to create an xml file from the ArrayList<Person> People.
                try {

                    Make_xml run = new Make_xml(People, text);
                    run.toxml(run.getPeople(),run.getPath());
                } catch (TransformerConfigurationException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
            else{
                System.out.println("Pick a file that can be read.");
            }
                    
                
            }

        };
        button.addActionListener(buttonListener);
    }











}













    




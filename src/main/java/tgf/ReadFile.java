package tgf;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner; // Import the Scanner class to read text files


import tgf.Person.compareName;
import tgf.Person.compareCompany;
import tgf.Person.compareDate;
import tgf.Person.compareColor;

import java.awt.*;
import java.awt.Color;
import java.lang.reflect.Field;

public class ReadFile
{

    //Create all values that will be used in the class.
    private ArrayList<Person> People = new ArrayList<Person>();
    private Date myDate;
    private String name;
    private String company;
    private Color myColor;
    String splitVariable[] = {"test","test"};





    public void readfile(String text)
    {
        //Clears the People ArrayList if button is hit again.
        //Creates the Scanner object
        People = new ArrayList<Person>();
        String nextLine = "";
        String splitVariable[] = {" ", " "};
        File myObj = new File(text);
        Scanner sc;
        try {
            sc = new Scanner(myObj);
            //Loops through file skipping empty lines.
            while(sc.hasNext())
            {
                nextLine = sc.nextLine();
                if(!nextLine.isEmpty())
                {   
                    //Gets rid of ":" delimiter and gets rid of white space.
                    splitVariable = nextLine.split(":");
                    splitVariable[0] = splitVariable[0].trim();
                    splitVariable[1] = splitVariable[1].trim();
                    System.out.println(splitVariable[0].trim() + " : " + splitVariable[1].trim());

                    //Converts extracts String values for Month, Day, and Year then converts to Date type.
                    //Sets the MyDate value.
                    if( splitVariable[0].equals("Date"))
                    {
                        String Month = splitVariable[1].substring(0,2);
                        String Day = splitVariable[1].substring(3,5);
                        String Year = "20" + splitVariable[1].substring(6);
                        String dateToConvert = Year +  Month  + Day;
                        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
                        myDate = originalFormat.parse(dateToConvert);

            
                    }
                    //Sets the Name value.
                    else if(splitVariable[0].equals("Name"))
                    {
                        
                        name = splitVariable[1];
                    }
                    //Sets the Company value.
                    else if(splitVariable[0].equals("Company"))
                    {
                        company = splitVariable[1];
                    }
                    //Convets indigo to blue and violet to magenta since they do not exist in Color class.
                    //Converts String Color value to Color value.
                    //Sets the MyColor value.
                    else if(splitVariable[0].equals("Color"))
                    {
                        if(splitVariable[1].equals("indigo"))
                        {
                            splitVariable[1] = "blue";
                        }
                        if(splitVariable[1].equals("violet"))
                        {
                            splitVariable[1] = "magenta";
                        }                   
                        Field field = Class.forName("java.awt.Color").getField(splitVariable[1]);
                        myColor = (Color)field.get(null);
    
                    }
    

                }
               else if(splitVariable[0].equals("Color"))
               {
                    //Adds Person to ArrayList<Person> People
                    
                    People.add(new Person(name, company, myColor, myDate));
                    splitVariable[0] = " ";
                    splitVariable[0] = " ";
                    System.out.println("Person Added");
    
                }
    
            }

            

        } catch (FileNotFoundException | ParseException | NoSuchFieldException | SecurityException | ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }






    }


    public ArrayList<Person> getPeopleArray()
    {   
        //Returns ArrayList<Person> People
        return People;
    }







}

    














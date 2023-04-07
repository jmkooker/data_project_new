/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tgf;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import org.immutables.value.Value;

/**
 *
 * @author jkooker
 */
    @Value.Immutable
public abstract class MyPerson {


    @Value.Parameter
    abstract String getName();

    @Value.Parameter
    abstract String getCompany();
    
    
     @Value.Parameter
    abstract Color getColor();

    @Value.Parameter
    abstract Date getDate();   
    

    public MyPerson()
    {
    
    }


        

    //converts Color type to String Color name.
    public String colortoString(Color x)
    {
        
        int theColor = x.getRGB();
        String colorName = " ";
 

        if(theColor == Color.red.getRGB())
        {
            colorName = "red";
        }
        else if(theColor == Color.black.getRGB())
        {
            colorName = "black";
        }
        else if(theColor == Color.yellow.getRGB())
        {
            colorName = "yellow";
        }
        else if(theColor == Color.pink.getRGB())
        {
            colorName = "pink";
        }
        else if(theColor == Color.orange.getRGB())
        {
            colorName = "orange";
        }
        else if(theColor == Color.magenta.getRGB())
        {
            colorName = "magenta";
        }
        else if(theColor == Color.blue.getRGB())
        {
            colorName = "blue";
        }
        else if(theColor == Color.green.getRGB())
        {
            colorName = "green";
        }
        else if(theColor == Color.white.getRGB())
        {
            colorName = "white";
        }
        else if(theColor == Color.cyan.getRGB())
        {
            colorName = "cyan";
        }
        else if(theColor == Color.darkGray.getRGB())
        {
            colorName = "darkGray";
        }
        else if(theColor == Color.gray.getRGB())
        {
            colorName = "gray";
        }
        else if(theColor == Color.lightGray.getRGB())
        {
            colorName = "lightGray";
        }




        return colorName;
    }

    

    //getDateString returns the String Date in yyyy/MM/dd format.
    public String getDateString(Date x)
    {   
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return formatter.format(x);
    }




    //Compares name for person.
    public class compareName implements Comparator<MyPerson>
    {
    public int compare(MyPerson o1, MyPerson o2) 
    {
        return o1.getName().compareTo(o2.getName());
    }
    }
    //Compares Company for person.
    public class compareCompany implements Comparator<MyPerson>
    {
    public int compare(MyPerson o1, MyPerson o2) 
    {
        return o1.getCompany().compareTo(o2.getCompany());
    }
    }    
     //Compares Color for person.
    public class compareColor implements Comparator<MyPerson>
    {
    public int compare(MyPerson o1, MyPerson o2) 
    {
        //String color1 = Integer.toString(o1.getColor().getRGB());
        //String color2 = Integer.toString(o2.getColor().getRGB());
        //return color1.compareTo(color2);

        return o1.colortoString(o1.getColor()).compareTo(o2.colortoString(o2.getColor()));
    }
    } 
    
    //Compares Date for person.
    public class compareDate implements Comparator<MyPerson>
    {
    public int compare(MyPerson o1, MyPerson o2) 
    {
        return o1.getDate().compareTo(o2.getDate());
    }
    } 
    
    //Sorts an ArrayList<Person> by the type selected.
    public ArrayList<MyPerson> sortPeopleArray(String type, ArrayList<MyPerson> People)

    {   System.out.println("getPeopleArray run");
        if(type == "Name")
        {
            Collections.sort(People,  new compareName());
        }
        else if(type == "Company")
        {
            Collections.sort(People,  new compareCompany());
        }
        else if(type == "Date")
        {
            Collections.sort(People,  new compareDate());
        }
        else
        {
            Collections.sort(People,  new compareColor());           
        }




        return People;
    }


       
    
}

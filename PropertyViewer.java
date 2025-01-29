import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;  
//sfsf

/**
 * This project implements a simple application. Properties from a fixed
 * file of airbnb listings can be displayed. 
 * @author Iffat Siddick
 * @version 8
 * Going to commit/push all work to personal github repository
 */
public class PropertyViewer
{    
    // the Graphical User Interface that will display information to the user
    //allows access Portfolio's methods
    private PropertyViewerGUI gui;
    //allows access to the list of properties
    //and methods of Portfolio
    private Portfolio portfolio;
    //keeps private count of which property is being displayed from the list
    private int index;
    //int instance variable count automatically initialised to 0 when complied
    //keeps count value consistant throughout program
    private int count;
    //allows the average to be accessed by any method in this class
    //so can be updated to current average whenever a new property is viewed
    private int average;
    //allows the pricing to be updated throughut the program 
    private int pricing;
    
    /**
     * Create a PropertyViewer and display its GUI on screen.
     */
    public PropertyViewer()
    {   
        //creates the gui
        gui = new PropertyViewerGUI(this);
        //creates the list object from the given file
        portfolio = new Portfolio("airbnb-london.csv");
        //displays first properties details as soon as Property Viewer is run
        displayFirstProperty();
    }

    /**
     * displays details of first property in the list
     */
    public void displayFirstProperty()
    {   
        Property property;
        //property now holds the details of the first property in the list of index value 0
        property = portfolio.getProperty(0);
        index = 0;
        gui.showProperty(property);
        gui.showID(property);
        //the count is updated to show another property has been viewed
        numberOfPropertiesViewed();
        //average of property's viewed prices is updated
        averagePropertyPrice(property.getPrice());
    }
    
    /**
     * changes status of favourite for current property
     * and checks if the updated status needs to be displayed
     */
    public void toggleFavourite()
    {   
        Property toggle;
        toggle = portfolio.getProperty(index);
        //toggle uses the Property method to change the status of the property
        toggle.toggleFavourite();
        //internal method call to check if the favourite status of the 
        //method needs to be displayed
        showFavourite(); 
    }
    
    /**
     * checks if favourite status of current property needs to be displayed
     * only true of the property has been favourited
     */
    public void showFavourite()
    {
        Property property;
        //holds the current property the gui is displaying
        property = portfolio.getProperty(index);
        //accesses the PropertyViewerGUI method to check if favourite status
        //needs to be displayed. If so it will be displayed
        gui.showFavourite(property);
    }
    
    /**
     *this displays the nexts property's details when the next button is clicked
     */
    public void nextProperty()
    {
        //holds the value of the total listings in the properties list
        int maxLength = portfolio.numberOfProperties();
        Property property;
        //if the current property is the last listing, the index will be set to 0
        //to show the first property in the list
        if (index == (maxLength - 1))
        {
            index = 0;
            property = portfolio.getProperty(index);
        }
        //if the index is not at the end of the list the index will be increased by 1
        else
        {
            property = portfolio.getProperty(index + 1);
            index = index + 1;
        }
        //the next listings details are displayed
        gui.showProperty(property);
        //the next listings id is displayed
        gui.showID(property);
        //updates count as another property has been viewed
        numberOfPropertiesViewed();
        //updates average of property's viewed prices
        averagePropertyPrice(property.getPrice());
    }

    /**
     * diplays the pervious listing in the list when the previous button is clicked
     */
    public void previousProperty()
    {
        //holds the total amount of listings in the list
        int maxLength = portfolio.numberOfProperties();
        Property property;
        //if the index value is 0, so the first item in the list is being displayed,
        //the index is set to the index value of the last listing in the list
        if (index == 0)
        {
            index = maxLength - 1;
            property = portfolio.getProperty(index);
        }
        else
        //if the index is any other value is is decreased by 1 and 
        //property holds the details of the previous listing
        {
            property = portfolio.getProperty(index - 1);
            index = index - 1;
        }
        //the listing details held by property is displayed
        gui.showProperty(property);
        //the id of that property is displayed
        gui.showID(property);
        //updates count as another property has been viewed
        numberOfPropertiesViewed();
        //updates average of property's viewed prices
        averagePropertyPrice(property.getPrice());
    }
    
    //----- methods for challenge tasks -----
    
    /**
     * This method opens the system's default internet browser
     * The Google maps page shows the current properties location on the map.
     */
    public void viewMap() throws Exception
    {
        Property property;
        property = portfolio.getProperty(index);
        double latitude = property.getLatitude();
        double longitude = property.getLongitude();
        //creates url that will display property on google maps
        URI uri = new URI("https://www.google.com/maps/place/" + latitude + "," + longitude);
        java.awt.Desktop.getDesktop().browse(uri); 
    }
    
    /**
     * keeps track of how many properties have been viewed
     */
    public void numberOfPropertiesViewed()
    {
        //count is incremented by 1 when another property has been viewed
        count += 1;
    }
    /**
     * returns the amout of properties viewed
     */
    public int getNumberOfPropertiesViewed()
    {   
        //count is returned to the user, showing the amount of properties viewed
        return count;
    }
    
    /**
     * keeps track of the current average price of the total properties viewed
     */
    public void averagePropertyPrice(int price)
    {   
        //total holds the amount of properties viewed
        int total = getNumberOfPropertiesViewed();
        //running total of property prices
        //keeps average updated to current value
        pricing = pricing + price;
        //average calculated
        average = pricing/total;
    }
    
    /**
     * returns average to user
     */
    public int getAveragePrice()
    {
        return average;
    }
    
    /**
     * shows statistics of amount of properties viewed
     * and average price of these properties
     * in a new window
     * by calling another class
     */
    public void showStatistics()
    {
        newFrame window; 
        window = new newFrame(this);
    }
}

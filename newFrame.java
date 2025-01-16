import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * New frame to display statistics
 *
 * @Iffat Siddick
 * @version 3
 */
public class newFrame
{
    private JFrame frame;
    private JPanel statisticsPanel;
    private boolean fixedSize;

    private PropertyViewer viewer;
    
    private JTextField viewsLabel;
    private JTextField avPriceLabel;

    /**
     * creates a new window and displays statistics on screen.
     */
    public newFrame(PropertyViewer viewer)
    {   
        //PropertyViewer varaible now holds current PropertyViewer object
        this.viewer = viewer;
        fixedSize = false;
        makeFrame();
        setPropertyViewSize(400, 250);
        showStatistics();
    }

    /**
     * sets size of window
     */
    public void setPropertyViewSize(int width, int height)
    {
        statisticsPanel.setPreferredSize(new Dimension(width, height));
        frame.pack();
        fixedSize = true;
    }
    
    /**
     * displays total views and average price
     * by accessing the methods from PropertyViewer that pass the statistics back
     * and sets these as strings in the propertyPanel
     */
    public void showStatistics()
    {
        viewsLabel.setText("" + viewer.getNumberOfPropertiesViewed());
        avPriceLabel.setText("" + viewer.getAveragePrice());
    }
    
    /**
     * creates frame, sets layout and creates labels for total views and average price
     * Also makes frame visible to user
     */
    public void makeFrame()
    {
        frame = new JFrame("View Statistics Application");
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
        
        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(6, 6));

        // Create the property pane in the center
        statisticsPanel = new JPanel();
        statisticsPanel.setLayout(new GridLayout(6,2));
        
        //label for total views created
        statisticsPanel.add(new JLabel("Number of views: "));
        viewsLabel = new JTextField("default");
        viewsLabel.setEditable(false);
        statisticsPanel.add(viewsLabel);
        
        //label for average prive of properties viewed created
        statisticsPanel.add(new JLabel("Average price: "));
        avPriceLabel = new JTextField("default");
        avPriceLabel.setEditable(false);
        statisticsPanel.add(avPriceLabel);
        
        statisticsPanel.setBorder(new EtchedBorder());
        contentPane.add(statisticsPanel, BorderLayout.CENTER);
        
        frame.pack();
        
        frame.setVisible(true);
    }
    
}

package asgn2GUIs;
import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.text.DefaultCaret;

import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;
import asgn2Restaurant.PizzaRestaurant;
import java.time.LocalTime;
import asgn2Exceptions.*;

import java.awt.*;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 * This class is the graphical user interface for the rest of the system.
 * Currently it is a 租ummy� class which extends JFrame and implements Runnable and ActionLister.
 * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to
 * interact with the rest of the system. You may choose to implement this class as you like, including changing
 * its class signature � as long as it  maintains its core responsibility of acting as a GUI for the rest of the system.
 * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 *
 *
 * @author n9667261 Josh Arrowsmith and n9450106 Cameron Short
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {

    private PizzaRestaurant restaurant = new PizzaRestaurant();
    private JButton logButton = new JButton("Select Log File");
    private JButton resetButton = new JButton("RESET");
    private JButton costButton = new JButton("Show Total Cost");
    private JButton distButton = new JButton("Show Total Distance");
    private JLabel Title = new JLabel("PIZZA");
    private JLabel totalCost = new JLabel();
    private JLabel totalDist = new JLabel();
    private JPanel titlePanel = new JPanel();
    private JPanel operationPanel = new JPanel();
    private JPanel valuesPanel = new JPanel();
    private JFileChooser logFile = new JFileChooser(new File("logs"));
    DefaultTableModel pizzaModel;
    DefaultTableModel custModel;
    JTable pizzaTable;
    JTable customerTable;
    JScrollPane jsp;
    JScrollPane jsc;



    /**
     * Creates a new Pizza GUI with the specified title
     * @param title - The title for the supertype JFrame
     */
    public PizzaGUI(String title) throws LogHandlerException, PizzaException, CustomerException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font titleFont = new Font("Serif", Font.BOLD, 25);
        this.setSize(1000, 700);
        
        //ContentsPane Setup
        getContentPane().setLayout(null);
        titlePanel.setBounds(0, 0, 1000, 100);
        operationPanel.setBounds(0, 100, 1000, 400);
        valuesPanel.setBounds(375, 500, 300, 200);  
        
        //TitlePanel setup
        getContentPane().add(titlePanel);
        Title.setFont(titleFont);
        Title.setForeground(Color.RED);
        titlePanel.add(Title);
        
        //OperationsPanel Setup
        getContentPane().add(operationPanel);
        operationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        operationPanel.add(logButton);
        logFile.setDialogTitle("Select log file to read");
        logFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        logReadActivity(logButton);
        resetTable(resetButton);   
        
        //ValuesPanel Setup
        getContentPane().add(valuesPanel);
        
        costTextSetup(costButton);
        distTextSetup(distButton);
    }


    /**
     * Sets up an ActionListener for a button. The action listener opens up a 
     * window which lets a user select a log file and then generates tables from those files.
     * @param button - The button which the ActionListener is setup for.
     * @throws PizzaException 
     * @throws CustomerException
     * @throws LogHandlerException
     */
    private void logReadActivity(JButton button) throws PizzaException, CustomerException, LogHandlerException {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	logFile.showOpenDialog(null);
                	restaurant.processLog(String.valueOf(logFile.getSelectedFile().getAbsolutePath()));
                	tableSetup();
                } catch (Exception e1) {
                }
            }
        });
    }
    
    /**
     * Adds total cost to value panel 
     * @param button- The button which the ActionListener is setup for.
     */
    private void costTextSetup(JButton button){
    	button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		valuesPanel.add(totalCost);
        		double cost = CalculateProfit();
        		JOptionPane.showMessageDialog(null, " $" + cost);
        	}
        });
    }
    /**
     * Add total distance to value panel
     * @param button- The button which the ActionListener is setup for.
     */
    private void distTextSetup(JButton button){
    	button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		valuesPanel.add(totalDist);
            	double dist = CalculateDistance();
            	DecimalFormat df = new DecimalFormat("#.##");
            	String distText = df.format(dist);
            	JOptionPane.showMessageDialog(null, distText + " Blocks");
        	}
        });
    }


    /**
     * Makes heading for each tables then it loops though
     * restaurant classes and put the results in a array then displays in JTable inside 
     * a JScrollPane then shows costButton and distButton
     * @throws PizzaException
     * @throws CustomerException
     * @throws LogHandlerException
     */
	private void tableSetup() throws PizzaException, CustomerException, LogHandlerException {
        repaint();
        String[] custHeader = {
                "Customer Name",
                "Mobile Number",
                "Customer Type",
                "X,Y position",
                "Delivery Distance (Blocks)"
        };
        
        String[] pizzaHeader = {
            "Pizza Type",
            "Quantity",
            "Order Price ($)",
            "Order Cost ($)",
            "Order Profit ($)",
        };
        
        
        Object[][] pizzaData = new Object[restaurant.getNumPizzaOrders()][5];
        Object[][] custData = new Object[restaurant.getNumCustomerOrders()][5];

        for (int pizzaIndex = 0; pizzaIndex < restaurant.getNumPizzaOrders(); pizzaIndex++) {
            pizzaData[pizzaIndex][0] = restaurant.getPizzaByIndex(pizzaIndex).getPizzaType();
            pizzaData[pizzaIndex][1] = restaurant.getPizzaByIndex(pizzaIndex).getQuantity();
            pizzaData[pizzaIndex][2] = restaurant.getPizzaByIndex(pizzaIndex).getOrderPrice();
            pizzaData[pizzaIndex][3] = restaurant.getPizzaByIndex(pizzaIndex).getOrderCost();
            pizzaData[pizzaIndex][4] = restaurant.getPizzaByIndex(pizzaIndex).getOrderProfit();
        }
        
        for (int custIndex = 0; custIndex < restaurant.getNumPizzaOrders(); custIndex++) {
            custData[custIndex][0] = restaurant.getCustomerByIndex(custIndex).getName();
            custData[custIndex][1] = restaurant.getCustomerByIndex(custIndex).getMobileNumber();
            custData[custIndex][2] = restaurant.getCustomerByIndex(custIndex).getCustomerType();
            custData[custIndex][3] = restaurant.getCustomerByIndex(custIndex).getLocationX() + " , " + restaurant.getCustomerByIndex(custIndex).getLocationY();
            DecimalFormat df = new DecimalFormat("#.00");
    		String distText = df.format(restaurant.getCustomerByIndex(custIndex).getDeliveryDistance());
            custData[custIndex][4] = distText;
        }
        //PizzaTable setup
        pizzaModel = new DefaultTableModel(pizzaData, pizzaHeader);
        pizzaTable = new JTable(pizzaModel);
        jsp = new JScrollPane(pizzaTable);
        jsp.setViewportView(pizzaTable);
        jsp.setPreferredSize(new Dimension(470, 300));
        
        //CustomerTable Setup
        custModel = new DefaultTableModel(custData, custHeader);
        customerTable = new JTable(custModel);
        jsc = new JScrollPane(customerTable);
        jsc.setViewportView(customerTable);
        jsc.setPreferredSize(new Dimension(470, 300));
        
        operationPanel.add(jsp);
        operationPanel.add(jsc);
        operationPanel.add(resetButton);
        operationPanel.remove(logButton);
        valuesPanel.add(costButton);
        valuesPanel.add(distButton);
        
        revalidate();
    }
	/**
	 * Sets up an ActionListener for a button, calls the reset function that reset the
	 * table then marks the container as invalid and performs
	 * layout of the container
	 * @param - The button that the ActionListener listenses too
	 * @throws PizzaException
	 * @throws CustomerException
	 * @throws LogHandlerException
	 */
    private void resetTable(JButton button) throws PizzaException, CustomerException, LogHandlerException {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    reset();
                    revalidate();

                } catch (Exception e1) {

                }
            }
        });
    }
    
    /**
     * Calls the get total profit function from restaurant class then 
     * set a double called totalProfit the value and returns it 
     * @return - total project of pizzas 
     */
    public double CalculateProfit(){
        double totalProfit = restaurant.getTotalProfit();
        return totalProfit;
    }
    
    /**
     * Calls the get total distance function from restaurant class then
     * set a double called totalDistance the value and returns it 
     * @return -Total distance
     */
    public double CalculateDistance(){
        double totalDistance = restaurant.getTotalDeliveryDistance();
        return totalDistance;
    }
    /**
     * Reset the GUI and tables
     */
    void reset() {
        remove(jsp);
        pizzaModel.setRowCount(0);
        operationPanel.remove(jsp);
        operationPanel.remove(jsc);
        operationPanel.add(logButton);
        operationPanel.repaint();
        valuesPanel.remove(totalCost);
        valuesPanel.remove(totalDist);
        valuesPanel.remove(costButton);
        valuesPanel.remove(distButton);
        valuesPanel.repaint();
        revalidate();
        operationPanel.remove(resetButton);
    }
    
    @Override
    public void run() {
        // TO DO
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
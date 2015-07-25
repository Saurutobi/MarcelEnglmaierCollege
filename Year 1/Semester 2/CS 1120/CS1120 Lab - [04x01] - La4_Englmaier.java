package la4_englmaier;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.DecimalFormat;

/**
 *
 * @author Marcel Englmaier
 */
public class La4_Englmaier extends JFrame{

    //<editor-fold defaultstate="collapsed" desc="Declarations">
        JPanel panelUserInput;
        JPanel panelOutput;
        JPanel panelButton;
        
        JTextField adultTicketPrice;
        JTextField adultTicketsSold;
        JTextField childTicketPrice;
        JTextField childTicketsSold;
        
        JLabel labelAdultTicketPrice;
        JLabel labelAdultTicketsSold;
        JLabel labelChildTicketPrice;
        JLabel labelChildTicketsSold;
        JLabel labelAdultTicketGrossRevenueMarker;
        JLabel labelAdultTicketGrossRevenue;
        JLabel labelChildTicketGrossRevenueMarker;
        JLabel labelChildTicketGrossRevenue;
        JLabel labelAdultTicketNetRevenueMarker;
        JLabel labelAdultTicketNetRevenue;
        JLabel labelChildTicketNetRevenueMarker;
        JLabel labelChildTicketNetRevenue;
        
        JButton revenueButton;
    //</editor-fold>
        
    public La4_Englmaier()
    {
        setSize(600, 175);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Movie Ticket Monetary Calculator");
        
        //<editor-fold defaultstate="collapsed" desc="initiating">
        panelUserInput = new JPanel(new GridLayout(4,4));
        panelOutput = new JPanel(new GridLayout(4,4));
        panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        adultTicketPrice = new JTextField(10);
        adultTicketsSold = new JTextField(10);
        childTicketPrice = new JTextField(10);
        childTicketsSold = new JTextField(10);
        
        labelAdultTicketPrice = new JLabel("Adult Ticket Price:");
        labelAdultTicketsSold = new JLabel("Adult Tickets Sold:");
        labelChildTicketPrice = new JLabel("Child Ticket Price:");
        labelChildTicketsSold = new JLabel("Child Tickets Sold:");
        
        labelAdultTicketGrossRevenueMarker = new JLabel("Adult Ticket Gross Revenue:   ");
        labelAdultTicketGrossRevenue = new JLabel("-----");
        labelChildTicketGrossRevenueMarker = new JLabel("Child Ticket Gross Revenue:   ");
        labelChildTicketGrossRevenue = new JLabel("-----");
        
        labelAdultTicketNetRevenueMarker = new JLabel("Adult Ticket Net Revenue:");
        labelAdultTicketNetRevenue = new JLabel("-----");
        labelChildTicketNetRevenueMarker = new JLabel("Child Ticket Net Revenue:");
        labelChildTicketNetRevenue = new JLabel("-----");
        
        revenueButton = new JButton("Get Revenue");
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="adding to panels">
        panelUserInput.add(labelAdultTicketPrice);
        panelUserInput.add(adultTicketPrice);
        panelUserInput.add(labelAdultTicketsSold);
        panelUserInput.add(adultTicketsSold);
        panelUserInput.add(labelChildTicketPrice);
        panelUserInput.add(childTicketPrice);
        panelUserInput.add(labelChildTicketsSold);
        panelUserInput.add(childTicketsSold);
        
        panelOutput.add(labelAdultTicketGrossRevenueMarker);
        panelOutput.add(labelAdultTicketNetRevenueMarker);
        panelOutput.add(labelAdultTicketGrossRevenue);
        panelOutput.add(labelAdultTicketNetRevenue);
        panelOutput.add(labelChildTicketGrossRevenueMarker);
        panelOutput.add(labelChildTicketNetRevenueMarker);
        panelOutput.add(labelChildTicketGrossRevenue);
        panelOutput.add(labelChildTicketNetRevenue);
        
        panelButton.add(revenueButton);
        revenueButton.addActionListener(new button1listener());
        //</editor-fold>
        
        add(panelUserInput, BorderLayout.WEST);
        add(panelOutput, BorderLayout.EAST);
        add(panelButton, BorderLayout.SOUTH);
        
        setVisible(true);  
    }
    
    public static void main(String[] args) {
        //JEREMY: why is this needed? why can't the constructor statement just be put in the main()?
        new La4_Englmaier();
    }
    
    private class button1listener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e)
        {
            if(!adultTicketPrice.getText().isEmpty() && !adultTicketsSold.getText().isEmpty() && !childTicketPrice.getText().isEmpty() && !childTicketsSold.getText().isEmpty())
            {
                adultTickets adultTicketCalculator = new adultTickets(Integer.parseInt(adultTicketsSold.getText()), Double.parseDouble(adultTicketPrice.getText()));
                childTickets childTicketCalculator = new childTickets(Integer.parseInt(childTicketsSold.getText()), Double.parseDouble(childTicketPrice.getText()));
                DecimalFormat formatMyDouble = new DecimalFormat("0.00");
                labelAdultTicketGrossRevenue.setText("" + formatMyDouble.format(adultTicketCalculator.calculateGrossRevenue()));
                labelAdultTicketNetRevenue.setText("" + formatMyDouble.format(adultTicketCalculator.calculateNetRevenue()));
                labelChildTicketGrossRevenue.setText("" + "" + formatMyDouble.format(childTicketCalculator.calculateGrossRevenue()));
                labelChildTicketNetRevenue.setText("" + formatMyDouble.format(childTicketCalculator.calculateNetRevenue()));
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Required fields left empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
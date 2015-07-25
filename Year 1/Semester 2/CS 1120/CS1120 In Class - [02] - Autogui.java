package autogui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Marcel Englmaier
 */
public class Autogui extends JFram
{
    JPanel panel;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    JTextField operand1;
    JTextField operand2;
    JButton button1;
    JLabel label1;
    
    public Autogui()
    {
        setSize(800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(2, 2));
        
        panel = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        
        operand1 = new JTextField(10);
        operand2 = new JTextField(10);
        button1 = new JButton("Add");
        label1 = new JLabel("----");
        panel.add(operand1);
        panel2.add(operand2);
        panel3.add(button1);
        panel4.add(label1);
        
        button1.addActionListener(new button1listener());
        
        add(panel, BorderLayout.WEST);
        add(panel2, BorderLayout.EAST);
        add(panel3, BorderLayout.SOUTH);
        add(panel4, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    public static void main(String[] args)
	{
        new Autogui();
    }
    
    private class button1listener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e)
        {
            int a = Integer.parseInt(operand1.getText());
            int b = Integer.parseInt(operand2.getText());
            int c = b+a;
            label1.setText(Integer.toString(c));
        }
    }
}
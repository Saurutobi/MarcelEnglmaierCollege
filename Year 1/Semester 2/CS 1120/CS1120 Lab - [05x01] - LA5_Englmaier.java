package la5_englmaier;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Marcel Englmaier
 */
public class LA5_Englmaier extends JFrame
{    
    //<editor-fold defaultstate="collapsed" desc="declarations">
    JPanel panelLabelsAndFields;
    JPanel panelFile;
    JPanel panelInputText;
    JPanel panelTranslatedText;
    JPanel panelButtons;
    
    
    JButton buttonCheckFile;
    JButton buttonTranslate;
    JButton buttonClear;
    
    JLabel labelFileName;
    JLabel labelInput;
    JLabel labelOutput;
    JLabel labelTranslatedText;
    JLabel labelFileNameStatus;
    
    JTextField fieldFileName;
    JTextField fieldInputText;
    
    public TranslateLogic translater;
    //</editor-fold>
    
    /**
    * this method is the constructor, and builds the GUI so that the user can use it
    */
    public LA5_Englmaier()
    {
        //window creation and settings
        setSize(400,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Translation Machine");
              
        //<editor-fold defaultstate="collapsed" desc="initiating">
        panelLabelsAndFields = new JPanel(new GridLayout(3,1));
        
        panelFile = new JPanel(new BorderLayout());
        labelFileName = new JLabel("Enter File Name:");
        fieldFileName = new JTextField(10);
        labelFileNameStatus = new JLabel("");
        //makes the labelfileNameStatus easier to identify/read
        labelFileNameStatus.setForeground(new Color(255, 0, 255));
        
        panelInputText = new JPanel(new BorderLayout());
        labelInput = new JLabel("Enter Input Text:");
        fieldInputText = new JTextField(10);
        
        panelTranslatedText = new JPanel(new BorderLayout());
        labelTranslatedText = new JLabel("Translated Text:");
        labelOutput = new JLabel(" ");
        //makes the labelOutput easier to identify/read
        labelOutput.setForeground(new Color(255, 0, 255));
        
        panelButtons = new JPanel(new GridLayout(3, 1));
        buttonCheckFile = new JButton("Load File");
        buttonTranslate = new JButton("Translate Text");
        buttonTranslate.setEnabled(false);
        buttonClear = new JButton("Clear All The Fields");
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="adding to window">
        panelLabelsAndFields.add(panelFile);
        panelLabelsAndFields.add(panelInputText);
        panelLabelsAndFields.add(panelTranslatedText);
        
        panelFile.add(labelFileName, BorderLayout.WEST);
        panelFile.add(fieldFileName, BorderLayout.CENTER);
        panelFile.add(labelFileNameStatus, BorderLayout.EAST);
        
        panelInputText.add(labelInput, BorderLayout.WEST);
        panelInputText.add(fieldInputText, BorderLayout.CENTER);
        
        panelTranslatedText.add(labelTranslatedText, BorderLayout.WEST);
        panelTranslatedText.add(labelOutput, BorderLayout.CENTER);
        
        panelButtons.add(buttonCheckFile);
        panelButtons.add(buttonTranslate);
        panelButtons.add(buttonClear);
        
        buttonCheckFile.addActionListener(new buttonCheckFilelistener());
        buttonTranslate.addActionListener(new buttonTranslatelistener());
        buttonClear.addActionListener(new buttonClearlistener());
        //</editor-fold>
        
        add(panelLabelsAndFields, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.SOUTH);
        
        setVisible(true);
        
        //initiates the actual translate object
        translater = new TranslateLogic();
    }
    
    /**
     * this method is the main method, which calls the constructor of itself so it works
     * @param args
     * 
     */
    public static void main(String[] args) {
        //calls its own constructor to build GUI
        new LA5_Englmaier();
    }
    
    /**
     * this method checks if file exists, and performs appropriate actions
     */
    private class buttonCheckFilelistener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e)
        {
            //checks if file exists, and performs appropriate actions
            if(translater.fileDoesNotExist(fieldFileName.getText()))
            {
                labelFileNameStatus.setText("File Load Error");
                JOptionPane.showMessageDialog(null, "File Does Not Exist", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                labelFileNameStatus.setText("File Loaded Successfully");
                buttonTranslate.setEnabled(true);
                buttonCheckFile.setEnabled(false);
            }
        }
    }
    
    /*
     *this method starts translation and displays translation 
     */
    private class buttonTranslatelistener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e)
        {
            //starts translation and displays translation
            labelOutput.setText(translater.translate(fieldInputText.getText()));
            //dissables button so that user must clear before retranslation, which means there is no double-translation
            buttonTranslate.setEnabled(false);
        }
    }
    
    /**
     * this method sets all variables to 0, or the equivalent of 0(null or empty)
     */
    private class buttonClearlistener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e)
        {
            //resets all settings
            fieldFileName.setText("");
            labelFileNameStatus.setText("");
            fieldInputText.setText("");
            labelOutput.setText("");
            translater.reset();
            buttonTranslate.setEnabled(false);
            buttonCheckFile.setEnabled(true);
        }
    }
}
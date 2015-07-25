/* PROJECT: WorldDataProject #6         CLASS: DataUpdate
* Written By: Marcel Englmaier
* FILE STRUCTURE: OOP
* DESCRIPTION: This Class was taken from Kaminski, then changed to fit my needs
*******************************************************************************/

package OOPClasses;

import java.io.*;
import java.sql.*;


public class DataUpdate
{
    //<editor-fold defaultstate="collapsed" desc="Public Declarations">
    
    
    //**************************** PUBLIC DECLARATIONS ************************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Declarations">
    
    
    //**************************** PRIVATE DECLARATIONS ************************
    private static String sql;
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Constructor(s)">
    
    
    //**************************** PUBLIC CONSTRUCTOR(S) ***********************
    public DataUpdate()
    {
        
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Get/Set Methods">
    
    
    //**************************** PUBLIC GET/SET METHODS **********************
    public static void setSQLString(String temp)
    {
        sql = temp;
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Service Methods">
    
    
    //**************************** PUBLIC SERVICE METHODS **********************
    public static void DoInsert(Connection conn, FileWriter file, int transNum) throws IOException
    {
        //create log entry
        file.write(String.format("\r\nSQL (%d): %s\r\n", transNum, sql));

        try
        {
            //create a Statement object
            Statement stmt = conn.createStatement();
            int result = stmt.executeUpdate(sql);
            //do all the return value stuff
            if (result != 0)
            {
                file.write("\r\nOK, INSERT was done\r\n");
            }
        }
        catch (Exception ex)
        {
            file.write("\r\nERROR on " + transNum + ", INSERT not done\r\n");
            file.write(ex.toString() + "\r\n");
            System.out.println("ERROR on " + transNum + ", INSERT not done\r\n");
        }
    }
    
    public static void DoUpdate(Connection conn, FileWriter file, int transNum) throws IOException
    {
        //create log entry
        file.write(String.format("\r\nSQL (%d): %s\r\n", transNum, sql));

        try
        {
            Statement stmt = conn.createStatement();
            int result = stmt.executeUpdate(sql);
            //do all the return value stuff
            if (result != 0)
            {
                file.write("\r\nOK, UPDATE was done\r\n");
            }
        }
        catch (Exception ex)
        {
            file.write("\r\nERROR on " + transNum + ", UPDATE not done\r\n");
            file.write(ex.toString() + "\r\n");
            System.out.println("ERROR on " + transNum + ", UPDATE not done\r\n");
        }
    }
    
    public static void DoDelete(Connection conn, FileWriter file, int transNum) throws IOException
    {
        //create log entry
        file.write(String.format("\r\nSQL (%d): %s\r\n", transNum, sql));

        try
        {
            Statement stmt = conn.createStatement();
            int result = stmt.executeUpdate(sql);
            //do all the return value stuff
            if (result != 0)
            {
                file.write("\r\nOK, DELETE was done\r\n");
            }
        }
        catch (Exception ex)
        {
            file.write("\r\nERROR on " + transNum + ", DELETE not done\r\n");
            file.write(ex.toString() + "\r\n");
            System.out.println("ERROR on " + transNum + ", DELETE not done\r\n");
        }
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    
    //**************************** PRIVATE METHODS *****************************
    //None
    
    
    //</editor-fold>
}
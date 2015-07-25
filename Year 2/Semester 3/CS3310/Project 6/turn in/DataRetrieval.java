/* PROJECT: WorldDataProject #6         CLASS: DataRetrieval
* Written By: Marcel Englmaier
* FILE STRUCTURE: OOP
* DESCRIPTION: This Class was taken from Kaminski, then changed to fit my needs
*******************************************************************************/

package OOPClasses;

import java.io.*;
import java.sql.*;
import java.text.NumberFormat;

public class DataRetrieval
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
    //None
    
    
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
    public static void DoQueryWhichGetsMultRows(Connection conn, FileWriter file, int transNum) throws IOException
    {
        //create Log entry
        file.write(String.format("\r\nSQL (%d): %s\r\n", transNum, sql));
        
        try
        {
            //create a Statement object
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            String tempValue = "";
            //do all the return value stuff
            while (rs.next())
            {
                for(int i = 1; i <= rsmd.getColumnCount(); i++)
                {
                    try
                    {
                        file.write(String.format("%-15s", rs.getObject(i).toString()));
                    }
                    catch(NullPointerException e)
                    {
                        file.write(String.format("%-15s", "NULL"));
                    }
                }
                file.write("\n");
            }
            rs.close();
            stmt.close();
        }
        catch (Exception ex)
        {
            file.write("\r\nERROR on " + transNum + ", QUERY not done\r\n");
            file.write(ex.toString() + "\r\n");
            System.out.println("\r\nERROR on " + transNum + ", QUERY not done");
        }
    }
    
    public static void DoQueryWhichGetsSingleValue(Connection conn, FileWriter file, int transNum) throws IOException
    {
        //create Log entry
        file.write(String.format("\r\nSQL (%d): %s\r\n", transNum, sql));

        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //do all the return value stuff
            if (rs.next())
            {
                int number = rs.getInt(1);
                file.write(String.format("\r\n%d\r\n", 
                        number));
            }
            stmt.close();
        }
        catch (Exception ex)
        {
            file.write("\r\nERROR on " + transNum + ", QUERY not done\r\n");
            file.write(ex.toString() + "\r\n");
            System.out.println("\r\nERROR on " + transNum + ", QUERY not done");
        }
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    
    //**************************** PRIVATE METHODS *****************************
    //None
    
    
    //</editor-fold>
}
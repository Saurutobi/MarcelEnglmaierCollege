/* PROJECT: WorldDataProject #6         PROGRAM: Driver
 * Written By: Marcel Englmaier
 * PROJECT CLASSES USED: DataRetrieval, DataUpdate
 * FILES ACCESSED: none
 * DESCRIPTION: this class has a few lines from Kaminski's driver, but is mostly mine
 *******************************************************************************/
package Driver;

import OOPClasses.DataRetrieval;
import OOPClasses.DataUpdate;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Driver 
{

    //<editor-fold defaultstate="collapsed" desc="Public Declarations">
    
    
    //**************************** PUBLIC DECLARATIONS ************************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Declarations">
    
    
    //**************************** PRIVATE DECLARATIONS ************************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Main">
    
    
    //****************************** PUBLIC Main *******************************
    public static void main(String[] args) throws SQLException, IOException
    {
        //pre-operations stuff
        DataRetrieval dataGetter = new DataRetrieval();;
        DataUpdate dataUpdate = new DataUpdate();;
        Connection conn;
        FileWriter file;
        int count;
        String url = "jdbc:mysql://localhost:3306/world";
        String user = "root";
        Scanner temp = new Scanner(System.in);
        String password = temp.nextLine();
        
        file = new FileWriter("WorldLogFile.txt");
        Scanner trans = new Scanner(new File("WorldTrans.txt"));
        String currentLine = "";
        conn = DriverManager.getConnection(url, user, password);
        count = 0;
        
        file.write("Connecting to MySQL and loading up WorldTrans.txt\r\n");
        
        file.write("OK, the DB Connection is OPENED\r\n");
        
        //do transdata
        while(trans.hasNextLine())
        {
            try
            {
                currentLine = trans.nextLine();
                count++;
                //do switch on first letter, then pass currentLine to private methods
                switch(currentLine.split(" ")[0].charAt(0))
                {
                    case 'S':
                        dataGetter.setSQLString(currentLine);
                        if(!currentLine.split(" ")[1].equals("COUNT(*)"))
                        {
                            dataGetter.DoQueryWhichGetsMultRows(conn, file, count);
                        }
                        else
                        {
                            dataGetter.DoQueryWhichGetsSingleValue(conn, file, count);
                        }
                        break;
                    case 'I':
                        dataUpdate.setSQLString(currentLine);
                        dataUpdate.DoInsert(conn, file, count);
                        break;
                    case 'D':
                        dataUpdate.setSQLString(currentLine);
                        dataUpdate.DoDelete(conn, file, count);
                        break;
                    case 'U':
                        dataUpdate.setSQLString(currentLine);
                        dataUpdate.DoUpdate(conn, file, count);
                }  
            }
            catch (Exception ex)
            {
                file.write("\r\nERROR, DB Connection didn't work - no trans done\r\n");
                file.write(ex.toString());
                System.out.println("ERROR, DB Connection didn't work - no trans done");
            }
        }
        conn.close();
        System.out.println("See WorldLogFile.txt in project folder");
        file.write("\r\nEXITING PROGRAM");
        file.close();
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Get/Set Methods">
    
    
    //**************************** PUBLIC GET/SET METHODS **********************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Service Methods">
    
    
    //**************************** PUBLIC SERVICE METHODS **********************
    //None
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    
    //**************************** PRIVATE METHODS *****************************
    //None
    
    
    //</editor-fold>
}
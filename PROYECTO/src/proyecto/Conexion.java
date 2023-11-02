/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author XIC
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class Conexion {
    
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/proyecto";
   static final String USER = "root";
   static final String PASS = "";
   Connection conn;
   
   Conexion ()
   {
       try{
          conn = DriverManager.getConnection(DB_URL, USER, PASS);
      } 
       catch(Exception ex)
       {
       }
       
   }
   
  public DefaultTableModel  CargarTodo()
   {
       try {
         String QUERY = "SELECT id,nombre , ficha, puntaje FROM usuarios";
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERY);	      
         return buildTableModel(rs);
         }
       catch(SQLException ex){
           System.out.println(ex.getMessage());
       return null;
       }
   }
      
          public DefaultTableModel  CargarUni(String elId)
   {
       try {
         String QUERY = "SELECT id,nombre , ficha, puntaje FROM autos where id="+elId;
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERY);	      
         return buildTableModel(rs);
         }
       catch(SQLException ex){
       return null;
       }
   }
      
     public static DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {

    ResultSetMetaData metaData = rs.getMetaData();

    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = metaData.getColumnCount();
    for (int column = 1; column <= columnCount; column++) {
        columnNames.add(metaData.getColumnName(column));
    }

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            vector.add(rs.getObject(columnIndex));
        }
        data.add(vector);
    }

    return new DefaultTableModel(data, columnNames);

}  
   
}

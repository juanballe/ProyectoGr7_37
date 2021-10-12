package com.tienda.DAO;

import java.sql.*;

public class Conexion {
	static String bd = "grupo37_equipo7";
	//static String bd = "tienda";
    static String login = "admin";
	//static String login = "root";
    static String password = "Admin123$";
    //static String password = "Admin";
    static String url = "jdbc:mariadb://camiloc2.czo3ixoe3xoe.us-east-1.rds.amazonaws.com/"+bd;
    //static String url = "jdbc:mysql://localhost/"+bd;
    
    Connection connection = null;
    
    public Conexion() {
        try{
           //obtenemos el driver de para mysql
           Class.forName("org.mariadb.jdbc.Driver");
           //Class.forName("com.mysql.cj.jdbc.Driver");
           //obtenemos la conexión
           connection = DriverManager.getConnection(url,login,password);
           if (connection!=null){
              System.out.println("Conexión a base de datos "+bd+" OK\n");
           }else {
               System.out.println("No se pudo conectar a la base de datos");
           }
        }
        catch(SQLException e){
           System.out.println(e);
        }catch(ClassNotFoundException e){
           System.out.println(e);
        }catch(Exception e){
           System.out.println(e);
        }
     }
    /**permite retornar la conexion*/
    public Connection getConnection(){
        return connection;
     }
     public void desconectar(){
        connection = null;
     }
	
}

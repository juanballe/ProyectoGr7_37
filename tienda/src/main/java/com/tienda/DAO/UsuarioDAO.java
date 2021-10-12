package com.tienda.DAO;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.tienda.DTO.UsuarioDTO;

public class UsuarioDAO {
	PreparedStatement preparedstatement;
	
	public void registrarUsuario(UsuarioDTO usuario)
    {
     Conexion conex= new Conexion();
     try {
      Statement estatuto = conex.getConnection().createStatement();
      estatuto.executeUpdate("INSERT INTO usuarios VALUES ('"+usuario.getCedula_usuario()+"', '"
        +usuario.getEmail_usuario()+"', '"+usuario.getNombre_usuario()+"','"+usuario.getPassword()+"','"+usuario.getUsuario()+"')");
      JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
      estatuto.close();
      conex.desconectar();
      
     } catch (SQLException e) {
               System.out.println(e.getMessage());
      JOptionPane.showMessageDialog(null, "No se Registro la persona");
     }
    }
	public ArrayList<UsuarioDTO> consultarUsuario(int documento) {
        ArrayList< UsuarioDTO> miCliente = new ArrayList< UsuarioDTO>();
        Conexion conex= new Conexion();
         
        try {
         PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM usuarios where cedula_usuario = ? ");
         consulta.setInt(1, documento);
         ResultSet res = consulta.executeQuery();
         
        if(res.next()){
            UsuarioDTO usuario= new UsuarioDTO();
            usuario.setCedula_usuario(Integer.parseInt(res.getString("cedula_usuario")));
            usuario.setEmail_usuario(res.getString("email_usuario"));
            usuario.setNombre_usuario(res.getString("nombre_usuario"));
            usuario.setPassword(res.getString("password"));
            usuario.setUsuario(res.getString("usuario"));
       
          miCliente.add(usuario);
                }
                res.close();
                consulta.close();
                conex.desconectar();
         
        } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "no se pudo consultar la persona\n"+e);
        }
        return miCliente;
		}
	
	public ArrayList<UsuarioDTO> listaDeUsuarios() {
        ArrayList< UsuarioDTO> miCliente = new ArrayList< UsuarioDTO>();
        Conexion conex= new Conexion();
         
        try {
         PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM usuarios");
         ResultSet res = consulta.executeQuery();
         while(res.next()){
             UsuarioDTO usuario= new UsuarioDTO();
                usuario.setCedula_usuario(Integer.parseInt(res.getString("cedula_usuario")));
                usuario.setEmail_usuario(res.getString("email_usuario"));
                usuario.setNombre_usuario(res.getString("nombre_usuario"));
                usuario.setPassword(res.getString("password"));
                usuario.setUsuario(res.getString("usuario"));
       
          miCliente.add(usuario);
                }
                res.close();
                consulta.close();
                conex.desconectar();
         
        } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "no se pudo consultar la Persona\n"+e);
        }
        return miCliente;
		}
	
	public void eliminarUsuario(int cedula) {
        Conexion conex = new Conexion();
        try {
            String query = "DELETE FROM usuarios WHERE cedula_usuario = ?";
            preparedstatement = conex.getConnection().prepareStatement(query);
            preparedstatement.setInt(1,cedula);
            preparedstatement.executeUpdate();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
	
	
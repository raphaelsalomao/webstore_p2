/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class registrar {

    String login = "";
    String senha = "";
    Connection con;
    String query;
    PreparedStatement state;
    ResultSet rs;
    Statement statement;
    int i;
    boolean registro_ok;
    String user_existe="false";
    
    public String userexiste(String login) {
        this.login = login;
        query = "select username from login_details";        
        con = DAOConnection.sqlconnection();
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs != null && rs.next()) {
                if (rs.getString(1).equalsIgnoreCase(login)) {
                    user_existe = "true";
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro " + ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                    System.out.println("Conexão interrompida");
                }
            } catch (SQLException e) {
                System.out.println("Erro " + e);
            }
        }
        return user_existe;
    }

    public boolean registrauser(String login, String senha) {
        this.login = login;
        this.senha = senha;
        try {
            con = DAOConnection.sqlconnection();
            query = "insert into login_details values(?,?,?,?)";
            state = con.prepareStatement(query);
            state.setString(1, null);
            state.setString(2, login);
            state.setString(3, senha);
            state.setString(4, "user");
            i = state.executeUpdate();
            if (i > 0) {
                System.out.println("Usuário adicionado");
            }
            registro_ok = true;
        } catch (SQLException ex) {
            Logger.getLogger(registrar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                    System.out.println("Conexão interrompida");
                }
                if (state != null) {
                    state.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro " + e);
            }
        }
        return registro_ok;
    }
}

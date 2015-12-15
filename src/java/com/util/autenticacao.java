package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class autenticacao {

    String username = "";
    String password = "";
    boolean result;
    Connection con;
    PreparedStatement state;
    Statement stmt;
    String query;
    ResultSet rs;
    String autenticacao_status;
    String IsAdmin;
    HashMap autenticacao_status_map = new HashMap();

    public HashMap autenticar(String username, String password) {
        try {
            this.username = username;
            this.password = password;
            con = DAOConnection.sqlconnection();
            query = "select password from login_details where username='" + username + "'";
            state = con.prepareStatement(query);
            rs = state.executeQuery();
            while (rs != null && rs.next()) {
                String db_password = rs.getString(1);
                if (db_password.equals(password)) {
                    
                    autenticacao_status = "true";
                    IsAdmin = isAdmin();
                } else {
                  
                    autenticacao_status = "false";
                }
            }
          autenticacao_status_map.put("autenticacao_status", autenticacao_status);
          autenticacao_status_map.put("IsAdmin", IsAdmin);
            
        } catch (SQLException ex) {
            Logger.getLogger(autenticacao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(autenticacao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (state != null) {
                try {
                    state.close();
                } catch (SQLException ex) {
                    Logger.getLogger(autenticacao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return autenticacao_status_map;
    }

    public String isAdmin() {
        try {
            query = "select admin from login_details where username='" + username + "'";
            state = con.prepareStatement(query);
            rs = state.executeQuery();
            while (rs != null && rs.next()) {
                IsAdmin = rs.getString(1);
                if (IsAdmin.equals("admin")) {
                    System.out.println("O usuário é administrador do sistema");
                    IsAdmin = "admin";                    
                }
                else{
                    IsAdmin = "user";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(autenticacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IsAdmin;
    }
}
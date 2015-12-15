package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.HashMap;
import java.util.logging.Logger;

public class Query {

    static Connection con;
    static Statement stmt;
    static PreparedStatement ps;
    static String query;
    static ResultSet rs;
    static ArrayList list = new ArrayList();
    static ProductDetails productdetails = new ProductDetails();
    static HashMap map = new HashMap();

    public static HashMap get_product_info(String classifier_name) {
        try {
            con = DAOConnection.sqlconnection();
            if (classifier_name.equalsIgnoreCase("books")) {
                query = "select nome,autor,descricao,preco,books_id from books";
            }
            if (classifier_name.equalsIgnoreCase("electronics")) {
                query = "select nome,marca,descricao,preco,electronics_id from electronics";
            }

            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);
            ArrayList nomeproduto = new ArrayList();
            ArrayList marcaproduto = new ArrayList();
            ArrayList descricaoproduto = new ArrayList();
            ArrayList precoproduto = new ArrayList();
            ArrayList productid = new ArrayList();

            while (rs != null && rs.next()) {
                nomeproduto.add(rs.getString(1));           
                marcaproduto.add(rs.getString(2));        
                descricaoproduto.add(rs.getString(3));
                precoproduto.add(rs.getString(4));
                productid.add(rs.getString(5));
            }
            productdetails.setNome_produto(nomeproduto);
            productdetails.setMarca_produto(marcaproduto);
            productdetails.setDescricao_produto(descricaoproduto);
            productdetails.setPreco_produto(precoproduto);

            map.put("nomeproduto", nomeproduto);      
            map.put("marcaproduto", marcaproduto);       
            map.put("descricaoproduto", descricaoproduto);
            map.put("precoproduto", precoproduto);
            map.put("productid",productid);
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return map;
    }


    public void insert_data(String classifier_name) {
        try {
            con = DAOConnection.sqlconnection();

            if (classifier_name.equals("electronics")) {
                query = "select nome,marca,descricao,preco from electronics";
            }

            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);
            ArrayList nomeproduto = new ArrayList();
            ArrayList marcaproduto = new ArrayList();
            ArrayList descricaoproduto = new ArrayList();
            ArrayList precoproduto = new ArrayList();

            while (rs != null && rs.next()) {
                nomeproduto.add(rs.getString(1));           
                marcaproduto.add(rs.getString(2));        
                descricaoproduto.add(rs.getString(3));
                precoproduto.add(rs.getString(4));
            }
            productdetails.setNome_produto(nomeproduto);
            productdetails.setMarca_produto(marcaproduto);
            productdetails.setDescricao_produto(descricaoproduto);
            productdetails.setPreco_produto(precoproduto);


            map.put("nomeproduto", nomeproduto);      
            map.put("marcaproduto", marcaproduto);        
            map.put("descricaoproduto", descricaoproduto);
            map.put("precoproduto", precoproduto);
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public String insert_data_into_db(String classifier_name, String admin_nome_produto, String admin_marca_produto, String admin_descricao_produto, String admin_preco_produto) {
        String insertion_result = "";
        try {
            con = DAOConnection.sqlconnection();

            if (classifier_name.equals("Books")) {
                query = " insert into books values (?,?,?,?,?)";
            }
            if (classifier_name.equals("Electronics")) {
                query = " insert into electronics values (?,?,?,?,?)";
            }


            ps = con.prepareStatement(query);
            ps.setString(1, null);
            ps.setString(2, admin_nome_produto);
            ps.setString(3, admin_marca_produto);
            ps.setString(4, admin_descricao_produto);
            ps.setString(5, admin_preco_produto);
            int i = ps.executeUpdate();

            if (i > 0) {
                insertion_result = "success";

            } else {
                insertion_result = "failure";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return insertion_result;
    }

    String remove_data(String classifier_name, String admin_radio_selection, String admin_id_ou_nome) {
        String db_delete_result = "";
        System.out.println("admin_radio_selection = " + admin_radio_selection);
        System.out.println("admin_id_ou_nome = " + admin_id_ou_nome);
        System.out.println("classifier_name = " + classifier_name);
        try {
            con = DAOConnection.sqlconnection();
            stmt = con.createStatement();
            if (classifier_name.equalsIgnoreCase("Books")) {
                if (admin_radio_selection.equals("id")) {
                    query = " DELETE FROM books WHERE books_id = '" + admin_id_ou_nome + "'";
                } else {
                    query = " DELETE FROM books WHERE nome = '" + admin_id_ou_nome + "'";
                }
            }
            if (classifier_name.equalsIgnoreCase("Electronics")) {
                if (admin_radio_selection.equals("id")) {
                    query = " DELETE FROM electronics WHERE electronics_id = '" + admin_id_ou_nome + "'";
                } else {
                    query = " DELETE FROM electronics WHERE nome = '" + admin_id_ou_nome + "'";
                }
            }
            
            int i = stmt.executeUpdate(query);
            

            if (i > 0) {
                db_delete_result = "success";

            } else {
                db_delete_result = "failure";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return db_delete_result;
    }
    
    

    String update_data_into_db(String classifier_name, String admin_id_produto, String admin_nome_produto,
                        String admin_marca_produto, String admin_descricao_produto, String admin_preco_produto) {
        String db_update_result="";
        try {
            con = DAOConnection.sqlconnection();
            stmt = con.createStatement();
            if (classifier_name.equals("Books")) {                
                    query = "update books set nome='"+admin_nome_produto+"',autor='"+admin_marca_produto+"',descricao='"
                        +admin_descricao_produto+"',preco='"+admin_preco_produto+"'where books_id='"+admin_id_produto+"'";
            }
            if (classifier_name.equals("Electronics")) {                 
                    query = "update electronics set nome='"+admin_nome_produto+"',marca='"+admin_marca_produto+"',descricao='"
                        +admin_descricao_produto+"',preco='"+admin_preco_produto+"'where electronics_id='"+admin_id_produto+"'";
            }          
            int i = stmt.executeUpdate(query);
            if (i > 0) {
                db_update_result = "success";
            } else {
                db_update_result = "failure";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return db_update_result;
    }
}

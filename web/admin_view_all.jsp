
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="estilo_loja.css" type="text/css" >
        <style type="text/css">
            #table_header{                
                background-color: orange;
                color:white;
            }
        </style>
    </head>
    <body>
        <%! ArrayList nomeproduto = new ArrayList();
            ArrayList descricaoproduto = new ArrayList();
            ArrayList marcaproduto = new ArrayList();
            ArrayList precoproduto = new ArrayList();
            ArrayList productid = new ArrayList();
        %>
        <%
            nomeproduto = (ArrayList) session.getAttribute("nomeproduto");
            descricaoproduto = (ArrayList) session.getAttribute("descricaoproduto");
            marcaproduto = (ArrayList) session.getAttribute("marcaproduto");
            precoproduto = (ArrayList) session.getAttribute("precoproduto");
            productid = (ArrayList) session.getAttribute("productid");
            System.out.println("nomeproduto = " + nomeproduto);
            System.out.println("descricaoproduto = " + descricaoproduto);
            System.out.println("marcaproduto = " + marcaproduto);
            System.out.println("precoproduto = " + precoproduto);
            System.out.println("productid = " + productid);            
        %>



        <% String classifier_name = "none";
            session.setAttribute("admin_mode", "view");
            String db_insertion_result = (String) session.getAttribute("db_insertion_result");
            if ((String) session.getAttribute("classifier_name") != null) {
                classifier_name = (String) session.getAttribute("classifier_name");
            }%>       
        
        <div id="big_wrapper">            
            <div class="logmeout"> <input type="submit" name="signout" value=" Logout "> </div>
            <form name="admin" action="Controller" method="post">
                <nav id="links"> 
                    <ul>                                             
                        <li> <a href="admin_update.jsp" title="Update" > <img id="update"  border="0" src="Images/update.jpg" width="25" height="25" alt="Loading" /> </a> </li>
                        <li> <a href="admin_remove.jsp" title="Delete" > <img id="delete"  border="0" src="Images/delete.png" width="25" height="25" alt="Loading" /> </a> </li>
                        <li> <a href="admin_view_all.jsp" title="Insert" > <img id="view"  border="0" src="Images/view.jpg" width="75" height="75" alt="Loading" /> </a> </li>
                        <li> <a href="admin_insert.jsp" title="Insert" > <img id="insert"  border="0" src="Images/insert.jpg" width="75" height="25" alt="Loading" /> </a> </li>
                    </ul>
                </nav>
                <center>      <br /><h3> Selecione um produto </h3> <br />  <br /> 
                    <% if (db_insertion_result != null) {
                            if (db_insertion_result.equals("success")) {%>
                    <span style="color:green">  <b> Operação concluída  </b> </span> 
                    <% }
                        if (db_insertion_result.equals("failure")) {%>
                    <span style="color:red">  <b> Operação com erro</b> </span> 
                    <% }
                        }%>
                    <br /> <br />
                    <select name="admin_classifer_choice">
                        <option value=""> </option>
                        <option value="Books"> Livros </option> 
                        <option value="Electronics"> Eletrônicos </option>
                    </select> 
                    <input type="submit"  value =" > " name="admin_view_all_products" /> <br /> <br /> 
                    <div id="admin_mode"> </div>
                    <% if ((String) session.getAttribute("classifier_name") != null) {
                            classifier_name = (String) session.getAttribute("classifier_name");%>      
                    Categoria selecionada : <span style="color:green">  <b> <%= classifier_name%> </b> </span>

                    <% }%>      <br /> <br />
                    <% if (nomeproduto != null) {%> 
                    <table border="2" cellspacing="0" cellpadding="0">
                        <tr>
                        <div id="table_header">  <th> Item Id </th>
                            <th> Nome </th>
                            <th> Autor</th>
                            <th> Descrição </th>
                            <th> Preço </th> 
                        </div>
                        </tr>

                        <%        for (int i = 1; i <= nomeproduto.size(); i++) {
                        %>  
                        <tr>
                            <td> <%= productid.get(i - 1)%> </td> 
                            <td> <%= nomeproduto.get(i - 1)%>        </td>                                
                            <td> <%= marcaproduto.get(i - 1)%>          </td>
                            <td> <%= descricaoproduto.get(i - 1)%> </td>
                            <td> <%= precoproduto.get(i - 1)%>       </td>
                        </tr>                    
                        <%
                        }%>
                    </table>
                    <% }%> 
                </center>
                <br /> <br />
                <form>
                    </div>
                    </body>
                    </html>





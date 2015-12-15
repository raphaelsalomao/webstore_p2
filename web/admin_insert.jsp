<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="estilo_loja.css" type="text/css" >
    </head>
    <body>

        <% String classifier_name = "none";
            session.setAttribute("admin_mode", "insert");
            String db_insertion_result = (String) session.getAttribute("db_insertion_result");
            if ((String) session.getAttribute("classifier_name") != null) {
                classifier_name = (String) session.getAttribute("classifier_name");           
            }%>       

        <div id="big_wrapper">            
            
            <form name="admin" action="Controller" method="post">
                 <div class="logmeout"> <input type="submit" name="signout" value=" Logout "> </div>
                <nav id="links"> 
                    <ul>                                             
                        <li> <a href="admin_update.jsp" title="Update" > <img id="update"  border="0" src="Images/update.jpg" width="25" height="25" alt="Loading" /> </a> </li>
                        <li> <a href="admin_remove.jsp" title="Delete" > <img id="delete"  border="0" src="Images/delete.png" width="25" height="25" alt="Loading" /> </a> </li>                    
                        <li> <a href="admin_insert.jsp" title="Insert" > <img id="insert"  border="0" src="Images/insert.jpg" width="75" height="75" alt="Loading" /> </a> </li>
                        <li> <a href="admin_view_all.jsp" title="View" > <img id="view"  border="0" src="Images/view.jpg" width="25" height="25" alt="Loading" /> </a> </li>          
                    </ul>
                </nav>
                <center>      <br /><h3> Selecione um produto para inserir </h3> <br />  <br /> 
                     <% if(db_insertion_result!=null){
                         if(db_insertion_result.equals("success")){%>
                     <span style="color:green">  <b> Operação concluída  </b> </span> 
                     <% } if(db_insertion_result.equals("failure")) { %>
                     <span style="color:red">  <b> Erro na operação   </b> </span> 
                     <% }} %>
                     <br /> <br />
                    <select name="admin_classifer_choice">
                        <option value=""> </option>
                        <option value="Books"> Livros </option> 
                        <option value="Electronics"> Eletrônicos </option>
                    </select> 
                    <input type="submit"  value ="  Ir  " name="admin_catagory" /> <br /> <br /> 
                    <div id="admin_mode"> </div>
                    <% if ((String) session.getAttribute("classifier_name") != null) {
                         classifier_name = (String) session.getAttribute("classifier_name");%>      
                    Categoria Selecionada : <span style="color:green">  <b> <%= classifier_name%> </b> </span>

                    <% }%>      <br /> <br />
                    <% if (classifier_name != null || classifier_name != "") {%>
                    Nome &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <input type="text" name="admin_nome_produto" /> <br />  <br />
                    Autor &nbsp; <input type="text" name="admin_marca_produto" /> <br />  <br />
                    Descrição &nbsp; &nbsp; <input type="text" name="admin_descricao_produto" /> <br />  <br />
                    Preço &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; <input type="text" name="admin_preco_produto" /> <br />  <br />
                    <input type="submit" value="  Insert  " name="admin_db_changes" />
                    <% }%>
                </center>
                <br /> <br />
                <form>
                    </div>
                    </body>
                    </html>

<%@page import="com.util.CartDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="com.util.ProductDetails" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="com.util.Query" %>

<%@ page errorPage="error.jsp"%>

<%!  ProductDetails pro = new ProductDetails();
    CartDetails mycart = new CartDetails();
    ProductDetails sessionbean = new ProductDetails();
    String name = null;
  
    ArrayList nomeproduto = new ArrayList();
    ArrayList descricaoproduto = new ArrayList();
    ArrayList marcaproduto = new ArrayList();
    ArrayList precoproduto = new ArrayList();
    Query queryObject = new Query();

%>
<%
    nomeproduto = (ArrayList) session.getAttribute("nomeproduto");
    descricaoproduto = (ArrayList) session.getAttribute("descricaoproduto");
    marcaproduto = (ArrayList) session.getAttribute("marcaproduto");
    precoproduto = (ArrayList) session.getAttribute("precoproduto");
    sessionbean.setDescricao_produto(descricaoproduto);

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo_loja.css">
        <title>Página JSP</title>
    </head>
    <body>
        <script type="text/javascript">
        
            function changeImage()
            {
                element=document.getElementById('myimage');

                
                if (element.src.match("Images/image_3.gif"))
                {
                    element.src="Images/image_1.jpg";
                }
                else if(element.src.match("Images/image_1.jpg"))
                {
                    element.src="Images/image_2.jpg";
                }
                else 
                {
                    element.src="Images/image_3.gif";
                }
            }    
        </script>
        <% String autenticacao = (String) session.getAttribute("autenticacao");
            if (autenticacao == null) {
        %>
   
        <div id="login_in">
            <form name="Home_Page" action="Controller" method="Post">
                <div align="right">                       
                    Login    <input  type="text" maxlength="40" name="login_name"> &nbsp;&nbsp;
                    Senha    <input type="password" maxlength="40" name="login_senha">             
                    <input type="submit" name="login_submit" value="Submit" onclick="check();">             
                    <a href="registration.jsp"> Registrar </a>
                </div>                    
            </form>
            <% }
                if (autenticacao != null) {
                    if (autenticacao.equals("Auth_Success")) {
            %>
            <div id="user_info"> Bem Vindo  <%= (session.getAttribute("name"))%> <span style="color:green"> <!--%= name %-->! </span> </div>
            <form name="logout" action="Controller" method="Post">
                <div class="logmeout"> <input type="submit" name="signout" value=" Logout "> </div>
            </form>         
            <%} else {%>
            <div id="wrong_user"> <span style="color:red"> Credenciais incorretas </span> </div>
            <form name="Home_Page" action="Controller" method="Post">
                <div id="login_in" align="right">                       
                    Login    <input  type="text" maxlength="40" name="login_name"> &nbsp;&nbsp;
                    Senha    <input type="password" maxlength="40" name="login_senha">             
                    <input type="submit" name="login_submit" value="Submit" onclick="check();">             
                    <a href="registration.jsp"> Registrar </a>
                </div>                    
            </form>

            <%}
                }%>

        </div>  
        <div id="big_wrapper">            
            <header id="top_header">  WebStore - P2 Java Web </header>
            <nav id="links"> 
                <ul>
                    <li> <a href="index.jsp"> Home </a> </li>
                    <li> <a href="index.jsp"> Produtos </a> </li>
                    <li> <a href="http://enderecoteste"> Novidades </a></li>
                    <li> <a href="mailto://teste@ucam.edu.br">Contato </a> </li>
                </ul>
            </nav>
            <section id="main_content">
                <article id="main_article">
                    <form name="cart" action="Controller" method="Post">
                        <select name="Item">
                            <option value="books"> Livros </option>
                            <option value="electronics"> Eletrônicos </option>

                        </select>
                        <input type="submit" value="Catálogo" name="product">
                    </form>

                    <% if (session.getAttribute("total_cart_items") != null) {%>
                    <span id="total_cart_items"> Total de produtos = <%= session.getAttribute("total_cart_items")%></span>
                    <% }%>

                    <% if (nomeproduto != null) {%> <form name="cart_details" method="Post" action="Controller">                         
                        <span id="add_to_cart"> <input type="submit" value="Adicionar ao carrinho" name="carrinho"> </span>
                        <span id="Finish_n_checkout"> <input type="submit" value="Finalizar" name="finalizar">    
                            <%        for (int i = 1; i <= nomeproduto.size(); i++) {
                            %> 

                        <article id="inner_article">                      
                            <table cellspacing="0" cellpadding="0">
                                <br /> 
                                <tr><td id="table_data"> <%= i%> . 
                                        <img id="myimage"  onclick="changeImage()" border="0" src="Images/image_3.gif" width="75" height="75" alt="Loading..">
                                        <span id="inner_artcile_heading" > <%= nomeproduto.get(i - 1)%> </span> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; </td></tr>
                                <span id="quantity"> Qtd. <input type="text" name="quantity" value="" size="1"> </span> <br>
                                <tr><td id="table_data"> by 
                                        <span id="inner_artcile_dealer"> <%= marcaproduto.get(i - 1)%> </span> <br> </td></tr>
                                <tr><td id="table_data"> <%= descricaoproduto.get(i - 1)%> <br>  </td></tr>
                                <tr><td id="table_data"> Price: $<%= precoproduto.get(i - 1)%> <br>  </td></tr>                                
                            </table>
                        </article>

                        <%
                            }%>
                        <% session.setAttribute("product_name", nomeproduto);
                            }%>                            

                    </form>  
                </article>
                <article id="main_article">
                    Trabalho P2 - Webstore Java
                </article>            
            </section>

            <aside id="updates"> Testando objetos na página </aside>
            <footer id="the_footer"> <a href="www.ucam.edu.br">UCAM</a>  </footer>
        </div>
    </body>
</html>

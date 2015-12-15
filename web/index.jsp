<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
String name=(String) request.getAttribute("name");
String names=(String) session.getAttribute("name");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo_loja.css">
        
    </head>
    <body>

        <script type="text/javascript">
            function form_validation(){
            var name=document.Login_Form.login_name.value;
            var password=document.Login_Form.login_senha.value;
            var flag = true;             
            if(name==""){
                document.getElementById("invalid_login").style.color="red";
                document.getElementById('invalid_login').innerHTML="Digite um login";
                flag = false;
            }             
            if(password==""){
                document.getElementById("invalid_login").style.color="red";
                document.getElementById('invalid_login').innerHTML="Digite uma senha";
                flag = false;
            }    
            return flag;
            }
        </script>
        
       
        <div id="invalid_login"> </div>
        <% String autenticacao = (String) session.getAttribute("autenticacao");            
            if (autenticacao == null) {
        %>                
        <div id="login_in">
            <form name="Login_Form" action="Controller" method="Post">
                <div align="right">                       
                    Login    <input  type="text" maxlength="40" name="login_name"> &nbsp;&nbsp;
                    Senha    <input type="password" maxlength="40" name="login_senha">             
                    <input type="submit" name="login_submit" value="Submit" onclick="return form_validation()">             
                    <a href="registration.jsp"> Registrar </a>
                </div>                    
            </form>
            <% }
                if (autenticacao != null) { if(autenticacao.equals("Auth_Success"))
                { 
            %>
            
            <form name="logout" action="Controller" method="Post">
                <div class="logmeout"> <input type="submit" name="signout" value=" Logout "> </div>
            </form>            
            <%} else { %>
            <div id="wrong_user"> <span style="color:red"> Credenciais inválidas </span> </div>
              <form name="Login_Form" action="Controller" method="Post">
                <div id="login_in" align="right">                       
                    Login    <input  type="text" maxlength="40" name="login_name"> &nbsp;&nbsp;
                    Senha    <input type="password" maxlength="40" name="login_senha">             
                    <input type="submit" name="login_submit" value="Submit" onclick="return form_validation()">             
                    <a href="registration.jsp"> Register </a>
                </div>                    
            </form>
          
            <%}}%>

        </div>  
        <div id="big_wrapper">            
            
             
            <section id="main_content">
                <article id="main_article">
                    <select name="Item">
                        <option> Livros </option>
                        <option> Eletrônicos </option>
                        
                    </select>
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

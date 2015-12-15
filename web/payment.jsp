

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="estilo_loja.css" type="text/css" /> 
        <style type="text/css">
            #big_wrapper{
                text-align: center;
                background-color: sienna;
                color:white;
            }
            #link.active{
                color:white;
            }
        </style>
    </head>
    <body>        
        <form action="Controller" method="Post">                   
                <div class="logmeout"> <input type="submit" name="signout" value=" Logout "> </div>
        </form>
        <div id="big_wrapper">
            <% String Confirmation = (String) session.getAttribute("Confirmacao_pedido");
                if (Confirmation == null) {
            %>

            <form action="Controller" method="Post">                   
                <!--div class="logmeout"> <input type="submit" name="signout" value=" Logout "> </div-->
                <h2> Pagamento </h2> <br /> <br />
                Bandeira do cartão <select name="card_type">
                    <option> American Express </option>   
                    <option> Visa </option>   
                    <option> Mastercard </option>   
                    
                </select> <br /> <br />                                         
                Número do cartão <input type="text" value="" maxlength="16" name="card_number" /> <br /> <br />
                Nome no cartão <input type="text" value="" maxlength="20" name="card_name" /> <br /> <br />
                Data de validade <select name="expiration_month">
                    <option> 1 </option> <option> 2 </option> <option> 3 </option> <option> 4 </option>   
                    <option> 5 </option> <option> 6 </option> <option> 7 </option> <option> 8 </option>   
                    <option> 9 </option> <option> 10 </option> <option> 11 </option> <option> 12 </option>   
                </select>                      
                <select name="expiration_year">
                    <option> 2015 </option>   
                    <option> 2016 </option>   
                    <option> 2017 </option>   
                    <option> 2018 </option>   
                    <option> 2019 </option>   
                </select> <br />  <br /> <br />

                <h2> Endereço de cobrança </h2> <br /> <br /> 
                Nome <input type="text" value="" name="nome_completo" /> <br /> <br />
                Endereço  &nbsp; &nbsp; <input type="text" value="" name="endereco" /> <br /> <br />
                País  &nbsp; <input type="text" value="" name="pais_origem" /> <br /> <br />
                CEP &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" value="" name="cep_origem" /> <br /> <br />
                <input type="submit" name="place_order" />              


                <% }  %>
                <%
                    if (Confirmation != null) {
                %>

                <h1> Pagamento confirmado  <br /> <br /> <br /> 
                    </h1>  <br /> <br /> <br /> <br /> <br />
                <div id="link">    <h2> <a href="index.jsp"> Voltar à página inicial </a> </h2> </div>
                <% }%>

            </form>         
        </div>
    </body>
</html>

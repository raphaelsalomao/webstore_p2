<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de Registro</title>
        <link rel="stylesheet" href="estilo_loja.css" type="text/css">        
    </head>
    <body>
        <div id="big_wrapper">
            <form action="Controller" method="Post">
                <center>
                    <h1> Página de Registro </h1>
                </center> <br> <br> 
                <div>
                    <center>
                        <pre>
                Login <input type="text" name="logn" > <br>
                Senha <input type="password" name="senha"> <br> <br>                
                        </pre>
                        <input type="submit" name="submit" value="Registro">
                        <input type="reset" value="Reset">    <br> <br>
                        <a href="index.jsp"> Voltar </a>

                    </center>
                </div>

            </form>
        </div>
    </body>
</html>

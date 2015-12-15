package com.util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    int total_cart_items = 0;
    SessionBean bean = new SessionBean();
    HashMap map = new HashMap();
    ArrayList list = new ArrayList();
    ArrayList produtos_lista = new ArrayList();
    ArrayList quantidade_carrinho = new ArrayList();
    ArrayList user_nome_produto = new ArrayList();
    CartDetails mycart = new CartDetails();
    ProductDetails sessionbean = new ProductDetails();
    Query queryObject = new Query();
    HashMap autenticacao_status_map = new HashMap();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        try {
            out.println("<h1>Servlet Controller at " + request.getContextPath() + "</h1>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login_name;
        String login_senha;
        String registro_user;
        String registro_senha;
        boolean result;
        String user_existe;
        RequestDispatcher rd;
        HttpSession session = request.getSession(true);
        session.setAttribute("Confirmacao_pedido", null);


        if (request.getParameter("signout") != null) {
            session.setAttribute("autenticacao", null);
            session.removeAttribute("autenticacao"); 
            session.setAttribute("name", null);
            session.removeAttribute("product_name");
            session.removeAttribute("nomeproduto");
            session.removeAttribute("db_insertion_result");
            session.removeAttribute("db_deletion_result");
            session.removeAttribute("db_update_result");            
            session.removeAttribute("descricaoproduto");
            session.removeAttribute("marcaproduto");
            session.removeAttribute("precoproduto");
            session.removeAttribute("quantity_in_cart");
            session.setAttribute("product_in_cart",null);
            session.removeAttribute("product_in_cart");
            session.setAttribute("classifier_name", null);
            session.removeAttribute("classifier_name");
            total_cart_items = 0;
            session.removeAttribute("total_cart_items");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }

        if (request.getParameter("login_submit") != null) {
            login_name = request.getParameter("login_name");
            login_senha = request.getParameter("login_senha");                   
            System.out.println("login_senha = " + login_senha.trim()); 
            user_existe = new registrar().userexiste(login_name.trim());
            if (user_existe.equals("false")) {
                session.setAttribute("autenticacao", "Auth_Failure");
                rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
            if (user_existe.equals("true")) {
                autenticacao_status_map = new autenticacao().autenticar(login_name.trim(), login_senha.trim()); //bruno, essa linha redireciona para a página de administrador se o usuário for administrador
                String autenticacao_status = (String) autenticacao_status_map.get("autenticacao_status");
                String IsAdmin = (String) autenticacao_status_map.get("IsAdmin");
                System.out.println("autenticacao status = " + autenticacao_status + " user = " + IsAdmin);
                if (autenticacao_status.equals("true")) {
                    bean.setName(login_name);
                    request.setAttribute("name", login_name);
                    session.setAttribute("name", login_name);
                    System.out.println("autenticacao Succesfull. User granted permission!!");
                    if (IsAdmin.equals("user")) {
                        session.setAttribute("autenticacao", "Auth_Success");
                        rd = request.getRequestDispatcher("/user_cart.jsp");
                        rd.forward(request, response);
                    } else {
                        session.setAttribute("autenticacao", "Auth_Success");
                        session.setAttribute("IsAdmin", "yes");
                        rd = request.getRequestDispatcher("/admin.jsp");
                        rd.forward(request, response);
                    }

                } else {
                    session.setAttribute("autenticacao", "Auth_Failure");
                    rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }
            }
        }


        if (request.getParameter("registration_submit") != null) {
            registro_user = request.getParameter("registro_user");
            registro_senha = request.getParameter("registro_senha");
            System.out.println("registro_user before trimming = " + registro_user);
            System.out.println("registration username after trimming = " + registro_user.trim());
            System.out.println("registro_senha = " + registro_senha.trim());

            if (registro_senha.trim().equals("") || registro_user.trim().equals("")) {
                PrintWriter out = response.getWriter();
                response.setContentType("text/html");
                try {
                    out.println("<h1> A senha não pode estar em branco! </h1>");
                } finally {
                    out.close();
                }
            }
            user_existe = new registrar().userexiste(registro_user.trim());
            System.out.println("user_existe true or false= " + user_existe);
            response.setContentType("text/html");
            if (user_existe.equals("true")) {
                PrintWriter out = response.getWriter();
                try {
                    out.println("<h1> Usuário já existe </h1>");
                } finally {
                    out.close();
                }
            } else {
                result = new registrar().registrauser(registro_user.trim(), registro_senha.trim());
                if (result == true) {
                    PrintWriter out = response.getWriter();
                    try {
                        out.println("<h1> Registro concluído com sucesso! </h1>");
                    } finally {
                        out.close();
                    }
                } else {
                    PrintWriter out = response.getWriter();
                    try {
                        out.println("<h1> Falha no registro </h1>");
                    } finally {
                        out.close();
                    }
                }
            }
        }

        
        if (request.getParameter("product") != null) {
            String classifier_name = (String) request.getParameter("Item");
            System.out.println("O produto selecionado é = " + classifier_name);
            map = new Query().get_product_info(classifier_name);
    

            session.setAttribute("nomeproduto", map.get("nomeproduto"));
            session.setAttribute("marcaproduto", map.get("marcaproduto"));
            session.setAttribute("descricaoproduto", map.get("descricaoproduto"));
            session.setAttribute("precoproduto", map.get("precoproduto"));
            session.setAttribute("productid", map.get("productid"));
            session.setAttribute("autenticacao", "Auth_Success"); 
          
            rd = request.getRequestDispatcher("/user_cart.jsp");
            rd.forward(request, response);
        }

        if (request.getParameter("carrinho") != null) {
            String itemnames[] = request.getParameterValues("quantity");                             
            user_nome_produto = (ArrayList) session.getAttribute("product_name");

            for (int i = 0; i < itemnames.length; i++) {
                System.out.println("Os valores são " + itemnames[i]);
                System.out.println("O produto selecionado é = " + user_nome_produto.get(i));
                if (itemnames[i] == "") {
                    System.out.println("Produto  \" " + user_nome_produto.get(i) + " \"não foi selecionado");                 
                } else {
                    total_cart_items = total_cart_items + (Integer.parseInt(itemnames[i]));                  
                    quantidade_carrinho.add(itemnames[i]);
                    produtos_lista.add(user_nome_produto.get(i));
                    System.out.println("Produtos no carrinho = " + produtos_lista);
                }
            }
            session.setAttribute("total_cart_items", total_cart_items);
            session.setAttribute("product_in_cart", produtos_lista);
            session.setAttribute("quantity_in_cart", quantidade_carrinho);
            rd = request.getRequestDispatcher("/user_cart.jsp");
            rd.forward(request, response);
        }

        if (request.getParameter("finalizar") != null) {
            rd = request.getRequestDispatcher("/loaded_cart.jsp");
            rd.forward(request, response);
        }


        if (request.getParameter("pagamento") != null) {
            rd = request.getRequestDispatcher("/payment.jsp");
            rd.forward(request, response);
        }

   
        if (request.getParameter("place_order") != null) {
   
            String card_type = (String) request.getParameter("card_type");
            String card_name = request.getParameter("card_name");
            String expiration_month = (String) request.getParameter("expiration_month");
            String expiration_year = (String) request.getParameter("expiration_year");


       
            String nome_completo = request.getParameter("nome_completo");
            String endereco = request.getParameter("endereco");
            String pais_origem = request.getParameter("pais_origem");
            String cep_origem = request.getParameter("cep_origem");
            session.setAttribute("Confirmacao_pedido", "Confirmed");
            String Confirmation_Status = (String) session.getAttribute("Confirmacao_pedido");
            System.out.println("Confirmation_Status = " + Confirmation_Status); 
            rd = request.getRequestDispatcher("/payment.jsp");
            rd.forward(request, response);
        }
    
        if (request.getParameter("admin_catagory") != null) {
            String classifier_name = (String) request.getParameter("admin_classifer_choice");
            System.out.println("O produto selecionado é " + classifier_name);
            session.setAttribute("classifier_name", classifier_name);

            String admin_mode = (String) session.getAttribute("admin_mode");
            if (admin_mode.equals("insert")) {
                rd = request.getRequestDispatcher("/admin_insert.jsp");
                rd.forward(request, response);
            }
            if (admin_mode.equals("update")) {
                rd = request.getRequestDispatcher("/admin_update.jsp");
                rd.forward(request, response);
            }
            if (admin_mode.equals("remove")) {
                rd = request.getRequestDispatcher("/admin_remove.jsp");
                rd.forward(request, response);
            }
        }

        if (request.getParameter("admin_db_changes") != null) {
            String admin_mode = (String) session.getAttribute("admin_mode");
            String classifier_name = (String) session.getAttribute("classifier_name");
            String admin_nome_produto = request.getParameter("admin_nome_produto");
            String admin_marca_produto = request.getParameter("admin_marca_produto");
            String admin_descricao_produto = request.getParameter("admin_descricao_produto");
            String admin_preco_produto = request.getParameter("admin_preco_produto");
            if (admin_mode.equals("insert")) {
                String db_insertion_result;
                db_insertion_result = new Query().insert_data_into_db(classifier_name, admin_nome_produto, admin_marca_produto, admin_descricao_produto, admin_preco_produto);
                session.setAttribute("db_insertion_result", db_insertion_result);
                rd = request.getRequestDispatcher("/admin_insert.jsp");
                rd.forward(request, response);

            }            
            
            if (admin_mode.equals("update")) {
                String db_update_result="";
                String admin_id_produto=request.getParameter("admin_id_produto");
                db_update_result = new Query().update_data_into_db(classifier_name, admin_id_produto, admin_nome_produto, admin_marca_produto, admin_descricao_produto, admin_preco_produto);
                session.setAttribute("db_update_result", db_update_result);
                rd = request.getRequestDispatcher("/admin_update.jsp");
                rd.forward(request, response);

            }
        }
        
         if (request.getParameter("admin_db_delete") != null) {            
            String classifier_name = (String) session.getAttribute("classifier_name");
            String admin_radio_selection = request.getParameter("admin_radio_delete");
            String admin_id_ou_nome = request.getParameter("admin_id_ou_nome");
            String db_deletion_result = "";
            db_deletion_result = new Query().remove_data(classifier_name,admin_radio_selection,admin_id_ou_nome);
            session.setAttribute("db_deletion_result",db_deletion_result);
            rd = request.getRequestDispatcher("/admin_remove.jsp");
            rd.forward(request, response);
        }

        if (request.getParameter("admin_view_all_products") != null) {            
            String classifier_name = request.getParameter("admin_classifer_choice");
            session.setAttribute("classifier_name",classifier_name);
            System.out.println("classifier_name from view all = " + classifier_name);
            map = new Query().get_product_info(classifier_name);            
            session.setAttribute("nomeproduto", map.get("nomeproduto"));
            session.setAttribute("marcaproduto", map.get("marcaproduto"));
            session.setAttribute("descricaoproduto", map.get("descricaoproduto"));
            session.setAttribute("precoproduto", map.get("precoproduto"));            
            session.setAttribute("productid", map.get("productid"));            
            rd = request.getRequestDispatcher("/admin_view_all.jsp");
            rd.forward(request, response);            
        }     
    }
}

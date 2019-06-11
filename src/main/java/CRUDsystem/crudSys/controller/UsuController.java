package CRUDsystem.crudSys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CRUDsystem.crudSys.jdbc.UsuarioDAO;
import CRUDsystem.crudSys.model.Usuario;

@WebServlet("/usucontroller.do")
public class UsuController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String acao = req.getParameter("acao");
		if(acao.equals("exc")) {
			String id = req.getParameter("id");
			Usuario usu = new Usuario();
			if(id!= null)
				usu.setId(Integer.parseInt(id));
			UsuarioDAO usuDAO = new UsuarioDAO();
			usuDAO.excluir(usu);
			
			resp.sendRedirect("usucontroller.do?acao=lis");
		}else if(acao.equals("lis")) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			List<Usuario> lista = usuarioDAO.buscarTodos();

			req.setAttribute("lista", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listausu.jsp");
			dispatcher.forward(req, resp);
		}else if(acao.equals("alt")) {
			String id = req.getParameter("id");
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.buscarPorId(Integer.parseInt(id));
			
			req.setAttribute("usu", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			dispatcher.forward(req, resp);
		}else if(acao.equals("cad")) {
			
			Usuario usuario = new Usuario();
			usuario.setId(0);
			usuario.setNome("");
			usuario.setLogin("");
			usuario.setSenha("");
			
			req.setAttribute("usu", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			dispatcher.forward(req, resp);
		}	
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usu = new Usuario();
		if(id != null)
			usu.setId(Integer.parseInt(id));
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usu);
		
		resp.getWriter().print("<h1>Sucesso!</h1>");
	}

}

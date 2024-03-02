package appJavaFiles;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AdministratorSignOut")
public class AdminstratorSignOut extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			
			HttpSession session = req.getSession();
			session.invalidate();
			
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, resp);
		}
		catch(Exception e) {
			RequestDispatcher rd = req.getRequestDispatcher("serverError.html");
			rd.forward(req, resp);
			e.printStackTrace();
		}
		
		
	}
}

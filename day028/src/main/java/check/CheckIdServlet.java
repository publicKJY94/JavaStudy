package check;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calc.CalcBean;
import checkid.MemberDAO;

/**
 * Servlet implementation class CheckIdServlet
 */
@WebServlet("/CheckIdServlet")
public class CheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		CheckIdBean ck = new CheckIdBean();
		MessageBean msg = new MessageBean();
		ck.setId(id);
		boolean result = ck.checkId();
		String printMsg = msg.printCkId(result);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(printMsg);
	}

}

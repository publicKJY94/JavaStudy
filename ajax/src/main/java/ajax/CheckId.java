package ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.MemberDTO;

/**
 * Servlet implementation class CheckId
 */
@WebServlet("/check.do")
public class CheckId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckId() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		System.out.println("ajax요청 들어옴");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		System.out.println(request.getParameter("mid"));
		mDTO.setMemberID(request.getParameter("mid"));
		mDTO.setSearchCondition("중복검사");
		mDTO = mDAO.selectOne(mDTO);
		System.out.println(mDTO);
		if (mDTO == null) {
			out.print(true);
		} else {
			out.print(false);
		}
	}

}

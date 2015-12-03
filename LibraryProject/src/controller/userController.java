package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.ItemsManager;
import biz.UserManager;
import dto.Items;
import dto.User;

/**
 * Servlet implementation class userController
 */
@WebServlet("/user/*")
public class userController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getPathInfo();
		System.out.println("PATH is" + path);
		
		UserManager usermgr = new UserManager();
		RequestDispatcher rd = null;
		
		switch (path) {
		case "/login":
			String id =request.getParameter("userid");
			String pwd= request.getParameter("pwd");
			System.out.println(id+"    "+pwd);
			User loguser = usermgr.getOneUser(request.getParameter("userid"));
			System.out.println("id:"+loguser.getUserId()+"password:"+loguser.getPassword());
			if(loguser.getUserId()==null){
				System.out.println("no find");
				rd = request.getRequestDispatcher("../jsp/login.jsp");
				rd.forward(request, response);
				break;
			}
			else{
				if( !loguser.getPassword().equals(request.getParameter("pwd"))){
					System.out.println("password not equall");
					rd = request.getRequestDispatcher("../jsp/login.jsp");
					rd.forward(request, response);
					break;
				}
				else{
					if(loguser.getRole().equals("librarian")){
						rd = request.getRequestDispatcher("../jsp/libsearch.jsp");
						rd.forward(request, response);
						break;
					}
					else if(loguser.getRole().equals("student")){
						rd = request.getRequestDispatcher("../jsp/stusearch.jsp");
						rd.forward(request, response);
						break;
					}
				}
				
			}
			
		
	}

}
}

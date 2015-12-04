package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import biz.UserManager;
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
		HttpSession session = request.getSession();
		
		
		switch (path) {
		case "/login":
			boolean iscorrectlogin=true;
			String id =request.getParameter("userid");
			String pwd= request.getParameter("pwd");
			System.out.println(id+"    "+pwd);
			User loguser = usermgr.getOneUser(id);
			System.out.println("id:"+loguser.getUserId()+"password:"+loguser.getPassword());
		
			
			
			if(loguser.getUserId()==null){
				iscorrectlogin = false;
				request.setAttribute("correctlogin", iscorrectlogin);
				System.out.println("no find");
				rd = request.getRequestDispatcher("../jsp/login.jsp");
				rd.forward(request, response);
				break;
			}
			else{
				if( !loguser.getPassword().equals(request.getParameter("pwd"))){
					iscorrectlogin = false;
					request.setAttribute("correctlogin", iscorrectlogin);
					rd = request.getRequestDispatcher("../jsp/login.jsp");
					rd.forward(request, response);
					break;
				}
				else{
					session.setAttribute("loginuser", loguser);
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
		case "/logout":
			
			session.getId();
			session.invalidate();
			rd = request.getRequestDispatcher("../jsp/HomePage");
			rd.forward(request, response);
			
		case "/maintainstudent":
			ArrayList<User> stulist = (ArrayList<User>) usermgr.getStudents();
			request.setAttribute("stulist", stulist);
			rd = request.getRequestDispatcher("../jsp/maintainstudent.jsp");
			rd.forward(request, response);
			break;
			
		case "/studetail":
			String uid = request.getParameter("userid");
			System.out.println(request.getParameter("userid"));
			User stu = usermgr.getOneUser(uid);
			System.out.println(stu.toString());
			request.setAttribute("stu", stu);
			rd = request.getRequestDispatcher("../jsp/studetail.jsp");
			rd.forward(request,response);
			
		case "/updatestudent":
			
			
			
			
			
			
		case "/createstudent":
			boolean isphonecorrect=false;
			boolean iscurrentuser =false;
			boolean isidright = true;
			boolean ispasswordsame = false;
			boolean ispasswordnull = false;
			boolean isusernamenull = false;
			if(request.getParameter("studentid").length()!=8){
				isidright = false;
			}else{
				try{
					Integer.parseInt(request.getParameter("studentid"));
				}catch(Exception e){
					isidright = false;
				}
			}
			if(request.getParameter("phone").equals("")){
					isphonecorrect=true;
			}else{
				if(request.getParameter("phone").length()==8){
					
					try{
						Integer.parseInt(request.getParameter("phone"));
						isphonecorrect = true;
					}catch(Exception e){
						isphonecorrect =false;
					}
				}
			}
			
			
			if(request.getParameter("password").equals(request.getParameter("confirmpassword"))){
				ispasswordsame = true;
			}
			if(request.getParameter("password").equals("")){
				ispasswordnull=true;
			}
			if(request.getParameter("studentname").equals("")){
				isusernamenull = true;
			}
			
			
			User newuser= new User();
			if(isidright&&!ispasswordnull&&ispasswordsame&&!isusernamenull&&isphonecorrect){
				System.out.println("allcorrect");
				String userid = ("S"+request.getParameter("studentid"));
				String username = request.getParameter("studentname");
				String password = request.getParameter("password");
				String roletype = "student";
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				String address = request.getParameter("address");
				String status = "1";
				
				//get current date
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				Date createdate= Date.valueOf(dateFormat.format(cal.getTime()));

				int onloannumber =0;

				try{
					Date dateofbirth = Date.valueOf(request.getParameter("dateofbirth"));
					System.out.println("dateofbirth ok");
					newuser = new User(userid,username,password,roletype,status,dateofbirth,address,email,phone,createdate,onloannumber);
					
				}catch(Exception e){
					System.out.println("dob not ok");
					newuser = new User(userid,username,password,roletype,status,address,email,phone,createdate,onloannumber);
					
				}
				
				
				User valuser =usermgr.getOneUser(newuser.getUserId());
				if(newuser.equals(valuser)){
					iscurrentuser=true;
					request.setAttribute("iscurrentuser", iscurrentuser);
					rd = request.getRequestDispatcher("../jsp/newstudent.jsp");
					rd.forward(request, response);
				}
				else{
					iscurrentuser = true;
					usermgr.createUser(newuser);
					rd = request.getRequestDispatcher("../jsp/maintainuser.jsp");
					rd.forward(request, response);
				}
			}
			else{
				System.out.println("not correct");
				request.setAttribute("isidright", isidright);
				request.setAttribute("ispasswordsame", ispasswordsame);
				request.setAttribute("ispasswordnull", ispasswordnull);
				request.setAttribute("isusernamenull", isusernamenull);
				request.setAttribute("isphonecorrect", isphonecorrect);
				System.out.println(isidright);
				System.out.println(ispasswordsame);
				rd = request.getRequestDispatcher("../jsp/newstudent.jsp");
				rd.forward(request, response);
				
			}
				
					
				
		}
			
			
		
			
		
	}

}


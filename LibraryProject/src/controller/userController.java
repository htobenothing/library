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

import biz.TransactionManager;
import biz.UserManager;
import dto.Transcation;
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
		TransactionManager TM =new TransactionManager();
		UserManager usermgr = new UserManager();
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		
		//
		String userid;
		String username;
		String password;
		String roletype;
		String email;
		String phone;
		String address;
		String status;
		Date dateOfBirth;
		Date createDate;
		ArrayList<User> stulist;

		boolean isphonecorrect=false;
		boolean iscurrentuser =false;
		boolean isidright = true;
		boolean ispasswordsame = false;
		boolean ispasswordnull = false;
		boolean isusernamenull = false;
		
		
		switch (path) {
		case "/login":
			ArrayList<Transcation> list1= new ArrayList<Transcation>();
			ArrayList<Transcation> list2= new ArrayList<Transcation>();
			Date from;
			from=Date.valueOf("1800-1-1");
			Date to;
			to=Date.valueOf("3000-1-1");
			try{
				list1=TM.findTransactionByCondition(-1, 1, from,to);
				list2=TM.findTransactionByCondition(-1, 3, from, to);
				for(Transcation it : list1){
					long a=System.currentTimeMillis();
					a-=a%(24*60*60*1000)+2*60*60*1000;
					Date returndate=new Date(a);						
					long due=returndate.getTime()/(24*60*60*1000)-it.getDueDate().getTime()/(24*60*60*1000);
					if(due>0)
						TM.overdueTransaction(it.getTransactionID());
				}
				for(Transcation it:list2){
					long a=System.currentTimeMillis();
					a-=a%(24*60*60*1000)+2*60*60*1000;
					Date returndate=new Date(a);						
					long due=returndate.getTime()/(24*60*60*1000)-it.getDueDate().getTime()/(24*60*60*1000);
					if(due>0)
						TM.overdueTransaction(it.getTransactionID());
				}
					
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			
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
			break;
			
		case "/logout":
			
			session.invalidate();
			System.out.println("session destroy");
			rd = request.getRequestDispatcher("../jsp/HomePage.jsp");
			System.out.println("should go home");
			rd.forward(request, response);
			
		case "/maintainstudent":
			User u;
			session=request.getSession();
			u=(User)session.getAttribute("loginuser");
			System.out.println(u.toString());
			
			System.out.println(checkLoginLib(request.getSession()));
			if(checkLoginLib(request.getSession())){
				
				stulist = (ArrayList<User>) usermgr.getStudents();
				request.setAttribute("stulist", stulist);
				rd = request.getRequestDispatcher("../jsp/maintainstudent.jsp");
				rd.forward(request, response);
				}else{
					session.invalidate();
					rd=request.getRequestDispatcher("../jsp/login.jsp");
					rd.forward(request, response);
			}
			break;
			
		case "/showStuById":
			System.out.println(checkLoginLib(request.getSession()));
			if(checkLoginLib(request.getSession())){	
			stulist= new ArrayList<User>();
			if(request.getParameter("userid")==""){
				stulist = (ArrayList<User>) usermgr.getStudents();
				request.setAttribute("stulist", stulist);
				rd = request.getRequestDispatcher("../jsp/maintainstudent.jsp");
				rd.forward(request, response);
				break;
			}else{
				User showuser = usermgr.getOneUser(request.getParameter("userid"));
				System.out.println(showuser);
				boolean isnorecord=false;
				if(showuser.getUserId()==null){
					isnorecord = true;
					request.setAttribute("isnorecord", isnorecord);
					rd = request.getRequestDispatcher("../jsp/maintainstudent.jsp");
					rd.forward(request, response);
					break;
				}else{
					stulist.add(showuser);
					request.setAttribute("stulist", stulist);
					rd = request.getRequestDispatcher("../jsp/maintainstudent.jsp");
					rd.forward(request, response);
					break;
				}
				
			}
			}else{
				session.invalidate();
				rd=request.getRequestDispatcher("../jsp/login.jsp");
				rd.forward(request, response);
			}
			break;
			
		case "/studetail":
			if(checkLoginLib(request.getSession())){
			String uid = request.getParameter("userid");
			System.out.println(request.getParameter("userid"));
			User stu = usermgr.getOneUser(uid);
			System.out.println(stu.toString());
			session.setAttribute("stu", stu);
			/*request.setAttribute("stu", stu);*/
			rd = request.getRequestDispatcher("../jsp/studetail.jsp");
			rd.forward(request,response);
			}else{
				session.invalidate();
				rd=request.getRequestDispatcher("../jsp/login.jsp");
				rd.forward(request, response);
			}
			
		case "/updatestudent":
			if(checkLoginLib(request.getSession())){

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
			System.out.println(request.getParameter("password"));
			System.out.println(request.getParameter("confirmpassword"));
			if(request.getParameter("password").equals("")){
				ispasswordnull=true;
				
			}
			if(request.getParameter("studentname").equals("")){
				isusernamenull = true;
			}
			System.out.println(request.getParameter("studentname"));
			System.out.println((!ispasswordnull)&&ispasswordsame&&(!isusernamenull)&&isphonecorrect);
			if((!ispasswordnull)&&ispasswordsame&&(!isusernamenull)&&isphonecorrect){
				System.out.println("inform ok");
				userid = request.getParameter("studentid");
				username = request.getParameter("studentname");
				password = request.getParameter("password");
				roletype = request.getParameter("roletype");
				email = request.getParameter("email");
				phone = request.getParameter("phone");
				address = request.getParameter("address");
				status = request.getParameter("status");
				User updateuser= new User(userid, username, password, roletype, status, address, email, phone);
				System.out.println(updateuser);
				usermgr.updateStudent(updateuser);
				rd =request.getRequestDispatcher("maintainstudent");
				rd.forward(request,response);
				break;
			}else{

				request.setAttribute("ispasswordsame", ispasswordsame);
				System.out.println(ispasswordsame);
				request.setAttribute("ispasswordnull", ispasswordnull);
				System.out.println(ispasswordnull);
				request.setAttribute("isusernamenull", isusernamenull);
				System.out.println(isusernamenull);
				request.setAttribute("isphonecorrect", isphonecorrect);
				rd = request.getRequestDispatcher("../jsp/studetail.jsp");
				rd.forward(request, response);
				
			}
			}else{
				session.invalidate();
				rd=request.getRequestDispatcher("../jsp/login.jsp");
				rd.forward(request, response);
			}

			
		case "/createstudent":
			if(checkLoginLib(request.getSession())){
			if(request.getParameter("studentid").length()!=8){
				isidright = false;
			}else{
				try{
					Integer.parseInt(request.getParameter("studentid"));
				}catch(Exception e){
					isidright = false;
				}
			}
			System.out.println(isidright);
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
				userid = ("S"+request.getParameter("studentid"));
				username = request.getParameter("studentname");
				password = request.getParameter("password");
				roletype = "student";
				email = request.getParameter("email");
				phone = request.getParameter("phone");
				address = request.getParameter("address");
				status = "1";
				
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
					iscurrentuser = false;
					usermgr.createUser(newuser);
					rd = request.getRequestDispatcher("maintainstudent");
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
			}else{
				session.invalidate();
				rd=request.getRequestDispatcher("../jsp/login.jsp");
				rd.forward(request, response);
			}
					
				
		}
		
	}
	protected boolean checkLoginStu(HttpSession session){
		User loguser=(User)session.getAttribute("loginuser");
		try{
		if(loguser.getRole().equals("student"))
			return true;
		else
			return false;
		}
		catch(Exception exception){return false;}
	}
	protected boolean checkLoginLib(HttpSession session){
		User loguser=(User)session.getAttribute("loginuser");
		
		try{
			if(loguser.getRole().equals("librarian"))
				return true;
			
			else
				return false;
			}
			catch(Exception exception){return false;}
	}
}


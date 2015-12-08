package controller;

import java.awt.Checkbox;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import biz.ItemsManager;
import biz.UserManager;
import dto.Items;
import dto.User;


/**
 * Servlet implementation class ItemsController
 */
@WebServlet("/items/*")
public class ItemsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		System.out.println("PATH is" + path);
		HttpSession session;
		session=request.getSession();
		UserManager UM=new UserManager();
		User loguser;
		User user;
		try{		
		loguser=(User)session.getAttribute("loginuser");
		user=UM.getOneUser(loguser.getUserId());
		session.setAttribute("loginuser", user);}
		catch(Exception e){}		
		ItemsManager mgr = new ItemsManager();
		RequestDispatcher rd = null;
		Items itm; int result=0; boolean istitlenull = false;
		switch (path) {		
			
		case "/homesearch":
			
			if(request.getParameter("title").equals("")){
				istitlenull = true;
				request.setAttribute("istitlenull", istitlenull);
				rd = request.getRequestDispatcher("../jsp/HomePage.jsp");				
			}else{
			ArrayList<Items> itmlist = mgr.searchItemsByTitle(request.getParameter("title"));
			request.setAttribute("itmlist", itmlist);
			rd = request.getRequestDispatcher("../jsp/HomeSearch.jsp");			
			}	
			rd.forward(request, response);
			break;
		
		case "/maintainsearch":
			
			String i = request.getParameter("itemNumber");
			String sts = request.getParameter("itemstatus");	
			
			System.out.println(request.getParameter("itemNumber"));
			System.out.println(request.getParameter("itemstatus"));
			
			if(i.length() !=0 && sts.equals("-1")){
				try{
					itm = mgr.getOneItems(Integer.parseInt(request.getParameter("itemNumber")));
					HttpSession session1 = request.getSession();
					session1.setAttribute("itmobj", itm);
					rd = request.getRequestDispatcher("../jsp/ItemDetail.jsp");
				}catch(Exception e){
					System.out.println("Incorrect!");
					boolean isNumber = false;
					request.setAttribute("isNumber", isNumber);
					rd = request.getRequestDispatcher("../jsp/MaintainItem.jsp");
				}				
				rd.forward(request, response);
				break;
			}
			ArrayList<Items> list1 = null;
			if(i.length()== 0 && sts.equals("-1")){
				System.out.println("here..");
				list1 = mgr.getAllItems();
			}else if(i.length()==0 && sts != "-1"){
				list1 = mgr.searchItemByStatus(request.getParameter("itemstatus"));
				
			}else if(i.length()!=0 && sts != "-1"){
				list1 = mgr.searchItembyStatusItemNumber(request.getParameter("itemstatus"), 
						Integer.parseInt(request.getParameter("itemNumber")));
			}
			
			request.setAttribute("itmlist", list1);
			rd = request.getRequestDispatcher("../jsp/MaintainItem.jsp");
			rd.forward(request, response);
			break;

		case "/searchresult":	
			session=request.getSession();
			session.setAttribute("homelist", null);
			
			String t = request.getParameter("title"); 
			int id = Integer.parseInt(request.getParameter("itemtypeID"));
			String s = request.getParameter("itemstatus");	
			ArrayList<Items> list;

			
			if(t.length()==0 && s.equals("-1") && id == -1){
				//get All Items
				list = mgr.getAllItems();
				
			}else if((t.length()!=0) && s.equals("-1") && id == -1){
				//get Item By Title
				list = mgr.searchItemsByTitle(request.getParameter("title"));
				
			}else if((t.length()==0) && s != "-1" && id == -1){
				//get Item By Status
				list = mgr.searchItemByStatus(request.getParameter("itemstatus"));
				
			}else if((t.length()==0) && s.equals("-1") && id != -1){
				//get Item By ItemType
				list = mgr.searchItemsByItemType(Integer.parseInt(request.getParameter("itemtypeID")));
				
			}else if((t.length()!=0) && s.equals("-1") && id != -1){
				//get Item By title&ItemType
				list = mgr.searchItembyTitleItemType(request.getParameter("title"), Integer.parseInt(request.getParameter("itemtypeID")));
				
			}else if((t.length()!=0) && s != "-1" && id == -1){
				//get Item By title&status
				list = mgr.searchItembyTitleStatus(request.getParameter("title"), request.getParameter("itemstatus"));
				
			}else if((t.length()== 0) && s != "-1" && id != -1){
				//get Item By Status&ItemType				
				list = mgr.searchItembyStatusItemType(request.getParameter("itemstatus"),Integer.parseInt(request.getParameter("itemtypeID")) );			
				
			}else{
				//get ItemBy FullCriteria
				list = mgr.searchItemsByFullCriteria(request.getParameter("title"), Integer.parseInt(request.getParameter("itemtypeID")), request.getParameter("itemstatus"));
			}
			request.setAttribute("itmlist", list);	
			
			System.out.println(request.getSession().getAttribute("loginuser"));
			
			if(null == request.getSession().getAttribute("loginuser")){
				rd = request.getRequestDispatcher("../jsp/HomeSearch.jsp");
			}else{
				User u = (User)request.getSession().getAttribute("loginuser");
				System.out.println(u.getUserId().substring(0, 1));
				String role = u.getUserId().substring(0, 1);
				if(role.equals("S")){
					System.out.println("i m here.");
					rd = request.getRequestDispatcher("../jsp/stusearch.jsp");
				}else if(role.equals("L")){
					rd = request.getRequestDispatcher("../jsp/libsearch.jsp");
				}
			}		
			
			rd.forward(request, response);
			break;
			
		case "/edit":
			
			itm = mgr.getOneItems(Integer.parseInt(request.getParameter("itemNumber")));
			System.out.println(itm.getAuthor());
			//HttpSession session = request.getSession();
			//session.setAttribute("itmobj", itm);
			
			request.setAttribute("itmobj", itm);
			rd = request.getRequestDispatcher("../jsp/ItemDetail.jsp");
			rd.forward(request, response);	
			break;
			
		case "/update":
			if(checkLoginLib(request.getSession())){
				
			
			itm = new Items();
			itm.setItemNumber(Integer.parseInt(request.getParameter("itemNumber")));			
			itm.setTitle(request.getParameter("title"));
			itm.setAuthor(request.getParameter("author"));
			itm.setPublisher(request.getParameter("publisher"));
			itm.setYear(request.getParameter("year"));
			itm.setDescription(request.getParameter("description"));
			itm.setIsbn(request.getParameter("isbn"));		
			itm.setItemstatus(request.getParameter("itemstatus"));			
			
			result = mgr.updateItems(itm);
			rd = request.getRequestDispatcher("../jsp/MaintainItem.jsp");
			rd.forward(request, response);
			}else{
				session.invalidate();
				rd=request.getRequestDispatcher("../jsp/login.jsp");
				rd.forward(request, response);
			}
			break;
			
			
		case "/add":
			if(checkLoginLib(request.getSession())){
			boolean isauthornull = false;
			boolean isyearcorrect = false;

			if (request.getParameter("title").equals("")) {
				istitlenull = true;
			}
			if(request.getParameter("author").equals("")){
				isauthornull = true;
			}
			
			if(request.getParameter("year").length() != 0){
				if(request.getParameter("year").length() == 4){
					try{
						Integer.parseInt(request.getParameter("year"));
						isyearcorrect = true;
					}catch(Exception e){
						isyearcorrect = false;
					}
				}
			}
			System.out.println(1);
			itm = new Items();
			System.out.println(!istitlenull && !isauthornull);
			if (!istitlenull && !isauthornull) {
				System.out.println(2);
				itm.setTitle(request.getParameter("title"));
				itm.setAuthor(request.getParameter("author"));
				
				itm.setPublisher(request.getParameter("publisher"));
				System.out.println(2);
				itm.setYear(request.getParameter("year"));
				itm.setDescription(request.getParameter("description"));
				System.out.println(3);
				itm.setIsbn(request.getParameter("isbn"));
				itm.setItemtypeID(Integer.parseInt(request.getParameter("itemtypeID")));
				itm.setItemstatus(request.getParameter("itemstatus"));
				result = mgr.createItems(itm);
				System.out.println(result);				
				rd = request.getRequestDispatcher("../jsp/libsearch.jsp");
				rd.forward(request, response);
				
				} else {
					System.out.println(3);
				request.setAttribute("istitlenull", istitlenull);
				request.setAttribute("isauthornull", isauthornull);
				request.setAttribute("isyearcorrect", isyearcorrect);
				System.out.println(4);
				rd = request.getRequestDispatcher("../jsp/CreateItem.jsp");
				System.out.println(5);
				rd.forward(request, response);				
			
			}
			
			}else{
				session.invalidate();
				rd=request.getRequestDispatcher("../jsp/login.jsp");
				rd.forward(request, response);
			}
			break;
	
		default:
			throw new ServletException("404");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
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

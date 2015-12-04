package controller;

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
		
		ItemsManager mgr = new ItemsManager();
		RequestDispatcher rd = null;
		Items itm; int result=0;
		switch (path) {
		
		//testing......
		case "/list":
			ArrayList<Items> testing = mgr.getAllItems();
			request.setAttribute("itmlist", testing);
			rd = request.getRequestDispatcher("/ItemsList.jsp");
			rd.forward(request, response);
			break;
		case "/homesearch":
			ArrayList<Items> itmlist = mgr.searchItemsByTitle(request.getParameter("title"));
			request.setAttribute("itmlist", itmlist);
			System.out.println(request.getParameter("title"));
			rd = request.getRequestDispatcher("../jsp/HomeSearch.jsp");
			rd.forward(request, response);	
			break;
		
		case "/maintainsearch":
			System.out.println(request.getParameter("itemNumber"));
			System.out.println(request.getParameter("itemstatus"));
			
			String i = request.getParameter("itemNumber");
			String sts = request.getParameter("itemstatus");
			
			if(i.length()== 0 && sts.equals("-1")){
				ArrayList<Items> list = mgr.getAllItems();
				request.setAttribute("itmlist", list);
				rd = request.getRequestDispatcher("../jsp/MaintainItem.jsp");
				rd.forward(request, response);
			}else if(i.length()==0 && sts != "-1"){
				ArrayList<Items> list = mgr.searchItemByStatus(request.getParameter("itemstatus"));
				request.setAttribute("itmlist", list);
				rd = request.getRequestDispatcher("../jsp/MaintainItem.jsp");
				rd.forward(request, response);
				
			}else if(i.length()!=0 && sts != "-1"){
				ArrayList<Items> list = mgr.searchItembyStatusItemNumber(request.getParameter("itemstatus"), Integer.parseInt(request.getParameter("itemNumber")));
				request.setAttribute("itmlist", list);
				rd = request.getRequestDispatcher("../jsp/MaintainItem.jsp");
				rd.forward(request, response);
			}
			break;			
			
		case "/searchresult":	
			
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
			HttpSession session = request.getSession();
			session.setAttribute("itmobj", itm);
			rd = request.getRequestDispatcher("../jsp/ItemDetail.jsp");
			rd.forward(request, response);	
			break;
			
		case "/update":
			itm = new Items();
			itm.setItemNumber(Integer.parseInt(request.getParameter("itemNumber")));
			itm.setTitle(request.getParameter("title"));
			itm.setAuthor(request.getParameter("author"));
			itm.setPublisher(request.getParameter("publisher"));
			itm.setYear(request.getParameter("year"));
			itm.setDescription(request.getParameter("description"));
			itm.setIsbn(request.getParameter("isbn"));
			itm.setItemtypeID(Integer.parseInt(request.getParameter("itemtypeID")));
			itm.setItemstatus(request.getParameter("itemstatus"));
			result = mgr.updateItems(itm);
			System.out.println(result);
			break;
		case "/add":
			itm = new Items();
//			itm.setItemNumber(request.getParameter("itemnumber"));
			itm.setTitle(request.getParameter("title"));
			itm.setAuthor(request.getParameter("author"));
			itm.setPublisher(request.getParameter("publisher"));
			itm.setYear(request.getParameter("year"));
			itm.setDescription(request.getParameter("description"));
			itm.setIsbn(request.getParameter("isbn"));
			itm.setItemtypeID(Integer.parseInt(request.getParameter("itemtypeID")));
			itm.setItemstatus(request.getParameter("itemstatus"));			
			result = mgr.createItems(itm);			
			System.out.println(result);	
			break;		
	
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

}

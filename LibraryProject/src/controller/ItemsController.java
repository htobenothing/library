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
		case "/searchbytitle":
			ArrayList<Items> itmlist = mgr.searchItemsByTitle(request.getParameter("title"));
			request.setAttribute("itmlist", itmlist);
			System.out.println(request.getParameter("title"));
			rd = request.getRequestDispatcher("../jsp/HomeSearch.jsp");
			rd.forward(request, response);	
			break;
		case "/list":
			ArrayList<Items> list = mgr.getAllItems();
			request.setAttribute("itmlist", list);
			rd = request.getRequestDispatcher("/ItemsList.jsp");
			rd.forward(request, response);	
			break;
		case "/edit":
			itm = mgr.getOneItems(Integer.parseInt(request.getParameter("itemNumber")));
			System.out.println(itm.getAuthor());
			HttpSession session = request.getSession();
			session.setAttribute("itmobj", itm);
			rd = request.getRequestDispatcher("/EditItem.jsp");
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

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.catalina.connector.Request;

import biz.TransactionManager;
import dto.Transcation;

/**
 * Servlet implementation class TransactionControl
 */
@WebServlet("/transaction/*")
public class TransactionControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionControl() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		System.out.println("PATH is" + path);
		TransactionManager TM=new TransactionManager();
		RequestDispatcher rd = null;
		switch (path) {
		case "/viewtransaction":
			System.out.println("hehe");
			int itemType=Integer.parseInt(request.getParameter("Item Type"));
			
			int satus=Integer.parseInt(request.getParameter("Status"));System.out.println("hehehe");
			Date from=Date.valueOf(request.getParameter("startdate"));
			Date to=Date.valueOf(request.getParameter("enddate"));
			System.out.println(itemType+"\n"+satus+"\n"+from+"\n"+to);
			try {
				ArrayList<Transcation> list= TM.findTransactionByCondition(itemType, satus, from, to);
				request.setAttribute("slist", list);
				rd = request.getRequestDispatcher("../jsp/librariantransaction.jsp");for(Transcation t:list)System.out.println(t.toString());
				rd.forward(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

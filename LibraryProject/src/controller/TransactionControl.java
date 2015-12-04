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
import dao.tfactory;
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
		int transactionID;
		System.out.println("PATH is" + path);
		TransactionManager TM=new TransactionManager();
		RequestDispatcher rd = null;
		switch (path) {
		case "/viewtransactionlib":
			int itemType=Integer.parseInt(request.getParameter("Item Type"));			
			int satus=Integer.parseInt(request.getParameter("Status"));
			Date from;
			System.out.println(request.getParameter("startdate"));
			try{
				from=Date.valueOf(request.getParameter("startdate"));}
			catch (Exception e) {from=Date.valueOf("1800-01-01");} 
				
			Date to;
			try
			{
				to=Date.valueOf(request.getParameter("enddate"));}
			catch(Exception e){
				to=Date.valueOf("3000-01-01");
			}
			System.out.println(itemType+"\n"+satus+"\n"+from+"\n"+to);
			try {
				ArrayList<Transcation> list= TM.findTransactionByCondition(itemType, satus, from, to);
				ArrayList<TransactionWithEntity>list2=new ArrayList<TransactionWithEntity>();
				for(Transcation transcation:list){
					list2.add(new TransactionWithEntity(transcation));
				}
				request.setAttribute("slist", list2);
				rd = request.getRequestDispatcher("../jsp/librariantransaction.jsp");for(Transcation t:list)System.out.println(t.toString());
				rd.forward(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case"/returnlib":
			String userID=request.getParameter("studentid");			
			try{
				ArrayList<Transcation> list=TM.findTransactionByUserIDandNOTStatus(userID, "0");
	
				ArrayList<TransactionWithEntity>list2=new ArrayList<TransactionWithEntity>();
				for(Transcation transcation:list){
					list2.add(new TransactionWithEntity(transcation));
				}
				request.setAttribute("rlist", list2);
				rd = request.getRequestDispatcher("../jsp/libreturn.jsp");
				rd.forward(request, response);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			break;
		case"/returnlib2":
			transactionID=Integer.parseInt(request.getParameter("tansactionid"));
			try{
			Transcation t=TM.findTransactionByID(transactionID);
			TransactionWithEntity te=new TransactionWithEntity(t);
			Date returndate=new Date(System.currentTimeMillis());
			te.setReturnDate(returndate);
			request.setAttribute("returnID", te);
			long due=te.getReturnDate().getTime()/(24*60*60*1000)-te.getDueDate().getTime()/(24*60*60*1000);
			if(due<=0) due=0;
			request.setAttribute("duefee", "$"+due);
			request.setAttribute("type", "library");
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			rd = request.getRequestDispatcher("../jsp/returndetail.jsp");
			rd.forward(request, response);
		case"/returnlib3":
			transactionID=Integer.parseInt(request.getParameter("transactionid"));
			try{
				Transcation t=TM.findTransactionByID(transactionID);
				t.setReturnDate(new Date(System.currentTimeMillis()));
				TM.updateTransaction(t);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			if(request.getAttribute("type")=="library")
				rd = request.getRequestDispatcher("../jsp/libreturn.jsp");
			rd.forward(request, response);
			
			
		}
		
	}

}

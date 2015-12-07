package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.websocket.Session;

import org.apache.catalina.connector.Request;
import org.apache.tomcat.dbcp.pool2.impl.GenericKeyedObjectPool;

import biz.ItemsManager;
import biz.TransactionManager;
import biz.UserManager;
import dao.tfactory;
import dto.Items;
import dto.Transcation;
import dto.User;

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
		process(request,response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		TransactionManager TM=new TransactionManager();
		UserManager UM=new UserManager();
		User user=new User();;
		HttpSession session;
		session=request.getSession();
		User loguser;
		ItemsManager IM=new ItemsManager();
		int transactionID;
		loguser=(User)session.getAttribute("loginuser");
		try{
		
		user=UM.getOneUser(loguser.getUserId());
		session.setAttribute("loginuser", user);
		}
		catch(Exception e){}
				
		System.out.println("PATH is" + path);
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
			session=request.getSession();
			session.setAttribute("return", "library");
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			rd = request.getRequestDispatcher("../jsp/returndetail.jsp");
			rd.forward(request, response);
		case"/returnfinal":
			transactionID=Integer.parseInt(request.getParameter("transactionid"));
			session=request.getSession();
			try{				
				Transcation t=TM.findTransactionByID(transactionID);
				t.setReturnDate(new Date(System.currentTimeMillis()));
				TM.updateTransaction(t);
				request.setAttribute("returnID", null);
				System.out.println(request.getAttribute("type"));
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			if(session.getAttribute("return")=="library")
				rd = request.getRequestDispatcher("../jsp/libreturn.jsp");
			else if(session.getAttribute("return")=="student")
			{
				user=UM.getOneUser(loguser.getUserId());
				session.setAttribute("loginuser", user);	
				rd=request.getRequestDispatcher("../jsp/stureturn.jsp");
			}
			else 
				rd=request.getRequestDispatcher("../jsp/login.jsp");
			rd.forward(request, response);			
		case "/returnstu":
			session=request.getSession();
			loguser=(User)session.getAttribute("loginuser"); 
		
			try{
			ArrayList<Transcation> list=TM.findTransactionByUserIDandNOTStatus(loguser.getUserId(), "0");
			ArrayList<TransactionWithEntity>list2=new ArrayList<TransactionWithEntity>();
			for(Transcation transcation:list){
				list2.add(new TransactionWithEntity(transcation));
			}
			request.setAttribute("srlist", list2);
			rd = request.getRequestDispatcher("../jsp/stureturn.jsp");
			rd.forward(request, response);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			break;
		case"/returnstu2":
			transactionID=Integer.parseInt(request.getParameter("tansactionid"));
			session=request.getSession();
			try{
			Transcation t=TM.findTransactionByID(transactionID);
			TransactionWithEntity te=new TransactionWithEntity(t);
			Date returndate=new Date(System.currentTimeMillis());
			te.setReturnDate(returndate);
			request.setAttribute("returnID", te);
			long due=te.getReturnDate().getTime()/(24*60*60*1000)-te.getDueDate().getTime()/(24*60*60*1000);
			if(due<=0) due=0;
			request.setAttribute("duefee", "$"+due);
			session.setAttribute("return", "student");System.out.println(session.getAttribute("return"));
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			rd = request.getRequestDispatcher("../jsp/returndetail.jsp");
			rd.forward(request, response);
			break;
		
		case "/viewtransactionstu":		
			session=request.getSession();
			loguser=(User)session.getAttribute("loginuser"); 
			Date sfrom;
			System.out.println(request.getParameter("startdate"));
			try{
				sfrom=Date.valueOf(request.getParameter("startdate"));}
			catch (Exception e) {sfrom=Date.valueOf("1800-01-01");} 
			
			Date sto;
			try
			{
				sto=Date.valueOf(request.getParameter("enddate"));}
			catch(Exception e){
				sto=Date.valueOf("3000-01-01");
			}
			System.out.println("\n"+sfrom+"\n"+sto);
			try {
				ArrayList<Transcation> list= TM.findTransactionByTimeandUerID(loguser.getUserId(), sfrom, sto);
				ArrayList<TransactionWithEntity>list2=new ArrayList<TransactionWithEntity>();
				for(Transcation transcation:list)
				list2.add(new TransactionWithEntity(transcation));

				request.setAttribute("uslist", list2);
				rd = request.getRequestDispatcher("../jsp/stutransaction.jsp");for(Transcation t:list)System.out.println(t.toString());
				rd.forward(request, response);
			}
			
			catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/viewonloantransactionstu":
			session=request.getSession();
			loguser=(User)session.getAttribute("loginuser"); 
			try{
				ArrayList<Transcation> list=TM.findTransactionByUserIDandNOTStatus(loguser.getUserId(), "0");
				ArrayList<TransactionWithEntity>list2=new ArrayList<TransactionWithEntity>();
				for(Transcation transcation:list)
				list2.add(new TransactionWithEntity(transcation));
				request.setAttribute("ruslist", list2);
				rd = request.getRequestDispatcher("../jsp/stutransaction.jsp");for(Transcation t:list)System.out.println(t.toString());
				rd.forward(request, response);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		case "/renew":
			session=request.getSession();
			transactionID=Integer.parseInt(request.getParameter("transactionid"));
			try{
				System.out.println("try");
				TM.renewTransaction(TM.findTransactionByID(transactionID));
				System.out.println("finish");
				rd = request.getRequestDispatcher("../jsp/stutransaction.jsp");
				rd.forward(request, response);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			break;
		case "/stuborrow":
			session=request.getSession();
			loguser=(User)session.getAttribute("loginuser"); 
			System.out.println("hehe");
			String[] borrow=request.getParameterValues("borrow");
			user=UM.getOneUser(loguser.getUserId());
			try{
				if(user.getOnloanNumber()+borrow.length<=10)
				{
					ArrayList<Items> list=new ArrayList<Items>();
					for(int i=0;i<borrow.length;i++)
					{
						list.add(IM.getOneItems( Integer.parseInt(borrow[i])));					
					}				
					request.setAttribute("sblist", list);
					session.setAttribute("user", user);
					session.setAttribute("usertype", "student");
					rd = request.getRequestDispatcher("../jsp/borrowcomfirm.jsp");
					rd.forward(request, response);	
				}
				else {
					request.setAttribute("message", "Cannot borrow over 10 items!!!");
					rd = request.getRequestDispatcher("../jsp/stusearch.jsp");
					rd.forward(request, response);	
				}
			}
	
			catch (Exception e) {request.setAttribute("message","must select at least one item!");
			rd = request.getRequestDispatcher("../jsp/stusearch.jsp");
			rd.forward(request, response);
				// TODO: handle exception
			} 
			break;
		case "/finalborrow":
			session=request.getSession();
			User borrower=(User)session.getAttribute("user");
			String[] itemid=request.getParameterValues("confirm");
			Date now=new Date(System.currentTimeMillis());
			Calendar calendar=Calendar.getInstance();
			calendar.add(Calendar.DATE, 30);
			long b=calendar.getTimeInMillis();
			Date due=new Date(b);
			Transcation t=new Transcation();
			try{ 
				for(int i=0;i<itemid.length;i++){
					t.setUerID(borrower.getUserId()); 
					t.setIteamID(Integer.parseInt(itemid[i]));
					t.setLoanDate(now);
					t.setDueDate(due);
					TM.insertTransaction(t);}
				user=UM.getOneUser(borrower.getUserId());
				session.setAttribute("user", user);
			}
					
			
			catch (Exception e) {
				// TODO: handle exception
			}
			
			String type=(String)session.getAttribute("usertype");
			if (type=="student") {
				user=UM.getOneUser(loguser.getUserId());
				session.setAttribute("loginuser", user);	
				rd = request.getRequestDispatcher("../jsp/stusearch.jsp");	
			}
			else if(type=="library"){
				rd = request.getRequestDispatcher("../jsp/libsearch.jsp");
			}
			else {
				rd = request.getRequestDispatcher("../jsp/login.jsp");
			}
			session.setAttribute("usertype", null);
			session.setAttribute("user", null);
			rd.forward(request, response);
			break;
		case "/libborrow":
			session=request.getSession();
			User student=new User();
			student=UM.getOneUser(request.getParameter("sdutentID"));
			try{
				if(student.getUserName()!=null){
					String[] lborrow=request.getParameterValues("borrow");
					session.setAttribute("user", student);
					 if(student.getOnloanNumber()+lborrow.length<=10)
					{
						ArrayList<Items> list=new ArrayList<Items>();
						for(int i=0;i<lborrow.length;i++)
						{
							list.add(IM.getOneItems( Integer.parseInt(lborrow[i])));					
						}				
						request.setAttribute("sblist", list);
						
						session.setAttribute("usertype", "library");
						rd = request.getRequestDispatcher("../jsp/borrowcomfirm.jsp");
						rd.forward(request, response);	
					}
					else{
						request.setAttribute("message", "Cannot borrow over 10 items!!!");
						rd = request.getRequestDispatcher("../jsp/libsearch.jsp");
						rd.forward(request, response);
					}				
				}
				else {
					request.setAttribute("message", "Wrong student ID!!");
					rd = request.getRequestDispatcher("../jsp/libsearch.jsp");
					rd.forward(request, response);	
				}
			}
			
			catch (Exception e) {
				request.setAttribute("message", "Must select at least one item");
				rd = request.getRequestDispatcher("../jsp/libsearch.jsp");
				rd.forward(request, response);
			}
				
			break;
		case"/home":
			
			System.out.println("home");
			String[] search=request.getParameterValues("borrow");
			ArrayList<Items> list=new ArrayList<Items>();
			session=request.getSession();
			try{
			for(int i=0;i<search.length;i++)
			{
				list.add(IM.getOneItems(Integer.parseInt(search[i])));
			}
			session.setAttribute("homelist", list);}
			catch (Exception e) {
				session.setAttribute("homelist", null);
			}
			rd = request.getRequestDispatcher("../jsp/login.jsp");
			rd.forward(request, response);	
		}
	}	
}
		
	





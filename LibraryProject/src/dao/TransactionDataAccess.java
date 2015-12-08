package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.catalina.tribes.group.interceptors.StaticMembershipInterceptor;

import com.mysql.jdbc.Statement;

import dto.Transcation;

public class TransactionDataAccess implements TransactionDao {
	
	private static final String FINDER_ALL_QRY = "SELECT * FROM "+MySQLConstants.TRANSACTION_TABLE_NAME;
	private static final String FINDER_CONDITION_QRY = "SELECT * FROM "+MySQLConstants.TRANSACTION_TABLE_NAME+" Where ";
	private static final String UPDATE_QRY = "UPDATE library.transaction SET ";
	private static final String INSERT_QRY="INSERT INTO "+MySQLConstants.TRANSACTION_TABLE_NAME+" (`userID`,`itemNumber`,`status`,`loandate`,`duedate`) VALUES ";
	
	private Connection openConnection() {
		try {
			Class.forName(MySQLConstants.DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(MySQLConstants.URL, MySQLConstants.USER, MySQLConstants.PASSWORD);

		} catch (Exception e) {
			System.out.println("ERROR: Unable to Connect to Database.");
		}
		return connection;
	}
	@Override
	public ArrayList<Transcation> findAllTranscation() throws Exception {
		
		ArrayList<Transcation> result=new ArrayList<Transcation>();
		Connection conn=openConnection();
		Statement stmt=(Statement) conn.createStatement();
		ResultSet rs=stmt.executeQuery(FINDER_ALL_QRY);
		while (rs.next()) {
			Transcation t=new Transcation();
			t.setTransactionID(rs.getInt(1));
			t.setUerID(rs.getString(2));
			t.setIteamID(rs.getInt(3));
			t.setStatus(rs.getString(4));
			t.setLoanDate(rs.getDate(5));
			t.setDueDate(rs.getDate(6));
			t.setReturnDate(rs.getDate(7));
			result.add(t);
		}
		stmt.close();
		conn.close();
		return result;
		
	}

	@Override
	public ArrayList<Transcation> findTransactionByUserID(String userID) throws Exception {
		ArrayList<Transcation> result=new ArrayList<Transcation>();
		Connection conn=openConnection();
		Statement stmt=(Statement) conn.createStatement();
		ResultSet rs=stmt.executeQuery(FINDER_CONDITION_QRY+MySQLConstants.TRANSACTION_COL_USERID+" = \'"+userID+"\'");
		while (rs.next()) {
			Transcation t=new Transcation();
			t.setTransactionID(rs.getInt(1));
			t.setUerID(rs.getString(2));
			t.setIteamID(rs.getInt(3));
			t.setStatus(rs.getString(4));
			t.setLoanDate(rs.getDate(5));
			t.setDueDate(rs.getDate(6));
			t.setReturnDate(rs.getDate(7));
			result.add(t);
		}
		stmt.close();
		conn.close();
		return result;

	}

	@Override
	public ArrayList<Transcation> findTransactionByItemID(int iteamID) throws Exception {
		ArrayList<Transcation> result=new ArrayList<Transcation>();
		Connection conn=openConnection();
		Statement stmt=(Statement) conn.createStatement();
		ResultSet rs=stmt.executeQuery(FINDER_CONDITION_QRY+MySQLConstants.TRANSACTION_COL_ITEAMNUMBER+" = \'"+ iteamID+"\'");
		while (rs.next()) {
			Transcation t=new Transcation();
			t.setTransactionID(rs.getInt(1));
			t.setUerID(rs.getString(2));
			t.setIteamID(rs.getInt(3));
			t.setStatus(rs.getString(4));
			t.setLoanDate(rs.getDate(5));
			t.setDueDate(rs.getDate(6));
			t.setReturnDate(rs.getDate(7));
			result.add(t);
		}
		stmt.close();
		conn.close();
		return result;
	}

	@Override
	public ArrayList<Transcation> findTransactionByTime(Date from, Date to) throws Exception {
		ArrayList<Transcation> result=new ArrayList<Transcation>();
		Connection conn=openConnection();
		Statement stmt=(Statement) conn.createStatement();
		ResultSet rs=stmt.executeQuery(FINDER_CONDITION_QRY+MySQLConstants.TRANSACTION_COL_LOANDATE+" >= \'"+from +"\' and "+MySQLConstants.TRANSACTION_COL_LOANDATE+" <= \'"+to+"\'");
		
		while (rs.next()) {
			Transcation t=new Transcation();
			t.setTransactionID(rs.getInt(1));
			t.setUerID(rs.getString(2));
			t.setIteamID(rs.getInt(3));
			t.setStatus(rs.getString(4));
			t.setLoanDate(rs.getDate(5));
			t.setDueDate(rs.getDate(6));
			t.setReturnDate(rs.getDate(7));
			result.add(t);
		}
		stmt.close();
		conn.close();
		return result;
	}

	@Override
	public int updateTransaction(Transcation t) throws Exception {
		Connection conn=openConnection();
		Statement stmt=(Statement) conn.createStatement();
		int i=0;
		conn.setAutoCommit(false);
		try{
		String qry=UPDATE_QRY+" `"+MySQLConstants.TRANSACTION_COL_RETURNDATE+"` = \'"+t.getReturnDate()+"\', `"+MySQLConstants.TRANSACTION_COL_STATUS+"`=\'"+0+"\'";
		qry+=" where "+"`"+MySQLConstants.TRANSACTION_COL_TRANSACTIONDI+"`="+t.getTransactionID()+"";
		System.out.println(qry);
		i=stmt.executeUpdate(qry);
		String qry2="UPDATE `library`.`user`SET `onloannumber` = `onloannumber`-1 WHERE `userid` =\'"+t.getUerID()+"\'"; 
		System.out.println(qry2);
		stmt.executeUpdate(qry2);
		String qry3="UPDATE `library`.`items` SET `itemstatus` =\'1\' WHERE `itemNumber` ="+t.getIteamID();
		System.out.println(qry3);
		stmt.execute(qry3);
		conn.commit();
		}
		catch(Exception e){
			conn.rollback();
			}			
		stmt.close();
		conn.close();
		return i;
		
	}
	@Override
	public int insertTransaction(Transcation t) throws Exception{
		Connection conn;
		Statement stmt;
		conn=openConnection();
		stmt=(Statement) conn.createStatement();
		conn.setAutoCommit(false);
		int i=0;
		try{
			String qry1 = INSERT_QRY+" ( \'"+t.getUerID()+"\' ,\'"+t.getIteamID()+"\' ,\'"+1+"\',\'"+t.getLoanDate()+"\', \'"+t.getDueDate()+"\' )";
			
			
			System.out.println(qry1);
			
			i= stmt.executeUpdate(qry1);
			String qry2="UPDATE `library`.`user`SET `onloannumber` = `onloannumber`+1 WHERE `userid` =\'"+t.getUerID()+"\'"; 
			System.out.println(qry2);
			stmt.executeUpdate(qry2);
			String qry3="UPDATE `library`.`items` SET `itemstatus` =\'0\' WHERE `itemNumber` ="+t.getIteamID();
			System.out.println(qry3);
			stmt.execute(qry3);
			conn.commit();
		}
		catch(Exception exception){
			conn.rollback();
		}
			stmt.close();
			stmt.close();
			return i;		
	}
	public int overdueTransaction(int transsationID)throws Exception
	{
		String qry=UPDATE_QRY+" `"+MySQLConstants.TRANSACTION_COL_STATUS+"`= \'"+2+"\'";
		qry+="where" +" `"+MySQLConstants.TRANSACTION_COL_TRANSACTIONDI+"` = "+transsationID;
		Connection conn=openConnection();
		Statement stmt=(Statement) conn.createStatement();
		System.out.println(qry);
		int i=stmt.executeUpdate(qry);
		stmt.close();
		conn.close();
		return i;
		
	}
	@Override
	public ArrayList<Transcation> findTransactionByCondition(int itemType, int satus, Date from, Date to)
			throws Exception {
		Connection conn=openConnection();
		Statement stmt=(Statement) conn.createStatement();
		String qry="select * from library.transaction join library.items where items.itemNumber= transaction.itemnumber";
		if(itemType==-1&&satus==-1&&from==null&&to==null)
		{
			conn.close();
			stmt.close();
			return this.findAllTranscation();
		}
		else{ 
			ArrayList<Transcation> result=new ArrayList<Transcation>();
			if(itemType!=-1){
				qry+=" and "+MySQLConstants.ITEMS_COL_ITEMTYPEID+"="+itemType;
			}
			if(satus!=-1){
				qry+=" and "+MySQLConstants.TRANSACTION_COL_STATUS+"=\'"+satus+"\'";
			}else{}
			if(from!=null){
				qry+=" and "+MySQLConstants.TRANSACTION_COL_LOANDATE+" >= \'"+from +"\'";				
			}else{}
			if(to!=null){
				qry+=" and "+MySQLConstants.TRANSACTION_COL_LOANDATE+" <= \'"+to+"\'";
			}else{}
			System.out.println(qry);
			ResultSet rs=stmt.executeQuery(qry);
			
			while (rs.next()) {
				Transcation t=new Transcation();
				t.setTransactionID(rs.getInt(1));
				t.setUerID(rs.getString(2));
				t.setIteamID(rs.getInt(3));
				t.setStatus(rs.getString(4));
				t.setLoanDate(rs.getDate(5));
				t.setDueDate(rs.getDate(6));
				t.setReturnDate(rs.getDate(7));
				rs.getInt(8);
				rs.getString(9);
				rs.getString(10);
				rs.getString(11);
				rs.getString(12);
				rs.getString(13);
				rs.getString(14);
				rs.getInt(15);
				rs.getString(16);
				result.add(t);
			}
			stmt.close();
			conn.close();
			return result;
		}
	}
	@Override
	public ArrayList<Transcation> findTransactionByUserIDandStatus(String userID,String status) throws Exception {
		ArrayList<Transcation> result=new ArrayList<Transcation>();
		Connection conn=openConnection();
		Statement stmt=(Statement) conn.createStatement();
		ResultSet rs=stmt.executeQuery(FINDER_CONDITION_QRY+MySQLConstants.TRANSACTION_COL_USERID+" = \'"+userID+"\'"+" and "+MySQLConstants.TRANSACTION_COL_STATUS+"=\'"+status+"\'");
		while (rs.next()) {
			Transcation t=new Transcation();
			t.setTransactionID(rs.getInt(1));
			t.setUerID(rs.getString(2));
			t.setIteamID(rs.getInt(3));
			t.setStatus(rs.getString(4));
			t.setLoanDate(rs.getDate(5));
			t.setDueDate(rs.getDate(6));
			t.setReturnDate(rs.getDate(7));
			result.add(t);
		}
		stmt.close();
		conn.close();
		return result;
	}
	@Override
	public ArrayList<Transcation> findTransactionByUserIDandNOTStatus(String userID, String status) throws Exception {
		ArrayList<Transcation> result=new ArrayList<Transcation>();
		Connection conn=openConnection();
		Statement stmt=(Statement) conn.createStatement();System.out.println(FINDER_CONDITION_QRY+MySQLConstants.TRANSACTION_COL_USERID+" = \'"+userID+"\'"+" and "+MySQLConstants.TRANSACTION_COL_STATUS+"!=\'"+status+"\'");
		ResultSet rs=stmt.executeQuery(FINDER_CONDITION_QRY+MySQLConstants.TRANSACTION_COL_USERID+" = \'"+userID+"\'"+" and "+MySQLConstants.TRANSACTION_COL_STATUS+"!=\'"+status+"\'");
		while (rs.next()) {
			Transcation t=new Transcation();
			t.setTransactionID(rs.getInt(1));
			t.setUerID(rs.getString(2));
			t.setIteamID(rs.getInt(3));
			t.setStatus(rs.getString(4));
			t.setLoanDate(rs.getDate(5));
			t.setDueDate(rs.getDate(6));
			t.setReturnDate(rs.getDate(7));
			result.add(t);
		}
		stmt.close();
		conn.close();
		return result;
	}
	@Override
	public Transcation findTransactionByID(int transactionID) throws Exception {
		Transcation t=new Transcation();
		Connection conn=openConnection();
		Statement stmt=(Statement) conn.createStatement();
		ResultSet rs=stmt.executeQuery(FINDER_CONDITION_QRY+MySQLConstants.TRANSACTION_COL_TRANSACTIONDI+" = \'"+transactionID+"\'");
		while (rs.next()) {
			t.setTransactionID(rs.getInt(1));
			t.setUerID(rs.getString(2));
			t.setIteamID(rs.getInt(3));
			t.setStatus(rs.getString(4));
			t.setLoanDate(rs.getDate(5));
			t.setDueDate(rs.getDate(6));
			t.setReturnDate(rs.getDate(7));
		}
		stmt.close();
		conn.close();
		return t;
	}
	@Override
	public ArrayList<Transcation> findTransactionByTimeandUerID(String userID, Date from, Date to) throws Exception {
		ArrayList<Transcation> result=new ArrayList<Transcation>();
		Connection conn=openConnection();
		Statement stmt=(Statement) conn.createStatement();System.out.println(FINDER_CONDITION_QRY+MySQLConstants.TRANSACTION_COL_LOANDATE+" >= \'"+from +"\' and "+MySQLConstants.TRANSACTION_COL_LOANDATE+" <= \'"+to+"\' and "+MySQLConstants.TRANSACTION_COL_USERID+" = \'"+userID+"\'");
		ResultSet rs=stmt.executeQuery(FINDER_CONDITION_QRY+MySQLConstants.TRANSACTION_COL_LOANDATE+" >= \'"+from +"\' and "+MySQLConstants.TRANSACTION_COL_LOANDATE+" <= \'"+to+"\' and "+MySQLConstants.TRANSACTION_COL_USERID+" = \'"+userID+"\'");
		
		while (rs.next()) {
			Transcation t=new Transcation();
			t.setTransactionID(rs.getInt(1));
			t.setUerID(rs.getString(2));
			t.setIteamID(rs.getInt(3));
			t.setStatus(rs.getString(4));
			t.setLoanDate(rs.getDate(5));
			t.setDueDate(rs.getDate(6));
			t.setReturnDate(rs.getDate(7));
			result.add(t);
		}
		stmt.close();
		conn.close();
		return result;
	}
	@Override
	public int renewTransaction(Transcation t) throws Exception {
		
		if(t.getStatus().equals("1")){
			
		Connection conn=openConnection();
		Statement stmt=(Statement) conn.createStatement();
		int i=0;
		Date due=t.getDueDate();
		long r=due.getTime();
		r+=15*24*60*60*1000;
		Date renew=new Date(r);
		try{
			String qry=UPDATE_QRY+" `"+MySQLConstants.TRANSACTION_COL_STATUS+"`= \'"+3+"\' , "+MySQLConstants.TRANSACTION_COL_DUEDATE+" =\'"+renew+"\'";
			qry+="where" +" `"+MySQLConstants.TRANSACTION_COL_TRANSACTIONDI+"` = "+t.getTransactionID();
			System.out.println(qry);
			i=stmt.executeUpdate(qry);
			System.out.println("finish qry");
			stmt.close();
			conn.close();
			return i;
		}
		catch(Exception e){return i;}}
		return 0;
	}

}

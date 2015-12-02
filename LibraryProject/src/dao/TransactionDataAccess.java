package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.catalina.tribes.group.interceptors.StaticMembershipInterceptor;

import com.mysql.jdbc.Statement;

import dto.Transcation;

public class TransactionDataAccess implements TransactionDao {
	
	private static final String FINDER_ALL_QRY = "SELECT * FROM "+TSQLConstants.TRANSACTION_TABLE_NAME;
	private static final String FINDER_CONDITION_QRY = "SELECT * FROM "+TSQLConstants.TRANSACTION_TABLE_NAME+" Where ";
	private static final String UPDATE_QRY = "UPDATE library.transaction SET ";
	private static final String INSERT_QRY="INSERT INTO "+TSQLConstants.TRANSACTION_TABLE_NAME+" (`userID`,`itemNumber`,`status`,`loandate`,`duedate`) VALUES ";
	
	private Connection openConnection() {
		try {
			Class.forName(TSQLConstants.DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(TSQLConstants.URL, TSQLConstants.USER, TSQLConstants.PASSWORD);

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
		ResultSet rs=stmt.executeQuery(FINDER_CONDITION_QRY+TSQLConstants.TRANSACTION_COL_USERID+" = \'"+userID+"\'");
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
		ResultSet rs=stmt.executeQuery(FINDER_CONDITION_QRY+TSQLConstants.TRANSACTION_COL_ITEAMNUMBER+" = \'"+ iteamID+"\'");
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
		ResultSet rs=stmt.executeQuery(FINDER_CONDITION_QRY+TSQLConstants.TRANSACTION_COL_LOANDATE+" >= \'"+from +"\' and "+TSQLConstants.TRANSACTION_COL_LOANDATE+" <= \'"+to+"\'");
		
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
		String qry=UPDATE_QRY+" `"+TSQLConstants.TRANSACTION_COL_RETURNDATE+"` = \'"+t.getReturnDate()+"\', `"+TSQLConstants.TRANSACTION_COL_STATUS+"`=\'"+t.getStatus()+"\'";
		qry+=" where "+"`"+TSQLConstants.TRANSACTION_COL_TRANSACTIONDI+"`="+t.getTransactionID()+"";
		System.out.println(qry);
		Connection conn=openConnection();
		Statement stmt=(Statement) conn.createStatement();
		int i=stmt.executeUpdate(qry);
		stmt.close();
		conn.close();
		return i;
		
	}
	@Override
	public int insertTransaction(Transcation t) throws Exception{
		String qry = INSERT_QRY+" ( \'"+t.getUerID()+"\' ,\'"+t.getIteamID()+"\' ,\'"+t.getStatus()+"\',\'"+t.getLoanDate()+"\', \'"+t.getDueDate()+"\' )";
		Connection conn=openConnection();
		System.out.println(qry);
		Statement stmt=(Statement) conn.createStatement();		
		int i= stmt.executeUpdate(qry);
		stmt.close();
		stmt.close();
		return i;
	}
	public int overdueTransaction(int transsationID)throws Exception
	{
		String qry=UPDATE_QRY+" `"+TSQLConstants.TRANSACTION_COL_STATUS+"`= \'"+2+"\'";
		qry+="where" +" `"+TSQLConstants.TRANSACTION_COL_TRANSACTIONDI+"` = "+transsationID;
		Connection conn=openConnection();
		Statement stmt=(Statement) conn.createStatement();
		System.out.println(qry);
		int i=stmt.executeUpdate(qry);
		stmt.close();
		conn.close();
		return i;
		
	}

}

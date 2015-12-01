package dao;

import java.sql.Date;
import java.util.ArrayList;

import dto.Transcation;

public interface TransactionDao {
	public ArrayList<Transcation> findAllTranscation()throws Exception;
	public ArrayList<Transcation> findTransactionByUserID(String userID)throws Exception;
	public ArrayList<Transcation> findTransactionByItemID(int iteamID)throws Exception;
	public ArrayList<Transcation> findTransactionByTime(Date from,Date to) throws Exception;
	public int updateTransaction(Transcation t)throws Exception;
	public int insertTransaction(Transcation t)throws Exception;
	public int overdueTransaction(int transsationID)throws Exception;
}

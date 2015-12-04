package dao;

import java.sql.Date;
import java.util.ArrayList;

import dto.Transcation;
import dto.dto;

public interface TransactionDao {
	public ArrayList<Transcation> findAllTranscation()throws Exception;
	public ArrayList<Transcation> findTransactionByUserID(String userID)throws Exception;
	public ArrayList<Transcation> findTransactionByItemID(int iteamID)throws Exception;
	public ArrayList<Transcation> findTransactionByTime(Date from,Date to) throws Exception;
	public int updateTransaction(Transcation transactionID)throws Exception;
	public int insertTransaction(Transcation t)throws Exception;
	public int overdueTransaction(int transsationID)throws Exception;
	public ArrayList<Transcation> findTransactionByCondition(int itemType, int satus, Date from, Date to)throws Exception;
	public ArrayList<Transcation> findTransactionByUserIDandStatus(String userID, String status)throws Exception;
	public ArrayList<Transcation> findTransactionByUserIDandNOTStatus(String userID,String status) throws Exception;
	public Transcation findTransactionByID(int transactionID) throws Exception; 
	public ArrayList<Transcation> findTransactionByTimeandUerID(String uerID,Date from,Date to) throws Exception;
		
	}

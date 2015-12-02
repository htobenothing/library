package biz;

import java.sql.Date;
import java.util.ArrayList;

import dao.DAOFactory;
import dao.TransactionDao;
import dao.tfactory;
import dto.Transcation;

public class TransactionManager {
	public TransactionManager() {		
	}
	DAOFactory f =new DAOFactory();
	TransactionDao transactionDao=f.geTransactionDao();
	public ArrayList<Transcation> findAllTranscation()throws Exception{
		return transactionDao.findAllTranscation();
	}
	
	public ArrayList<Transcation> findTransactionByUserID(String userID)throws Exception{
		return transactionDao.findTransactionByUserID(userID);		
	}
	
	public ArrayList<Transcation> findTransactionByItemID(int iteamID)throws Exception{
		return transactionDao.findTransactionByItemID(iteamID);
	}
	
	public ArrayList<Transcation> findTransactionByTime(Date from,Date to) throws Exception{
		return transactionDao.findTransactionByTime(from, to);
	}
	
	public int updateTransaction(Transcation t)throws Exception{
		return transactionDao.updateTransaction(t);
	}
	
	public int insertTransaction(Transcation t)throws Exception{
		return transactionDao.insertTransaction(t);
	}
	
	public int overdueTransaction(int transsationID)throws Exception{
		return transactionDao.overdueTransaction(transsationID);
	}
	
	public ArrayList<Transcation> findTransactionByCondition(int itemType, int satus, Date from, Date to)throws Exception{
		return transactionDao.findTransactionByCondition(itemType, satus, from, to);
	}
	
}

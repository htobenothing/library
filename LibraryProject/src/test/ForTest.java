package test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.spi.CalendarDataProvider;

import javax.swing.JSpinner.DateEditor;

import org.apache.catalina.util.ConcurrentDateFormat;

import biz.TransactionManager;
import dao.TransactionDao;
import dao.TransactionDataAccess;
import dto.Transcation;

public class ForTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TransactionManager mt=new TransactionManager();
		Calendar calendar=Calendar.getInstance();
		long a=System.currentTimeMillis();
		Date d1=new Date(a);
		calendar.add(Calendar.DATE, 30);
		long b=calendar.getTimeInMillis();
		Date d2=new Date(b);
		Date date=new Date(100, 8, 1);
		System.out.println(d1+"\n"+d2);
		
		Transcation t=new Transcation("S10203024", 3, "1",d1 , d2, null);
		//ArrayList<Transcation>list= new ArrayList<>();
		//list=dao.findAllTranscation();
		//for(Transcation t1:list){System.out.println(t1.toString());}
		//dao.updateTransaction(t);
		mt.insertTransaction(t);
	}

}

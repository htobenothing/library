package dto;

import java.io.Serializable;
import java.sql.Date;

public class Transcation implements Serializable
{
	private int transactionID;
 	private String uerID;
 	private int itemNumber;
 	private String status;
 	private Date loanDate;
 	private Date dueDate;
 	private Date returnDate;
 	
 	public Transcation() {
 		super();
 	}
 
 	public Transcation(int transactionID, String uerID, int itemNumber, String status, Date loanDate, Date dueDate,
		Date returnDate) {
	super();
	this.transactionID = transactionID;
	this.uerID = uerID;
	this.itemNumber = itemNumber;
	this.status = status;
	this.loanDate = loanDate;
	this.dueDate = dueDate;
	this.returnDate = returnDate;
	}

 	public Transcation(String uerID, int iteamID, String status, Date loanDate, Date dueDate,
		Date returnDate)
	{
 		this(0, uerID, iteamID, status, loanDate, dueDate, returnDate);
	}
 	public int getTransactionID() {
 		return transactionID;
 	}

 	public void setTransactionID(int transactionID) {
 		this.transactionID = transactionID;
 	}
 	public String getUerID() {
 		return uerID;
 	}
 	public void setUerID(String uerID) {
 		this.uerID = uerID;
 	}
 	public int getIteamID() {
 		return itemNumber;
 	}
 	public void setIteamID(int iteamID) {
 		this.itemNumber = iteamID;
 	}
 	public String getStatus() {
 		return status;
 	}
 	public void setStatus(String status) {
 		this.status = status;
 	}
 	public Date getLoanDate() {
 		return loanDate;
 	}
 	public void setLoanDate(Date loanDate) {
 		this.loanDate = loanDate;
 	}
 	public Date getDueDate() {
 		return dueDate;
 	}
 	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
 	}
	public Date getReturnDate() {
		return returnDate;
}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
}
	@Override
	public String toString() {
	return "TranscationDto [transactionID=" + transactionID + ", uerID=" + uerID + ", iteamID=" + itemNumber + ", status="
			+ status + ", loanDate=" + loanDate + ", dueDate=" + dueDate + ", returnDate=" + returnDate + "]";
}
}

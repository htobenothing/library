package dto;

import java.sql.Date;

import biz.ItemsManager;
import biz.UserManager;

public class TransactionWithEntity {
	private int tranasctionID;
	private int itemID;
	private String title;
	private String username;
	private Date borrowDate;
	private Date returnDate;
	private Date dueDate;
	private String statusNumber;
	private String status;
	private int itemType;
	private ItemsManager IM=new ItemsManager();
	private UserManager UM=new UserManager();
	public TransactionWithEntity(Transcation t) {
		super();
		this.tranasctionID = t.getTransactionID();
		this.itemID = t.getIteamID();				
		this.borrowDate = t.getLoanDate();
		this.returnDate = t.getReturnDate();
		this.dueDate = t.getDueDate();
		this.statusNumber=t.getStatus();
		this.status = this.Status(t.getStatus());
		this.itemType = IM.getOneItems(t.getIteamID()).getItemtypeID();
		this.title = IM.getOneItems(t.getIteamID()).getTitle();
		this.username = UM.getOneUser(t.getUerID()).getUserName();
	}
	public int getTranasctionID() {
		return tranasctionID;
	}
	public void setTranasctionID(int tranasctionID) {
		this.tranasctionID = tranasctionID;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getItemType() {
		return itemType;
	}
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
	private String Status(String status){
		switch (status) {
		case "0":
			return "return";
		case "1":
			return "on loan";
		case "2":
			return "overdue";
		case "3":
			return"renew";			
		default:
			return null;
		}
	}
	@Override
	public String toString() {
		return "TransactionWithEntity [tranasctionID=" + tranasctionID + ", itemID=" + itemID + ", title=" + title
				+ ", username=" + username + ", borrowDate=" + borrowDate + ", returnDate=" + returnDate + ", dueDate="
				+ dueDate + ", status=" + status + ", itemType=" + itemType + ", IM=" + IM + ", UM=" + UM + "]";
	}
	public String getStatusNumber() {
		return statusNumber;
	}
	public void setStatusNumber(String statusNumber) {
		this.statusNumber = statusNumber;
	}
	
	
	
	
}

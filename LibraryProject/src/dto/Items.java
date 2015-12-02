package dto;

public class Items {
	//Attributes
	private int itemNumber;
	private String title;
	private String author;
	private String publisher;
	private String year;
	private String description;
	private String isbn;
	private int itemtypeID;
	
	private String itemstatus;
	
	//Constructors
	public Items() {
		super();
	}
	public Items(int itemNumber, String title, String author, String publisher, String year, String description,
			String isbn, int itemtypeID, String itemstatus) {
		super();
		this.itemNumber = itemNumber;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.year = year;
		this.description = description;
		this.isbn = isbn;
		this.itemtypeID = itemtypeID;
		this.itemstatus = itemstatus;
	}
	
	//Getter-Setter
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getItemstatus() {
		return itemstatus;
	}
	public void setItemstatus(String itemstatus) {
		this.itemstatus = itemstatus;
	}
	public int getItemtypeID() {
		return itemtypeID;
	}
	public void setItemtypeID(int itemtypeID) {
		this.itemtypeID = itemtypeID;
	}
}

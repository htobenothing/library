package dao;

public class tfactory {
	public TransactionDao geTransactionDao()
	{
		return new TransactionDataAccess();
	}
}

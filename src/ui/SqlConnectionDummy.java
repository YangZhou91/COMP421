package ui;
import java.util.Date;
import java.util.List;

public class SqlConnectionDummy {
	
	public SqlConnectionDummy ()
	{
		
	}
	
	public boolean addBook(Book book)
	{
		return false;
		
	}
	
	public boolean editCreditCard(String email, CreditCard card) 
	{
		return false;
		
	}
	
	public List<String> getPurchases(Date date) 
	{
		return null;
		
	}
	
	public boolean increaseCategoryPrice(int category, float price)
	{
		return false;
		
	}
	
	public boolean increasePublisherPrice(String publisherName, float price)
	{
		return false;
		
	}
	
	public float getTotalGiftCardPurchases()
	{
		return 0;
		
	}
	
	public boolean rewardPointsPromotion(int amount)
	{
		return false;
		
	}
	
	public void close()
	{
		
	}


}

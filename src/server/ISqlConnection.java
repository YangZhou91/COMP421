package server;

import java.util.Date;
import java.util.List;

public interface ISqlConnection {
	
	public boolean addBook(Book book);
	
	public boolean editCreditCard(String email, CreditCard card);
	
	public List<String> getPurchases(Date date);
	
	public boolean increaseCategoryPrice(int category, float price);
	
	public boolean increasePublisherPrice(String publisherName, float price);
	
	public float getTotalGiftCardPurchases();
	
	public boolean rewardPointsPromotion(int amount);
	
	public void close();


}

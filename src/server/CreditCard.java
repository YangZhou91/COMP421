package server;


import java.util.Date;

public class CreditCard {
	
	private final int cid = 1;
	private int number;
	private Date expiry;
	private String name;
	private int cvv;
	private String billing;
	
	public CreditCard(int number, Date expiry, String name, int cvv, String billing)
	{
		this.number = number;
		this.expiry = expiry;
		this.name = name;
		this.cvv = cvv;
		this.billing = billing;
	}
	
	public void setNumber(int number)
	{
		this.number = number;
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public void setExpiry(Date expiry)
	{
		this.expiry = expiry;
	}
	
	public Date getExpiry()
	{
		return expiry;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setCvv(int cvv)
	{
		this.cvv = cvv;
	}
	
	public int getCvv()
	{
		return cvv;
	}
	
	public void setBilling(String billing)
	{
		this.billing = billing;
	}
	
	public String getBilling()
	{
		return billing;
	}

}

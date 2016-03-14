package server;


import java.util.Date;

public class Book {
	
	private int isbn13;
	private String name;
	private String authors;
	private String publisher;
	private Date publicationDate;
	private String description;
	private String edition;
	private int copies;
	private float price;
	private String language;
	private int categories;
	
	public Book(int isbn, String name, String authors, String publisher, Date publicationDate, String description, String edition, int copies, float price, String language, int categories)
	{
		this.isbn13 = isbn;
		this.name = name;
		this.authors = authors;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
		this.description = description;
		this.edition = edition;
		this.copies = copies;
		this.price = price;
		this.language = language;
		this.categories = categories;
	}
	
	public void setIsbn13(int isbn)
	{
		this.isbn13 = isbn;
	}
	
	public int getIsbn13()
	{
		return isbn13;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setAuthors(String authors)
	{
		this.authors = authors;
	}
	
	public String getAuthors()
	{
		return authors;
	}
	
	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}
	
	public String getPublisher()
	{
		return publisher;
	}
	
	public void setPublicationDate(Date pubDate)
	{
		this.publicationDate = pubDate;
	}
	
	public Date getPublicationDate()
	{
		return publicationDate;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setEdition(String edition)
	{
		this.edition = edition;
	}
	
	public String getEdition()
	{
		return edition;
	}
	
	public void setCopies(int copies)
	{
		this.copies = copies;
	}
	
	public int getCopies()
	{
		return copies;
	}
	
	public void setPrice(float price)
	{
		this.price = price;
	}
	
	public float getPrice()
	{
		return price;
	}
	
	public void setLanguage(String language)
	{
		this.language = language;
	}
	
	public String getLanguage()
	{
		return language;
	}
	
	public void setCategories(int categories)
	{
		this.categories = categories;
	}
	
	public int getCategories()
	{
		return categories;
	}
	

}

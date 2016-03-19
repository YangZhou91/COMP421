package server;


import java.util.Date;

public class Book {
	
	private String isbn13;
	private String name;
	private String authors;
	private String publisher;
	private Date publicationDate;
	private String description;
	private int edition;
	private int copies;
	private float price;
	private String language;
	private int categories;
	
	public Book(String isbn, String name, String authors, String publisher, Date publicationDate, String description, int edition, int copies, float price, String language, int categories)
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
	
	public void setIsbn13(String isbn)
	{
		this.isbn13 = isbn;
	}
	
	public String getIsbn13()
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
	
	public void setEdition(int edition)
	{
		this.edition = edition;
	}
	
	public int getEdition()
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

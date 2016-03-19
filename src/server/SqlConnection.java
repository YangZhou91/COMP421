package server;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class SqlConnection {
    
    private Statement stmt;
    
    public SqlConnection() throws SQLException {
        
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (Exception cnfe) {
            System.out.println("Class not found");
        }
        
        //Postgresql connection info
        String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
        String usernamestring = "cs421g28";
        String passwordstring = "g28@comp421";
        Connection con = DriverManager.getConnection(url, usernamestring, passwordstring);
        
        this.stmt = con.createStatement();
        
    }
    
        
    public boolean addBook(Book book) throws SQLException {
        String str = "INSERT INTO book (isbn, authors, name, publisher, publication_date, edition, price, quantity, description, language, categories) " +
            "VALUES ('" + book.getIsbn13() + "', '" + book.getAuthors() + "', '" + book.getName() + "', '" + book.getPublisher() + "', '" +
            book.getPublicationDate() + "', " + book.getEdition() + ", " + book.getPrice() + ", " + book.getCopies() + ", '" +
            book.getDescription() + "', '"  + book.getLanguage() + "', " + book.getCategories() +
            ")";
        stmt.executeUpdate(str);
        return true;
    }
 
    public boolean editCreditCard(String email, CreditCard card) throws SQLException {

        String str1 = "SELECT creditcard_id FROM payment_method WHERE pid IN (SELECT payment_method FROM customer WHERE email = '" + email + "')";

        ResultSet rs = stmt.executeQuery(str1);
        if (!rs.next()) {
            throw new SQLException();
        }

        String str2 = "UPDATE credit_card SET card_number = " + card.getNumber() + ", expiry_date = '" + card.getExpiry() + "', name = '" + card.getName() +
            "', cvv = " + card.getCvv() + ", billing_address = '" + card.getBilling() + "' WHERE cid = " + card.getCid();
        stmt.executeUpdate(str2);
        
        return true;
    }
 
    public List<String> getPurchases(Date date) throws SQLException {
        String str = "SELECT pid, shipping_method, total FROM purchase WHERE date = '" + date + "'";
        ResultSet rs = stmt.executeQuery(str);
        
        Boolean bool = rs.next();
        if (!bool) {
            throw new SQLException();
        }
        
        ArrayList<String> output = new ArrayList<String>(100);
        
        while (bool) {
            output.add("pid: " + String.valueOf(rs.getInt("pid")));
            output.add("shipping method: " + rs.getString("shipping_method"));
            output.add("total: " + String.valueOf(rs.getFloat("total")));
            bool = rs.next();
        }
            
        return output;
    }
 
    public boolean increaseCategoryPrice(int category, float price) throws SQLException{
        String str = "UPDATE book SET price = price + " + String.valueOf(price) + " WHERE categories = " + category;
        stmt.executeUpdate(str);
        
        return true;
    }
 
    public boolean increasePublisherPrice(String publisherName, float price) throws SQLException {
        String str = "UPDATE book SET price = price + " + String.valueOf(price) + " WHERE publisher = '" + publisherName + "'";
        stmt.executeUpdate(str);
        
        return true;
    }
 
    public float getTotalGiftCardPurchases() throws SQLException {
        String str = "SELECT SUM(total) FROM purchase WHERE pid IN (SELECT pid FROM payment_method WHERE giftcard_id IS NOT NULL)";
        ResultSet rs = stmt.executeQuery(str);
        if (!rs.next()) {
            return (float) 0.0;
        } else {
            return rs.getFloat(1);
        }
    }
 
    public boolean rewardPointsPromotion(Date date, int amount) throws SQLException {
        String str1 = "SELECT user_id FROM payment_method WHERE giftcard_id IS NOT NULL AND pid IN (SELECT payment_method_id FROM purchase " +
            "WHERE date = '" + date + "')";
        String str2 = "UPDATE \"user\" SET reward_point = reward_point + " + String.valueOf(amount) + " WHERE uid IN (" + str1 + ")";

        stmt.executeUpdate(str2);
        
        return true;
    }
    
    public void updatePurchases (int pid) throws SQLException {
        String str = "UPDATE purchase SET shipping_method = 'Expedited' WHERE pid = " + String.valueOf(pid);
        stmt.executeUpdate(str);
        
    }
 
    public void close() {
    }



}

package ui;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridLayout;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import server.Book;
import server.CreditCard;
import server.SqlConnection;

import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class MainWindow {
	

	private SqlConnection sql;
	
	protected Shell shlBookstoreApplication;
	private Text txtIsbn;
	private Text txtAuthors;
	private Text txtPublisher;
	private Text txtDescription;
	private Text txtEdition;
	private Text txtPublicationDate;
	private Text txtCopies;
	private Text txtPrice;
	private Text txtName;
	private Text txtLanguage;
	private Text txtCategories;
	private Text txtCardNumber;
	private Text txtExpiryDate;
	private Text txtCardName;
	private Text txtCvv;
	private Text txtBillingAddress;
	private Button btnEditCard;
	private Text txtUserEmail;
	private Label labelEditCardResult;
	private Label labelAddBookResult;
	private Text txtStartDate;
	private Text txtNumberOfPoints;
	private Text txtAmountToIncrease;
	private Text txtCategory;
	private Text txtAmountToIncreasePub;
	private Text txtPublisherIncrease;
	private Group grpIncreaseBookPrice;
	private Group grpGiftCardPromotion;
	private Label labelIncreasePub;
	private Label labelCategoryPrice;
	private Label labelRewardPoints;
	private Label labelGiftCardAmount;
	private Label labelFindPurchases;
	private Text txtDate;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		
		try {
			sql = new SqlConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Display display = Display.getDefault();
		createContents();
		shlBookstoreApplication.open();
		shlBookstoreApplication.layout();
		while (!shlBookstoreApplication.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlBookstoreApplication = new Shell();
		shlBookstoreApplication.setSize(1106, 468);
		shlBookstoreApplication.setText("Bookstore Application");
		shlBookstoreApplication.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		createFindPurchases();
		
		grpGiftCardPromotion = new Group(shlBookstoreApplication, SWT.NONE);
		grpGiftCardPromotion.setText("Gift Card Promotion");
		
		createFindTotalAmount();
		createRewardBonusPoints();
		
		grpIncreaseBookPrice = new Group(shlBookstoreApplication, SWT.NONE);
		grpIncreaseBookPrice.setText("Increase Book Price");
		
		createIncreaseByCategory();
		createIncreaseByPublisher();
		
		createEditCreditCard();
		
		createAddBook();

	}
	
	private void createFindPurchases() {
		
		Group grpFindPurchases = new Group(shlBookstoreApplication, SWT.NONE);
		grpFindPurchases.setText("Find Purchases");
		grpFindPurchases.setLayout(new FillLayout(SWT.VERTICAL));
		

		labelFindPurchases = new Label(grpFindPurchases, SWT.NONE);
		
		txtStartDate = new Text(grpFindPurchases, SWT.BORDER);
		txtStartDate.setText("Date");
		txtStartDate.setToolTipText("Date must be dd/MM/yyy");
		txtStartDate.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				DateFormat format = new SimpleDateFormat("dd/MM/yyy");
				try {
					format.parse(txtStartDate.getText());
				}
				catch (Exception E) {
					labelFindPurchases.setText(txtStartDate.getToolTipText());
				}
			}
		});
		
		Button btnFindPurchases = new Button(grpFindPurchases, SWT.NONE);
		
		btnFindPurchases.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DateFormat format = new SimpleDateFormat("dd/MM/yyy");
				try {
					Date date = format.parse(txtStartDate.getText());
					List<String> purchases = sql.getPurchases(new java.sql.Date(date.getTime()));
					
					if (purchases.size() == 0 || purchases == null)
					{
						labelFindPurchases.setText("No purchases found.");
					}
					else 
					{
						String allPurchases = "";
						for (int i = 0; i < purchases.size(); i++)
						{
							allPurchases += purchases.get(i) + "\n";
						}
						
						labelFindPurchases.setText(allPurchases);
					}
					
				}
				catch (Exception E) {
					labelFindPurchases.setText("Error.");
				}
				

			}
		});
		btnFindPurchases.setText("Find Purchases");
		
	}
	
	private void createFindTotalAmount() {
		grpGiftCardPromotion.setLayout(new FillLayout(SWT.VERTICAL));
		
		Group grpFindTotalAmount = new Group(grpGiftCardPromotion, SWT.NONE);
		grpFindTotalAmount.setLayout(new FillLayout(SWT.VERTICAL));
		grpFindTotalAmount.setText("Find Total Amount Spent");
		
		Button btnCalculateAmount = new Button(grpFindTotalAmount, SWT.NONE);
		btnCalculateAmount.setText("Calculate Amount");
		
		labelGiftCardAmount = new Label(grpFindTotalAmount, SWT.NONE);
		
		btnCalculateAmount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				float amount;
				try {
					amount = sql.getTotalGiftCardPurchases();
					labelGiftCardAmount.setText(String.valueOf(amount));
					int x = 0; //debugging
				} catch (SQLException E) {
					labelGiftCardAmount.setText("Could not get total amount.");
				}

			}
		});
		
		
	}
	
	private void createRewardBonusPoints() {
		
		Group grpRewardBonusPoints = new Group(grpGiftCardPromotion, SWT.NONE);
		grpRewardBonusPoints.setLayout(new FillLayout(SWT.VERTICAL));
		grpRewardBonusPoints.setText("Reward Bonus Points");
		

		labelRewardPoints = new Label(grpRewardBonusPoints, SWT.NONE);
		
		txtNumberOfPoints = new Text(grpRewardBonusPoints, SWT.BORDER);
		txtNumberOfPoints.setText("Number of Points");
		txtNumberOfPoints.setToolTipText("Number of points must be an integer.");
		txtNumberOfPoints.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					Integer.parseInt(txtNumberOfPoints.getText());
				}
				catch (Exception E) {
					labelRewardPoints.setText(txtNumberOfPoints.getToolTipText());
				}
				
			}
		});
		
		
		txtDate = new Text(grpRewardBonusPoints, SWT.BORDER);
		txtDate.setText("Date");
		txtDate.setToolTipText("Date must be dd/MM/yyy");
		txtDate.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				DateFormat format = new SimpleDateFormat("dd/MM/yyy");
				try {
					format.parse(txtDate.getText());
				}
				catch (Exception E) {
					labelRewardPoints.setText(txtDate.getToolTipText());
				}
			}
		});
		
		Button btnRewardBonusPoints = new Button(grpRewardBonusPoints, SWT.NONE);
		
		btnRewardBonusPoints.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					DateFormat format = new SimpleDateFormat("dd/MM/yyy");
					java.sql.Date date = new java.sql.Date(format.parse(txtDate.getText()).getTime());
					
					if(sql.rewardPointsPromotion(date, Integer.parseInt(txtNumberOfPoints.getText()))) {
						labelRewardPoints.setText("Success!");
					}
					else {
						labelRewardPoints.setText("Could not reward points.");
					}
				} 
				catch (Exception E) {
					labelRewardPoints.setText("Could not reward points.");
				}

			}
		});
		btnRewardBonusPoints.setText("Reward Bonus Points");
		
		
	}
	
	private void createIncreaseByCategory() {
		grpIncreaseBookPrice.setLayout(new FillLayout(SWT.VERTICAL));
		
		Group grpByCategory = new Group(grpIncreaseBookPrice, SWT.NONE);
		grpByCategory.setLayout(new FillLayout(SWT.VERTICAL));
		grpByCategory.setText("By Category");
		
		labelCategoryPrice = new Label(grpByCategory, SWT.NONE);
		
		txtAmountToIncrease = new Text(grpByCategory, SWT.BORDER);
		txtAmountToIncrease.setText("Amount to Increase");
		txtAmountToIncrease.setToolTipText("Amount to increase must be a decimal.");
		txtAmountToIncrease.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					Float.parseFloat(txtAmountToIncrease.getText());
				}
				catch (Exception E) {
					labelCategoryPrice.setText(txtAmountToIncrease.getToolTipText());
				}
				
			}
		});
		
		txtCategory = new Text(grpByCategory, SWT.BORDER);
		txtCategory.setText("Category");
		
		Button btnIncreasePrice = new Button(grpByCategory, SWT.NONE);
		
		btnIncreasePrice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					if(sql.increasePublisherPrice(txtCategory.getText(), Float.parseFloat(txtAmountToIncrease.getText()))) {
						labelCategoryPrice.setText("Success!");
					}
					else {
						labelCategoryPrice.setText("Could not increase price.");
					}
				} 
				catch (Exception E) {
					labelCategoryPrice.setText("Could not increase price.");
				}

			}
		});
		
		btnIncreasePrice.setText("Increase Price");
		
	}
	
	private void createIncreaseByPublisher() {
		
		Group grpByPublisher = new Group(grpIncreaseBookPrice, SWT.NONE);
		grpByPublisher.setLayout(new FillLayout(SWT.VERTICAL));
		grpByPublisher.setText("By Publisher");
		
		labelIncreasePub = new Label(grpByPublisher, SWT.NONE);
		
		txtAmountToIncreasePub = new Text(grpByPublisher, SWT.BORDER);
		txtAmountToIncreasePub.setText("Amount to Increase");
		txtAmountToIncreasePub.setToolTipText("Amount to increase must be a decimal.");
		txtAmountToIncreasePub.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					Float.parseFloat(txtAmountToIncreasePub.getText());
				}
				catch (Exception E) {
					labelIncreasePub.setText(txtAmountToIncreasePub.getToolTipText());
				}
				
			}
		});
		
		txtPublisherIncrease = new Text(grpByPublisher, SWT.BORDER);
		txtPublisherIncrease.setText("Publisher");
		
		Button btnIncreasePrice_1 = new Button(grpByPublisher, SWT.NONE);
		
		btnIncreasePrice_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					if(sql.increasePublisherPrice(txtPublisherIncrease.getText(), Float.parseFloat(txtAmountToIncreasePub.getText()))) {
						labelIncreasePub.setText("Success!");
					}
					else {
						labelIncreasePub.setText("Could not increase price.");
					}
				} 
				catch (Exception E) {
					labelIncreasePub.setText("Could not increase price.");
				}

			}
		});
		btnIncreasePrice_1.setText("Increase Price");
		
		
	}
	
	private void createEditCreditCard() {
		
		Group grpEditCreditCard = new Group(shlBookstoreApplication, SWT.NONE);
		grpEditCreditCard.setText("Edit Credit Card");
		grpEditCreditCard.setLayout(new FillLayout(SWT.VERTICAL));

		labelEditCardResult = new Label(grpEditCreditCard, SWT.NONE);
		
		txtUserEmail = new Text(grpEditCreditCard, SWT.BORDER);
		txtUserEmail.setText("User Email");
		
		txtCardNumber = new Text(grpEditCreditCard, SWT.BORDER);
		txtCardNumber.setText("Card Number");
		
		txtExpiryDate = new Text(grpEditCreditCard, SWT.BORDER);
		txtExpiryDate.setText("Expiry Date");
		txtExpiryDate.setToolTipText("Date must be dd/MM/yyy");
		txtExpiryDate.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				DateFormat format = new SimpleDateFormat("dd/MM/yyy");
				try {
					format.parse(txtExpiryDate.getText());
				}
				catch (Exception E) {
					labelEditCardResult.setText(txtExpiryDate.getToolTipText());
				}
			}
		});
		
		txtCardName = new Text(grpEditCreditCard, SWT.BORDER);
		txtCardName.setText("Name");
		
		txtCvv = new Text(grpEditCreditCard, SWT.BORDER);
		txtCvv.setText("CVV");
		txtCvv.setToolTipText("CVV must be an integer");
		txtCvv.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					Integer.parseInt(txtCvv.getText());
				}
				catch (Exception E) {
					labelEditCardResult.setText(txtCvv.getToolTipText());
				}
				
			}
		});
		
		txtBillingAddress = new Text(grpEditCreditCard, SWT.BORDER);
		txtBillingAddress.setText("Billing Address");
		
		btnEditCard = new Button(grpEditCreditCard, SWT.NONE);
		
		btnEditCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					DateFormat format = new SimpleDateFormat("dd/MM/yyy");
					Date date = format.parse(txtExpiryDate.getText());
					
					CreditCard card = new CreditCard(txtCardNumber.getText(), date, txtCardName.getText(), Integer.parseInt(txtCvv.getText()), txtBillingAddress.getText());
					if(sql.editCreditCard(txtUserEmail.getText(), card)) {
						labelEditCardResult.setText("Success!");
					}
					else {
						labelEditCardResult.setText("Could not edit card.");
					}
				} 
				catch (Exception E) {
					labelEditCardResult.setText("Could not edit card.");
				}

			}
		});
		btnEditCard.setText("Edit Card");
		
	}
	
	private void createAddBook() {
		
		Group grpAddBook = new Group(shlBookstoreApplication, SWT.NONE);
		grpAddBook.setText("Add Book");
		grpAddBook.setLayout(new FillLayout(SWT.VERTICAL));

		labelAddBookResult = new Label(grpAddBook, SWT.NONE);
		
		txtIsbn = new Text(grpAddBook, SWT.BORDER);
		txtIsbn.setText("ISBN-13");
		
		txtName = new Text(grpAddBook, SWT.BORDER);
		txtName.setText("Name");
		
		txtAuthors = new Text(grpAddBook, SWT.BORDER);
		txtAuthors.setText("Authors");
		
		txtPublisher = new Text(grpAddBook, SWT.BORDER);
		txtPublisher.setText("Publisher");
		
		txtPublicationDate = new Text(grpAddBook, SWT.BORDER);
		txtPublicationDate.setText("Publication Date");
		txtPublicationDate.setToolTipText("Date must be dd/MM/yyy.");
		txtPublicationDate.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				DateFormat format = new SimpleDateFormat("dd/MM/yyy");
				try {
					format.parse(txtPublicationDate.getText());
				}
				catch (Exception E) {
					labelAddBookResult.setText(txtPublicationDate.getToolTipText());
				}
			}
		});
		
		txtDescription = new Text(grpAddBook, SWT.BORDER);
		txtDescription.setText("Description");
		
		txtEdition = new Text(grpAddBook, SWT.BORDER);
		txtEdition.setText("Edition");
		
		txtCopies = new Text(grpAddBook, SWT.BORDER);
		txtCopies.setText("Copies");
		txtCopies.setToolTipText("Copies must be an integer.");
		txtCopies.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					Integer.parseInt(txtCopies.getText());
				}
				catch (Exception E) {
					labelAddBookResult.setText(txtCopies.getToolTipText());
				}
				
			}
		});
		
		txtPrice = new Text(grpAddBook, SWT.BORDER);
		txtPrice.setText("Price");
		txtPrice.setToolTipText("Price must be a decimal.");
		txtPrice.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					Float.parseFloat(txtPrice.getText());
				}
				catch (Exception E) {
					labelAddBookResult.setText(txtPrice.getToolTipText());
				}
				
			}
		});
		
		txtLanguage = new Text(grpAddBook, SWT.BORDER);
		txtLanguage.setText("Language");
		
		txtCategories = new Text(grpAddBook, SWT.BORDER);
		txtCategories.setText("Category");
		txtCategories.setToolTipText("Category must be an integer.");
		txtCategories.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					Integer.parseInt(txtCategories.getText());
				}
				catch (Exception E) {
					labelAddBookResult.setText(txtCategories.getToolTipText());
				}
				
			}
		});
		
		Button btnAddBook = new Button(grpAddBook, SWT.NONE);
		
		btnAddBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					DateFormat format = new SimpleDateFormat("dd/MM/yyy");
					Date date = format.parse(txtPublicationDate.getText());
					
					Book book = new Book(txtIsbn.getText(), txtName.getText(), txtAuthors.getText(), txtPublisher.getText(), date, txtDescription.getText(), txtEdition.getText(), Integer.parseInt(txtIsbn.getText()), Float.parseFloat(txtPrice.getText()), txtLanguage.getText(), Integer.parseInt(txtCategories.getText()));
					
					if(sql.addBook(book)) {
						labelAddBookResult.setText("Success!");
					}
					else {
						labelAddBookResult.setText("Could not add book.");
					}
					
				} 
				catch (Exception E) {
					labelAddBookResult.setText("Could not add book.");
				}

			}
		});
		btnAddBook.setText("Add Book");
		
	}
}

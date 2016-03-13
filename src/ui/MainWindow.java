package ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridLayout;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class MainWindow {
	
	// CHANGE
	private SqlConnectionDummy sql;
	
	protected Shell shell;
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
		
		// CHANGE
		sql = new SqlConnectionDummy();
		
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(891, 468);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group grpGiftCardPromotion = new Group(shell, SWT.NONE);
		grpGiftCardPromotion.setText("Gift Card Promotion");
		
		Group grpFindTotalAmount = new Group(grpGiftCardPromotion, SWT.NONE);
		grpFindTotalAmount.setText("Find Total Amount Spent");
		grpFindTotalAmount.setBounds(10, 21, 155, 186);
		
		Group grpRewardBonusPoints = new Group(grpGiftCardPromotion, SWT.NONE);
		grpRewardBonusPoints.setText("Reward Bonus Points");
		grpRewardBonusPoints.setBounds(10, 213, 155, 206);
		
		Group grpIncreaseBookPrice = new Group(shell, SWT.NONE);
		grpIncreaseBookPrice.setText("Increase Book Price");
		
		Group grpByCategory = new Group(grpIncreaseBookPrice, SWT.NONE);
		grpByCategory.setText("By Category");
		grpByCategory.setBounds(10, 20, 155, 186);
		
		Group grpByPublisher = new Group(grpIncreaseBookPrice, SWT.NONE);
		grpByPublisher.setText("By Publisher");
		grpByPublisher.setBounds(10, 212, 155, 207);
		
		Group grpFindPurchases = new Group(shell, SWT.NONE);
		grpFindPurchases.setText("Find Purchases");
		
		Group grpEditCreditCard = new Group(shell, SWT.NONE);
		grpEditCreditCard.setText("Edit Credit Card");
		grpEditCreditCard.setLayout(new FillLayout(SWT.VERTICAL));
		
		txtUserEmail = new Text(grpEditCreditCard, SWT.BORDER);
		txtUserEmail.setText("User Email");
		
		txtCardNumber = new Text(grpEditCreditCard, SWT.BORDER);
		txtCardNumber.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					Integer.parseInt(txtCardNumber.getText());
				}
				catch (Exception E) {
					txtCardNumber.setText("ERROR: Card number must be an integer");
				}
				
			}
		});
		txtCardNumber.setText("Card Number");
		
		txtExpiryDate = new Text(grpEditCreditCard, SWT.BORDER);
		txtExpiryDate.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				DateFormat format = new SimpleDateFormat("dd/MM/yyy");
				try {
					format.parse(txtExpiryDate.getText());
				}
				catch (Exception E) {
					txtExpiryDate.setText("ERROR: Date must be dd/MM/yyy");
				}
			}
		});
		txtExpiryDate.setText("Expiry Date");
		
		txtCardName = new Text(grpEditCreditCard, SWT.BORDER);
		txtCardName.setText("Name");
		
		txtCvv = new Text(grpEditCreditCard, SWT.BORDER);
		txtCvv.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					Integer.parseInt(txtCvv.getText());
				}
				catch (Exception E) {
					txtCvv.setText("ERROR: CVV must be an integer");
				}
				
			}
		});
		txtCvv.setText("CVV");
		
		txtBillingAddress = new Text(grpEditCreditCard, SWT.BORDER);
		txtBillingAddress.setText("Billing Address");
		
		btnEditCard = new Button(grpEditCreditCard, SWT.NONE);
		labelEditCardResult = new Label(grpEditCreditCard, SWT.NONE);
		
		btnEditCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					DateFormat format = new SimpleDateFormat("dd/MM/yyy");
					Date date = format.parse(txtExpiryDate.getText());
					
					CreditCard card = new CreditCard(Integer.parseInt(txtCardNumber.getText()), date, txtCardName.getText(), Integer.parseInt(txtCvv.getText()), txtBillingAddress.getText());
					if(sql.editCreditCard(txtUserEmail.getText(), card)) {
						labelEditCardResult.setText("Success!");
					}
					else {
						labelEditCardResult.setText("Could not edit card.");
					}
				} 
				catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnEditCard.setText("Edit Card");
		
		
		Group grpAddBook = new Group(shell, SWT.NONE);
		grpAddBook.setText("Add Book");
		grpAddBook.setLayout(new FillLayout(SWT.VERTICAL));
		
		txtIsbn = new Text(grpAddBook, SWT.BORDER);
		txtIsbn.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					Integer.parseInt(txtIsbn.getText());
				}
				catch (Exception E) {
					txtIsbn.setText("ERROR: ISBN must be an integer");
				}
				
			}
		});

		txtIsbn.setText("ISBN-13");
		
		txtName = new Text(grpAddBook, SWT.BORDER);
		txtName.setText("Name");
		
		txtAuthors = new Text(grpAddBook, SWT.BORDER);
		txtAuthors.setText("Authors");
		
		txtPublisher = new Text(grpAddBook, SWT.BORDER);
		txtPublisher.setText("Publisher");
		
		txtPublicationDate = new Text(grpAddBook, SWT.BORDER);
		txtPublicationDate.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				DateFormat format = new SimpleDateFormat("dd/MM/yyy");
				try {
					format.parse(txtPublicationDate.getText());
				}
				catch (Exception E) {
					txtPublicationDate.setText("ERROR: Date must be dd/MM/yyy");
				}
			}
		});
		txtPublicationDate.setText("Publication Date");
		
		txtDescription = new Text(grpAddBook, SWT.BORDER);
		txtDescription.setText("Description");
		
		txtEdition = new Text(grpAddBook, SWT.BORDER);
		txtEdition.setText("Edition");
		
		txtCopies = new Text(grpAddBook, SWT.BORDER);
		txtCopies.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					Integer.parseInt(txtCopies.getText());
				}
				catch (Exception E) {
					txtCopies.setText("ERROR: Copies must be an integer");
				}
				
			}
		});
		txtCopies.setText("Copies");
		
		txtPrice = new Text(grpAddBook, SWT.BORDER);
		txtPrice.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					Float.parseFloat(txtPrice.getText());
				}
				catch (Exception E) {
					txtPrice.setText("ERROR: Price must be a decimal number");
				}
				
			}
		});
		txtPrice.setText("Price");
		
		txtLanguage = new Text(grpAddBook, SWT.BORDER);
		txtLanguage.setText("Language");
		
		txtCategories = new Text(grpAddBook, SWT.BORDER);
		txtCategories.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				try {
					Integer.parseInt(txtCategories.getText());
				}
				catch (Exception E) {
					txtCategories.setText("ERROR: Category must be an integer");
				}
				
			}
		});
		txtCategories.setText("Category");
		
		Button btnAddBook = new Button(grpAddBook, SWT.NONE);
		labelAddBookResult = new Label(grpAddBook, SWT.NONE);
		
		btnAddBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					DateFormat format = new SimpleDateFormat("dd/MM/yyy");
					Date date = format.parse(txtPublicationDate.getText());
					
					Book book = new Book(Integer.parseInt(txtIsbn.getText()), txtName.getText(), txtAuthors.getText(), txtPublisher.getText(), date, txtDescription.getText(), txtEdition.getText(), Integer.parseInt(txtIsbn.getText()), Float.parseFloat(txtPrice.getText()), txtLanguage.getText(), Integer.parseInt(txtCategories.getText()));
					
					if(sql.addBook(book)) {
						labelAddBookResult.setText("Success!");
					}
					else {
						labelAddBookResult.setText("Could not add book.");
					}
					
				} 
				catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnAddBook.setText("Add Book");


	}
}

package Flights;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of the Client
 * @author group_0350
 * 
 */
public class Client {
	private String lastName;
	private String firstNames;
	private String email;
	private String PhoneNumber;
	private String address;
	private String creditCardNumber;
	private String expiryDate;
	
	//The list has a List of String where each String is a flight 
	private List<List<String>> UserItinerary = new ArrayList<List<String>>(); 
	/**
	 * Creates a Client with the given lastName, firstName, email,
	 * address, creditCardNumber and expiryDate
	 * 
	 * @param lastName client's last name 
	 * @param firstNames client's first name
	 * @param email client's email
	 * @param address client's address
	 * @param lastName client's credit card number
	 * @param expiryDate expiry date of the credit card
	 */
	public Client(String lastName, String firstNames, String email,
			String address, String creditCardNumber, String expiryDate) {
		this.lastName = lastName;
		this.firstNames = firstNames;
		this.email = email.trim();
		this.address = address;
		this.creditCardNumber = creditCardNumber;
		this.expiryDate = expiryDate;
	}
	
	
	/**
	 * Return last name
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * Sets the last name
	 * 
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * Returns the first names
	 * 
	 * @return the firstNames
	 */
	public String getFirstNames() {
		return firstNames;
	}


	/**
	 * Sets the first names
	 * 
	 * @param firstNames the firstNames to set
	 */
	public void setFirstNames(String firstNames) {
		this.firstNames = firstNames;
	}


	/**
	 * Returns the email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * Sets the email
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * Returns the phone number
	 * 
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return PhoneNumber;
	}


	/**
	 * Sets the phone number
	 * 
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}


	/**
	 * Returns the address
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * Sets the address
	 * 
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * Returns the credit card number
	 * 
	 * @return the creditCardNumber
	 */
	public String getCreditCardNumber() {
		return creditCardNumber;
	}


	/**
	 * Sets the credit card number
	 * 
	 * @param creditCardNumber the creditCardNumber to set
	 */
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}


	/**
	 * Returns the expiry date
	 * 
	 * @return the expiryDate
	 */
	public String getExpiryDate() {
		return expiryDate;
	}


	/**
	 * Set expiry date
	 * 
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}


	/**
	 * Return user itinerary
	 * 
	 * @return the userItinerary
	 */
	public String getUserItinerary() {
		return UserItinerary.toString();
	}

	/**
	 * Add itinerary given a route
	 * 
	 * @param route
	 */
	public void addItinerary(String route) {
		List<String> userSelected = new ArrayList<String>();
		String[] subflight = route.split("\n");
		
		for(String flight : subflight){
			userSelected.add(flight);
		}
		
		UserItinerary.add(userSelected);
	}
	

	/**
	 * Returns a String Representation of the Client 
	 * 
	 * @return returns the String Representation
	 */
	@Override
	public String toString() {
		return lastName + "," + firstNames
				+ "," + email + "," + address
				+ "," + creditCardNumber + ","
				+ expiryDate;
	}
	
}
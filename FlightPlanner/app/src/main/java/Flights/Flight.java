package Flights;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representation of the Flight
 * 
 * @author group_0350
 * 
 */
public class Flight {

	private String flightNumber;
	private String pDepartureDateAndTime;
	private Date departureDateAndTime;
	private Date departureDate;
	private Date arrivalDateAndTime;
	private String pArrivalDateAndTime;
	private String airline;
	private String origin;
	private String destination;
	private String price;
	private int numberOfSeats;

	// 2014-05-26 1:15 Date(int year, int month, int date, int hrs, int min)
	// departureDateAndTime.trim().split(" ")[0].split("-")[]
	/**
	 * Creates a Flight with the given flight number, departure date and time,
	 * arrival date and time, airline name, origin, destination and price
	 * 
	 * @param flightNumber
	 *            flight number
	 * @param departureDateAndTime
	 *            departure date and time
	 * @param arrivalDateAndTime
	 *            arrival date and time
	 * @param airline
	 *            name of the airline
	 * @param origin
	 *            flight origin
	 * @param destination
	 *            flight destination
	 * @param price
	 *            cost of the flight
	 */
	public Flight(String flightNumber, String departureDateAndTime,
			String arrivalDateAndTime, String airline, String origin,
			String destination, String price, String numberOfSeats) {

		this.flightNumber = flightNumber.trim();
		this.pDepartureDateAndTime = departureDateAndTime.trim();
		this.pArrivalDateAndTime = arrivalDateAndTime.trim();
		this.airline = airline.trim();
		this.origin = origin.trim();
		this.destination = destination.trim();
		this.price = price.trim();
		this.numberOfSeats = Integer.parseInt(numberOfSeats);
		String dateFormat = "yyyy-MM-dd HH:mm";
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		try {
			this.departureDateAndTime = formatter.parse(departureDateAndTime);

		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("departureDateAndTime is in wrong format");
		}

		try {
			this.arrivalDateAndTime = formatter.parse(arrivalDateAndTime);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("arrivalDateAndTime is in wrong format");
		}

		String newdateFormat = "yyyy-MM-dd";
		String compareFormatDepartureDate = departureDateAndTime.split(" ")[0];
		SimpleDateFormat newformatter = new SimpleDateFormat(newdateFormat);

		try {
			departureDate = newformatter.parse(compareFormatDepartureDate);

		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("GetItinerary input date is in wrong format");
		}

	}

	
	/**
	 * Returns the flight number
	 * 
	 * @return the flight number
	 */
	public String getFlightNumber() {
		return flightNumber;
	}

	
	/**
	 * Sets the flight number
	 * 
	 * @param flightNumber
	 */
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	
	/**
	 * Returns the departure date and time
	 * 
	 * @return departure date and time
	 */
	public Date getDepartureDateAndTime() {
		return departureDateAndTime;
	}

	
	/**
	 * Returns the departure date of flight (YYYY-MM-DD)
	 * 
	 * @return the departure date of flight (YYYY-MM-DD)
	 */
	public Date getDepartureDates() {
		return departureDate;

	}

	
	/**
	 * Sets the departure date and time
	 * 
	 * @param departureDateAndTime
	 */
	public void setDepartureDateAndTime(String departureDateAndTime) {
		// here I have to convert the string input in to a date then work with
		// it
		String dateFormat = "yyyy-MM-dd HH:mm";
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		try {
			this.departureDateAndTime = formatter.parse(departureDateAndTime);

		} catch (ParseException e) {
			e.printStackTrace();
			System.out
					.println("departureDateAndTime is in wrong format (SetDepar...) ");
		}

	}

	
	/**
	 * Returns the arrival date and time
	 * 
	 * @return the arrival date and time
	 */
	public Date getArrivalDateAndTime() {
		return arrivalDateAndTime;
	}

	
	/**
	 * Sets arrival date and time
	 * 
	 * @param arrivalDateAndTime
	 */
	public void setArrivalDateAndTime(String arrivalDateAndTime) {
		// here I have to convert the string input in to a date then work with
		// it
		String dateFormat = "yyyy-MM-dd HH:mm";
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		try {
			this.arrivalDateAndTime = formatter.parse(arrivalDateAndTime);

		} catch (ParseException e) {
			e.printStackTrace();
			System.out
					.println("arrivalDateAndTime is in wrong format (setArri...)");
		}
	}

	
	/**
	 * Returns the airline name
	 * 
	 * @return the airline name
	 */
	public String getAirline() {
		return airline;
	}

	
	/**
	 * Set the airline name
	 * 
	 * @param airline
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}

	
	/**
	 * Returns the origin
	 * 
	 * @return the origin of flight
	 */
	public String getOrigin() {
		return origin;
	}

	
	/**
	 * Sets the origin
	 * 
	 * @param origin
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	
	/**
	 * Returns the destination of Flight
	 * 
	 * @return the destination of Flight
	 */
	public String getDestination() {
		return destination;
	}

	
	/**
	 * Sets the destination
	 * 
	 * @param destination
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	
	/**
	 * Returns the price
	 * 
	 * @return the price of flight travel
	 */
	public float getPrice() {
		return Float.parseFloat(price);
	}

	
	/**
	 * Sets the price
	 * 
	 * @param price
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	
	
	/**
	 * Returns the number of seats
	 * 
	 * @return numberOfSeats number of seats
	 */
	public int getnumberOfSeats(){
		return this.numberOfSeats;
	}
	
	
	/**
	 * Returns true if number of seats > 0 , false otherwise
	 * 
	 * @return boolean
	 */
	public boolean isSeatAvailable(){
		if(this.numberOfSeats > 0){
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Returns a String Representation of the flight
	 * 
	 * @return returns the String Representation
	 */
	@Override
	public String toString() {
		return flightNumber + "," + pDepartureDateAndTime + ","
				+ pArrivalDateAndTime + "," + airline + "," + origin + ","
				+ destination;
	}

	
	/**
	 * Returns a String Representation of the flight with number of seats 
	 * 
	 * @return returns the String Representation
	 */
	public String displayFlight() {
		return "Date: " + pDepartureDateAndTime + "~" + pArrivalDateAndTime
				+ "\n" + "Flight Number: " + flightNumber + " Seats: "
				+ numberOfSeats + "\n" + "Airline: " + airline + ", " + "$"
				+ price;
	}

}

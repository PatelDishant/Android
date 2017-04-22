package Flights;

/**
 * Representation of the Driver class
 * @author group_0350
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *A class that represents the basic functionality of the Flight Application
 *In this class, the Client and Flight info are read from the file and stored
 */

public class Driver {
	// key: email Address, Value: Client Object
	static HashMap<String, Client> clientUser = new HashMap<String, Client>();

	// Key: Origin of Flight, Value: List of Flight from Origin
	static Map<String, List<Flight>> flightByOrigin = new HashMap<String, List<Flight>>();

	/**
	 * takes a path to an input csv file in the above format and uploads the
	 * client information into the application
	 * 
	 * @param path
	 *            string of the path to file
	 */
	public static void uploadClientInfo(String path) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(path));
		String text;

		while ((text = br.readLine()) != null) {

			// parse the data
			String[] elements = text.split(",");
			if (elements.length == 6) {
				Client client = new Client(elements[0], elements[1],
						elements[2], elements[3], elements[4], elements[5]);
				// uploads the client information here
				clientUser.put(elements[2], client);
			}

		}

		br.close();
	}

	/**
	 * takes a path to an input csv file and uploads the flight information into
	 * the hash Map
	 * 
	 * @param path
	 *            string of the path to file
	 */
	public static void uploadFlightInfo(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String text;
		while ((text = br.readLine()) != null) {
			// Parse the text by ,.

			String[] elements = text.split(",");

			// This makes sure that we get 8 data.
			if (elements.length == 8) {

				// Creating the flight object
				Flight flightObj = new Flight(elements[0], elements[1],
						elements[2], elements[3], elements[4], elements[5],
						elements[6], elements[7]);

				// here add to flightByOrigin, if the origin already exist in
				// map add to value list
				if (flightByOrigin.containsKey(elements[4].trim())) {
					flightByOrigin.get(elements[4]).add(flightObj);
				}

				else {
					// if origin does not exist put origin and list of object
					// into map
					List<Flight> newArray = new ArrayList<Flight>();
					newArray.add(flightObj);

					flightByOrigin.put(elements[4], newArray);
				}

			}
		}

		br.close();

	}

	/**
	 * Given an email, will return a client object associated with the email if
	 * email does not exist returns null
	 * 
	 * @param email
	 *            email of the client
	 * @return returns client corresponding to email
	 */
	public static Client getClientObj(String email) {
		for (Client client : clientUser.values()) {
			if (client.getEmail().equals(email)) {
				return client;
			}
		}
		return new Client(null, null, null, null, null, null);
	}

	/**
	 * Given an email, will return a string representation of client with
	 * matching email.
	 * 
	 * @param email
	 * @return string representation of Client
	 */
	public static String getClient(String email) {
		return getClientObj(email).toString();
	}

	/**
	 * Given date,origin and destination, returns string of flights matching
	 * given parameters
	 * 
	 * @param date
	 *            the departure date
	 * @param origin
	 *            the departure location
	 * @param destination
	 *            the arrival location
	 * @return String of flights that matched give input
	 */
	public static String getFlights(String date, String origin,
			String destination) {
		Date userDepartureDate = null;
		String dateFormat = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		// Making sure that given date is in correct format
		try {
			userDepartureDate = formatter.parse(date);

		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("GetFlight input date is in wrong format");
		}

		String result = "";
		// traverse flights by origin to find the matching search
		for (Flight flight : flightByOrigin.get(origin)) {

			// Checking that search meets user's specification
			if ((flight.getDepartureDates().compareTo(userDepartureDate) == 0)
					&& origin.equals(flight.getOrigin())
					&& destination.equals(flight.getDestination())) {
				// Place the result in the result string
				result = result + flight.toString() + "," + flight.getPrice()
						+ "\n";
			}
		}
		return result;
	}

	/**
	 * Uses Users specification to to find all possible Itinerary and returns it
	 * as a String.
	 * 
	 * @param date
	 *            the date of departure
	 * @param origin
	 *            departure location
	 * @param destination
	 *            the arrival location
	 * @return returns string representation found Itinerary
	 */

	public static String getItineraries(String date, String origin,
			String destination) {

		// The finalResult has all possible Itinerary matching the given
		// criteria (date, origin, destination)
		List<List<Flight>> finalResult = basicItinerary(date, origin,
				destination);

		String result = "";
		for (List<Flight> path : finalResult) {

			String totalTime = "";
			int tempIndex = path.size();

			if (tempIndex > 1) {

				// If there are more than one flight, To find total travel time.
				// We need to use the first flight's departure time and last
				// flight's arrival time
				totalTime = getTimeHourMinutes(path.get(0),
						path.get(tempIndex - 1));
			} else {

				// If only one flight get the flight time
				totalTime = getTimeHourMinutes(path.get(0), path.get(0));
			}

			result += turnListToString(path) + getCostOfList(path) + "\n"
					+ totalTime + "\n\n";

		}

		return result;

	}

	/**
	 * Returns Itineraries sorted by cost.
	 * 
	 * @param date
	 *            The date of departure
	 * @param origin
	 *            The departure location
	 * @param destination
	 *            The arrival location
	 * @return string representation of the found Itinerary
	 */
	public static String getItinerariesSortedByCost(String date, String origin,
			String destination) {

		// Get all the possible Itineraries
		List<List<Flight>> finalResult = basicItinerary(date, origin,
				destination);

		// This is used to sort the Itinerary
		List<Float> listOfCost = new ArrayList<Float>();

		// This is used to string the Itinerary Key:cost vale:Itinerary
		HashMap<Float, List<List<String>>> flightMap = new HashMap<Float, List<List<String>>>();

		String result = "";

		for (List<Flight> route : finalResult) {

			String totalTime = "";
			int tempIndex = route.size();

			if (tempIndex > 1) {
				// If there are more than one flight, To find total travel time.
				// We need to use the first flight's departure time and last
				// flight's arrival time
				totalTime = getTimeHourMinutes(route.get(0),
						route.get(tempIndex - 1));
			} else {
				// If only one flight get the flight time

				totalTime = getTimeHourMinutes(route.get(0), route.get(0));
			}

			// We compute the cost of the route(Itinerary)
			float cost = getCostOfList(route);

			// Take flights in route and represent it as a string
			String stringIter = turnListToString(route) + cost + "\n"
					+ totalTime + "\n";

			List<String> listOfStringIter = new ArrayList<String>();
			listOfStringIter.add(stringIter);

			// We are storing It in a map by key:cost
			if (!flightMap.containsKey(cost)) {
				listOfCost.add(cost);
				List<List<String>> toAddList = new ArrayList<List<String>>();
				toAddList.add(listOfStringIter);
				flightMap.put(cost, toAddList);

			} else {
				flightMap.get(cost).add(listOfStringIter);

			}
		}

		// Here we sort the cost
		Collections.sort(listOfCost);

		for (Float price : listOfCost) {

			for (List<String> eachRoute : flightMap.get(price)) {
				result += eachRoute.toString().replace("[", "")
						.replace("]", "\n");
			}
		}

		return result;

	}

	/**
	 * Returns Itineraries sorted by Time.
	 * 
	 * @param date
	 *            The date of departure
	 * @param origin
	 *            The departure location
	 * @param destination
	 *            The arrival location
	 * @return string representation of the found Itinerary
	 */
	public static String getItinerariesSortedByTime(String date, String origin,
			String destination) {

		String result = "";
		// this map is used to store vale: itinerary by key: time
		HashMap<Integer, List<List<String>>> flightMap = new HashMap<Integer, List<List<String>>>();

		List<List<Flight>> finalResult = basicItinerary(date, origin,
				destination);

		List<Integer> listOfTravelTime = new ArrayList<Integer>();

		// travse each Itinerary
		for (List<Flight> route : finalResult) {
			// -------------------------------------------------//
			String totalTime = "";
			int flightTime = 0;
			int tempIndex = route.size();

			//
			if (tempIndex > 1) {
				// If there are more than one flight, To find total travel time.
				// We need to use the first flight's departure time and last
				// flight's arrival time
				totalTime = getTimeHourMinutes(route.get(0),
						route.get(tempIndex - 1));
				flightTime = getTimeInMinutes(route.get(0),
						route.get(tempIndex - 1));
			} else {
				// If only one flight get the flight time
				totalTime = getTimeHourMinutes(route.get(0), route.get(0));
				flightTime = getTimeInMinutes(route.get(0), route.get(0));
			}

			String stringIter = turnListToString(route) + getCostOfList(route)
					+ "\n" + totalTime + "\n";

			List<String> listOfStringIter = new ArrayList<String>();
			listOfStringIter.add(stringIter);

			// put Itinerary in to map according to their flight time
			if (!flightMap.containsKey(flightTime)) {
				listOfTravelTime.add(flightTime);
				List<List<String>> toAddList = new ArrayList<List<String>>();
				toAddList.add(listOfStringIter);
				flightMap.put(flightTime, toAddList);
			} else {
				flightMap.get(flightTime).add(listOfStringIter);

			}
		}
		// sort the time then use it to get flights from flightMap
		Collections.sort(listOfTravelTime);

		for (Integer time : listOfTravelTime) {

			for (List<String> eachRoute : flightMap.get(time)) {
				result += eachRoute.toString().replace("[", "")
						.replace("]", "\n");
			}
		}

		return result;
	}

	/**
	 * Returns List of Itinerary (Itinerary is a list of flight Objects)
	 * 
	 * @param date
	 *            The date of departure
	 * @param origin
	 *            The departure location
	 * @param destination
	 *            The arrival location
	 * @return List of Itinerary (Itinerary is a list of flight Objects)
	 */
	private static List<List<Flight>> basicItinerary(String date,
			String origin, String destination) {

		Date userDepartureDate = null;
		String dateFormat = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		try {
			userDepartureDate = formatter.parse(date);

		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("GetItinerary input date is in wrong format");
		}

		// Used to store paths taken
		List<Flight> paths = new ArrayList<Flight>();

		// Used to store Itineraries
		List<List<Flight>> finalResult = new ArrayList<List<Flight>>();

		getSubItinerary(userDepartureDate, origin, destination, paths,
				finalResult, true);

		return finalResult;

	}

	/**
	 * gets Itinerary and puts in finalPaths List
	 * 
	 * @param date
	 *            date of departure
	 * @param origin
	 *            departure location
	 * @param dest
	 *            arrival location
	 * @param paths
	 *            list used to keep track of paths taken
	 * @param finalPaths
	 *            List used to store itineraries found
	 * @param checkPath
	 *            true if its the first path
	 */
	private static void getSubItinerary(Date date, String origin, String dest,
			List<Flight> paths, List<List<Flight>> finalPaths, boolean checkPath) {

		if (flightByOrigin.containsKey(origin)) {

			for (Flight flight : flightByOrigin.get(origin)) {

				if (flight.isSeatAvailable()) {

					boolean found = false;
					// if this is the very first flight add to path
					if (checkPath) {
						if (flight.getDepartureDates().equals(date)) {
							paths.add(flight);
						} else {
							continue;
						}
					} else {
						// if its not the first flight check if it meets 6hour
						// stayOverTime. If it does not goto For loop

						if (!isValidTimeDiff(paths.get(paths.size() - 1),
								flight)) {

							continue;
						} else {
							// if it meets 6hour layOverTime add to path
							paths.add(flight);
						}
					}

					if ((date.compareTo(flight.getDepartureDates()) == 0)
							&& origin.equals(flight.getOrigin()) && checkPath) {

						if (flight.getDestination().equals(dest)) {
							List<Flight> copy = new ArrayList<Flight>(paths);
							finalPaths.add(copy);
							found = true;
						}

					}

					// here we are seeing if the flight leads to our destination
					else if (origin.equals(flight.getOrigin())) {
						// this is to make sure that the first flight does not
						// get added, if it does not leave on the same day
						if (!checkPath) {
							if (flight.getDestination().equals(dest)) {
								List<Flight> copy = new ArrayList<Flight>(paths);
								finalPaths.add(copy);
								found = true;

							}
						}

					}

					if (!found) {
						getSubItinerary(date, flight.getDestination(), dest,
								paths, finalPaths, false);
					}
					// When I am done with all possible root at the location,
					// remove it from path
					paths.remove(paths.size() - 1);
				}
			}
		}
	}

	/**
	 * Returns the cost of the flights in the List
	 * 
	 * @param listFlights
	 *            This is each Itinerary
	 * @return The total cost of flights in the List
	 */
	private static float getCostOfList(List<Flight> listFlights) {
		float cost = 0;
		// Loop through each flight and get price and store it
		for (Flight flights : listFlights) {
			cost += flights.getPrice();
		}

		return cost;
	}

	/**
	 * turns the List of fights in to string representation. Each flight on its
	 * own line
	 * 
	 * @param listFlights
	 *            List of flights
	 * @return a string representation of the Flights in the List
	 */
	private static String turnListToString(List<Flight> listFlights) {
		String stringIter = "";
		for (Flight flights : listFlights) {
			stringIter += flights.toString() + "\n";
		}
		return stringIter;

	}

	/**
	 * Returns the hour and minutes of the total fight time as a string
	 * 
	 * @param firstFlight
	 *            The departure flight
	 * @param lastFlight
	 *            The arrival flight
	 * @return The total time in string format
	 */
	private static String getTimeHourMinutes(Flight firstFlight,
			Flight lastFlight) {
		// arrival is initial departure is final
		// final - initial = difference
		int timeInMinutes = 0;

		// If first flight and last flight are the same then we need to find the
		// time between departure and arrival
		if (firstFlight.equals(lastFlight)) {
			timeInMinutes = (int) ((lastFlight.getArrivalDateAndTime()
					.getTime() - firstFlight.getDepartureDateAndTime()
					.getTime()) / (1000.0 * 60));

			// If both flights are not the same then get the time difference
			// between first flight departure time and last flight arrival time
		} else {
			timeInMinutes = (int) ((lastFlight.getArrivalDateAndTime()
					.getTime() - firstFlight.getDepartureDateAndTime()
					.getTime()) / (1000.0 * 60));
		}

		// Get the hour
		int hours = timeInMinutes / 60;

		// Get the minutes
		int minutes = timeInMinutes % 60;

		return String.format("%d:%02d", hours, minutes);

	}

	/**
	 * returns true if the time difference is less the six hours and greater
	 * than zero hours
	 * 
	 * @param arrival
	 *            The flight you arrive on
	 * @param departure
	 *            The flight you depart of
	 * @return true if time is less than six hours and greater than zero hours
	 */
	private static boolean isValidTimeDiff(Flight arrival, Flight departure) {

		// Calculating the time difference in hours
		float timeDiff = (float) ((departure.getDepartureDateAndTime()
				.getTime() - arrival.getArrivalDateAndTime().getTime()) / (1000.0 * 60 * 60));
		if (timeDiff < 6 && timeDiff > 0) {
			return true;
		}

		return false;
	}

	/**
	 * Return the total time in minutes
	 * 
	 * @param firstFlight
	 *            The departure flight
	 * @param lastFlight
	 *            The arrival flight
	 * @return total time in minutes
	 */
	private static int getTimeInMinutes(Flight firstFlight, Flight lastFlight) {
		int timeInMinutes = 0;

		// If first flight and last flight are the same then we need to find the
		// time between departure and arrival
		if (firstFlight.equals(lastFlight)) {
			timeInMinutes = (int) ((lastFlight.getArrivalDateAndTime()
					.getTime() - firstFlight.getDepartureDateAndTime()
					.getTime()) / (1000.0 * 60));

			// If both flights are not the same then get the time difference
			// between first flight departure time and last flight arrival time
		} else {
			timeInMinutes = (int) ((lastFlight.getArrivalDateAndTime()
					.getTime() - firstFlight.getDepartureDateAndTime()
					.getTime()) / (1000.0 * 60));
		}
		return timeInMinutes;
	}
}

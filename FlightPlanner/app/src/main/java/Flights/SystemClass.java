package Flights;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;

public class SystemClass implements Serializable {
	private static final long serialVersionUID = -7953119145375536249L;

	public String username;
	public static String path;

	/**
	 * Creates a system class that takes in username and path
	 * 
	 * @param userName
	 * @param directory
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	public SystemClass(String userName, String directory) throws IOException {
		this.username = userName;
		this.path = directory;
		//populate();
	}

	/**
	 * @throws IOException
	 */
	public void populate() throws IOException {
		duplicateFile(path + "/Client.csv", path + "/Client_copy.csv");
		Driver.uploadClientInfo(path + "/Client_copy.csv");
		duplicateFile(path + "/Flight.csv", path + "/Flight_copy.csv");
		Driver.uploadFlightInfo(path + "/Flight_copy.csv");

	}

	public void UploadClientInfo() throws IOException {
		duplicateFile(path + "/Client.csv", path + "/Client_copy.csv");
		Driver.uploadClientInfo(path + "/Client_copy.csv");
	}

	public void UploadFlightInfo() throws IOException {
		duplicateFile(path + "/Flight.csv", path + "/Flight_copy.csv");
		Driver.uploadFlightInfo(path + "/Flight_copy.csv");
	}

	/**
	 * Duplicate file
	 * 
	 * @param oldName
	 * @param newName
	 */
	private void duplicateFile(String oldName, String newName) {
		// TODO Auto-generated method stub
		// create duplicate file
		File newFile = new File(newName);
		if (newFile.isFile()) {
			// do nothing
		} else {
			try {

				// if the file does not exist create the file at appPath
				// location
				// with same name
				if (!newFile.exists()) {
					newFile.createNewFile();
				}

				// this opens the file to read user given
				BufferedReader inputStream = new BufferedReader(new FileReader(
						oldName));
				// this opens the file to write done by app.
				BufferedWriter writeStream = new BufferedWriter(new FileWriter(
						newFile, true));
				String line;
				while ((line = inputStream.readLine()) != null) {
					// here write the lines from path to appFile
					writeStream.append((line + "\n"));

				}
				writeStream.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns getClient from the Driver class
	 * 
	 * @param name
	 * @return client
	 */
	public Client getClient(String name) {
		return Driver.getClientObj(name);
	}

	/**
	 * Returns getFlights from the Driver class
	 * 
	 * @param origin
	 * @param destination
	 * @param date
	 * @return flights
	 */
	public String getFlights(String origin, String destination, String date) {
		return Driver.getFlights(date, origin, destination);
	}

	/**
	 * Returns the flight bookings
	 * 
	 * @param dir
	 * @return result
	 * @throws IOException
	 */
	public String getBookings(String dir) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(dir));
		String text;
		String result = "";
		while ((text = br.readLine()) != null) {
			// parse the data
			if (text.length() > 0) {
				String[] elements = text.split("\\|");
				if (elements[0].equals(this.username)) {
					result += elements[1] + "\n";
				}
			}
		}
		br.close();
		return result;
	}

	/**
	 * Returns itineraries
	 * 
	 * @param origin
	 * @param destination
	 * @param date
	 * @return itineraries
	 */
	public String getItineraries(String origin, String destination, String date) {
		return Driver.getItineraries(date, origin, destination);
	}

	/**
	 * Removes the line
	 * 
	 * @param path
	 * @param line
	 */
	public void removeLine(String path, String line) {

		try {
			File inFile = new File(path);
			// Construct the new file that will later be renamed to the original
			// filename.
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
			BufferedReader br = new BufferedReader(new FileReader(path));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			String text = null;
			// Read from the original file and write to the new
			// unless content matches data to be removed.
			while ((text = br.readLine()) != null) {

				if (!text.trim().equals(line)) {

					pw.println(text + "\r\n");
					pw.flush();
				}
			}
			pw.close();
			br.close();
			// Delete the original file
			inFile.delete();
			// Rename the new file to the filename the original file had.
			tempFile.renameTo(inFile);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Returns itineraries sorted by time
	 * 
	 * @param origin
	 * @param destination
	 * @param date
	 * @return itineraries sorted by time
	 */
	public String getItinerariesByTime(String origin, String destination,
			String date) {
		return Driver.getItinerariesSortedByTime(date, origin, destination);
	}

	/**
	 * Returns itineraries sorted by cost
	 * 
	 * @param origin
	 * @param destination
	 * @param date
	 * @return itineraries sorted by cost
	 */
	public String getItinerariesByCost(String origin, String destination,
			String date) {
		return Driver.getItinerariesSortedByCost(date, origin, destination);
	}

}

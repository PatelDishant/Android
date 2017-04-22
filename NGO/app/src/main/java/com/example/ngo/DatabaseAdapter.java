package com.example.ngo;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class DatabaseAdapter extends AppCompatActivity {
	
	DatabaseManager dbManager;
	SQLiteDatabase userLogin;
	SQLiteDatabase addUser;
	SQLiteDatabase ngoData;
	SQLiteDatabase addProfile;

	public DatabaseAdapter(Context context){
		dbManager = new DatabaseManager(context);
	}
	
	public boolean contains(String userStr) {
		// TODO Auto-generated method stub
		boolean usrExists;
		userLogin = dbManager.getReadableDatabase();
		String[] col ={DatabaseManager.USER_LOGIN_USERNAME};
		String where = "Username = ?";
		String[] arg = {userStr};
		Cursor resultS = userLogin.query(DatabaseManager.USER_LOGIN, col, where, arg, null, null, null, null);
		resultS.moveToFirst();
		if(resultS.getCount() == 1){
			usrExists = true;
		}else{
			usrExists = false;
		}
		return usrExists;
	}
	
	public boolean containsEmail(String emailStr) {
		// TODO Auto-generated method stub
		boolean emailExists;
		userLogin = dbManager.getReadableDatabase();
		String[] col = {DatabaseManager.USER_LOGIN_EMAIL};
		String where = "Email = ?";
		String[] arg = {emailStr};
		Cursor resultS = userLogin.query(DatabaseManager.USER_LOGIN, col, where, arg, null, null, null, null);
		if(resultS.getCount() == 1){
			emailExists = true;
		}else{
			emailExists = false;
		}
		return emailExists;
	}
	
	boolean isValidEmail(CharSequence email) {
		// TODO Auto-generated method stub
		// Not working
		return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
		}
	
	public ArrayList<String> ngoNameList() {
		// TODO Auto-generated method stub
		ArrayList<String> ngoNames;
		ngoData = dbManager.getReadableDatabase();
		Cursor resultS = ngoData.query(DatabaseManager.NGO_DATA, null, null, null, null, null, null, null);
		resultS.moveToFirst();
		ngoNames = new ArrayList<String>();
		while(!resultS.isAfterLast()){
			ngoNames.add(resultS.getString(resultS.getColumnIndex(DatabaseManager.NGO_COL)));
			resultS.moveToNext();
		}
		
		return ngoNames;
	}

	public ArrayList<String> userNameList() {
		// TODO Auto-generated method stub
		ArrayList<String> userNames;
		ngoData = dbManager.getReadableDatabase();
		Cursor resultS = ngoData.query(DatabaseManager.USER_LOGIN, null, null, null, null, null, null, null);
		resultS.moveToFirst();
		userNames = new ArrayList<String>();
		while(!resultS.isAfterLast()){
			userNames.add(resultS.getString(resultS.getColumnIndex(DatabaseManager.USER_LOGIN_USERNAME)));
			resultS.moveToNext();
		}

		return userNames;
	}
		   
		
	
		
	public void registerAccount(String email, String user, String pass){
		addUser = dbManager.getWritableDatabase();
		ContentValues ctValue = new ContentValues();
		ctValue.put(DatabaseManager.USER_LOGIN_USERNAME, user);
		ctValue.put(DatabaseManager.USER_LOGIN_PASSWORD, pass);
		ctValue.put(DatabaseManager.USER_LOGIN_EMAIL, email);
		addUser.insert(DatabaseManager.USER_LOGIN, null, ctValue);
	}
	
	
	/** checkPassword searches the database UserLogin with the argument user name and password
	 * it also checks if the resulting table has only 1 entry since user name is unique 
	 * lastly, it checks if the single entry has the same user name and password (sort of redundant)
	 * */
	public boolean checkPassword(String username, String password){
		
		boolean loginSuccess = false;
		userLogin = dbManager.getReadableDatabase();
		String[] columns = {DatabaseManager.USER_LOGIN_USERNAME,DatabaseManager.USER_LOGIN_PASSWORD};
		String whereColumn = "Username = ? AND Password = ?";
		String[] arguments = {username,password};
		Cursor resultingTable = userLogin.query(DatabaseManager.USER_LOGIN, columns, whereColumn, arguments, null, null, null, null);
		resultingTable.moveToFirst();
		if(resultingTable.getCount() == 1){
			if(resultingTable.getString(resultingTable.getColumnIndex("Username")).equals(username) && resultingTable.getString(resultingTable.getColumnIndex("Password")).equals(password)){
				loginSuccess = true;
			}
			
		}
		return loginSuccess;
	}
	
	public void insertIntoProfile(){
		addProfile = dbManager.getWritableDatabase();
		ContentValues ctValue = new ContentValues();
		
		Bitmap bitmp = BitmapFactory.decodeFile("C:\\Users\\Dishant\\Desktop\\Android_Projects\\Images\\thailand.jpg");
		int bi = bitmp.getByteCount();
		ByteBuffer byBuff = ByteBuffer.allocate(bi);
		bitmp.copyPixelsToBuffer(byBuff);
		byte [] bArray = byBuff.array();
		
		ctValue.put(DatabaseManager.NGO_COL, "Actual NGO");
		ctValue.put(DatabaseManager.NGO_COL2, "647-860-0600");
		ctValue.put(DatabaseManager.NGO_COL3, "Elec-mail");
		ctValue.put(DatabaseManager.NGO_COL4, bArray);
		addUser.insert(DatabaseManager.NGO_DATA, null, ctValue);
	}
	
	public void setProfile(String profileName){
		ngoData = dbManager.getWritableDatabase();
		String where = "Name = ?";
		String[] arg = {profileName};
		Cursor result = ngoData.query(DatabaseManager.NGO_DATA, null, where, arg, null, null, null, null);
		
	}
	
	public byte[] getProfileImage(String name) {
		// TODO Auto-generated method stub
		ngoData = dbManager.getReadableDatabase();
		String where = "Name = ?";
		String[] arg = {name};
		Cursor resultS = ngoData.query(DatabaseManager.NGO_DATA, null, where, arg, null, null, null, null);
		resultS.moveToFirst();
		byte[] img = resultS.getBlob(resultS.getColumnIndex("Image"));
		//Bitmap bimp = BitmapFactory.decodeByteArray(img, 0, img.length);
		return img;
	}

	public CharSequence getProfileName(String name) {
		// TODO Auto-generated method stub
		ngoData = dbManager.getReadableDatabase();
		String where = "Name = ?";
		String[] arg = {name};
		Cursor resultS = ngoData.query(DatabaseManager.NGO_DATA, null, where, arg, null, null, null, null);
		resultS.moveToFirst();
		String pName = resultS.getString(resultS.getColumnIndex("Name"));
		return pName;
		
		
	}

	public CharSequence getProfilePhone(String name) {
		// TODO Auto-generated method stub
		ngoData = dbManager.getReadableDatabase();
		String where = "Name = ?";
		String[] arg = {name};
		Cursor resultS = ngoData.query(DatabaseManager.NGO_DATA, null, where, arg, null, null, null, null);
		resultS.moveToFirst();
		String pNum = resultS.getString(resultS.getColumnIndex("Number"));
		return pNum;
		
	}

	public CharSequence getProfileEmail(String name) {
		// TODO Auto-generated method stub
		ngoData = dbManager.getReadableDatabase();
		String where = "Name = ?";
		String[] arg = {name};
		Cursor resultS = ngoData.query(DatabaseManager.NGO_DATA, null, where, arg, null, null, null, null);
		resultS.moveToFirst();
		String pEmail = resultS.getString(resultS.getColumnIndex("Email"));
		return pEmail;
	}
	

	
	

	
	class DatabaseManager extends SQLiteOpenHelper{
		
		public static final String NGO_DATA = "NgoData";
		public static final String NGO_COL = "Name";
		public static final String NGO_COL2 = "Number";
		public static final String NGO_COL3 = "Email";
		public static final String NGO_COL4 = "Image";
		public static final String USER_LOGIN_EMAIL = "Email";
		private static final String DATABASE_NAME = "NGO.db";
		private static final String USER_LOGIN = "UserLogin";
		private static final String USER_LOGIN_USERNAME = "Username";
		private static final String USER_LOGIN_PASSWORD = "Password";
		private static final int DATABASE_VERSION = 1;
		private static final String CREATE_TABLE_NGO = "CREATE TABLE NgoData(Name TEXT PRIMARY KEY NOT NULL, Number TEXT, Email TEXT, Image BLOB);";
		private static final String CREATE_TABLE_USERLOGIN = "CREATE TABLE UserLogin(Username TEXT PRIMARY KEY NOT NULL, Password TEXT NOT NULL, Email TEXT NOT NULL);";
		private static final String DROP_TABLE_USERLOGIN = "DROP TABLE IF EXISTS UserLogin;";
		private static final String DROP_TABLE_NGO = "DROP TABLE IF EXISTS NgoData;";
		private Context ctx;

		public DatabaseManager(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
			ctx = context;
		}
		
		
		
		/** onCreate for DatabaseManager creates the table UserLogin when a DatabaseManager object is created */
		public void onCreate(SQLiteDatabase db) {
						
			try{
				//Creating the database table UserLogin
				db.execSQL(CREATE_TABLE_USERLOGIN);
				db.execSQL("INSERT INTO UserLogin(Username, Password, Email) VALUES ('Dishant Patel', 'pass' , 'dishant.patelhotmail.com');");
				db.execSQL("INSERT INTO UserLogin(Username,Password, Email) VALUES ('Bonita Patel', 'pass' , 'bonita.patelhotmail.com');");
				db.execSQL("INSERT INTO UserLogin(Username,Password, Email) VALUES ('Scott Hux', 'pass' , 'scott.huxhotmail.com');");
				db.execSQL("INSERT INTO UserLogin(Username,Password, Email) VALUES ('Paresh Pandey', 'pass' , 'pareshpandeyhotmail.com');");
				db.execSQL("INSERT INTO UserLogin(Username,Password, Email) VALUES ('Alex Seeds', 'pass' , 'alexseedshotmail.com');");
				db.execSQL("INSERT INTO UserLogin(Username,Password, Email) VALUES ('Tiffany Lu', 'pass' , 'tiffanyluhotmail.com');");
				db.execSQL("INSERT INTO UserLogin(Username,Password, Email) VALUES ('Jamie Chen', 'pass' , 'jamiechenhotmail.com');");
				db.execSQL(CREATE_TABLE_NGO);
				

				db.execSQL("INSERT INTO NgoData(Name, Number, Email) VALUES('CARP', '(416)363-8748', 'contactuscarpto2.ca');");
				db.execSQL("INSERT INTO NgoData(Name, Number, Email) VALUES('Save the Children Canada','(416)221-5501', 'contactuscarpto2.ca');");
				db.execSQL("INSERT INTO NgoData(Name, Number, Email) VALUES('BC Civil Liberties Association (BCCLA)','(604)630-9754', 'contactuscarpto2.ca');");
				db.execSQL("INSERT INTO NgoData(Name, Number, Email) VALUES('CanadaHelps','(416)628-6948', 'contactuscarpto2.ca');");
				db.execSQL("INSERT INTO NgoData(Name, Number, Email) VALUES('CUSO International','(613)829-7445', 'contactuscarpto2.ca');");
				db.execSQL("INSERT INTO NgoData(Name, Number, Email) VALUES('KAIROS Canada','(416)463-5312', 'contactuscarpto2.ca');");
				db.execSQL("INSERT INTO NgoData(Name, Number, Email) VALUES('Ten Thousand Villages','(519)662-1879', 'contactuscarpto2.ca');");
				db.execSQL("INSERT INTO NgoData(Name, Number, Email) VALUES('ACORN Canada','(416)461-9233', 'contactuscarpto2.ca');");
				db.execSQL("INSERT INTO NgoData(Name, Number, Email) VALUES('Alberta Children's Hospital Foundation','(403)955-8818', 'contactuscarpto2.ca');");
				db.execSQL("INSERT INTO NgoData(Name, Number, Email) VALUES('Children's Health Foundation of Vancouver Island','(250)519-6977', 'contactuscarpto2.ca');");
				/*CREATE TABLE Employees
				(
				    Id int,
				    Name varchar(50) not null,
				    Photo varbinary(max) not null
				)


				INSERT INTO Employees (Id, Name, Photo) 
				SELECT 10, 'John', BulkColumn 
				FROM Openrowset( Bulk 'C:\photo.bmp', Single_Blob) as EmployeePicture*/
				

				
				//db.execSQL("INSERT INTO NgoData(Photo) SELECT BulkColumn FROM Openrowset(Bulk 'C:\\Users\\Dee\\Desktop\\Android Apps\\Images\\globelogo', Single_Blob) as img;");
				
				
			}catch(SQLException exception){
				//raise exception and output a message on toast
				int toastDuration = Toast.LENGTH_LONG;
				String toastMessage = "Error creating Database Table";
				Toast toast = Toast.makeText(ctx, toastMessage, toastDuration);
				toast.show();
			}
		}

		/** onUpgrade updates the DatabaseManger so that the DatabaseManger is the same as before, HOWEVER with the UPDATED INFO */
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
			try{
				//drop the old database and output a new one
				db.execSQL(DROP_TABLE_USERLOGIN);
				db.execSQL(DROP_TABLE_NGO);
				onCreate(db);
			
			}catch(SQLException execption){
				//raise exception and output a message on toast
				int toastDuration = Toast.LENGTH_LONG;
				String toastMessage = "Error updating Database Table";
				Toast toast = Toast.makeText(ctx, toastMessage, toastDuration);
				toast.show();
			}
		}
	}









	





	
}

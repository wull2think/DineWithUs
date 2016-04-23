//S16 18-641 Java Smartphone Design
//Hui Jun Tay (htay)
//Project 1

package database;

//helper class containing constants used for
//jdbc connections, as well as table names
//modify as necessary
public class DatabaseConstants {
	
	public static final String url = 
			"jdbc:mysql://localhost/dinewithusdb?useSSL=false";
	public static final String driverName = "com.mysql.jdbc.Driver";
	public static final String user = "htay";
	public static final String passwd = "641JavaSpring";
	
	
	protected JDBCAdapter jdbc;
	
	public DatabaseConstants() {}
	

}

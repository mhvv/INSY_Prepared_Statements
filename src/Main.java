/*
 * @author mhaden
 * @date 20.02.2016
 * @version 0.5
 * 
 * HowTo:
 * 
 * Open:
 * /etc/postgresql/9.3/main/pg_hba.conf
 * 
 * Add:
 * line 93:
 * host	schokofabrik	schokouser	192.168.110.0/24	md5
 * 
 * host	datenbankname	username	ip adresse			md5
 * 
 * Modify:
 * /etc/postgresql/9.3/main/postgresql.conf
 * line 59: 
 * listen_addresses= '*'
 */

public class Main {
	private static String ip_adress;
	private static String port_number;
	private static String database;
	private static String user;
	private static String password;

	/**
	 * set default value if some input is empty
	 */
	public static void default_value() {
		if (ip_adress == null) {
			ip_adress = "192.168.110.135";
		}
		if (port_number == null) {
			port_number = "5432";
		}
		if (database == null) {
			database = "schokofabrik";
		}
		if (user == null) {
			user = "schokouser";
		}
		if (password == null) {
			password = "schokouser";
		}
	}

	/**
	 * main method
	 * 
	 * @param args
	 *            start arguments
	 */
	public static void main(String[] args) {
		PropertiesFile property = new PropertiesFile();
		CLI cli = new CLI();
		DBConnect conn = new DBConnect();

		if (args.length == 0) { // use properties file if no arguments are given
			ip_adress = property.read_property("ip_adress");
			port_number = property.read_property("port_number");
			database = property.read_property("database");
			user = property.read_property("user");
			password = property.read_property("password");
			conn.db_connect(ip_adress, port_number, database, user, password);
		} else {
			cli.getcli(args);
			ip_adress = cli.getArgument("ip_adress");
			port_number = cli.getArgument("port_number");
			database = cli.getArgument("database");
			user = cli.getArgument("user");
			password = cli.getArgument("password");
			
			conn.db_connect(ip_adress, port_number, database, user, password);
		}
	}
}
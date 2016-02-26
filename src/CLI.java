import org.apache.commons.cli.*;

/**
 * handles CLI functionality
 * 
 * @author mhaden
 * @date 20.02.2016
 * @version 0.5
 */

public class CLI {
	private CommandLine cmd;
	
	/**
	 * handles cli arguments
	 * 
	 * @param args
	 *            program cli arguments
	 */
	public void parse(String[] args) {
		// create Options object
		Options options = new Options();

		// add options
		options.addOption("ip", true, "IP Adress");
		options.addOption("port", true, "Port Number");
		options.addOption("d", true, "Database Name");
		options.addOption("u", true, "Username");
		options.addOption("p", true, "Password");
		options.addOption("c", true, "Configuration Properties File");

		CommandLineParser parser = new DefaultParser();

		try {
			// parse arguments
			cmd = parser.parse(options, args);

		} catch (ParseException exp) {
			System.err.println("Parsing failed.  Reason: " + exp.getMessage());
			// automatically generate the help statement
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("ConnectDatabase", options, true);
		}
	}

	/**
	 * return specific value from from given cli argument
	 * 
	 * @param argumentname name of the argument
	 * @return value from argument
	 */
	public String getArgument(String argumentname) {
		return cmd.getOptionValue(argumentname);
	}
}

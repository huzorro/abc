/**
 * 
 */
package me.huzorro.simple;

import static java.lang.System.out;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CommandStartService {

	/**
	 * 
	 */
	public CommandStartService() {
		// TODO Auto-generated constructor stub
		Options options = new Options();
		OptionBuilder optionBuilder = OptionBuilder.withLongOpt("");
		optionBuilder.create();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
/*		start	-up <channelIds=901077>
		-down 
		-duplex
		-h*/
		
		Options options = new Options();
		options.addOption("h", false, "print help for the command");
		options.addOption( OptionBuilder.withLongOpt( "block-size" )
                .withDescription( "use SIZE-byte blocks" )
                .hasArgs()
                .withArgName("SIZE").withValueSeparator(',').create());
		options.addOption(OptionBuilder.withLongOpt("upstream")
				.withArgName("channelIds=901077").hasArgs().withValueSeparator().withDescription("start upstream")
				.create("u"));
		
		options.addOption(OptionBuilder.withLongOpt("downstream")
				.withArgName("channelIds, .../all").hasArgs().withValueSeparator().withDescription("start downstream")
				.create("d"));
        CommandLineParser parser = new PosixParser();
        CommandLine cli = null;
        try {
        	cli = parser.parse(options, args);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("FAILS");
		}
        
        if(cli.hasOption("h")) {
    		HelpFormatter helpFormatter = new HelpFormatter();
    		OutputStream o = new ByteArrayOutputStream();

    		PrintWriter pw = new PrintWriter(o);
    		helpFormatter.printHelp("start", options);
    		helpFormatter.printUsage(pw, 280, "start", options);
    		pw.flush();
    		out.println(o.toString());
    		
    		return;
        }
        
        if(cli.hasOption("block-size")) {
        	out.println(cli.getOptionValue("block-size"));
        	for(String s : cli.getOptionValues("block-size")) {
        		out.println(s);
        	}
        }
        if(cli.hasOption("u")) {
        	out.println(cli.getArgs().length);
        	for(String s  : cli.getArgs()) {
        		out.println(s + "~~");
        	}
        	for(String s : cli.getOptionValues("upstream")) {
        		out.println(s + "@@");
        	}
        	Properties properties = cli.getOptionProperties("up");
        	for(String p : properties.stringPropertyNames()) {
        		out.println(p);
        		out.println(properties.get(p));
        	}
        	for(Object p : properties.values()) {
        		out.println(p);
        	}
        }
        
		
		
	}

}

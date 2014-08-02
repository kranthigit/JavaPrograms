package com.collective.qa.NetworkLatency.utils;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DbbackupBasedOnDate {

	public static boolean dBBackup(String dbName, String dbUserName,
			String dbPassword, String path) {

		String executeCmd = "mysqldump -u " + dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + path;
		Process runtimeProcess;
		try {
			System.out.println(executeCmd);// this out put works in mysql shell
			runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();

			if (processComplete == 0) {
				System.out.println("Backup created successfully");
				return true;
			} else {
				System.out.println("Could not create the backup");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static void flagCheck(String filepath) {
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		File file = new File(filepath);
		if(file.exists()){
		Long lastModified = file.lastModified();
		Date date = new Date(lastModified);
		String output1 = dateformat.format(date).toString();
		// System.out.println("File created date is "+output1);

		try {
			Date today = new Date();
			String output = dateformat.format(today).toString();
			// System.out.println("Current date is "+output);
			if (output1.equals(output)) {
				System.out
						.println("As the file creation  & Current dates are equal. DB-Backup & other commands initiation aborted..");
			} else {
				System.out
						.println("File Creation & Current dates differ. Initiating DB-Backup & other commands....");
//				 DbbackupBasedOnDate.dBBackup("root","root","testlink","C:/Users/Master/Downloads/testlink_"+ output + ".sql");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		} else {
			System.out.println("File doesn't exist in the specified path");
		}
		

	}

	public static void main(String[] args) throws ParseException {

		flagCheck("/Users/Collective/name.txt");

	}
}

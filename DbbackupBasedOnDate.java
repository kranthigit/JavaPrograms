
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DbbackupBasedOnDate {

	public static boolean executeCommad(String command) {
		Process runtimeProcess;
		try {
			// System.out.println(executeCmd);// this out put works in mysql
			// shell
			runtimeProcess = Runtime.getRuntime().exec(command);
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

	public static boolean commands(String path) {
		String dbName = "testlink";
		String dbUserName = "root";
		String dbPassword = "root";

		String databaseCmd = "mysqldump -u " + dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + path;
		String pullCmd = "git pull" + path;
		String addCmd = "git add" + path;
		String cmtCmd = "git commit -m 'Latest Dump of testlink'";
		String pushCmd = "git push" + path;
		executeCommad(databaseCmd);
		// executeCommad(pullCmd);
		// executeCommad(addCmd);
		// executeCommad(cmtCmd);
		// executeCommad(pushCmd);
		return false;
	}

	public static void flagCheck(String path) {

		long createTime = 0;
		File latestFile = null;
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		File folder = new File(path);
		if (folder.isDirectory()) {
			for (File f : folder.listFiles()) {
				if (createTime < f.lastModified()) {
					latestFile = f;
					createTime = f.lastModified();
				}

			}
		}
		File file = new File(latestFile.getAbsolutePath());
		System.out.println(latestFile.getAbsolutePath());
		if (file.exists()) {
			Long lastModified = file.lastModified();
			Date date = new Date(lastModified);
			String output1 = dateformat.format(date).toString();

			 System.out.println("File created date is "+output1);

			try {

				Date today = new Date();
				String output = dateformat.format(today).toString();
				System.out.println("Current date is " + output);

				if (output1.equals(output)) {
					System.out
							.println("As the file creation  & Current dates are equal. DB-Backup & other commands initiation aborted..");
				} else {
					System.out
							.println("File Creation & Current dates differ. Initiating DB-Backup & other commands....");
					DbbackupBasedOnDate
							.commands("/home/kranthi/Dbbackup/testlink_"+ output + ".sql");

				}

			} catch (Exception e) {
				e.getMessage();
			}
		} else {
			System.out.println("File doesn't exist in the specified path");
		}

	}

	public static void main(String[] args) throws ParseException {

		flagCheck("/home/kranthi/Dbbackup/");

	}
}

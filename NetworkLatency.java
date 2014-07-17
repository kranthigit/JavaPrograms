import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkLatency {

	public static String ip = "127.0.0.1";
	public static String ping1 = "ping -c 5 ";

	public static void PingIP(String command) {

		CharSequence obj = "Packet loss";
		String output = "";
		String output1 = "";

		try {
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));

			// reading output stream of the command
			String x = "";
			while ((output = inputStream.readLine()) != null) {
				output1 = output;
				if (output.contains(obj)) {
					x = output;
				}

			}
			System.out.println((x.split(",")[2]).trim().split("%")[0] + "%");
			String temp[] = output1.split("/");
			System.out.println(temp[1] + temp[5]);

			inputStream.close();
			process.destroy();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(output1);
	}

	public static boolean isIpReachable() {

		try {
			InetAddress inet = InetAddress.getByName(ip);
			boolean reachable = inet.isReachable(5000);

			if (reachable) {
				System.out.println("Status: Host is Reacheable... Gogin to ping the ip address");
				// System.out.println(ping1 + ip);
				PingIP(ping1 + ip);

			} else {
				System.out.println("Status: Host is Not Reacheable... Unable to ping the server.");
			}
		} catch (UnknownHostException e) {
			System.err.println("Host does not exists");
		} catch (IOException e) {
			System.err.println("Error in reaching the Host");
		}
		return false;
	}

	public static void main(String[] args) {

		System.out.println("About to check Host Reacheable or Not..\n");
		isIpReachable();
	}
}






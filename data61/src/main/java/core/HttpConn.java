package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConn {
	 public static void sendPOST(StringWriter result) throws IOException {
			String   POST_PARAMS = result.toString();
				URL obj = new URL("http://localhost:8080");
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod("POST");
				
				// For POST only - START
				con.setDoOutput(true);
				OutputStream os = con.getOutputStream();
				os.write(POST_PARAMS.getBytes());
				os.flush();
				os.close();
				// For POST only - END

				int responseCode = con.getResponseCode();
				System.out.println("POST Response Code :: " + responseCode);

				if (responseCode == HttpURLConnection.HTTP_OK) { //success
					BufferedReader in = new BufferedReader(new InputStreamReader(
							con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();

					// print result
					
					System.out.println(response.toString());
				} else {
					
					System.out.println("POST request not worked");
				}
			}


}

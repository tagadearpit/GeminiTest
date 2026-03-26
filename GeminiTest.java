import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GeminiTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        String apiKey = "AIzaSyAAJikm3bL-b4pl6q7a0xtP3UDz6c8jHgE";

        // Enter question from terminal
        System.out.print("Enter your question: ");
        String question = scanner.nextLine().trim();

        try {
            // Gemini endpoint
            String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey;

            // JSON payload
            String jsonInputString = "{ \"contents\": [ { \"parts\": [ { \"text\": \"" + question + "\" } ] } ] }";

            // Open connection
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true);

            // Send request
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Read response
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response from Gemini:");
                System.out.println(response.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
import java.io.*;
import java.net.*;
//import org.json.JSONObject;

public class Chatmatta {
    private final String modelName;

    // Constructor accepting model name
    public Chatmatta(String modelName) {
        this.modelName = modelName;
    }

    // Function to send request to Ollama model
    public String getResponse(String prompt) {
        try {
            URL url = new URL("http://localhost:11434/api/generate");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            String jsonInput = String.format("{\"model\":\"%s\",\"prompt\":\"%s\"}", modelName, prompt);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                // Ollama streams JSON objects line-by-line
                if (line.contains("\"response\"")) {
    int start = line.indexOf("\"response\":\"") + 12;
    int end = line.indexOf("\"", start);
    if (start > 11 && end > start) {
        response.append(line.substring(start, end));
    }
}

            }
            br.close();
            return response.toString().trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "⚠️ Error: " + e.getMessage();
        }
    }
}


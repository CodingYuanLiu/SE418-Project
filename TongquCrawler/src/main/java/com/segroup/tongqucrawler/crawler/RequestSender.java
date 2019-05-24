import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RequestSender {
    public String send(String requestUrl, String method) {
        HttpURLConnection con;
        BufferedReader buffer;
        StringBuffer resultBuffer;
        try {
            URL url = new URL(requestUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = con.getInputStream();
                resultBuffer = new StringBuffer();
                String line;
                buffer = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                while ((line = buffer.readLine()) != null) {
                    resultBuffer.append(line);
                }
                return resultBuffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}

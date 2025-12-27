package co.unosend;

import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

class HttpClient {
    private static final Gson gson = new Gson();

    static <T> T post(Unosend client, String path, Object body, Class<T> responseType) throws UnosendException {
        return request(client, "POST", path, body, responseType);
    }

    static <T> T get(Unosend client, String path, Class<T> responseType) throws UnosendException {
        return request(client, "GET", path, null, responseType);
    }

    static <T> T delete(Unosend client, String path, Class<T> responseType) throws UnosendException {
        return request(client, "DELETE", path, null, responseType);
    }

    private static <T> T request(Unosend client, String method, String path, Object body, Class<T> responseType) throws UnosendException {
        try {
            URL url = new URL(client.getBaseUrl() + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestMethod(method);
            conn.setRequestProperty("Authorization", "Bearer " + client.getApiKey());
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "unosend-java/1.0.0");

            if (body != null && (method.equals("POST") || method.equals("PUT"))) {
                conn.setDoOutput(true);
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(gson.toJson(body).getBytes(StandardCharsets.UTF_8));
                }
            }

            int code = conn.getResponseCode();
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    code >= 400 ? conn.getErrorStream() : conn.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) response.append(line);
            }

            if (code >= 400) {
                throw new UnosendException(response.toString(), code);
            }

            return responseType == Void.class ? null : gson.fromJson(response.toString(), responseType);
        } catch (IOException e) {
            throw new UnosendException("Network error: " + e.getMessage(), 0);
        }
    }
}

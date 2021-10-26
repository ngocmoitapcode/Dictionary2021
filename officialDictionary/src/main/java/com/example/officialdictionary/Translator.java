package com.example.officialdictionary;

import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class Translator {
    private static String myUrl = "https://script.google.com/macros/s/AKfycbwZpA622GfjIk9yIbIuViv5stx6D-wa9r9x8dWuSMtusGCO1czR/exec";
    public static String translate(String fromLang, String toLang, String text) {
        String getTranslate = "";
        try {
            String urlStr = myUrl + "?q=" + URLEncoder.encode(text, "UTF-8") + "&target=" + toLang + "&source=" + fromLang;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            int statusCode = conn.getResponseCode();
            System.out.println("Status Code: " + statusCode);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()
            ));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                getTranslate += output;
            }
            conn.disconnect();
            return getTranslate;
        } catch (MalformedURLException e) {
            System.out.println("Internet is not connected");
        } catch (IOException e) {
            System.out.println("Internet is not connected");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Internet is not connected!");
            alert.show();
        }
        return getTranslate;
    }
}

/*


 */
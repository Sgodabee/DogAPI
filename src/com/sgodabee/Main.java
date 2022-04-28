package com.sgodabee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Main {

    private static HttpURLConnection connection;
    StringBuffer respondBuffer = new StringBuffer();

    public static void main(String[] args)  {
       BufferedReader bufferedReader;
        String line;
        StringBuffer stringBuffer = new StringBuffer();


        try {
            URL url = new URL("https://dog.ceo/api/breeds/list/all");
            connection = (HttpURLConnection) url.openConnection();


            connection.setRequestMethod("GET");
            connection.setConnectTimeout(150000);
            connection.setReadTimeout(150000);

            int status = connection.getResponseCode();

            if (status>299)
            {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line=bufferedReader.readLine()) !=null)
                {
                    stringBuffer.append(line);
                }
                bufferedReader.close();

            }

            else
            {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line=bufferedReader.readLine()) !=null)
                {
                    stringBuffer.append(line);
                }
                bufferedReader.close();
                System.out.println("Status"+": "+ "Success");
            }
            System.out.println(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            connection.disconnect();
        }

    }
}


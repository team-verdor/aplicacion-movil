package com.verdor.verdormobile.net;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Net {

    private HttpURLConnection urlConnection;

    public String requestString(String url, Map<String, String> parameters) {
        StringBuilder result = new StringBuilder();
        try {
            URL finalurl = new URL(url);

            urlConnection = (HttpURLConnection) finalurl.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);

            if (parameters != null) {
                urlConnection.setDoOutput(true);
                writeOutput(urlConnection, (HashMap<String, String>) parameters);
            }


            writeInput(urlConnection, result);

            urlConnection.disconnect();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

        Log.i("httpResponse", result.toString());

        return result.toString();
    }

    private void writeOutput(HttpURLConnection conn, HashMap<String, String> postDataParams) throws IOException {
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(getPostDataString(postDataParams));

        writer.flush();
        writer.close();
        os.close();
    }

    private void writeInput(HttpURLConnection conn, StringBuilder strb) throws IOException {
        InputStream in = new BufferedInputStream(conn.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = reader.readLine()) != null) {
            strb.append(line);
        }
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    public static void logNetInformation(Context c) {
        WifiManager wifii;
        DhcpInfo d;
        String s_dns1;
        String s_dns2;
        String s_gateway;
        String s_ipAddress;
        String s_leaseDuration;
        String s_netmask;
        String s_serverAddress;
        TextView info;

        wifii = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
        d = wifii.getDhcpInfo();

        s_dns1 = "DNS 1: " + String.valueOf(d.dns1);
        s_dns2 = "DNS 2: " + String.valueOf(d.dns2);
        s_gateway = "Default Gateway: " + String.valueOf(d.gateway);
        s_ipAddress = "IP Address: " + String.valueOf(d.ipAddress);
        s_leaseDuration = "Lease Time: " + String.valueOf(d.leaseDuration);
        s_netmask = "Subnet Mask: " + String.valueOf(d.netmask);
        s_serverAddress = "Server IP: " + String.valueOf(d.serverAddress);

        Log.i("Network Info", s_dns1 + "\n" + s_dns2 + "\n" + s_gateway + "\n" + s_ipAddress + "\n" + s_leaseDuration + "\n" + s_netmask + "\n" + s_serverAddress);
    }

    static public String intToIp(int addr) {
        return ((addr & 0xFF) + "." +
                ((addr >>>= 8) & 0xFF) + "." +
                ((addr >>>= 8) & 0xFF) + "." +
                ((addr >>>= 8) & 0xFF));
    }
}
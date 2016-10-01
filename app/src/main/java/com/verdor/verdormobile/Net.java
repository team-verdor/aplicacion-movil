package com.verdor.verdormobile;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Net {

    private HttpURLConnection urlConnection;

    public String requestString(String direccion) {
        StringBuilder result = new StringBuilder();
        try {
            URL finalurl = new URL( direccion);

            Log.d("URL", finalurl.toString());

            urlConnection = (HttpURLConnection) finalurl.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            urlConnection.disconnect();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

        Log.i("httpResponse", result.toString());

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
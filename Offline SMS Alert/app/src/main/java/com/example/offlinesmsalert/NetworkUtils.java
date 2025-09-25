package com.example.offlinesmsalert;

import java.net.InetAddress;

public class NetworkUtils {
    public static boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("8.8.8.8"); // Google DNS
            return !ipAddr.equals("");
        } catch (Exception e) {
            return false;
        }
    }
}

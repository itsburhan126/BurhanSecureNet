package com.network.burhansecurenet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.telephony.TelephonyManager

/**
 * Utility class for network-related operations
 */
object NetworkUtils {
    
    /**
     * Gets the mobile network type (2G, 3G, 4G, 5G)
     * @param context The application context
     * @return A string representing the mobile network type, or "UNKNOWN" if not available
     */
    fun getMobileNetworkType(context: Context): String {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        
        // Check if we're on a mobile network
        if (BurhanSecureNet.getNetworkType(context) != "MOBILE") {
            return "NOT_MOBILE"
        }
        
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            when (telephonyManager.dataNetworkType) {
                TelephonyManager.NETWORK_TYPE_GPRS,
                TelephonyManager.NETWORK_TYPE_EDGE,
                TelephonyManager.NETWORK_TYPE_CDMA,
                TelephonyManager.NETWORK_TYPE_1xRTT,
                TelephonyManager.NETWORK_TYPE_IDEN,
                TelephonyManager.NETWORK_TYPE_GSM -> "2G"
                
                TelephonyManager.NETWORK_TYPE_UMTS,
                TelephonyManager.NETWORK_TYPE_EVDO_0,
                TelephonyManager.NETWORK_TYPE_EVDO_A,
                TelephonyManager.NETWORK_TYPE_HSDPA,
                TelephonyManager.NETWORK_TYPE_HSUPA,
                TelephonyManager.NETWORK_TYPE_HSPA,
                TelephonyManager.NETWORK_TYPE_EVDO_B,
                TelephonyManager.NETWORK_TYPE_EHRPD,
                TelephonyManager.NETWORK_TYPE_HSPAP,
                TelephonyManager.NETWORK_TYPE_TD_SCDMA -> "3G"
                
                TelephonyManager.NETWORK_TYPE_LTE,
                TelephonyManager.NETWORK_TYPE_IWLAN,
                19 /* LTE_CA */ -> "4G"
                
                TelephonyManager.NETWORK_TYPE_NR -> "5G"
                
                else -> "UNKNOWN"
            }
        } else {
            @Suppress("DEPRECATION")
            when (telephonyManager.networkType) {
                TelephonyManager.NETWORK_TYPE_GPRS,
                TelephonyManager.NETWORK_TYPE_EDGE,
                TelephonyManager.NETWORK_TYPE_CDMA,
                TelephonyManager.NETWORK_TYPE_1xRTT,
                TelephonyManager.NETWORK_TYPE_IDEN,
                TelephonyManager.NETWORK_TYPE_GSM -> "2G"
                
                TelephonyManager.NETWORK_TYPE_UMTS,
                TelephonyManager.NETWORK_TYPE_EVDO_0,
                TelephonyManager.NETWORK_TYPE_EVDO_A,
                TelephonyManager.NETWORK_TYPE_HSDPA,
                TelephonyManager.NETWORK_TYPE_HSUPA,
                TelephonyManager.NETWORK_TYPE_HSPA,
                TelephonyManager.NETWORK_TYPE_EVDO_B,
                TelephonyManager.NETWORK_TYPE_EHRPD,
                TelephonyManager.NETWORK_TYPE_HSPAP,
                TelephonyManager.NETWORK_TYPE_TD_SCDMA -> "3G"
                
                TelephonyManager.NETWORK_TYPE_LTE,
                TelephonyManager.NETWORK_TYPE_IWLAN,
                19 /* LTE_CA */ -> "4G"
                
                TelephonyManager.NETWORK_TYPE_NR -> "5G"
                
                else -> "UNKNOWN"
            }
        }
    }
    
    /**
     * Gets the WiFi signal strength as a percentage
     * @param context The application context
     * @return A value between 0-100 representing WiFi signal strength, or -1 if not on WiFi
     */
    fun getWifiSignalStrength(context: Context): Int {
        // Only proceed if we're on WiFi
        if (BurhanSecureNet.getNetworkType(context) != "WIFI") {
            return -1
        }
        
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as android.net.wifi.WifiManager
        val wifiInfo = wifiManager.connectionInfo
        
        val rssi = wifiInfo.rssi
        val level = WifiManager.calculateSignalLevel(rssi, 5)
        
        // Convert to percentage (0-100)
        return (level * 100) / 4
    }
    
    /**
     * Checks if the device is connected to a metered network
     * @param context The application context
     * @return true if the device is connected to a metered network, false otherwise
     */
    fun isMeteredConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            
            !capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
        } else {
            @Suppress("DEPRECATION")
            connectivityManager.isActiveNetworkMetered
        }
    }
}

/**
 * Helper class for WiFi-related operations
 */
private object WifiManager {
    /**
     * Calculate the signal level from RSSI
     * @param rssi The RSSI value
     * @param numLevels The number of levels to divide the signal into
     * @return The signal level
     */
    fun calculateSignalLevel(rssi: Int, numLevels: Int): Int {
        if (rssi <= -100) {
            return 0
        } else if (rssi >= -55) {
            return numLevels - 1
        } else {
            val inputRange = (-55 - (-100))
            val outputRange = numLevels - 1
            return ((rssi - (-100)).toFloat() * outputRange / inputRange).toInt()
        }
    }
}
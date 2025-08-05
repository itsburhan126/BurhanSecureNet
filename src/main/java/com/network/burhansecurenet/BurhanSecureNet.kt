package com.network.burhansecurenet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

/**
 * BurhanSecureNet - A secure networking utility library for Android
 * Version: 1.0.4
 * 
 * A lightweight networking library for Android that provides utilities for network connectivity
 * checking, secure HTTP requests, and network diagnostics.
 */
object BurhanSecureNet {
    private const val TAG = "BurhanSecureNet"
    private const val VERSION = "1.0.4"
    
    /**
     * Greets the user with a welcome message
     * @param name The name to greet
     * @return A greeting message
     */
    fun greet(name: String): String {
        return "Hello, $name from BurhanSecureNet v$VERSION!"
    }
    
    /**
     * Checks if the device has an active internet connection
     * @param context The application context
     * @return true if the device has an active internet connection, false otherwise
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            
            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                   capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo
            @Suppress("DEPRECATION")
            return networkInfo != null && networkInfo.isConnected
        }
    }
    
    /**
     * Gets the current network type (WIFI, MOBILE, etc.)
     * @param context The application context
     * @return A string representing the current network type, or "NONE" if no network is available
     */
    fun getNetworkType(context: Context): String {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return "NONE"
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return "NONE"
            
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "WIFI"
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "MOBILE"
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> "ETHERNET"
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> "BLUETOOTH"
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> "VPN"
                else -> "UNKNOWN"
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return "NONE"
            
            @Suppress("DEPRECATION")
            return when (networkInfo.type) {
                ConnectivityManager.TYPE_WIFI -> "WIFI"
                ConnectivityManager.TYPE_MOBILE -> "MOBILE"
                ConnectivityManager.TYPE_ETHERNET -> "ETHERNET"
                ConnectivityManager.TYPE_BLUETOOTH -> "BLUETOOTH"
                ConnectivityManager.TYPE_VPN -> "VPN"
                else -> "UNKNOWN"
            }
        }
    }
    
    /**
     * Checks if a specific URL is reachable
     * @param url The URL to check
     * @param timeout The connection timeout in milliseconds
     * @param callback A callback function that receives a boolean indicating if the URL is reachable
     */
    fun isUrlReachable(url: String, timeout: Int = 5000, callback: (Boolean) -> Unit) {
        thread {
            try {
                val connection = URL(url).openConnection() as HttpURLConnection
                connection.connectTimeout = timeout
                connection.readTimeout = timeout
                connection.requestMethod = "HEAD"
                val responseCode = connection.responseCode
                callback(responseCode in 200..399)
            } catch (e: IOException) {
                logError("URL_CHECK", "Error checking URL: ${e.message}")
                callback(false)
            }
        }
    }
    
    /**
     * Gets the current library version
     * @return The library version string
     */
    fun getVersion(): String {
        return VERSION
    }
    
    /**
     * Logs a debug message
     * @param tag The log tag
     * @param message The message to log
     */
    fun logDebug(tag: String, message: String) {
        Log.d("$TAG-$tag", message)
    }
    
    /**
     * Logs an error message
     * @param tag The log tag
     * @param message The error message to log
     */
    fun logError(tag: String, message: String) {
        Log.e("$TAG-$tag", message)
    }
    
    /**
     * Logs an info message
     * @param tag The log tag
     * @param message The info message to log
     */
    fun logInfo(tag: String, message: String) {
        Log.i("$TAG-$tag", message)
    }
}
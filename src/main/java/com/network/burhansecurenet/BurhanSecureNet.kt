package com.network.burhansecurenet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log

/**
 * BurhanSecureNet - A secure networking utility library for Android
 * Version: 1.0.3
 */
object BurhanSecureNet {
    private const val TAG = "BurhanSecureNet"
    
    /**
     * Greets the user with a welcome message
     * @param name The name to greet
     * @return A greeting message
     */
    fun greet(name: String): String {
        return "Hello, $name from BurhanSecureNet v1.0.3!"
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
     * Gets the current library version
     * @return The library version string
     */
    fun getVersion(): String {
        return "1.0.3"
    }
    
    /**
     * Logs a message with the specified tag and message
     * @param tag The log tag
     * @param message The message to log
     */
    fun logMessage(tag: String, message: String) {
        Log.d("$TAG-$tag", message)
    }
}
package com.network.burhansecurenet

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

/**
 * A simple HTTP client for making network requests
 */
object HttpClient {
    private const val TAG = "HttpClient"
    private const val DEFAULT_TIMEOUT = 10000 // 10 seconds
    
    /**
     * Response data class for HTTP responses
     */
    data class Response(
        val code: Int,
        val body: String,
        val headers: Map<String, List<String>>,
        val error: String? = null
    )
    
    /**
     * Makes a GET request to the specified URL
     * @param context The application context
     * @param url The URL to make the request to
     * @param headers Optional headers to include in the request
     * @param timeout The connection timeout in milliseconds
     * @param callback A callback function that receives the response
     */
    fun get(
        context: Context,
        url: String,
        headers: Map<String, String> = mapOf(),
        timeout: Int = DEFAULT_TIMEOUT,
        callback: (Response) -> Unit
    ) {
        if (!BurhanSecureNet.isNetworkAvailable(context)) {
            callback(Response(0, "", mapOf(), "No network connection available"))
            return
        }
        
        thread {
            try {
                val connection = URL(url).openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = timeout
                connection.readTimeout = timeout
                
                // Add headers
                headers.forEach { (key, value) ->
                    connection.setRequestProperty(key, value)
                }
                
                // Get response
                val responseCode = connection.responseCode
                val responseHeaders = connection.headerFields
                
                val inputStream = if (responseCode >= 400) {
                    connection.errorStream
                } else {
                    connection.inputStream
                }
                
                val reader = BufferedReader(InputStreamReader(inputStream))
                val response = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()
                
                callback(Response(responseCode, response.toString(), responseHeaders))
            } catch (e: Exception) {
                BurhanSecureNet.logError(TAG, "Error making GET request: ${e.message}")
                callback(Response(0, "", mapOf(), e.message))
            }
        }
    }
    
    /**
     * Makes a POST request to the specified URL
     * @param context The application context
     * @param url The URL to make the request to
     * @param body The request body
     * @param headers Optional headers to include in the request
     * @param timeout The connection timeout in milliseconds
     * @param callback A callback function that receives the response
     */
    fun post(
        context: Context,
        url: String,
        body: String,
        headers: Map<String, String> = mapOf(),
        timeout: Int = DEFAULT_TIMEOUT,
        callback: (Response) -> Unit
    ) {
        if (!BurhanSecureNet.isNetworkAvailable(context)) {
            callback(Response(0, "", mapOf(), "No network connection available"))
            return
        }
        
        thread {
            try {
                val connection = URL(url).openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.connectTimeout = timeout
                connection.readTimeout = timeout
                connection.doOutput = true
                
                // Add headers
                headers.forEach { (key, value) ->
                    connection.setRequestProperty(key, value)
                }
                
                // Set content type if not specified in headers
                if (!headers.containsKey("Content-Type")) {
                    connection.setRequestProperty("Content-Type", "application/json")
                }
                
                // Write request body
                val writer = OutputStreamWriter(connection.outputStream)
                writer.write(body)
                writer.flush()
                writer.close()
                
                // Get response
                val responseCode = connection.responseCode
                val responseHeaders = connection.headerFields
                
                val inputStream = if (responseCode >= 400) {
                    connection.errorStream
                } else {
                    connection.inputStream
                }
                
                val reader = BufferedReader(InputStreamReader(inputStream))
                val response = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()
                
                callback(Response(responseCode, response.toString(), responseHeaders))
            } catch (e: Exception) {
                BurhanSecureNet.logError(TAG, "Error making POST request: ${e.message}")
                callback(Response(0, "", mapOf(), e.message))
            }
        }
    }
}
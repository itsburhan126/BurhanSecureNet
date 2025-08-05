# BurhanSecureNet

A lightweight networking library for Android that provides utilities for network connectivity checking, secure HTTP requests, and network diagnostics.

## Features

- Check network availability
- Determine network type (WiFi, Mobile, etc.)
- Check if a specific URL is reachable
- Mobile network type detection (2G, 3G, 4G, 5G)
- WiFi signal strength measurement
- Metered connection detection
- Simple HTTP client for GET and POST requests
- Logging utilities

## Installation

### Step 1: Add JitPack repository

Add the JitPack repository to your root build.gradle at the end of repositories:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Or in settings.gradle.kts (Kotlin DSL):

```kotlin
dependencyResolutionManagement {
    repositories {
        ...
        maven { url = uri("https://jitpack.io") }
    }
}
```

### Step 2: Add the dependency

Add the dependency to your app's build.gradle:

```gradle
dependencies {
    implementation 'com.github.itsburhan126:BurhanSecureNet:v1.0.5'
}
```

Or in build.gradle.kts (Kotlin DSL):

```kotlin
dependencies {
    implementation("com.github.itsburhan126:BurhanSecureNet:v1.0.5")
}
```

**Note:** Make sure to check [JitPack](https://jitpack.io/#itsburhan126/BurhanSecureNet) for the latest version tag.

## Usage

### Check Network Availability

```kotlin
import com.network.burhansecurenet.BurhanSecureNet

// Check if network is available
val isNetworkAvailable = BurhanSecureNet.isNetworkAvailable(context)
if (isNetworkAvailable) {
    // Network is available, proceed with network operations
} else {
    // Network is not available, show error message
}
```

### Get Network Type

```kotlin
import com.network.burhansecurenet.BurhanSecureNet

// Get the current network type
val networkType = BurhanSecureNet.getNetworkType(context)
when (networkType) {
    "WIFI" -> {
        // Connected to WiFi
    }
    "MOBILE" -> {
        // Connected to mobile data
    }
    "NONE" -> {
        // No network connection
    }
    else -> {
        // Other network types (ETHERNET, BLUETOOTH, VPN, etc.)
    }
}
```

### Check URL Reachability

```kotlin
import com.network.burhansecurenet.BurhanSecureNet

// Check if a URL is reachable
BurhanSecureNet.isUrlReachable("https://example.com") { isReachable ->
    if (isReachable) {
        // URL is reachable
    } else {
        // URL is not reachable
    }
}

// With custom timeout (in milliseconds)
BurhanSecureNet.isUrlReachable("https://example.com", 10000) { isReachable ->
    // Handle result
}
```

### Mobile Network Type Detection

```kotlin
import com.network.burhansecurenet.NetworkUtils

// Get mobile network type (2G, 3G, 4G, 5G)
val mobileNetworkType = NetworkUtils.getMobileNetworkType(context)
when (mobileNetworkType) {
    "2G" -> {
        // Connected to 2G network
    }
    "3G" -> {
        // Connected to 3G network
    }
    "4G" -> {
        // Connected to 4G network
    }
    "5G" -> {
        // Connected to 5G network
    }
    "NOT_MOBILE" -> {
        // Not connected to a mobile network
    }
    else -> {
        // Unknown mobile network type
    }
}
```

### WiFi Signal Strength

```kotlin
import com.network.burhansecurenet.NetworkUtils

// Get WiFi signal strength as percentage (0-100)
val wifiSignalStrength = NetworkUtils.getWifiSignalStrength(context)
if (wifiSignalStrength >= 0) {
    // Use the signal strength value (0-100)
} else {
    // Not connected to WiFi
}
```

### Metered Connection Detection

```kotlin
import com.network.burhansecurenet.NetworkUtils

// Check if connected to a metered network
val isMetered = NetworkUtils.isMeteredConnection(context)
if (isMetered) {
    // Connected to a metered network (like mobile data)
    // Consider reducing data usage
} else {
    // Connected to an unmetered network (like WiFi)
}
```

### HTTP Client

```kotlin
import com.network.burhansecurenet.HttpClient

// Make a GET request
HttpClient.get(context, "https://api.example.com/data") { response ->
    if (response.code in 200..299) {
        // Success - use response.body
        val data = response.body
    } else {
        // Error - handle response.error
    }
}

// Make a POST request
val jsonBody = "{\"name\":\"John\",\"age\":30}"
val headers = mapOf("Content-Type" to "application/json")

HttpClient.post(context, "https://api.example.com/users", jsonBody, headers) { response ->
    if (response.code in 200..299) {
        // Success - use response.body
        val data = response.body
    } else {
        // Error - handle response.error
    }
}
```

### Logging

```kotlin
import com.network.burhansecurenet.BurhanSecureNet

// Log debug message
BurhanSecureNet.logDebug("TAG", "Debug message")

// Log error message
BurhanSecureNet.logError("TAG", "Error message")

// Log info message
BurhanSecureNet.logInfo("TAG", "Info message")
```

### Get Library Version

```kotlin
import com.network.burhansecurenet.BurhanSecureNet

// Get the current library version
val version = BurhanSecureNet.getVersion()
```

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

Md Burhan Uddin

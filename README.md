<h1 align="center">🚀 BurhanSecureNet</h1>

<p align="center">
  <a href="https://jitpack.io/#itsburhan126/BurhanSecureNet"><img src="https://jitpack.io/v/itsburhan126/BurhanSecureNet.svg" alt="JitPack"></a>
</p>

<p align="center">
  <strong>Advanced Secure Networking Solution built with Kotlin for Android, implementing hybrid AES+RSA encryption.</strong>
</p>

<p align="center">
  <a href="#features">Features</a> •
  <a href="#installation">Installation</a> •
  <a href="#usage">Usage</a> •
  <a href="#technologies-used">Technologies Used</a> •
  <a href="#contributing">Contributing</a> •
  <a href="#license">License</a>
</p>

<hr />

<h2 id="features">✨ Features</h2>
<ul>
  <li>Hybrid encryption using AES for data and RSA for key exchange</li>
  <li>Secure communication protocols optimized for Android apps</li>
  <li>Clean and modular Kotlin code architecture</li>
  <li>Easy integration with secure backend services</li>
  <li>Detailed logging and error handling for secure data transmission</li>
  <li>Network connectivity checking</li>
  <li>Secure logging utilities</li>
  <li>Version tracking</li>
  <li>Simple integration</li>
</ul>

<h2 id="installation">🛠️ Installation</h2>

<h3>Option 1: Using JitPack</h3>

<h4>Step 1: Add the JitPack repository to your build file</h4>

<p>Add it in your root build.gradle at the end of repositories:</p>

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

<p>Or in settings.gradle.kts:</p>

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

<h4>Step 2: Add the dependency</h4>

```gradle
dependencies {
    implementation 'com.github.itsburhan126:BurhanSecureNet:1.0.3'
}
```

<h3>Option 2: Manual Setup</h3>
<p>Follow these steps to set up the Android project in your development environment:</p>

```
# Clone the repository
git clone https://github.com/itsburhan126/BurhanSecureNet.git

# Open Android Studio

# Choose "Open an Existing Project" and select the cloned repository folder

# Allow Android Studio to sync and download all Gradle dependencies automatically

# Connect your Android device via USB (with USB debugging enabled) or start an emulator

# Build and run the app using the "Run" button or Shift + F10
```

<h2 id="usage">🎯 Usage</h2>

<h3>Check Network Availability</h3>

```kotlin
import com.network.burhansecurenet.BurhanSecureNet

// Check if network is available
val isNetworkAvailable = BurhanSecureNet.isNetworkAvailable(context)
if (isNetworkAvailable) {
    // Perform network operations
} else {
    // Show network error message
}
```

<h3>Logging</h3>

```kotlin
// Log a message
BurhanSecureNet.logMessage("MyActivity", "This is a log message")
```

<h3>Get Library Version</h3>

```kotlin
// Get the current library version
val version = BurhanSecureNet.getVersion()
```

<h3>Secure Data Transmission</h3>
<p>After launching the app, it will:</p>
<ul>
  <li>Perform secure hybrid encryption of data before sending to backend services</li>
  <li>Handle encrypted responses and securely decrypt data on the client side</li>
  <li>Provide smooth UI for secure networking tasks</li>
</ul>

<h2 id="technologies-used">⚙️ Technologies Used</h2>
<ul>
  <li>Kotlin (Android)</li>
  <li>Android Jetpack Components</li>
  <li>OkHttp (HTTP client)</li>
  <li>Hybrid Encryption (AES + RSA)</li>
  <li>Gradle Build System</li>
</ul>

<h2 id="contributing">🤝 Contributing</h2>
<p>Contributions are welcome! Please fork the repo, create feature branches, and submit pull requests. Report issues and suggest improvements.</p>

<h2 id="license">📄 License</h2>

```
Copyright 2023 Md Burhan Uddin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

<hr />

<p align="center">
  Made with ❤️ by <a href="https://github.com/itsburhan126">Md Burhan Uddin</a>
</p>

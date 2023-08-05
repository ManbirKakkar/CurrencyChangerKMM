## CurrencyChangerKMM

CurrencyChangerKMM is a multi-platform mobile application that allows users to convert between different currencies. This project is built using Kotlin Multiplatform Mobile (KMM) technology, which enables sharing business logic code between Android and iOS platforms, reducing development time and maintenance efforts.

## Features
Currency conversion: Users can convert between various currencies using real-time exchange rates.
Automatic updates: Exchange rates are automatically updated at regular intervals to provide the most accurate and up-to-date information.
Prerequisites
Before running the CurrencyChangerKMM app, ensure you have the following prerequisites installed on your system:

Android Studio (for Android development)
Xcode (for iOS development)
Kotlin 1.5 or higher
Getting Started
Follow these steps to get the project up and running on your local machine:

## Clone the repository:

```bash
git clone https://github.com/ManbirKakkar/CurrencyChangerKMM.git
```

Open the project in Android Studio and Xcode to set up the respective environments for Android and iOS development.

Build the project using the IDEs' build commands.

Run the CurrencyChangerKMM app on an Android emulator or iOS simulator to test the functionalities.

## Project Structure
The project follows a standard KMM project structure with the following key directories:

shared: Contains the shared Kotlin code that can be used across both Android and iOS platforms. The core business logic and currency conversion functionality reside here.
androidApp: Android-specific code and resources for the application. This module depends on the shared module to access the shared Kotlin code.
iosApp: iOS-specific code and resources for the application. Like the androidApp, this module also depends on the shared module.
Dependencies
The CurrencyChangerKMM app utilizes the following external dependencies:

1. Ktor: A powerful networking library for making API requests and handling responses.
2. kotlinx.serialization: A library for serializing Kotlin objects to and from JSON format.
3. kotlinx.coroutines: A library for asynchronous programming using coroutines.
4. KMM Compose: A Kotlin Multiplatform library for building UI using Jetpack Compose, enabling a consistent UI across Android and iOS platforms.
5. Clean Architecture: The project follows the principles of Clean Architecture, which promotes separation of concerns and maintainability.
7. SQLDelight: A Kotlin Multiplatform library for type-safe database access, used for storing currency conversion data locally.

## Contacts
For any questions or inquiries, you can reach out to the project maintainer:

Manbir Kakkar
LinkedIn: [linkedin.com/in/manbirkakkar]linkedin.com/in/manbirkakkar

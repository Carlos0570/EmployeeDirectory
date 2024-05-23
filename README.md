# Employee Directory App

This is a simple Android app that displays a list of employees. The app demonstrates the use of the Model-View-ViewModel (MVVM) architecture pattern, coroutines, StateFlow and compose views.
## Build tools & versions

* Android Studio Jellyfish | 2023.3.1
* Kotlin 1.9.0
* Gradle 8.4.1

## Steps to run the app

1. Clone the repository.
2. Open the project in Android Studio.
3. Connect an Android device or start an emulator.
4. Click the "Run" button in Android Studio.

## Areas of focus

* Implementing the MVVM architecture pattern.
* Using coroutines for asynchronous tasks.
* Using Dagger HIlt for dependency injection.
* View creation usging Jetpack Compose.

## Reason for focus and problems solved

The focus was on implementing best practices for Android development to create a maintainable and scalable app.

* MVVM separates the UI logic from the business logic, making it easier to test and maintain the app.
* Coroutines help to manage asynchronous tasks efficiently and avoid blocking the main thread.
* StateFlow allows the UI to react to changes in data without manual intervention.

## Time spent on the project

Approximately 3 afternoons.

## Trade-offs and what would have been done differently with more time

* The UI could be further polished and customized.
* More unit tests could be written to improve the stability and reliability of the app.
* With additional time, I would map the endpoint data classes to separate UI data classes, this approach enhances scalability.
  
## Weakest part of the project

The project might be over-architected for its current requirements, as not all layers may be necessary. However, this architecture provides long-term benefits such as easier maintenance, better scalability, and improved testability for future requirements.

## Copied code or dependencies

* I incorporated Dagger Hilt, Retrofit, Jetpack Compose, Coil for image loading, Lottie for animations, and Mockk for testing.
* **Reference for image caching**: [Loading and Caching Images in Compose](https://medium.com/@sudhanshukumar04/coil-compose-loading-and-caching-images-in-compose-ebd7b25820c0)
* **Reference for testing**: [Programación Android by AristiDevs](https://www.youtube.com/watch?v=xCjIJMydI3s&t=895s&ab_channel=Programaci%C3%B3nAndroidbyAristiDevs)

## Other information

**Thank you for the opportunity to participate in this process, for providing the necessary materials, and for taking the time to review my code.**


| Happy path | Error Handling | Empty List |
|---------|---------|---------|
| <video src="https://github.com/Carlos0570/EmployeeDirectory/assets/119904210/c498a725-71cb-4fec-ba15-85c9852bc79f" controls="controls" style="max-width: 100%; height: auto;"></video> | <video src="https://github.com/Carlos0570/EmployeeDirectory/assets/119904210/80c2ee46-f1d2-430d-96db-dd8ed74544e1" controls="controls" style="max-width: 100%; height: auto;"></video> | <video src="https://github.com/Carlos0570/EmployeeDirectory/assets/119904210/7fc3147a-7f2b-443d-9c33-3c8f2746f6fd" controls="controls" style="max-width: 100%; height: auto;"></video> |

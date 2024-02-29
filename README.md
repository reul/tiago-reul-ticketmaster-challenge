# TicketMaster Interview challenge project

> This project uses my public repository [reul/CleanArchitectureExample](https://github.com/reul/CleanArchitectureExample) as a starting point.


## Requirements:

- Add TICKETMASTER_API_KEY in local.properties file. I assume it's secret and should not be staged in a version code system.
  Example: `TICKETMASTER_API_KEY=1234567890` (no quotes or spaces)

- JDK 19 is required to build and run the project.
- Android Studio Hedgehog 2020.3.1
- Android SDK 34

## Getting Started

1. Clone the repository: `git clone https://github.com/reul/CleanArchitectureExample.git`
2. Open the project in Android Studio or your preferred IDE.
3. Build and run the app on an emulator or physical device.



## Modules

### 1. Domain Module

Contains the business logic and entities of the application. It defines the use cases and interfaces that will be implemented in the other modules.

### 2. Data Module

Implements the data sources and repositories defined in the domain module. This module is responsible for interacting with external data, such as handling the network calls, and persisting and loading data from the Room database.

### 3. App module

The out-most layer in the clean architecture. Contains user interfaces, view-models, Dependency Injection
modules and any other services.

## Libraries and Dependencies

- [HILT](https://dagger.dev/hilt/) for dependency injection.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) for UI development.
- [Retrofit](https://square.github.io/retrofit/) for web API communication.
- [Kotlin Flow](https://kotlinlang.org/docs/flow.html) for handling asynchronous operations.
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) for asynchronous programming.
- [Coil](https://coil-kt.github.io/coil/) for loading and displaying images.
- [Room](https://developer.android.com/jetpack/androidx/releases/room/) as database framework.

## External API

The sample project fetches images from the [Shibe.online API](https://shibe.online/api/). The images are displayed in a grid using Jetpack Compose.

## Screenshot

<img src="Screenshot.png" alt="Screenshot" width="320px" />

## Testing

Some very simple Unit and UI tests were included.

Both the `domain` and `data` modules include tests using the following frameworks:

- [Kotlin Test](https://kotlinlang.org/api/latest/kotlin.test/) for writing tests in Kotlin.
- [MockK](https://mockk.io/) for mocking objects in tests.


The `app` module uses [Espresso](https://developer.android.com/training/testing/espresso).

## License

This project is licensed under the [Unlicense](LICENSE).


## If you've read so far

Thanks for reviewing this project and I hope you have a wonderful day.
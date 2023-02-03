# TfL
 App provides users with a list TFL Tube line status, The data shown in the app is retrieved from the API at https://api.tfl.gov.uk.

# Project Technology
MVVM Clean Code Architecture  
Kotlin  
Jetpack Compose  
Hilt Dagger (Dependency Injection)  
Material Design  
Coroutines  
Kotlin Flow  


# Architecture Design
The Project follows a MVVM with Repository pattern architecture. This architecture was chosen for:
- Separation of Concerns that provides a way to testing the architecture components in isolation and allows for the View classes to be updated without modifying the ViewModel classes.
- Resilience to configuration changes allows the ViewModel classes to store UI data that would otherwise be lost on screen rotation or activity lifecycle changes.
- Communication between fragments using a ViewModel class removes the need for fragments to communicate via an Activity using callbacks.

The View classes use data binding to communicate updates to their respective ViewModel classes. The Repository class communicates with a RESTful API using Retrofit.

![Alt text](Screenshot.png?raw=true "MVVM Architecture")

# Libraries Used
- Paging 3 to page data in from the backend
- Retrofit to provide access to the backend API endpoints
- UI ->Jetpack Compose
- Testing -> Unit test, Mocito,Mockk

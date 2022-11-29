Hello dear colleague. I'll hope you are doing well.



I want to add a few notes about the test challenge:

1)There wasn't any UI attached to the task and I decided to use the most straightforward UI possible because it wasn't the aim of the challenge.
2)I worked with currencies as with regular numbers. It's not a proper way to do such calculations but it wasn't in the requirements and I decided to simplify development.
3)I didn't find any requirements for automated tests, but I decided to add a few of them to show you that it matters for software development. You can find them inside the :common module in the "test" source set folder.



I used a lot of advanced development concepts in this code challenge because it is a regular way how I built large distributed android applications.

Language: Kotlin, Coroutines, Flow
Architecture: Multimodule + Single Activity + MVVM + UDF + Clean architecture
Jetpack Components: Lifecycle, SplashAPI, NavigationCompose, NavigationResult API,
UI: Jetpack Compose + Custom design system
HTTP: Retrofit2, OkHttp, Interceptors, HTTP Logging, Moshi
Data storage: Room, DataStore API
Dependency Injection: Hilt
Testing: Junit5, Mokk, Kottest

The project was built using Android Studio Dolphin | 2021.3.1 Patch 1
You should launched it using emulated device with API 33+



<img width="753" alt="image" src="https://user-images.githubusercontent.com/43451818/204488434-26f39261-38b8-40fc-a13e-0eab189a16f8.png">



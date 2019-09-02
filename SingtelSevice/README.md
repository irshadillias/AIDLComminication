# Singtel AIDL server application 

# Highlights

1. MVVM Architectural pattern
2. Offline Support 
3. Unit test demonstration using JUnit and Mockito
4. UI unit test demonstartion using Espresso
5. Gradle scripts for running sonarqube static code analysis, code coverage, etc.


It has been designed using **Android Architecture components** with **Room** for offline data caching. 

The whole application is built based on the MVVM architectural pattern.

# Application Architecture
![alt text](https://cdn-images-1.medium.com/max/1600/1*OqeNRtyjgWZzeUifrQT-NA.png)

# Screenshots
<img src="/screenshots/screenshot_mainpage.png" width="346" height="615" alt="Home"/> 
<img src="/screenshots/screenshot_details.png" width="346" height="615" alt="Home"/>

# Programming Practices Followed
a) Android Architectural Components <br/>
b) Dagger 2 for Dependency Injection <br/>
c) MVVM <br/>
d) Retrofit with Okhttp <br/>
e) Room for data caching <br/>
f) JUnit and Mockito for Unit testing <br/>
d) Repository pattern <br/>
e) View Databinding <br/>
e) RxJava <br/> 


# How to build ?

Open terminal and type the below command to generate debug build <br/>

``` ./gradlew assembleDebug ```

Open terminal and type the below command to generate release build <br/>

``` ./gradlew assembleRelease ```

# How to generate Sonarqube report ?

Open gradle.properties and update the below line with the sonarqube server url

```systemProp.sonar.host.url=http://localhost:9000```

Before running the sonarqube job, make sure the project version has been updated in the build.gradle. On every run, increment the version by 1.<br/>

```
            property "sonar.sources", "src/main"
            property "sonar.projectName", "TheMovieApp" // Name of your project
            property "sonar.projectVersion", "1.0.0" // Version of your project
            property "sonar.projectDescription", "TheMovieApp Application to list popular Articles"
```

For running the sonarqube job, type the below command in the terminal. <br/>

```./gradlew sonarqube assembleDebug```

<br/>

# How to generate code coverage report ?

Open terminal and type the following command

```./gradlew clean jacocoTestReport```

The coverage report will be generated on the following path.

``` app/build/reports ```

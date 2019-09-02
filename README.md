



 

 

#Section 1
#1) Create 2 android application.
Application A:    https://github.com/irshadillias/singtel/tree/master/singtelui

·       This application will only accept input from user and show output to user.

·       This application is prohibited to directly call the API

Application B:    https://github.com/irshadillias/singtel/tree/master/singtelui

·       This application will only process API calls.

·       This application will only accept data from application A and return output to application A.

 

#1.2. Possible way to resolve the issue
            1. AIDL

            2. Messenger

3. Broadcast receiver  

4. Deep linking

 

This application uses AIDL for the solving the problem.

 

#Reason

 

1.     In case you want your Service to handle multiple requests simultaneously then you’ll need to make use of AIDL directly and make sure your Service is capable of multi-threading and also ensure thread-safety.

2.     broadcast is one way and running on main

3.     AIDL is One to One communication and Using the underlying Android OS Binder framework

NB: In-app billing or subscription google using AIDL (IInAppBillingService.aidl)

 

#3) Extend Application A and B
1.      Both application should now support add(), subtracts(), multiply(), pow(). – Done

       2. Application B call to API for above functions are optional. (bonus point if the API is implemented). – Since Service are not deployed, Implementation commented out with Please refer : https://github.com/irshadillias/singtel/blob/master/SingtelSevice/app/src/main/java/singtel/irshadillias/service/interview/receivedata/ConvertJsonService.java

 

#Section 2
#Create an android application https://github.com/irshadillias/singtel/tree/master/SingtelLanguage

 
·       The application with have one page with a text “Hello World” in the center, and a few buttons below it => “English”, “Chinese”, “Hindi” etc

·       The page layout must be made inside layout/xml

·       The text “Hello World” must be define in android native string resources and layout/xml must refer to from this.

 

#Prepare a localization data file
·       Localization will contain the translation data by TSV or CSV or any other format as desired

·       This file needs to be hosted externally and not inside the APK itself.

Requirement
1.     Please make sure the localization system is dynamic and not hardcoded, meaning if you added a new TextView in the layout view and new text in android native string resources and localization data file, you don’t need to adjust the code base for any new translation text.    -  All additional UI change no need any single line code changes

2.     In addition to above please also list all the other solution you found or can think of tackling the task above.

-        create string repository and use though out the application

-        We can also create some custom views which extend the standard views and can handle getting the strings from repository internally.

                  All options are required lot of code changes

 

3.     Also list the reason why you choose this solution instead of the other solution you listed.

Use Restring library and load.

 

-        simple

-        clean

-        adding new language support

 

#Extend the app
·       Add more screens, more textview to the current screen, and more localization to fulfill requirement no. 1. –  Create additional fragment for full requirement two.



# Code Architecture


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

# Problem Name: Usage of Kotlin-mvvm-koin-rxJava2-retrofit

As per my understanding the requirement is - make three different requests and print the result after
applying the business logic.

1. 10thCharacterRequest:
 Grabs the content from https://developer.android.com/guide/platform and finds the 10th character and displays.
2. Every10thCharacterRequest:
Grabs the content from https://developer.android.com/guide/platform and finds every 10th character (i.e. 10th, 20th, 30th, etc.)
In this case I have not removed the white characters as I see it is emphasised in case 3 but not here.
Thus, I assumed that we need to display the white spaces as well
3. WordCounterRequest:
It Grabs the content from https://developer.android.com/guide/platform
and displays the count of each word after splits into words by removing whitespace characters.

In this case, I got confused by looking at the statement "count the occurrence of every *unique word
(case insensitive)* and display". When I look at the statement this is how I understood it
"when the word is *unique* it means that it is not repeated and hence the count would be obviously 1".

But I considered it as to display the words and how many number of times it occurs.

# Implementation
It is developed using MVVM clean architecture. It uses repository pattern to fetch the data from server.
As the app is being developed from the scratch, I decided to modularize it.

# Test Coverage
View are being tested by Instrumentation Tests
For testing UseCases, Repository and ViewModels UNIT tests are written

#Technologies used:
Kotlin, Retrofit, RxJava, Koin, JUnit, Espresso, Mockito, gradle, Jsoup, Jetpack library, Material design

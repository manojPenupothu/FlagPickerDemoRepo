# Project Title
Flag Picker Demo project is created to address the solution for displaying the appropriate country flags based on the selected country.
# Technical Requirements
1.jdk 1.8 or above
2.spring boot
3.Maven 3
# Running the app locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.flagpicker.FlagPickerDemo.FlagPickerDemoApplication class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

mvn spring-boot:run
## Scope
Scope of the project is to develop the app which displays country flag on selecting the country based on below emdpoints exposed.

1.http://localhost:8080//flagpicker/save/continents
This endpoint is used to push the given json content into database.
2.http://localhost:8080/flagpicker/displayCountries/America
This endpoint is used to retrive the countries and its flag by passing continent name
3.http://localhost:8080/flagpicker/displayFlag/Brazil
This endpoint is used to retrive the flag by passing country name.
4.http://localhost:8080/flagpicker/displayNoOfHits/Nigeria
This endpoint is used to retrive the number of views of corresponding flag by passing country name
## Future Enhancements
This app is proposed to be plugged to nosql database like mongo etc.
Integration with docker also considered as future scope.
oAuth security implementation can be done to provide authentication and authorization.
## Authors

Manoj Penupothu
email: manojpeupothu@gmail.com
git:https://github.com/manojPenupothu



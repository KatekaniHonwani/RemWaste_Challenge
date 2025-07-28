
# Rem Waste Selenium Framework

#Test Strategy Summary

#Objective: 
## Validate key user journeys on the Pizza Time UI and CRUD operations via Petstore API.

#Test Coverage:

Frontend: Add/remove from cart

Backend: Auth, Create/Read/Update/Delete pet records

#Tools Used:

Selenium + TestNG for UI

REST-assured for API

##Directory 

API - "src/test/java/api"
WEBUI - "src/test/java/WebUI"
#Execution:

##Compile the project:

mvn clean compile

UI: mvn test

API: mvn test (separate test class/package)


##Open report:

Screenshorts - \ScreenshortsSubfolder
start "" "report\index.html" 
##Limitations:

No backend auth in Pizza Time app

Swagger Petstore may have usage limits or resets

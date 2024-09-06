# Franki Weather App

## Description

### Story
_As a user I want to be able to view the weather in multiple cities_

---
Using the Weather API version 2.5 create a simple application that downloads the weather for a given
location. Be sure to include error handling for the API call.

#### Example call
https://api.openweathermap.org/data/2.5/weather?q=LosAngeles&appid=5deca2f99f97d972562a33188d696b4a


#### Minimum Requirements:
Display to the user at least the following fields
name
weather.main
main.temp
Code Expectations:
Please utilize the following tools and development practices
Asynchronous network access
Uni-directional data flow
Reactive elements
You will be assessed on:
Function - code has to compile and run
Readability - we read more code than we ever write
Structure - is everything laid out in an organized way
Patterns - using appropriate patterns for your architecture

### Installation

Clone repo or unzip it, then  open it Android studio.

### Installation (APK)
Install the apk with adb in the emulator or device.

$ adb install app.apk

## Usage

1. Connect to Wifi or any network
2. Open the app and wait until load
3. Main screen is the list of cities fetched from service
4. If no fetch, a placeholder is loaded.
5. Tap over (+) to add a new city

## Notes

* **Architecture:** MVVM with a modern android architecture using UI->Domain->Data layers.
* **Design patterns**: Dependency injection with Hilt, Observable patterns with Flow and states (StateFlow, SharedFlow).
* **Database:** Simple Room database integrated with flows and **Repository** patterns.
* **Datasources:** Local and Remote, Remote uses Retrofit to perform simple calls to mockeable.io.
* **Domain layer** Used a simple domain layer for extracting tasks as Use Cases, importan for larger projects, I consider it's better to use it early instead of refactoring later.
* **Known Issues** Needs some clean up, and keyboard hides the Snackbar component. No test included.

## Built With
<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/android/android-original.svg" height="40px" width="40px" />


## License

All rights reserved
All images and brands are property of Franki, used as demo proposes.

## Contacts

<a href="https://www.linkedin.com/in/pedro-daniel-gg/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" /></a>  <a href="mailto:dany.el553@gmail.com"><img src=https://raw.githubusercontent.com/johnturner4004/readme-generator/master/src/components/assets/images/email_me_button_icon_151852.svg /></a>

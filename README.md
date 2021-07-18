# beem-android-client
A library to ease the integration with the Beem Africa's OTP service in android application.
_________________________
[![](https://jitpack.io/v/4xMafole/beem-android-client.svg)](https://jitpack.io/#4xMafole/beem-android-client) [![](https://img.shields.io/badge/platform-android-orang)](https://img.shields.io/badge/platform-android-orange) [![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

## Prerequisites
Add this in your root ```build.gradle``` file (not on module ```build.gradle``` file)

        allprojects 
        {
          repositories 
          {
            ...
            maven { url "https://jitpack.io" }
          }
        }

## Dependency
Add this to your module's ```build.gradle```

        dependencies 
        {
                implementation 'com.github.4xMafole:beem-android-client:v0.1.0-beta'
        }
  
  ## How To Use
  
  - Allow internet permission in ```AndroidManifest.xml```
  
        <uses-permission android:name="android.permission.INTERNET"/>
 
  - Assigning Beem Credentials

        BeemCredentials.API_KEY = "<APP_API_KEY>";
        BeemCredentials.BEEM_APP_ID = <APP_ID>;
        BeemCredentials.SECRET_KEY = "<APP_SECRET_KEY>";

        //Default urls | Change depending on the api's version.
        BeemCredentials.REQUEST_URL = "https://apiotp.beem.africa/v1/request";
        BeemCredentials.VERIFY_URL = "https://apiotp.beem.africa/v1/verify";

- Running OTP Service

        OTPClient client = new OTPClient("<PHONE_NUMBER>");
        client.run();
        
- Validating the pin

        client.pinValidity("<PIN>");
     
- Checking pin validation ```success/failure```

        Handler handler = new Handler();
        handler.postDelayed(() ->
        {
            if(client._isPin)
            {
                //Success
                //...do something when pin is valid
            }
            else
            {
                //Failure
                //...do something when pin is invalid
            }
        }, 30000);
        
Using a ```handler``` to create a 30 second delay.

## Contribution
Please fork this repository and contribute back using [pull requests](https://github.com/4xMafole/beem-android-client/pulls).

Any contributions, large or small, major features, bug fixes, are all welcomed and appreciated but will be thoroughly reviewed.

### Contact - Let's become friends
- [![Twitter](https://img.shields.io/twitter/url?label=Twitter%20%404xMafole&style=social&url=https%3A%2F%2Ftwitter.com%2F4xmafole)](https://twitter.com/intent/tweet?text=Wow:&url=https%3A%2F%2Ftwitter.com%2F4xmafole)


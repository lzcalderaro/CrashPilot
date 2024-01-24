# Crash Pilot Android Library

[![](https://jitci.com/gh/lzcalderaro/crashPilot/svg)](https://jitci.com/gh/lzcalderaro/crashPilot)

## Overview
Crash Pilot is an Android library designed to streamline crash reporting and logging in your Android applications. It allows you to easily capture and manage crash reports, send logs to a specified server, and save logs to a local file.

## Installation

### Step 1: Add Jitpack Repository
Ensure that your `build.gradle` (Project-level) file includes the Jitpack repository:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add Dependency
Add the following dependency to your build.gradle (Module-level) file:

```groovy
dependencies {
    implementation 'com.github.lzcalderaro:crashPilot:Tag'
}
```

Replace `Tag` with the desired release version.

## Usage

###Initialize Crash Pilot in your Application class:
Create an Application class if you don't have one already. Override the attachBaseContext method and initialize Crash Pilot. Example:

```java
import android.app.Application;

public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        CrashPilot.init(this)
                .start()
                .connectToServer(<Your Endpoint>)
                .saveLogFile();
    }
```

##Crash Pilot Configuration:

- start(): This method is mandatory and initializes Crash Pilot.
- connectToServer(String endpoint): If you want to send crash reports to a server, call this method and provide the server's endpoint.
- saveLogFile(): If you want to save crash logs to a local file, call this method.

###CpLog Extension:
Replace the usage of Android's Log class with CpLog for enhanced logging capabilities:


```java
import com.lzcalderaro.crashpilot.src.controller.CpLog;

// ...

CpLog.d("YourTag", "Your log message");
```

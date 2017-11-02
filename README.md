# crash-exception  [![](https://jitpack.io/v/aliletter/crash-exception.svg)](https://jitpack.io/#aliletter/crash-exception)
Crash-Exception is used to crash exception when error occurred,and error informetion will be saved in where you set.
# How to
To get a Git project into your build:
## Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
## Step 2. Add the dependency

	dependencies {
          compile 'com.github.aliletter:crash-exception:v1.0.5'
          
	}

## Step 3. Initialize Crash-Exception in Application
```Java
   @Override
   public void onCreate()
        super.onCreate();
        ...
        CrashException.getInstance(Context context,String filepath) ;
        ...
   }
```
# Othor method recommend
```Java
//set Directory which will save error infomation 
CrashException.getInstance().setCrashExceptionDir(String);


//set Prompt Content when error occurred
CrashException.getInstance().setPromptContent(String);


//get File which saved error infomation 
CrashException.getInstance().getRecentExceptionFile()


//delete all file which saved error infomation 
CrashException.getInstance().clearExceptionFile()

```

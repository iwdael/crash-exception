# crash-exception  [![](https://jitpack.io/v/hacknife/crash-exception.svg)](https://jitpack.io/#hacknife/crash-exception)
Crash-Exception is used to crash exception when error occurred,and error informetion will be saved in where you set.[中文文档](https://github.com/hacknife/crash-exception/blob/master/README.md)
## Intruction
Abnormal information captured, including models, brands, screen resolution and other equipment information.
### Code Sample
```Java
   @Override
   public void onCreate()
        super.onCreate();
        ...
        CrashException.getInstance(Context context,String filepath) ;
        ...
   }
```
### Method Description
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
## How to
To get a Git project into your build:
### Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories.   [click here for details](https://github.com/hacknife/CarouselBanner/blob/master/root_build.gradle.png)
```Java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
### Step 2. Add the dependency
Add it in your application module build.gradle at the end of dependencies where you want to use.[click here for details](https://github.com/hacknife/CarouselBanner/blob/master/application_build.gradle.png)
```Java
	dependencies {
                ...
	        compile 'com.github.hacknife:crash-exception:v1.0.7'
	}
```
 
### Step 3. Add the permission
Add it in your application AndroidManifest.xml in the manifest label.   [click here for details](https://github.com/hacknife/OnHttp/blob/master/androimanifest.png)
```Java
        <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
<br><br><br>
## Thank you for your browsing
If you have any questions, please join the QQ group. I will do my best to answer it for you. Welcome to star and fork this repository, alse follow me.
<br>
![Image Text](https://github.com/hacknife/CarouselBanner/blob/master/qq_group.png)

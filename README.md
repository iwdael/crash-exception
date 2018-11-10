# crash-exception
[![](https://img.shields.io/badge/platform-android-orange.svg)](https://github.com/hacknife) [![](https://img.shields.io/badge/language-java-yellow.svg)](https://github.com/hacknife) [![](https://img.shields.io/badge/JCenter-1.0.8-brightgreen.svg)](http://jcenter.bintray.com/com/hacknife/crash-exception/) [![](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://github.com/hacknife) [![](https://img.shields.io/badge/license-apache--2.0-green.svg)](https://github.com/hacknife) [![](https://img.shields.io/badge/api-11+-green.svg)](https://github.com/hacknife)<br/><br/>
当程序发生错误时，Crash-Exception可以抓捕到异常信息，并将错误信息保存到你指定的目录中。
## Intruction
抓捕的异常信息中，包含机型，品牌，屏幕分辨率等设备信息。
### 代码示例
```Java
   @Override
   public void onCreate()
        super.onCreate();
        ...
        CrashException.getInstance(Context context,String filepath) ;
        ...
   }
```
### 方法说明
```Java
//设置异常信息储存的位置
CrashException.getInstance().setCrashExceptionDir(String);


//设置当程序崩溃的时候，提示的信息
CrashException.getInstance().setPromptContent(String);


//获取最新的异常文件
CrashException.getInstance().getRecentExceptionFile()


//删除保存在本地的所有异常文件
CrashException.getInstance().clearExceptionFile()

```
## 快速引入项目
合并以下代码到需要使用Module的dependencies中。
```Java
	dependencies {
                ...
	        compile 'com.hacknife:crash-exception:1.0.8'
	}
```
<br><br><br>
## 感谢浏览
如果你有任何疑问，请加入QQ群，我将竭诚为你解答。欢迎Star和Fork本仓库，当然也欢迎你关注我。
<br>
![Image Text](https://github.com/hacknife/CarouselBanner/blob/master/qq_group.png)

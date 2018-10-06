# crash-exception  [![](https://jitpack.io/v/hacknife/crash-exception.svg)](https://jitpack.io/#hacknife/crash-exception)
当程序发生错误时，Crash-Exception可以抓捕到异常信息，并将错误信息保存到你指定的目录中。[中文文档](https://github.com/hacknife/crash-exception/blob/master/README_ENGLISH.md)
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
## 如何配置
将本仓库引入你的项目:
### Step 1. 添加JitPack仓库到Build文件
合并以下代码到项目根目录下的build.gradle文件的repositories尾。[点击查看详情](https://github.com/hacknife/CarouselBanner/blob/master/root_build.gradle.png)
```Java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
### Step 2. 添加依赖   
合并以下代码到需要使用的application Module的dependencies尾。[点击查看详情](https://github.com/hacknife/CarouselBanner/blob/master/application_build.gradle.png)
```Java
	dependencies {
                ...
	        compile 'com.github.hacknife:crash-exception:v1.0.7'
	}
```
 
### Step 3. 添加权限
合并以下代码到应用的AndroidManifest.xml的manifest标签中。[点击查看详情](https://github.com/hacknife/OnHttp/blob/master/androimanifest.png)
```Java
        <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

<br><br><br>
## 感谢浏览
如果你有任何疑问，请加入QQ群，我将竭诚为你解答。欢迎Star和Fork本仓库，当然也欢迎你关注我。
<br>
![Image Text](https://github.com/hacknife/CarouselBanner/blob/master/qq_group.png)
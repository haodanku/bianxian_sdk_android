## 一、集成 SDK  
### 方式一：（推荐）Gradle 依赖引入  
1、目前仅支持 **mavenCentral**，所以需要在 **Project** 的 **gradle** 文件里面去加入：   
```groovy
allprojects {
    repositories {
		//...
        mavenCentral() //加入 mavenCentral 仓库
        //...
    }
}
```

2、在 **app module** 的 **gradle** 文件中引入好单库变现 **SDK** 的依赖：   
```groovy
dependencies {
    implementation 'com.haodanku.sdk:bianxian:1.0.9'
}
```

### 方式二：手动引入   
点击下载：[最新版 SDK](https://bianxian.haodanku.com/index/download)  
1、将 **aar** 包放到 **libs** 文件夹（**aar** 包的版本以当时最新的为准）：  
![WX20210816-174700@2x.png](https://cdn.nlark.com/yuque/0/2021/png/626389/1629107246517-ecf76d8c-9531-4e40-a599-16f871f19b2d.png#clientId=u82877699-3348-4&from=paste&height=129&id=uedeed90d&margin=%5Bobject%20Object%5D&name=WX20210816-174700%402x.png&originHeight=258&originWidth=688&originalType=binary&ratio=1&size=20059&status=done&style=none&taskId=u4463396a-057c-4675-90b8-b37ea291b1b&width=344)  

2、在 **app module** 的 **gradle** 文件中添加对 **aar** 包的依赖：  

```groovy
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar', '*.aar'])
}
```

## 二、项目配置
1、需要在 **AndroidManifest.xml** 中添加联网权限：  

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.haodanku.bianxian">

    <uses-permission android:name="android.permission.INTERNET"/>

</manifest>
```

## 三、SDK 的使用
### 1、初始化
建议在 **Application** 中对 **SDK** 进行初始化：   

```kotlin
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Hdk.setDebug(BuildConfig.DEBUG)
        Hdk.init(this, "appKey", "appSecret", object : Hdk.InitCallback {
            override fun onResult(code: Int, message: String) {
                Log.e("MyApp", "code:$code, msg:$message")
            }
        })
    }
    
}
```

### 2、日志显示   
如果需要显示错误日志，打印到 **log** ，通过 **setDebug** 方法开启：   

```kotlin
Hdk.setDebug(true)
```

### 3、打开变现主页  
通过 **openIndexPage** 方法可以打开好单库的变现主页：   

```kotlin
Hdk.openIndexPage()
```

### 4、获取单个页面
如果需要单个页面（目前只提供首页），可以使用以下方法获取 Fragment ，然后再将 Fragment 添加到您需要放置的地方：  

```kotlin
val fragment = Hdk.getSinglePage(SinglePage.MAIN)
```

### 5、通过 API 接口接入打开页面(v1.0.7版本开始支持)
根据接口返回的 **jump** 参数，可以使用以下方法直接打开相应的页面。

```kotlin
fun jumpPage(jump: String?)
```

### 6、打开金刚区相应的板块页面(v1.0.7版本开始支持)
通过一个方法即可直达打开金刚区某个模块，减少接入成本。**SDK** 提供以下方法可以直接打开：

```kotlin
/**
 * 跳转到单个按钮区域单页
 * @param nav 页面类型[NavPage.NavType]
*/
fun jumpNav(@NavPage.NavType nav: Int)
```

其中 NavType 类型有：
```java
public class NavPage {

    public static final int JD = 1;      		//京东
    public static final int PDD = 2;			//拼多多
    public static final int VIP = 3;			//唯品会
    public static final int MEI_TUAN = 4;		//美团外卖
    public static final int PHONE_BILL = 5;		//话费充值
    public static final int XIAN_BAO = 6;		//福利线报
    public static final int EPIDEMIC_AREA = 7;	//防疫专区
    public static final int KFC = 8;			//肯德基
    public static final int MOVIE = 9;			//电影票
    public static final int ELE = 10;			//饿了么（v1.0.9+ 支持）

}
```

## 四、混淆配置
SDK 自带混淆，接入方无需单独配置混淆规则。    

## 五、注意事项
### 1、Android P 以上设备 https 网络处理  
如果遇到 **Cleartext HTTP traffic to xxx not permitted** 或者页面显示“**网页无法打开 net::ERR_CLEARTEXT_NOT_PERMITTED**”问题，需要进行以下配置。    
（1）在 **res** 文件夹下创建一个 **xml** 文件夹，然后创建一个 **network_security_config.xml** 文件，文件内容如下：    
```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config cleartextTrafficPermitted="true">
        <trust-anchors>
            <certificates src="system" />
        </trust-anchors>
    </base-config>
</network-security-config>
```

（2）接着，在 **AndroidManifest.xml** 文件下的 **application** 标签增加以下属性：    
```xml
<application
	android:networkSecurityConfig="@xml/network_security_config"
/>
```

### 2、兼容和依赖说明
（1）**SDK** 目前仅兼容 **Android 5.0（API 21）** 以上设备，且目前只提供 **androidx** 的版本 。    
（2）**SDK** 为 **Kotlin** 项目，纯 **Java** 项目需要添加对 **Kotlin** 的支持。   
（3）**SDK** 依赖的库有：   
```groovy
    implementation "org.jetbrains.kotlin:kotlin-stdlib:1.5.20"
    //implementation "org.jetbrains.kotlin:kotlin-stdlib:1.4.32"  v1.0.7 版本后降级为 1.4.32
    implementation 'androidx.core:core-ktx:1.3.1'
    implementation 'androidx.appcompat:appcompat:1.2.0'
```
接入前请确保已经添加以上的库依赖。   

### 3、隐私政策
**SDK**目前采用 **imei **作为设备的唯一标识**，**需要在隐私政策中**告知用户。**

### 4、Demo 下载
下载链接：[https://github.com/haodanku/bianxian_sdk_android](https://github.com/haodanku/bianxian_sdk_android)


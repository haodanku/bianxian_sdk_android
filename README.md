<a name="vQhZX"></a>
## 一、集成 SDK
<a name="JtnHS"></a>
### 方式一：（推荐）Gradle 依赖引入
1、目前仅支持 **mavenCentral **，所以需要在 **Project** 的 **gradle** 文件里面去加入：
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
    implementation 'com.haodanku.sdk:bianxian:1.1.3'
}
```
<a name="Ee2vg"></a>
### 方式二：手动引入
点击下载：[最新版 SDK](https://bianxian.haodanku.com/index/download)<br />1、将 **aar** 包放到 **libs** 文件夹（**aar** 包的版本以当时最新的为准）：<br />![WX20210816-174700@2x.png](https://cdn.nlark.com/yuque/0/2021/png/626389/1629107246517-ecf76d8c-9531-4e40-a599-16f871f19b2d.png#clientId=u82877699-3348-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=129&id=uedeed90d&margin=%5Bobject%20Object%5D&name=WX20210816-174700%402x.png&originHeight=258&originWidth=688&originalType=binary&ratio=1&rotation=0&showTitle=false&size=20059&status=done&style=none&taskId=u4463396a-057c-4675-90b8-b37ea291b1b&title=&width=344)<br />2、在 **app module** 的 **gradle** 文件中添加对 **aar** 包的依赖：
```groovy
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar', '*.aar'])
}
```


<a name="pvZHD"></a>
## 二、项目配置
1、需要在 **AndroidManifest.xml** 中添加联网权限：
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.haodanku.bianxian">

    <uses-permission android:name="android.permission.INTERNET"/>

</manifest>
```
<a name="sWEEZ"></a>
## 三、SDK 的使用
<a name="yhHxg"></a>
### 1、初始化
建议在 **Application** 中对 **SDK** 进行初始化：
```kotlin
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Hdk.setDebug(BuildConfig.DEBUG)
        Hdk.init(this, "appKey", object : Hdk.InitCallback {
            override fun onResult(code: Int, message: String) {
                Log.e("MyApp", "code:$code, msg:$message")
            }
        })
    }
    
}
```
<a name="FfY6a"></a>
### 2、日志显示
如果需要显示错误日志，打印到 **log** ，通过 **setDebug** 方法开启，请在初始化方法前开启：
```kotlin
Hdk.setDebug(true)
```
<a name="blUkX"></a>
### 3、打开变现主页
通过 **openIndexPage** 方法可以打开好单库的变现主页：
```kotlin
Hdk.openIndexPage()
```
<a name="ij3KS"></a>
### 4、获取单个页面
如果需要单个页面（目前只提供首页），可以使用以下方法获取 Fragment ，然后再将 Fragment 添加到您需要放置的地方：
```kotlin
val fragment = Hdk.getSinglePage(SinglePage.MAIN)
```
<a name="qpaYH"></a>
### 5、通过 API 接口接入打开页面
根据 API 接口返回的 **jump** 参数，可以使用以下方法直接打开相应的页面。
```kotlin
/**
 * 跳转到相应的页面
 * @param jump 根据 API 接口获取的 jump 参数
*/
fun jumpPage(jump: String?)
```
<a name="ZTscu"></a>
### 6、打开金刚区相应的板块页面
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
<a name="m9ncE"></a>
### 
<a name="pSLgz"></a>
### 7、归因（返佣）能力
如需开启返佣功能，则要接入归因功能。在备注中提供用户标识，并通过好单库变现平台查询到带用户标识的订单信息。userId 参数将用于订单明细及归属，开启归因能力需要做如下的配置：
```kotlin
/**
 * 此方法在打开 APP（进入 MainActivity） 后 或者 登录/切换账号 的时候调用。
 * @param agentInfo 用户信息类
*/
fun setAgent(agentInfo: HdkAgentInfo?)


public class HdkAgentInfo {
    //用户唯一标识，用于跟踪佣金，请确保唯一性！（必须）
    //开启返佣必须要设置此参数，建议是全局用户唯一的字符串，切勿随意更改。
    //限制为长度 255，支持数字、字母、下划线或其组合。
    public String userId;
    //用户昵称，用于后台订单展示（非必须）
    public String nickName;
}


```
<a name="CADL5"></a>
### 8、跳转到某个模块

<br />部分场景为单独模块，需要通过调用好单库 API 接口获取跳转参数，进而使用此参数打开模块。具体方法为：
```kotlin
/**
 * 跳转到某个模块
 *@param module 模块类型
 *@param params 参数
*/
fun jumpModule(@ModulePage.ModuleType module: Int, params: Map<String, String>?)

//打开钱包模块需要参数 token
val params = mutableMapOf<String, String>()
params["token"] = "token值"
Hdk.jumpModule(ModulePage.WALLET, params)

//目前支持的模块
public class ModulePage {
    public static final int WALLET = 12; //钱包
}

```
<a name="mJIXt"></a>
### 9、获取列表组件
提供一个部分入口组件， 点击可以实现跳转到内容页。
```kotlin
/**
 * 加载组件
 * @param context Context
 * @param config 控件配置信息
 * @param listener 生成回调，成功返回控件，失败返回错误信息
*/
fun loadElement(context: Context,config: HdkBaseViewConfig,listener: HdkElementListener)

例如：
/**
 * 今日值得买组件加载
*/
val config = WorthBuyingTodayConfig() //组件类型
config.isAutoLoop = true //是否自动轮播，默认true
config.setMargin(10,10,10,10) //设置边距,默认0
config.radius = 10f //设置圆角，默认0f
Hdk.loadElement(context,config ,object : HdkElementListener {
	override fun onError(code: Int, error: String) {
        //失败回调
	}

    override fun onSuccess(hdkElement: View) {
        //成功回调
    }
})

/**
 * v1.1.3支持
 * 外卖瓷片组件加载
 * 步骤一：实现HdkImageProxy接口（图片适配器）
 * 步骤二: 添加配置
*/

class ImageProxy(val context: Context) : HdkImageProxy {

    override fun loadImageUrl(view: ImageView?, url: String?) {
        view?.let {
            Glide.with(context)
                .load(url)
                .into(it)
        }
    }
}

val config =  PorcelainConfig() //组件类型
config.setMargin(10, 10, 10, 10) //设置边距,默认0
config.proxyImage = ImageProxy(context)//添加图片适配器
Hdk.loadElement(context,config ,object : HdkElementListener {
	override fun onError(code: Int, error: String) {
        //失败回调
	}

    override fun onSuccess(hdkElement: View) {
        //成功回调
    }
})
```
目前支持的组件：

| 今日值得买 | 外卖瓷片区 |
| --- | --- |
| ![device-2021-12-02-155253.png](https://cdn.nlark.com/yuque/0/2021/png/626389/1638431601241-c88f2168-c5d0-46ef-93ed-a858b0eac779.png#clientId=ucd113cd0-7555-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=2340&id=u99ad982a&margin=%5Bobject%20Object%5D&name=device-2021-12-02-155253.png&originHeight=2340&originWidth=1080&originalType=binary&ratio=1&rotation=0&showTitle=false&size=746958&status=done&style=none&taskId=u7f969b61-4cbb-4724-8a9e-08e9241a9fe&title=&width=1080) | ![4d20f7b03d24e2264906c537a7fcde9.jpg](https://cdn.nlark.com/yuque/0/2021/jpeg/22177585/1638839613549-7f72909d-6ca9-4ad3-86ec-95bcac7e2b2b.jpeg#clientId=u4cd4c45d-7b6f-4&crop=0&crop=0&crop=1&crop=1&from=drop&height=690&id=ud0542cfd&margin=%5Bobject%20Object%5D&name=4d20f7b03d24e2264906c537a7fcde9.jpg&originHeight=2400&originWidth=1080&originalType=binary&ratio=1&rotation=0&showTitle=false&size=300251&status=done&style=none&taskId=ufa847e88-c3a0-435e-97a0-ca6a2246413&title=&width=310.6805725097656) |


<br />

<a name="Mm2hv"></a>
### 10、页面内点击事件监听
> v1.1.2 版本开始支持

如果需要监听页面上某些按钮的点击点击跳转等事件，可以通过注册跳转器来实现监听。<br />（1）步骤一：实现一个跳转器。
```kotlin
import android.content.Context
import com.haodanku.sdk.callback.HdkNavigate

class PageNavigate : HdkNavigate {
    
    /**
     * @param context Context
     * @param keyword 关键字 （ 目前支持钱包按钮关键字：wallet ）
     * @param params 页面数据
     */
    override fun navigate(context: Context?, keyword: String, params: Map<String, String>?) {
        //在这里根据实现点击返回的关键字进行相关操作，例如跳转钱包页面等..
        if ("wallet" == keyword) {
            val map = HashMap<String, String>()
            map["token"] = "xxxxxxxxxxxxxxxxxxxx"
            Hdk.jumpModule(ModulePage.WALLET, map)
        }
    }
    
}
```
（2）步骤二：在初始化调用 **init **方法前注册。
```kotlin
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        
        Hdk.setDebug(true)

        val navigate = PageNavigate()
        HdkNavigateCenter.registerNavigate(navigate)	//注册

        Hdk.init(this, "demo", object : Hdk.InitCallback {
            override fun onResult(code: Int, message: String) {
            }
        })
    }

}
```
完成以上操作，即可在页面某些按钮点击的时候获取到监听，进而做相关操作。<br />​<br />
<a name="ZqwQm"></a>
## 四、混淆配置
SDK 自带混淆，接入方无需单独配置混淆规则。<br />​<br />
<a name="Ol6Mu"></a>
## 五、注意事项
<a name="YwU5D"></a>
### 1、Android P 以上设备 https 网络处理
如果遇到 **Cleartext HTTP traffic to xxx not permitted** 或者页面显示“**网页无法打开 net::ERR_CLEARTEXT_NOT_PERMITTED**”问题，需要进行以下配置。<br />（1）在 **res** 文件夹下创建一个 **xml** 文件夹，然后创建一个 **network_security_config.xml** 文件，文件内容如下：
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

 ###  2、兼容和依赖说明
（1）**SDK** 目前仅兼容 **Android 5.0（API 21）** 以上设备，且目前只提供 **androidx** 的版本 。<br />
（2）**SDK** 为 **Kotlin** 项目，纯 **Java** 项目需要添加对 **Kotlin** 的支持。<br />
（3）**SDK** 依赖的库有：

```groovy
	implementation "org.jetbrains.kotlin:kotlin-stdlib:1.4.32"
        implementation 'androidx.core:core-ktx:1.3.1'
        implementation 'androidx.appcompat:appcompat:1.2.0'
	implementation 'com.google.android.material:material:1.3.0'
```
接入前请确保已经添加以上的库依赖。

 ###  3、隐私政策
 ** SDK ** 目前采用 **AndroidID **作为设备的唯一标识**，**需要在隐私政策中**告知用户。**<br />隐私政策链接：[https://bianxian.haodanku.com/index/privacy](https://bianxian.haodanku.com/index/privacy)<br />​<br />
<a name="PecCQ"></a>
### 4、Demo 下载
下载链接：[https://github.com/haodanku/bianxian_sdk_android](https://github.com/haodanku/bianxian_sdk_android)

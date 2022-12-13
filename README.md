Nemo SDK for Android
========================

FEATURES
--------
* [Login]
* [Tracking]

INSTALLATION
------------
Nemo SDKs are published to Maven as independent modules. To utilize a feature listed above
include the appropriate dependency (or dependencies) listed below in your `app/build.gradle` file.
```gradle
dependencies {
    
    // Nemo Login only
    implementation files('libs/nemosdk_login.aar')
    implementation("com.squareup.okio:okio:3.2.0")
    implementation 'net.openid:appauth:0.11.1'

    // Nemo Tracking only
    implementation files('libs/nemosdk_tracking.aar')
    implementation 'com.appsflyer:af-android-sdk:6.3.2'
    implementation 'com.android.installreferrer:installreferrer:2.2'
    implementation platform('com.google.firebase:firebase-bom:31.1.0')
    implementation 'com.google.firebase:firebase-analytics:21.2.0'
    implementation 'com.google.firebase:firebase-messaging:23.1.0'
}	

defaultConfig {
	
    manifestPlaceholders = [
      'appAuthRedirectScheme': 'redirect_uri'
      'appAuthRedirectCallback': '/callback'
    ]
}
```	
// IF used tracking add plugin
apply plugin: 'com.google.gms.google-services'
```
dependencies {
   ...
   // Google Services plugin
   classpath 'com.google.gms:google-services:4.3.3'
}
```
The google-services.json file is generally placed in the app/ directory (at the root of the Android Studio app module)
```
app/
   google-services.json
```

- add file auth_config.json to folder main/assets



USAGE NEMO LOGIN SDK
--------------------

```
//Initialize SDK 
	
NemoSDK nemoSDK = new NemoSDK();

nemoSDK.sdkInitialize(activity, new IGameOauthListener() {
  @Override
  public void onLoginSuccess(String access_token, String id_token) {

  }

  @Override
  public void onLoginFail(String msg, String code) {

  }

  @Override
  public void onLogoutSuccess() {
  
  }

  @Override
  public void onLogoutFail() {

  }
});
	
//Login
nemoSDK.login();

//getUserInfo  (return json)
nemoSDK.getUserInfo();

//Logout
nemoSDK.logout();
	
//Add function onActivityResult
super.onActivityResult(requestCode, resultCode, data);
if(nemoSDK != null){
  nemoSDK.onActivityResult(requestCode, resultCode, data);
}

//Add function onStart
super.onStart();
if(nemoSDK != null){
  nemoSDK.onStart();
}

//Add function onStop
super.onStop();
if(nemoSDK != null){
  nemoSDK.onStop();
}

//Add function onDestroy
super.onDestroy();
if(nemoSDK != null){
  nemoSDK.onDestroy();
}
```
	
USAGE NEMO TRACKING SDK
--------------------
To utilize a feature listed above include the appropriate listed below in your `AdroidManifest.xml` file.
```
<application android:supportsRtl="true">
 <receiver android:name="com.appsflyer.MultipleInstallBroadcastReceiver" android:exported="true" >
    <intent-filter>
      <action android:name="com.android.vending.INSTALL_REFERRER" />
    </intent-filter>
 </receiver>
 <receiver android:name="com.appsflyer.SingleInstallBroadcastReceiver" android:exported="true" >
  <intent-filter>
	<action android:name="com.android.vending.INSTALL_REFERRER" />
  </intent-filter>
 </receiver>
</application>
```

```
//Initialize SDK 

Tracking.getInstance(activity).start("af_key");

Tracking.getInstance(activity).trackingStartTrialEventAF()
Tracking.getInstance(activity).trackLoginEventAF()
Tracking.getInstance(activity).trackingStartTrialEventCustomAF(String jsonContent) // jsonContent = {"event": "event_name", "params": {"key": "value", "key2": "value2"} }

```


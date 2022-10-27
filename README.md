# Android NEMOSDK

# Configuration

-  build.gradle

	defaultConfig {
        
        manifestPlaceholders = [
                'appAuthRedirectScheme': 'gid-uat.nemoverse.io'
        ]
    }
	
    implementation files('libs/nemosdk.aar')
	
    implementation 'net.openid:appauth:0.11.1'
	
-  add file auth_config.json to folder main/assets


	
# Use SDK

## Initialize SDK 
	
	NemoSDK nemoSDK = new NemoSDK();

	nemoSDK.sdkInitialize(activity, new IGameOauthListener() {
		@Override
		public void onLoginSuccess(String access_token, String id_token) {

		}

		@Override
		public void onLogoutSuccess() {

			
		}

		@Override
		public void onLogoutFail() {

		}
	});
	
## Login

	nemoSDK.login();
	
## getUserInfo  (return json)

	nemoSDK.getUserInfo();

## Logout
	
	nemoSDK.logout();
	
## Add function onActivityResult
  
	super.onActivityResult(requestCode, resultCode, data);
	if(nemoSDK != null){
		nemoSDK.onActivityResult(requestCode, resultCode, data);
	}

## Add function onStart
	
	super.onStart();
	if(nemoSDK != null){
		nemoSDK.onStart();
	}

## Add function onStop

	super.onStop();
	if(nemoSDK != null){
		nemoSDK.onStop();
	}

## Add function onDestroy

	super.onDestroy();
	if(nemoSDK != null){
		nemoSDK.onDestroy();
	}

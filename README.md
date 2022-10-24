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
	
	OidcSDK oidcSDK = new OidcSDK();

	oidcSDK.sdkInitialize(this, new IGameOauthListener() {
		@Override
		public void onLoginSuccess(String Email, String UserName, String accesstoken) {

		}

		@Override
		public void onLogout() {

			
		}

		@Override
		public void onError() {

		}
	});
	
## Login

	oidcSDK.startAuth();

## Logout
	
	oidcSDK.logout();
	
## Add function onActivityResult
  
	super.onActivityResult(requestCode, resultCode, data);
	if(oidcSDK != null){
		oidcSDK.onActivityResult(requestCode, resultCode, data);
	}

## Add function onStart
	
	super.onStart();
	if(oidcSDK != null){
		oidcSDK.onStart();
	}

## Add function onStop

	super.onStop();
	if(oidcSDK != null){
		oidcSDK.onStop();
	}

## Add function onDestroy

	super.onDestroy();
	if(oidcSDK != null){
		oidcSDK.onDestroy();
	}

package kh.gosu.cuuam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nemo.nsdk.inteface.IGameOauthListener;
import com.nemo.nsdk.inteface.OnSingleClickListener;
import com.nemo.nsdk.oidc.OidcSDK;
import com.nemo.nsdk.oidc.User;


public class MainActivity extends AppCompatActivity {

    private Button btnDangNhap;
    private Button btnAccessToken;
    private Button btnShowInfoUser;
    private Button btnDangXuat;

    OidcSDK oidcSDK = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();

        oidcSDK = new OidcSDK();

        oidcSDK.sdkInitialize(this, new IGameOauthListener() {
            @Override
            public void onLoginSuccess(String Email, String UserName, String accesstoken) {

                btnDangNhap.setVisibility(View.GONE);
                btnDangXuat.setVisibility(View.VISIBLE);
                btnAccessToken.setVisibility(View.VISIBLE);
                btnShowInfoUser.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLogout() {

                btnDangNhap.setVisibility(View.VISIBLE);
                btnDangXuat.setVisibility(View.GONE);
                btnAccessToken.setVisibility(View.GONE);
                btnShowInfoUser.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });

        initView();

    }

    public void initView() {
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangNhap.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View var1) {
                oidcSDK.startAuth();
            }
        });


        btnDangXuat = (Button) findViewById(R.id.btnDangXuat);
        btnDangXuat.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View var1) {
                oidcSDK.logout();
            }
        });

        btnDangNhap.setVisibility(View.VISIBLE);

        btnDangXuat.setVisibility(View.GONE);

        btnAccessToken = (Button)findViewById(R.id.btnAccessToken);
        btnAccessToken.setVisibility(View.GONE);
        btnAccessToken.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View var1) {
                String token = oidcSDK.getToken();
                Toast.makeText(MainActivity.this, token  ,Toast.LENGTH_LONG).show();

                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", token);
                clipboard.setPrimaryClip(clip);
            }
        });

        btnShowInfoUser = (Button) findViewById(R.id.btnShowInfoUser);
        btnShowInfoUser.setVisibility(View.GONE);
        btnShowInfoUser.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View var1) {
                User user = oidcSDK.getUser();

                Toast.makeText(MainActivity.this, user.getEmail()+ "--"+user.getFull_name()  ,Toast.LENGTH_LONG).show();

                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", user.getEmail()+ "--"+user.getFull_name());
                clipboard.setPrimaryClip(clip);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(oidcSDK != null){
            oidcSDK.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(oidcSDK != null){
            oidcSDK.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(oidcSDK != null){
            oidcSDK.onStop();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(oidcSDK != null){
            oidcSDK.onDestroy();
        }
    }
}
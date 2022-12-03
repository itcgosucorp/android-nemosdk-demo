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
import com.nemo.nsdk.oidc.NemoSDK;
import com.nemo.nsdk.oidc.SdkConfig;
import com.nemo.nsdk.oidc.User;


public class MainActivity extends AppCompatActivity {

    private Button btnDangNhap;
    private Button btnShowInfoUser;
    private Button btnDangXuat;
    private Button btnRefreshToken;

    NemoSDK nemoSDK = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nemoSDK = new NemoSDK();

        initView();

        nemoSDK.sdkInitialize(this, new IGameOauthListener() {
            @Override
            public void onLoginSuccess(String access_token, String id_token) {

                Log.d("access_token", access_token);
                btnDangNhap.setVisibility(View.GONE);

                btnDangXuat.setVisibility(View.VISIBLE);
                btnRefreshToken.setVisibility(View.VISIBLE);
                btnShowInfoUser.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoginFail(String msg, String code) {

            }



            @Override
            public void onLogoutSuccess() {
                btnDangNhap.setVisibility(View.VISIBLE);
                btnDangXuat.setVisibility(View.GONE);
                btnShowInfoUser.setVisibility(View.GONE);
            }

            @Override
            public void onLogoutFail() {

            }
        });



    }

    public void initView() {
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangNhap.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View var1) {
                nemoSDK.login();
            }
        });


        btnDangXuat = (Button) findViewById(R.id.btnDangXuat);
        btnDangXuat.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View var1) {
                nemoSDK.logout();
            }
        });

        btnDangNhap.setVisibility(View.VISIBLE);
        btnDangXuat.setVisibility(View.GONE);

        btnShowInfoUser = (Button) findViewById(R.id.btnShowInfoUser);
        btnShowInfoUser.setVisibility(View.GONE);
        btnShowInfoUser.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View var1) {

                String info = nemoSDK.getUserInfo();

                Toast.makeText(MainActivity.this, info  ,Toast.LENGTH_LONG).show();

                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", info);
                clipboard.setPrimaryClip(clip);
            }
        });

        btnRefreshToken = (Button) findViewById(R.id.btnRefreshToken);
        btnRefreshToken.setVisibility(View.GONE);
        btnRefreshToken.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                Toast.makeText(MainActivity.this, SdkConfig.getInstance().refreshToken, Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(nemoSDK != null){
            nemoSDK.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(nemoSDK != null){
            nemoSDK.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(nemoSDK != null){
            nemoSDK.onStop();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(nemoSDK != null){
            nemoSDK.onDestroy();
        }
    }
}
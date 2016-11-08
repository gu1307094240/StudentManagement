package com.nuc.gu.student_management.activity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nuc.gu.student_management.R;
import com.nuc.gu.student_management.common.CommonURL;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Log.i("IP",getIp());
        initView();



        /**
         * 按钮设置监听事件
         */
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (password.getText().toString().equals("") || username.getText().toString().equals("")) {

                    Toast.makeText(LoginActivity.this, "输入不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    //new 一个请求
                    RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
                    CommonURL url = new CommonURL(getIp());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, CommonURL.loginURL, new Response.Listener<String>() {
                        //这里是返回正确反馈的接口（只要请求成功反馈的数据都这这里）
                        //数据处理反馈（可以这这里处理服务器返回的数据）
                        @Override
                        public void onResponse(String s) {
                            if (s.equals("true")) {
                                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this, GradeActivity.class);
                                intent.putExtra("username",username.getText().toString());
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "账号或密码不正确 ！", Toast.LENGTH_LONG).show();

                            }
                        }
                    }, new Response.ErrorListener() {
                        //volley 有专门处理error的库
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.e("TAG", volleyError.getMessage(), volleyError);
                        }
                    }) {
                        //向服务器发送数据
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("username", username.getText().toString().trim());
                            map.put("password", password.getText().toString().trim());
                            return map;
                        }

                    };
                    //把请求添加到请求队列里面
                    mQueue.add(stringRequest);

                }
            }


        });
    }

    private void initView(){
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.bt_login);
    }

    private String getIp(){
        WifiManager wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        if(ipAddress==0)return "未连接wifi";
        return ((ipAddress & 0xff)+"."+(ipAddress>>8 & 0xff)+"."
                +(ipAddress>>16 & 0xff)+"."+(ipAddress>>24 & 0xff));
    }


}

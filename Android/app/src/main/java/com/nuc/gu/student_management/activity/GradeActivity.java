package com.nuc.gu.student_management.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nuc.gu.student_management.R;
import com.nuc.gu.student_management.adapter.GradeAdapter;
import com.nuc.gu.student_management.bean.GradeBean;
import com.nuc.gu.student_management.bean.GradeJson;
import com.nuc.gu.student_management.common.CommonURL;
import com.nuc.gu.student_management.common.GsonImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GradeActivity extends Activity {

    private ListView listView;
    private GradeAdapter adapter;

    private List<GradeBean> lists = new ArrayList<GradeBean>();
    private String username ;
    private String[] courseName = {"java","javaee","oracle","html","c++","c","linux"};
    private String[] courseTime = {"48","48","32","36","48","48","36"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_grade);

        initView();
    }

    public void initView() {
        listView = (ListView) findViewById(R.id.lv_grade);
//    }
//    public List<GradeBean> getData() {


        //new 一个请求
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        CommonURL url = new CommonURL(getIp());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, CommonURL.getGradeURL, new Response.Listener<String>() {
            //这里是返回正确反馈的接口（只要请求成功反馈的数据都这这里）
            //数据处理反馈（可以这这里处理服务器返回的数据）
            @Override
            public void onResponse(String s) {
                GradeJson bean = GsonImpl.get().toObject(s, GradeJson.class);
                List<GradeBean> gradeBeanList = new ArrayList<GradeBean>();
                int length = bean.getGrade().size();
                for (int i = 0; i < 7; i++) {
                    GradeBean gradeBean = new GradeBean();
                    gradeBean.setNumber(bean.getGrade().get(i).getCnumber());
                    gradeBean.setName(courseName[i]);
                    gradeBean.setTime(courseTime[i]);
                    gradeBean.setGrade(bean.getGrade().get(i).getScore());
                    gradeBeanList.add(gradeBean);
                }
                adapter = new GradeAdapter(GradeActivity.this, gradeBeanList);
                listView.setAdapter(adapter);

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
                Intent intent = getIntent();
                username = intent.getStringExtra("username");
                map.put("snumber", username);
                return map;
            }

        };
        //把请求添加到请求队列里面
        mQueue.add(stringRequest);

//        for (int i = 0;i < 5;i++){
//            GradeBean gradeBean = new GradeBean();
//            gradeBean.setNumber(i + "");
//            gradeBean.setName(i + "");
//            gradeBean.setTime(i + "");
//            gradeBean.setGrade(i + "");
//            gradeBeanList.add(gradeBean);
//        }
//        return gradeBeanList;
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

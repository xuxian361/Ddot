package com.sundy.Ddot.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.androidquery.AQuery;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sundy.Ddot.AppController;
import com.sundy.Ddot.R;
import com.sundy.Ddot.utils.Constant;
import com.sundy.Ddot.utils.Utils;
import jazzyviewpager.Util;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sundy on 15/5/2.
 */
public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.btnBack)
    private ImageButton btnBack;

    private String token;
    private AQuery aq;


    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_login);

        aq = new AQuery(this);
        ViewUtils.inject(this);

    }

    @OnClick({R.id.btnBack, R.id.txt_not_member, R.id.btn_login})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.txt_not_member:
                goRegister();
                break;
            case R.id.btn_login:
                login();
                finish();
                break;
        }
    }

    private void login() {
        EditText edt_phone = aq.id(R.id.edt_phone).getEditText();
        EditText edt_pwd = aq.id(R.id.edt_pwd).getEditText();

        String tag_json_obj = "json_login";
        String url = Constant.HTTP_BASE + "/login/appLogin.do?username=" +
                edt_phone.getText().toString() + "&password=" + edt_pwd.getText().toString();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        try {
                            if (object.has("FF")) {
                                JSONObject FF = object.getJSONObject("FF");
                                if (FF != null) {
                                    getUserInfo(FF);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void getUserInfo(JSONObject FF) throws JSONException {
        token = FF.getString("token");
        String tag_json_obj = "json_login";
        String url = Constant.HTTP_BASE + "/user/appUsers.do?token=" + token;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        try {
                            if (object.has("FF")) {
                                JSONObject FF = object.getJSONObject("FF");
                                if (FF != null) {
                                    saveUserInfo(FF);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void saveUserInfo(JSONObject FF) throws JSONException {
        String user_id = FF.getString("user_id");
        String userName = FF.getString("userName");
        String lastlogin_time = FF.getString("lastlogin_time");
        String birthday = FF.getString("birthday");
        String email = FF.getString("email");
        String homeTown = FF.getString("homeTown");
        String nickName = FF.getString("nickName");
        String phone = FF.getString("phone");
        String place = FF.getString("place");
        String sex = FF.getString("sex");

        SharedPreferences preferences = getSharedPreferences(Utils.APP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Utils.isLogined, "true");
        editor.putString(Constant.USER_ID, user_id);
        editor.putString(Constant.USER_NAME, userName);
        editor.putString(Constant.USER_TOKEN, token);
        editor.putString(Constant.USER_LAST_LOGIN_TIME, lastlogin_time);
        editor.putString(Constant.USER_BIRTHDAY, birthday);
        editor.putString(Constant.USER_EMAIL, email);
        editor.putString(Constant.USER_HOMETOWN, homeTown);
        editor.putString(Constant.USER_NICKNAME, nickName);
        editor.putString(Constant.USER_PHONE, phone);
        editor.putString(Constant.USER_PLACE, place);
        editor.putString(Constant.USER_SEX, sex);

        editor.commit();
    }

    private void goRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }


}

package com.sundy.Ddot.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.androidquery.AQuery;
import com.google.zxing.client.android.MyQRCapture;
import com.sundy.Ddot.R;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sundy on 15/5/5.
 */
public class QRScanerFragment extends BaseFragment {

    private final String TAG = "QRScanerFragment";
    private View v;
    private JSONObject current_item;
    private MyQRCapture qr;

    public QRScanerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.d_qrscan, container, false);

        aq = new AQuery(v);
        init();

        return v;
    }

    private void init() {
        aq.id(R.id.btnBack).clicked(onClick);
        aq.id(R.id.linear_all).clicked(onClick);

        openQRcode();
    }

    private void openQRcode() {
        if (qr == null) {
            qr = new MyQRCapture(getActivity(), aq);
            qr.setQRCallBack(callBack);
        }
        qr.onResume();
    }

    //扫描后得到的结果
    private MyQRCapture.QRCallBack callBack = new MyQRCapture.QRCallBack() {
        @Override
        public void handleDecode(String strData) {
            Pattern pattern = Pattern.compile(".*,.*,.*");
            Matcher isNum = pattern.matcher(strData);

        }
    };

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    onBackPressed();
                    break;
            }
        }
    };


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (qr != null) {
            qr.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (qr != null) {
            qr.onDestroy();
            qr = null;
        }
    }
}

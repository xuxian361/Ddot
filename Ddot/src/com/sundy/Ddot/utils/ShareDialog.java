package com.sundy.Ddot.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.androidquery.AQuery;
import com.sundy.Ddot.R;

import java.util.HashMap;

/**
 * Created by sundy on 15/2/7.
 */
public class ShareDialog {

    private LinearLayout layout;
    private Activity mContext;
    private View rootView;
    private View view;
    private LayoutInflater inflater;
    private AQuery aq;
    private final String TAG = "ShareDialog";
    private PopupWindow mPopupWindow;

    private HashMap<String, String> mData;

    public ShareDialog() {
    }

    public ShareDialog(Activity context, View parent, HashMap<String, String> data) {
        this.mContext = context;
        this.rootView = parent;
        this.mData = data;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.share_dialog, null);
        aq = new AQuery(view);

        aq.id(R.id.btn_done).clicked(onClickListener);

        aq.id(R.id.linear_whatsapp).clicked(onClickListener);
        aq.id(R.id.linear_email).clicked(onClickListener);
        aq.id(R.id.linear_wechat).clicked(onClickListener);

        aq.id(R.id.linear_wechat_moments).clicked(onClickListener);
        aq.id(R.id.linear_wechat_favorite).clicked(onClickListener);
        aq.id(R.id.linear_qq).clicked(onClickListener);
        aq.id(R.id.linear_qzone).clicked(onClickListener);

        layout = (LinearLayout) aq.id(R.id.linear_share).getView();
        layout.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keycode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keycode == KeyEvent.KEYCODE_BACK) {
                }
                return false;
            }
        });

    }

    //显示Dailog
    public void show() {
        int width = Constant.SCREEN_WIDTH;
        int height = Constant.SCREEN_HEIGHT;

        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
            mPopupWindow.setWidth(width);
            mPopupWindow.setHeight(height);
        }
        mPopupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }

    //取消Dailog的显示
    public void dismiss() {
        if (mPopupWindow == null)
            return;
        mPopupWindow.dismiss();
        mPopupWindow = null;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_done:
                    dismiss();
                    break;
                case R.id.linear_whatsapp:
                    dismiss();
                    ShareControl.shareWithWhatsApp(mContext, mData);
                    break;
                case R.id.linear_email:
                    dismiss();
                    ShareControl.shareWithEmail(mContext, mData);
                    break;
                case R.id.linear_wechat:
                    dismiss();
                    ShareControl.shareWithWeChat(mContext, mData);
                    break;
                case R.id.linear_wechat_moments:
                    dismiss();
                    ShareControl.shareWithWeChat_Moments(mContext, mData);
                    break;
                case R.id.linear_wechat_favorite:
                    dismiss();
                    break;
                case R.id.linear_qq:
                    dismiss();
                    break;
                case R.id.linear_qzone:
                    dismiss();
                    break;
            }
        }
    };

}

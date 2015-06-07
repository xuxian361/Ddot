package com.sundy.Ddot.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import com.sundy.Ddot.R;
import com.tencent.mm.sdk.openapi.*;

import java.net.URL;
import java.util.HashMap;

/**
 * Created by sundy on 15/3/10.
 */
public class ShareControl {
    private static Activity mContext;

    public ShareControl() {
    }

    //----------------------------------- Email ------------------------------------
    public static void shareWithEmail(Activity context, HashMap<String, String> data) {
        mContext = context;
        try {
            Uri uri = Uri.parse("mailto:");
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            if (data.get("title") == null)
                intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
            else
                intent.putExtra(Intent.EXTRA_SUBJECT, data.get("title"));
            if (data.get("content") == null)
                intent.putExtra(Intent.EXTRA_TEXT, "");
            else
                intent.putExtra(Intent.EXTRA_TEXT, data.get("content"));
            context.startActivity(Intent.createChooser(intent, context.getString(R.string.choose_app)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //----------------------------------- WeChat ------------------------------------
    public static void shareWithWeChat(Activity context, HashMap<String, String> data) {
        mContext = context;
        PackageManager packageManager = context.getPackageManager();
        PackageInfo info;
        try {
            info = packageManager.getPackageInfo("com.tencent.mm", PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            if (info != null) {
                new WeChatBitmapWorkerTash(context, data).execute();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class WeChatBitmapWorkerTash extends AsyncTask<Integer, Void, Bitmap> {

        private Activity context;
        private HashMap<String, String> data;

        public WeChatBitmapWorkerTash() {
        }

        public WeChatBitmapWorkerTash(Activity context, HashMap<String, String> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        protected Bitmap doInBackground(Integer... integers) {
            Bitmap bmp = null;
            Bitmap bmp2 = null;
            try {
                if (data.get("image") != null) {
                    String image = data.get("image");
                    bmp = BitmapFactory.decodeStream(new URL(image).openStream());
                    bmp2 = Utils.compressImage(bmp);
                } else {
                    bmp2 = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bmp2;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            try {
                String title = "";
                if (data.get("title") != null)
                    title = data.get("title");
                String content = "";
                if (data.get("content") != null)
                    content = data.get("content");
                String url = "";
                if (data.get("url") != null)
                    url = data.get("url");

                IWXAPI iwxapi = WXAPIFactory.createWXAPI(context, Constant.WX_APP_ID, true);
                iwxapi.registerApp(Constant.WX_APP_ID);

                WXWebpageObject localWXWebpageObject = new WXWebpageObject();
                localWXWebpageObject.webpageUrl = url;
                WXMediaMessage localWXMediaMessage = new WXMediaMessage(localWXWebpageObject);
                localWXMediaMessage.title = title;
                localWXMediaMessage.description = content;
                localWXMediaMessage.thumbData = Utils.getBitmapBytes(bitmap, false);

                SendMessageToWX.Req localReq = new SendMessageToWX.Req();
                localReq.transaction = System.currentTimeMillis() + "";
                localReq.message = localWXMediaMessage;
                localReq.scene = SendMessageToWX.Req.WXSceneSession;
                iwxapi.sendReq(localReq);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //----------------------------------- WeChat Moment ------------------------------------
    public static void shareWithWeChat_Moments(Activity context, HashMap<String, String> data) {
        mContext = context;
        PackageManager packageManager = context.getPackageManager();
        PackageInfo info;
        try {
            info = packageManager.getPackageInfo("com.tencent.mm", PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            if (info != null) {
                new WeChatBitmapWorkerTash_Moments(context, data).execute();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class WeChatBitmapWorkerTash_Moments extends AsyncTask<Integer, Void, Bitmap> {

        private Activity context;
        private HashMap<String, String> data;

        public WeChatBitmapWorkerTash_Moments() {
        }

        public WeChatBitmapWorkerTash_Moments(Activity context, HashMap<String, String> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        protected Bitmap doInBackground(Integer... integers) {
            Bitmap bmp = null;
            Bitmap bmp2 = null;
            try {
                if (data.get("image") != null) {
                    String image = data.get("image");
                    bmp = BitmapFactory.decodeStream(new URL(image).openStream());
                    bmp2 = Utils.compressImage(bmp);
                } else {
                    bmp2 = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bmp2;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            try {
                String title = "";
                if (data.get("title") != null)
                    title = data.get("title");
                String content = "";
                if (data.get("content") != null)
                    content = data.get("content");
                String url = "";
                if (data.get("url") != null)
                    url = data.get("url");

                IWXAPI iwxapi = WXAPIFactory.createWXAPI(context, Constant.WX_APP_ID, true);
                iwxapi.registerApp(Constant.WX_APP_ID);

                WXWebpageObject localWXWebpageObject = new WXWebpageObject();
                localWXWebpageObject.webpageUrl = url;
                WXMediaMessage localWXMediaMessage = new WXMediaMessage(localWXWebpageObject);
                localWXMediaMessage.title = title;
                localWXMediaMessage.description = content;
                localWXMediaMessage.thumbData = Utils.getBitmapBytes(bitmap, false);

                SendMessageToWX.Req localReq = new SendMessageToWX.Req();
                localReq.transaction = System.currentTimeMillis() + "";
                localReq.message = localWXMediaMessage;
                localReq.scene = SendMessageToWX.Req.WXSceneTimeline;
                iwxapi.sendReq(localReq);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //----------------------------------- WhatsApp ------------------------------------
    public static void shareWithWhatsApp(Activity context, HashMap<String, String> data) {
        mContext = context;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo info;
            info = packageManager.getPackageInfo("com.whatsapp", PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            if (info != null) {
                String title = "";
                if (data.get("title") != null)
                    title = data.get("title");
                String content = "";
                if (data.get("content") != null)
                    content = data.get("content");
                String image = "";
                if (data.get("image") != null)
                    image = data.get("image");
                String url = "";
                if (data.get("url") != null)
                    url = data.get("url");

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, title + content + image + url);
                context.startActivity(Intent.createChooser(intent, context.getString(R.string.choose_app)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


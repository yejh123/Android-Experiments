package com.example.exp2.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    private static Toast mToast;

    public ToastUtil(Toast toast) {
        this.mToast = toast;
    }

    public static void showMsg(Context context, String msg)
    {
        if (mToast == null)
            mToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        else
            mToast.setText(msg);
        mToast.show();
    }
}

package com.ximcomputerx.formusic.utils;

import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ximcomputerx.formusic.R;
import com.ximcomputerx.formusic.application.MusicApplication;

/**
 * @CREATED HACKER
 */
public class DoubleClickExitUtil {

    private boolean isOnKeyBacking;
    private Handler mHandler;
    private Toast mBackToast;

    public DoubleClickExitUtil() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_BACK) {
            return false;
        }
        if (isOnKeyBacking) {
            mHandler.removeCallbacks(onBackTimeRunnable);
            if (mBackToast != null) {
                mBackToast.cancel();
            }
            ActivityManagerUtil.create().AppExit();
            return true;
        } else {
            isOnKeyBacking = true;
            if (mBackToast == null) {
                mBackToast = Toast.makeText(MusicApplication.getInstance(), R.string.app_back_exit, Toast.LENGTH_LONG);
            }
            mBackToast.show();
            mHandler.postDelayed(onBackTimeRunnable, 2000);
            return true;
        }
    }

    private Runnable onBackTimeRunnable = new Runnable() {
        @Override
        public void run() {
            isOnKeyBacking = false;
            if (mBackToast != null) {
                mBackToast.cancel();
            }
        }
    };
}

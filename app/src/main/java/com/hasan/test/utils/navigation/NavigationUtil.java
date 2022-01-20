package com.hasan.test.utils.navigation;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.hasan.test.BuildConfig;
import com.hasan.test.R;


/**
 * this class is used to navigate to another Fragment or start a new Activity
 */
final public class NavigationUtil {

    private NavigationUtil() {
    }

    public static void goToActivity(
            Context context,
            Intent intent,
            boolean isFinish,
            boolean isClearTask,
            Bundle bundle) {
        if (null != bundle)
            intent = intent.putExtras(bundle);
        if (isClearTask) {
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        } else {
//            intent.setFlags(FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        context.startActivity(intent);
        if (isFinish)
            ((Activity) context).finish();
    }

    public static void goToActivity(
            Context context,
            Class<?> target,
            boolean isFinish,
            boolean isClearTask,
            Bundle bundle
    ) {
        goToActivity(context, new Intent(context, target), isFinish, isClearTask, bundle);
    }

    public static void goToActivity(
            Context context,
            Class<?> target,
            Bundle bundle
    ) {
        goToActivity(context, new Intent(context, target), false, false, bundle);
    }

    public static void goToActivity(
            Context context,
            Class<?> target
    ) {
        goToActivity(context, target, false, false, null);
    }

    public static void goToActivity(
            Context context,
            Class<?> target,
            boolean isFinish
    ) {
        goToActivity(context, target, isFinish, false, null);
    }

    public static void goToActivity(
            Context context,
            Class<?> target,
            boolean isFinish,
            Bundle bundle
    ) {
        goToActivity(context, target, isFinish, false, bundle);
    }


    public static void goToActivity(
            Context context,
            Class<?> target,
            boolean isFinish,
            boolean isClearTask
    ) {
        goToActivity(context, target, isFinish, isClearTask, null);
    }

    public static void openGooglePlay(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public static void openAppShare(Context context) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,context.getResources().getString(R.string.app_name));
            String shareMessage = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID;
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            context.startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

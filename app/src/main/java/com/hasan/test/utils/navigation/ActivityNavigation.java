package com.hasan.test.utils.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ActivityNavigation {

    private final Context context;

    private FinishType finish;

    private Bundle arguments;

    private Integer activityForResultCode;

    private Intent intent;

    public static ActivityNavigation create(Context context) {
        return new ActivityNavigation(context);
    }

    private ActivityNavigation(Context context) {
        this.context = context;
    }

    public ActivityNavigation setArguments(Bundle arguments) {
        this.arguments = arguments;
        return this;
    }

    public ActivityNavigation finish() {
        this.finish = FinishType.NORMAL;
        return this;
    }

    public ActivityNavigation finishAffinity() {
        this.finish = FinishType.AFFINITY;
        return this;
    }

    public ActivityNavigation forResult(int code) {
        this.activityForResultCode = code;
        return this;
    }

    public void navigate(Class<?> targetClass) {
        intent = new Intent(context, targetClass);
        navigate();
    }

    public void navigate(Intent intent) {
        this.intent = intent;
        navigate();
    }

    private void navigate() {
        if (arguments != null)
            intent.putExtras(arguments);

        if (activityForResultCode == null)
            context.startActivity(intent);
        else
            ((Activity) context).startActivityForResult(intent, activityForResultCode);

        if (finish == FinishType.NORMAL)
            ((Activity) context).finish();
        else if (finish == FinishType.AFFINITY)
            ((Activity) context).finishAffinity();
    }


    private enum FinishType {
        NORMAL, AFFINITY
    }
}

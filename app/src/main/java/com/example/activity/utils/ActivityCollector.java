package com.example.activity.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Du
 */
public class ActivityCollector {
    private ActivityCollector() {
    }

    private static final ActivityCollector instance = new ActivityCollector();
    private final List<Activity> activities = new ArrayList<Activity>();

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public void finishActivity() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static ActivityCollector getInstance() {
        return instance;
    }
}

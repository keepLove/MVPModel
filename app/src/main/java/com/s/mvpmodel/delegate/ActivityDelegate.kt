package com.s.mvpmodel.delegate

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * Created by Administrator on 2019/3/18.
 */
class ActivityDelegate : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        activity?.let {
            ActivityManager.instance.addActivity(it)
        }
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
        ActivityManager.instance.currentResumeActivity = activity
    }

    override fun onActivityPaused(activity: Activity?) {
        ActivityManager.instance.currentResumeActivity = null
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
        activity?.let {
            ActivityManager.instance.removeActivity(activity)
        }
    }
}
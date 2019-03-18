package com.s.mvpmodel.delegate

import android.app.Activity
import java.util.*

/**
 * Created by Administrator on 2019/3/18.
 */
class ActivityManager private constructor() {

    /**
     * activity堆
     */
    private val activityList: LinkedList<Activity> = LinkedList()
    /**
     * 当前显示的activity
     */
    var currentResumeActivity: Activity? = null

    /**
     * 添加activity
     */
    internal fun addActivity(activity: Activity) {
        activityList.add(activity)
    }

    /**
     * 删除某一个activity
     */
    fun removeActivity(activity: Activity) {
        activityList.remove(activity)
    }

    /**
     * 获取前一个activity
     */
    fun getPreActivity(): Activity? {
        val size = activityList.size
        if (size >= 2) {
            return activityList[size - 2]
        }
        return null
    }

    /**
     * 获取当前的activity
     */
    fun getCurrentActivity(): Activity? {
        return activityList.lastOrNull()
    }

    companion object {
        val instance: ActivityManager by lazy(LazyThreadSafetyMode.NONE) {
            ActivityManager()
        }
    }
}
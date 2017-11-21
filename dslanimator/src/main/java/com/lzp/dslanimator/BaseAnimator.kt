package com.lzp.dslanimator

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.os.Build
import android.view.View
import android.view.animation.Interpolator

/**
 * author：liuzipeng
 * time: 2017/11/17 10:06
 */
open class BaseAnimator {
    var target: View? = null
    var values: Any? = null
    var repeatCount: Int = 0
    var repeatMode: Int = ValueAnimator.RESTART
    var duration: Long = 300
    var startDelay: Long = 0
    var interpolator: Interpolator? = null

    internal var mStart: (Animator) -> Unit = {}
    internal var mEnd: (Animator) -> Unit = {}
    internal var mRepeat: (Animator) -> Unit = {}
    internal var mCancel: (Animator) -> Unit = {}
    internal var mUpdate: (ValueAnimator) -> Unit = {}

    internal var mAnimator: ValueAnimator? = null

    fun getAnimator() = mAnimator!!

    /**
     * 是否已经暂停
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun isPaused() = mAnimator?.isPaused

    /**
     * 是否正在播放
     */
    fun isRunning() = mAnimator?.isRunning

    /**
     * 是否已经开始
     */
    fun isStarted() = mAnimator?.isStarted

    /**
     * 往回播放
     */
    fun rewind() {
        mAnimator?.removeAllListeners()
        mAnimator?.reverse()
    }

    /**
     * 取消一个属性动画
     */
    fun cancel() {
        mAnimator?.cancel()
    }

    fun onStart(onStart: (Animator) -> Unit) {
        mStart = onStart
    }

    fun onEnd(onEnd: (Animator) -> Unit) {
        mEnd = onEnd
    }

    fun onRepeat(onRepeat: (Animator) -> Unit) {
        mRepeat = onRepeat
    }

    fun onCancel(onCancel: (Animator) -> Unit) {
        mCancel = onCancel
    }

    fun onUpdate(onUpdate: (ValueAnimator) -> Unit) {
        mUpdate = onUpdate
    }
}
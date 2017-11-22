package com.lzp.dslanimator

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color

/**
 * author：liuzipeng
 * time: 2017/11/17 10:05
 */
class ColorAnimator : BaseAnimator() {
    var startColor: String = ""
    var endColor: String = ""

    fun initAnim() {
        if (target == null || startColor.isEmpty() || endColor.isEmpty()) return
        values = floatArrayOf(0f, 1f)
        values?.let {
            mAnimator = when (it) {
                is IntArray -> ValueAnimator.ofInt(*it)
                is FloatArray -> ValueAnimator.ofFloat(*it)
                else -> throw Throwable("values is not a supported data type")
            }
            mAnimator?.let { anim ->
                anim.duration = duration
                anim.repeatMode = repeatMode
                anim.repeatCount = repeatCount
                anim.interpolator = interpolator
                anim.startDelay = startDelay

                anim.addUpdateListener {
                    val value = it.animatedValue as Float
                    val color = ArgbEvaluator().evaluate(value,
                            Color.parseColor(startColor),
                            Color.parseColor(endColor)) as Int
                    target?.setBackgroundColor(color)
                }
            }
        }
    }

    /**
     * 开始动画
     */
    fun start() {
        mAnimator?.let {
            it.start()
            it.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator) {
                    mRepeat(animation)
                }

                override fun onAnimationEnd(animation: Animator) {
                    mEnd(animation)
                }

                override fun onAnimationCancel(animation: Animator) {
                    mCancel(animation)
                }

                override fun onAnimationStart(animation: Animator) {
                    mStart(animation)
                }
            })

            it.addUpdateListener {
                mUpdate(it)
            }
        }
    }
}
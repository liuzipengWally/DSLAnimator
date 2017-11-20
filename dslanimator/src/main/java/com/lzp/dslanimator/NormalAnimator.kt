package com.lzp.dslanimator

import android.animation.Animator
import android.animation.ObjectAnimator

/**
 * author：liuzipeng
 * time: 2017/11/14 17:24
 */
class NormalAnimator(private val property: String) : BaseAnimator() {
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

    fun initAnim() {
        if (target == null) return
        values?.let {
            mAnimator = when (it) {
                is IntArray -> ObjectAnimator.ofInt(target, property, *it)
                is FloatArray -> ObjectAnimator.ofFloat(target, property, *it)
                else -> throw Throwable("values is not a supported data type")
            }
            mAnimator?.let { anim ->
                anim.duration = duration
                anim.repeatMode = repeatMode
                anim.repeatCount = repeatCount
                anim.interpolator = interpolator
                anim.startDelay = startDelay
            }
        }
    }
}
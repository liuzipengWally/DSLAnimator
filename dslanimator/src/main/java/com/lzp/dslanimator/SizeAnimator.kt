package com.lzp.dslanimator

import android.animation.Animator
import android.animation.ValueAnimator

/**
 * author：liuzipeng
 * time: 2017/11/17 11:41
 */
class SizeAnimator(private val sizeType: SizeType) : BaseAnimator() {
    fun initAnim() {
        if (target == null) return
        values?.let {
            mAnimator = when (it) {
                is IntArray -> ValueAnimator.ofInt(*it)
                is FloatArray -> ValueAnimator.ofFloat(*it)
                else -> throw Throwable("values is not a supported data sizeType")
            }
            val params = target?.layoutParams!!
            mAnimator?.let { anim ->
                anim.duration = duration
                anim.repeatMode = repeatMode
                anim.repeatCount = repeatCount
                anim.interpolator = interpolator
                anim.startDelay = startDelay
                anim.addUpdateListener {
                    val value = when (values) {
                        is IntArray -> it.animatedValue as Int
                        else -> it.animatedValue as Float
                    }
                    when (sizeType) {
                        SizeType.WIDTH -> {
                            params.width = when (value) {
                                is Int -> value
                                is Float -> value.toInt()
                                else -> value as Int
                            }
                            target?.layoutParams = params
                        }
                        SizeType.HEIGHT -> {
                            params.height = when (value) {
                                is Int -> value
                                is Float -> value.toInt()
                                else -> value as Int
                            }
                            target?.layoutParams = params
                        }
                    }
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
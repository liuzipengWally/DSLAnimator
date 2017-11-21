package com.lzp.dslanimator

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.os.Build
import android.view.animation.Interpolator

/**
 * author：liuzipeng
 * time: 2017/11/16 15:36
 */
class Set(block: Set.() -> Unit) {
    private val set by lazy { AnimatorSet() }
    val animators by lazy { mutableListOf<Animator>() }
    var duration: Long = 300
    var startDelay: Long = 0
    var interpolator: Interpolator? = null
    var playMode = PlayMode.TOGETHER

    private var mStart: (Animator) -> Unit = {}
    private var mEnd: (Animator) -> Unit = {}
    private var mRepeat: (Animator) -> Unit = {}
    private var mCancel: (Animator) -> Unit = {}
    private var mUpdate: (ValueAnimator) -> Unit = {}

    init {
        block()
    }

    /**
     * 开始动画
     */
    fun start() {
        set.start()
        set.addListener(object : Animator.AnimatorListener {
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
    }

    internal fun initSet(): Set {
        when (playMode) {
            PlayMode.SEQUENTIALLY -> set.playSequentially(animators)
            PlayMode.TOGETHER -> set.playTogether(animators)
        }
        set.duration = duration
        set.startDelay = startDelay
        set.interpolator = interpolator

        return this
    }

    /**
     * 是否已经暂停
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun isPaused() = set.isPaused

    /**
     * 是否正在播放
     */
    fun isRunning() = set.isRunning

    /**
     * 是否已经开始
     */
    fun isStarted() = set.isStarted

    /**
     * 取消一个属性动画
     */
    fun cancel() {
        set.cancel()
    }


    /**
     * 往回播放
     */
    @TargetApi(Build.VERSION_CODES.O)
    fun rewind() {
        set.removeAllListeners()
        set.reverse()
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

    /**
     * 透明度动画
     */
    inline fun alphaAnim(block: NormalAnimator.() -> Unit) {
        val alpha = NormalAnimator("alpha")
        alpha.block()
        alpha.initAnim()
        animators.add(alpha.getAnimator())
    }

    /**
     * 横向缩放动画
     */
    inline fun scaleXAnim(block: NormalAnimator.() -> Unit) {
        val scaleX = NormalAnimator("scaleX")
        scaleX.block()
        scaleX.initAnim()
        animators.add(scaleX.getAnimator())
    }

    /**
     * 纵向缩放动画
     */
    inline fun scaleYAnim(block: NormalAnimator.() -> Unit) {
        val scaleY = NormalAnimator("scaleY")
        scaleY.block()
        scaleY.initAnim()
        animators.add(scaleY.getAnimator())
    }

    /**
     * 横向平移动画
     */
    inline fun translationXAnim(block: NormalAnimator.() -> Unit) {
        val translationX = NormalAnimator("translationX")
        translationX.block()
        translationX.initAnim()
        animators.add(translationX.getAnimator())
    }

    /**
     * 纵向平移动画
     */
    inline fun translationYAnim(block: NormalAnimator.() -> Unit) {
        val translationY = NormalAnimator("translationY")
        translationY.block()
        translationY.initAnim()
        animators.add(translationY.getAnimator())
    }

    /**
     * Z轴动画
     */
    inline fun translationZAnim(block: NormalAnimator.() -> Unit) {
        val translationZ = NormalAnimator("translationZ")
        translationZ.block()
        translationZ.initAnim()
        animators.add(translationZ.getAnimator())
    }

    /**
     * 2D旋转动画
     */
    inline fun rotationAnim(block: NormalAnimator.() -> Unit) {
        val rotation = NormalAnimator("rotation")
        rotation.block()
        rotation.initAnim()
        animators.add(rotation.getAnimator())
    }

    /**
     * 纵向3D旋转动画
     */
    inline fun rotationXAnim(block: NormalAnimator.() -> Unit) {
        val rotationX = NormalAnimator("rotationX")
        rotationX.block()
        rotationX.initAnim()
        animators.add(rotationX.getAnimator())
    }

    /**
     * 横向3D旋转动画
     */
    inline fun rotationYAnim(block: NormalAnimator.() -> Unit) {
        val rotationY = NormalAnimator("rotationY")
        rotationY.block()
        rotationY.initAnim()
        animators.add(rotationY.getAnimator())
    }

    /**
     * 颜色变化动画
     */
    inline fun colorAnim(block: ColorAnimator.() -> Unit) {
        val color = ColorAnimator()
        color.block()
        color.initAnim()
        animators.add(color.getAnimator())
    }

    inline fun widthAnim(block: SizeAnimator.() -> Unit) {
        val width = SizeAnimator(SizeType.WIDTH)
        width.block()
        width.initAnim()
        animators.add(width.getAnimator())
    }

    inline fun heightAnim(block: SizeAnimator.() -> Unit) {
        val height = SizeAnimator(SizeType.HEIGHT)
        height.block()
        height.initAnim()
        animators.add(height.getAnimator())
    }
}
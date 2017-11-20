package com.lzp.dslanimator

/**
 * author：liuzipeng
 * time: 2017/11/14 17:22
 */

/**
 * 透明度动画
 */
inline fun alphaAnim(block: NormalAnimator.() -> Unit): NormalAnimator {
    val alpha = NormalAnimator("alpha")
    alpha.block()
    alpha.initAnim()
    return alpha
}

/**
 * 横向缩放动画
 */
inline fun scaleXAnim(block: NormalAnimator.() -> Unit): NormalAnimator {
    val scaleX = NormalAnimator("scaleX")
    scaleX.block()
    scaleX.initAnim()
    return scaleX
}

/**
 * 纵向缩放动画
 */
inline fun scaleYAnim(block: NormalAnimator.() -> Unit): NormalAnimator {
    val scaleY = NormalAnimator("scaleY")
    scaleY.block()
    scaleY.initAnim()
    return scaleY
}

/**
 * 横向平移动画
 */
inline fun translationXAnim(block: NormalAnimator.() -> Unit): NormalAnimator {
    val translationX = NormalAnimator("translationX")
    translationX.block()
    translationX.initAnim()
    return translationX
}

/**
 * 纵向平移动画
 */
inline fun translationYAnim(block: NormalAnimator.() -> Unit): NormalAnimator {
    val translationY = NormalAnimator("translationY")
    translationY.block()
    translationY.initAnim()
    return translationY
}

/**
 * Z轴动画
 */
inline fun translationZAnim(block: NormalAnimator.() -> Unit): NormalAnimator {
    val translationZ = NormalAnimator("translationZ")
    translationZ.block()
    translationZ.initAnim()
    return translationZ
}

/**
 * 2D旋转动画
 */
inline fun rotationAnim(block: NormalAnimator.() -> Unit): NormalAnimator {
    val rotation = NormalAnimator("rotation")
    rotation.block()
    rotation.initAnim()
    return rotation
}

/**
 * 纵向3D旋转动画
 */
inline fun rotationXAnim(block: NormalAnimator.() -> Unit): NormalAnimator {
    val rotationX = NormalAnimator("rotationX")
    rotationX.block()
    rotationX.initAnim()
    return rotationX
}

/**
 * 横向3D旋转动画
 */
inline fun rotationYAnim(block: NormalAnimator.() -> Unit): NormalAnimator {
    val rotationY = NormalAnimator("rotationY")
    rotationY.block()
    rotationY.initAnim()
    return rotationY
}

/**
 * 颜色变化动画
 */
inline fun colorAnim(block: ColorAnimator.() -> Unit): ColorAnimator {
    val color = ColorAnimator()
    color.block()
    color.initAnim()
    return color
}

inline fun widthAnim(block: SizeAnimator.() -> Unit): SizeAnimator {
    val size = SizeAnimator(SizeType.WIDTH)
    size.block()
    size.initAnim()
    return size
}

inline fun heightAnim(block: SizeAnimator.() -> Unit): SizeAnimator {
    val height = SizeAnimator(SizeType.HEIGHT)
    height.block()
    height.initAnim()
    return height
}

fun animSet(block: Set.() -> Unit) = Set(block).initSet()


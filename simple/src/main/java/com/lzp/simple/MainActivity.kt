package com.lzp.simple

import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.FlingAnimation
import android.support.v7.app.AppCompatActivity
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import com.lzp.dslanimator.animSet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val anim by lazy {
        animSet {
            widthAnim {
                target = mButton
                values = intArrayOf(mButton.width, 500)
            }
            heightAnim {
                target = mButton
                values = intArrayOf(mButton.height, 500)
            }
            colorAnim {
                target = mButton
                startColor = "#636978"
                endColor = "#1AD372"
            }

            duration = 500
            interpolator = OvershootInterpolator()
            onEnd {
                Toast.makeText(this@MainActivity, "动画结束", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mButton.setOnClickListener {
            anim.start()
        }
    }
}

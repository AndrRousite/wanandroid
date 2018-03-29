package com.letion.wanandroid.weiget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.ViewPropertyAnimator
import android.widget.Button
import android.widget.TextView
import com.letion.wanandroid.R
import com.letion.wanandroid.util.AnimationUtils
import com.letion.wanandroid.util.ID
import com.letion.wanandroid.util.extensions.onClick
import com.letion.wanandroid.util.extensions.scale
import com.letion.wanandroid.util.extensions.unSafeLazy
import kotlinx.android.synthetic.main.layout_material_dialog.*

/**
 * <p>com.letion.wanandroid.weiget.MaterialDialog
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
class MaterialDialog(context: Context) : Dialog(context, R.style.MaterialDialogSheet){
    private val titleText by unSafeLazy {
        findViewById<TextView>(R.id.title)
    }
    private val messageText by unSafeLazy {
        findViewById<TextView>(R.id.message)
    }
    private val positiveButton by unSafeLazy {
        findViewById<Button>(R.id.positiveButton)
    }

    init {
        setContentView(R.layout.layout_material_dialog)
        setCancelable(true)
        setCanceledOnTouchOutside(true)
        dialogContainer.onClick { hide() }
    }

    override fun onDetachedFromWindow() {
        super.dismiss()
        super.onDetachedFromWindow()
    }

    infix fun title(title: String): MaterialDialog {
        titleText.text = title
        return this
    }

    infix fun message(message: String): MaterialDialog {
        messageText.text = message
        return this
    }

    fun addPositiveButton(text: String, action: MaterialDialog.() -> Unit): MaterialDialog {
        positiveButton.text = text.toUpperCase()
        positiveButton.onClick {
            action()
        }
        return this
    }

    override fun show() {
        super.show()
        val view = findViewById<View>(ID.container)
        with(view) {
            alpha = 0F
            scale = .85F
            animate()
                    .scale(1F)
                    .alpha(1F)
                    .setDuration(120)
                    .setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR)
                    .withLayer()
                    .start()
        }
    }

    override fun hide() {
        val view = findViewById<View>(ID.container)
        with(view) {
            animate()
                    .scale(.85F)
                    .alpha(0F)
                    .setDuration(110)
                    .setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR)
                    .withLayer()
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            this@MaterialDialog.dismiss()
                        }
                    })
                    .start()
        }
    }

    private fun ViewPropertyAnimator.scale(scale: Float): ViewPropertyAnimator {
        scaleX(scale)
        scaleY(scale)
        return this
    }
}
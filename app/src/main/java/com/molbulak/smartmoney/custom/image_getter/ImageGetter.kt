package com.molbulak.smartmoney.custom.image_getter

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.Html
import android.widget.TextView
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

class ImageGetter(
    private val context: Context,
    private val res: Resources,
    private val htmlTextView: TextView,
) : Html.ImageGetter {

    override fun getDrawable(url: String): Drawable {
        val holder = BitmapDrawablePlaceHolder(res, null)

        GlobalScope.launch(Dispatchers.IO) {
            runCatching {
                val bitmap = getBitmap(context, url)

                val drawable = BitmapDrawable(res, bitmap)

                val scale = 1.8// This makes the image scale in size.
                val width = (drawable.intrinsicWidth * scale).roundToInt()
                val height = (drawable.intrinsicHeight * scale).roundToInt()
                drawable.setBounds(0, 0, width, height)

                holder.setDrawable(drawable)
                holder.setBounds(0, 0, width, height)

                withContext(Dispatchers.Main) { htmlTextView.text = htmlTextView.text }
            }
        }

        return holder
    }

    internal class BitmapDrawablePlaceHolder(res: Resources, bitmap: Bitmap?) :
        BitmapDrawable(res, bitmap) {
        private var drawable: Drawable? = null

        override fun draw(canvas: Canvas) {
            drawable?.run { draw(canvas) }
        }

        fun setDrawable(drawable: Drawable) {
            this.drawable = drawable
        }
    }

    private suspend fun getBitmap(context: Context, url: String): Bitmap {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(url)
            .allowHardware(false) // Disable hardware bitmaps.
            .build()

        val result = (loader.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }
}
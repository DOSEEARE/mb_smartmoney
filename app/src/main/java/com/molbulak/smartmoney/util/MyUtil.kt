package com.molbulak.smartmoney.util

import android.Manifest
import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.watchers.MaskFormatWatcher
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*
import java.util.Date


object MyUtil {
    private val PERMISSIONS_REQUIRED = arrayOf(Manifest.permission.CAMERA)

    /** Convenience method used to check if all permissions required by this app are granted */
    fun hasPermissions(context: Context) = PERMISSIONS_REQUIRED.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    fun timeStamp(): Long {
        return System.currentTimeMillis() / 1000L
    }

    fun onlyDigits(string: String): String {
        return string.replace(Regex("[^0-9]"), "")
    }


    fun checkBiometricSupport(context: Context): Boolean {
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
        return if (context.packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            true
        } else true
    }

    fun inputMask(string: String): MaskFormatWatcher {
        val slots =
            UnderscoreDigitSlotsParser().parseSlots(string.replace(oldChar = '#', newChar = '_'))
        return MaskFormatWatcher(MaskImpl.createTerminated(slots))
    }

    fun currentTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ", Locale.getDefault())
        return sdf.format(Date())
    }

    fun hasPermissions(context: Context, vararg permissions: Array<String>): Boolean =
        permissions.all {
            ActivityCompat.checkSelfPermission(context,
                it.toString()) == PackageManager.PERMISSION_GRANTED
        }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun checkDigit(number: Int): String {
        return if (number <= 9) "0$number" else number.toString()
    }

    /*fun imageToBase64(filePath: String?): String {
        val bmp: Bitmap?
        val bos: ByteArrayOutputStream?
        val bt: ByteArray?
        var encodeString = ""
        val resizedBmp: Bitmap?
        try {
            bmp = BitmapFactory.decodeFile(filePath)
            bos = ByteArrayOutputStream()
            resizedBmp = Bitmap.createScaledBitmap(bmp, 320, 620, false)
            resizedBmp.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            bt = bos.toByteArray()
            encodeString = Base64.encodeToString(bt, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "data:image/png;base64,$encodeString"
    }*/

    fun imageToBase64(imageUri: Uri, context: Context): String {
        fun exifToDegrees(exifOrientation: Int): Float {
            return when (exifOrientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> {
                    Log.d("MYUTIL", "exifToDegrees:  90F")
                    90F
                }
                ExifInterface.ORIENTATION_ROTATE_180 -> {
                    Log.d("MYUTIL", "exifToDegrees:  180F")
                    180F
                }
                ExifInterface.ORIENTATION_ROTATE_270 -> {
                    Log.d("MYUTIL", "exifToDegrees:  270F")
                    270F
                }
                else -> 0F
            }
        }

        val exif = ExifInterface(imageUri.path!!)
        val rotation =
            exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        val rotationInDegrees = exifToDegrees(rotation)
        val imageStream: InputStream? = context.contentResolver.openInputStream(imageUri)
        val selectedImage = BitmapFactory.decodeStream(imageStream)
        val matrix = Matrix()
        matrix.preRotate(rotationInDegrees)
        val rotatedBitmap = Bitmap.createBitmap(selectedImage,
            0,
            0,
            selectedImage.width,
            selectedImage.height,
            matrix,
            true)
        val compressedBitmap = Bitmap.createScaledBitmap(rotatedBitmap, 320, 620, false)
        val baos = ByteArrayOutputStream()
        compressedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b: ByteArray = baos.toByteArray()
        return "data:image/png;base64,${Base64.encodeToString(b, Base64.DEFAULT)}"
    }


    fun base64ToImage(encodedImage: String?): Bitmap {
        val decodedString: ByteArray =
            Base64.decode(encodedImage?.replace("data:image/png;base64,", ""), Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    fun getDeviceName(): String? {
        fun capitalize(s: String?): String {
            if (s == null || s.isEmpty()) {
                return ""
            }
            val first = s[0]
            return if (Character.isUpperCase(first)) {
                s
            } else {
                Character.toUpperCase(first).toString() + s.substring(1)
            }
        }

        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            capitalize(model)
        } else {
            capitalize(manufacturer).toString() + " " + model
        }
    }

    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

}
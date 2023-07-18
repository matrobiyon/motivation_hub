package tj.motivation.hub.core.util

import android.content.res.Resources

fun convertDpToPx(dp : Float) : Float {
    return dp * Resources.getSystem().displayMetrics.density;
}

fun convertPxToDp(px : Float) : Float {
    return px / Resources.getSystem().displayMetrics.density;
}

fun getScreenWidth() : Float {
    return Resources.getSystem().displayMetrics.widthPixels.toFloat()
}

fun getScreenHeight() : Float {
    return Resources.getSystem().displayMetrics.heightPixels.toFloat()
}
package ru.cpt.fsp.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.ramcosta.composedestinations.spec.Direction

fun NavOptionsBuilder.clearBackStack(navigator: NavController) =
    popUpTo(navigator.backQueue.first().destination.route.orEmpty()) {
        inclusive = true
    }

fun Context.isPermissionGranted(permission: String): Boolean =
    ContextCompat.checkSelfPermission(
        this,
        permission
    ) == PackageManager.PERMISSION_GRANTED

fun NavController.navigate(
    direction: Direction,
    onlyIfResumed: Boolean = false,
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    val isResumed = currentBackStackEntry?.getLifecycle()?.currentState == Lifecycle.State.RESUMED
    if (onlyIfResumed && !isResumed) {
        return
    }
    navigate(direction.route, builder)
}
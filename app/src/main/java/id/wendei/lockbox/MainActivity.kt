package id.wendei.lockbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import id.wendei.lockbox.core.navigation.AppNavigation
import id.wendei.lockbox.core.navigation.NavigationWrapper
import id.wendei.lockbox.core.theme.LockBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        window.statusBarColor = Color.Black.toArgb()
        setContent {
            LockBoxTheme {
                NavigationWrapper {
                    AppNavigation()
                }
            }
        }
    }
}
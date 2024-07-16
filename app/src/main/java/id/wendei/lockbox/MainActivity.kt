package id.wendei.lockbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import id.wendei.lockbox.core.navigation.AppNavigation
import id.wendei.lockbox.core.navigation.NavigationWrapper
import id.wendei.lockbox.core.theme.LockBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            LockBoxTheme {
                NavigationWrapper {
                    AppNavigation()
                }
            }
        }
    }
}
package br.tech.cirdan.iota

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.tech.cirdan.iota.ui.navigation.AppNavigation
import br.tech.cirdan.iota.ui.theme.IotaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IotaTheme {
                AppNavigation()
            }
        }
    }
}
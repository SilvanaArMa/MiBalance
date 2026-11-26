
package com.mibalance.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mibalance.app.ui.NavegacionMiBalance
import com.mibalance.app.ui.theme.MiBalanceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MiBalanceTheme {
                NavegacionMiBalance()
            }
        }
    }
}

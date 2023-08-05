package org.dgeek.currencyexchange

import androidx.compose.ui.window.ComposeUIViewController
import org.dgeek.currencyexchange.framework.dihelper.CurrencyHelper
import org.dgeek.currencyexchange.ui.CurrencyExchangeScreen
import platform.UIKit.UIViewController

@Suppress("FunctionName", "unused")
fun MainViewController(currencyHelper: CurrencyHelper): UIViewController =
    ComposeUIViewController {
        CurrencyExchangeScreen(
            currencyHelper = currencyHelper,
        )
    }

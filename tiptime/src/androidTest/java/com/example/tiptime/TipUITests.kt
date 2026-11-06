package com.example.tiptime
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
class TipUITests {
 @get:Rule val rule=createComposeRule()
 @Test fun calculate_20_percent_tip(){rule.setContent{MaterialTheme{TipTimeLayout()}};rule.onNodeWithTag("bill").performTextInput("10");rule.onNodeWithTag("percent").performTextInput("20");rule.onNodeWithTag("result").assertTextContains("2,00",substring=true)}
 @Test fun rounding_switch_updates_result(){rule.setContent{MaterialTheme{TipTimeLayout()}};rule.onNodeWithTag("bill").performTextInput("11");rule.onNodeWithTag("percent").performTextInput("20");rule.onNodeWithTag("round").performClick();rule.onNodeWithTag("result").assertTextContains("3,00",substring=true)}
}

package com.example.tiptime
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.platform.testTag

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.focus.FocusDirection
import java.text.NumberFormat
import java.util.Locale

class MainActivity: ComponentActivity() {
 override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); enableEdgeToEdge(); setContent { MaterialTheme { TipTimeLayout() } } }
}
@Composable fun TipTimeLayout() {
 var amount by rememberSaveable { mutableStateOf("") }
 var percent by rememberSaveable { mutableStateOf("") }
 var round by rememberSaveable { mutableStateOf(false) }
 val focus=LocalFocusManager.current
 val tip=calculateTip(amount.replace(',', '.').toDoubleOrNull() ?: 0.0,percent.replace(',', '.').toDoubleOrNull() ?: 0.0,round)
 Surface(Modifier.fillMaxSize()) {
 Column(Modifier.safeDrawingPadding().padding(24.dp).verticalScroll(rememberScrollState()),verticalArrangement=Arrangement.spacedBy(24.dp)) {
 Text(stringResource(R.string.title),style=MaterialTheme.typography.headlineLarge)
 Text(stringResource(R.string.subtitle),style=MaterialTheme.typography.bodyLarge)
 OutlinedTextField(amount,{amount=it},label={Text(stringResource(R.string.bill))},singleLine=true,keyboardOptions=KeyboardOptions(keyboardType=KeyboardType.Number,imeAction=ImeAction.Next),keyboardActions=KeyboardActions(onNext={focus.moveFocus(FocusDirection.Down)}),modifier=Modifier.fillMaxWidth().testTag("bill"))
 OutlinedTextField(percent,{percent=it},label={Text(stringResource(R.string.percent))},singleLine=true,keyboardOptions=KeyboardOptions(keyboardType=KeyboardType.Number,imeAction=ImeAction.Done),keyboardActions=KeyboardActions(onDone={focus.clearFocus()}),modifier=Modifier.fillMaxWidth().testTag("percent"))
 Row(Modifier.fillMaxWidth(),verticalAlignment=Alignment.CenterVertically) { Text(stringResource(R.string.round),Modifier.weight(1f)); Switch(round,{round=it},Modifier.testTag("round")) }
 HorizontalDivider()
 Text(stringResource(R.string.result,NumberFormat.getCurrencyInstance(Locale.forLanguageTag("ru-RU")).format(tip)),style=MaterialTheme.typography.headlineMedium,modifier=Modifier.testTag("result"))
 }
 }
}

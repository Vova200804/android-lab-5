package com.example.tiptime
import kotlin.math.ceil
fun calculateTip(amount: Double, tipPercent: Double = 15.0, roundUp: Boolean = false): Double {
 if (!amount.isFinite() || !tipPercent.isFinite() || amount < 0 || tipPercent < 0) return 0.0
 val tip=amount*tipPercent/100.0
 return if(roundUp) ceil(tip) else tip
}

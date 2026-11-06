plugins { id("com.android.application"); id("org.jetbrains.kotlin.plugin.compose"); jacoco }
android {
 namespace = "com.example.artspace5"
 compileSdk { version = release(37) { minorApiLevel = 0 } }
 defaultConfig { applicationId = "com.example.artspace5"; minSdk = 24; targetSdk = 37; versionCode = 1; versionName = "1.0"; testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" }
 compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
 buildFeatures { compose = true }
 testOptions { unitTests.isIncludeAndroidResources = true }
 packaging { resources.excludes += "/META-INF/{AL2.0,LGPL2.1}" }
}
dependencies {
 implementation(platform("androidx.compose:compose-bom:2026.02.01"))
 implementation("androidx.activity:activity-compose:1.13.0")
 implementation("androidx.core:core-ktx:1.17.0")
 implementation("androidx.compose.material3:material3")
 implementation("androidx.compose.ui:ui")
 implementation("androidx.compose.ui:ui-tooling-preview")
 implementation("androidx.lifecycle:lifecycle-runtime-compose:2.9.4")
 implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")
 debugImplementation("androidx.compose.ui:ui-tooling")
 debugImplementation("androidx.compose.ui:ui-test-manifest")
 testImplementation("junit:junit:4.13.2")
 androidTestImplementation(platform("androidx.compose:compose-bom:2026.02.01"))
 androidTestImplementation("androidx.compose.ui:ui-test-junit4")
 androidTestImplementation("androidx.test.ext:junit:1.3.0")
 androidTestImplementation("androidx.test:runner:1.7.0")
 androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
}
jacoco { toolVersion = "0.8.14" }
tasks.withType<Test>().configureEach { extensions.configure<JacocoTaskExtension> { isIncludeNoLocationClasses = true; excludes = listOf("jdk.internal.*") } }
tasks.register<JacocoReport>("coverageReport") {
 dependsOn("testDebugUnitTest")
 executionData(files(layout.buildDirectory.file("jacoco/testDebugUnitTest.exec")))
 sourceDirectories.setFrom(files("src/main/java"))
 classDirectories.setFrom(fileTree(layout.buildDirectory.dir("intermediates/javac/debug")) { include("**/*.class") }, fileTree(layout.buildDirectory.dir("intermediates/built_in_kotlinc/debug/compileDebugKotlin/classes")) { include("**/*ViewModel.class", "**/TipCalculatorKt.class") })
 reports { xml.required.set(true); html.required.set(true) }
}


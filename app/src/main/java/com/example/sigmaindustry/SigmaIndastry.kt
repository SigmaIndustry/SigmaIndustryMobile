package com.example.sigmaindustry

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


// I can`t rename this class because ... stack (Manifest edit android:name not resolve)
@HiltAndroidApp
class MainActivity: Application()
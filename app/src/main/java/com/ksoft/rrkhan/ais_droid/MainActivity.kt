package com.ksoft.rrkhan.ais_droid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ksoft.rrkhan.ais_droid.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var clickIntent = Intent(this@MainActivity, LoginActivity::class.java);
        startActivity(clickIntent);
    }
}

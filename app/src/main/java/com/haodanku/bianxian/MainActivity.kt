package com.haodanku.bianxian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.haodanku.sdk.Hdk

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener {
            Hdk.openIndexPage()
        }
    }
}
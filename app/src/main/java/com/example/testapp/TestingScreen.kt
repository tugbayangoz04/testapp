package com.example.testapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TestingScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing_screen)

        val textTestIcerik = findViewById<TextView>(R.id.textTestIcerik)

        val testModel = intent.getSerializableExtra("test_data") as? TestModel
        if (testModel != null) {
            textTestIcerik.text = testModel.testMetni
        } else {
            textTestIcerik.text = "Test yüklenemedi."
        }
    }
}

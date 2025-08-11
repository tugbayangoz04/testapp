package com.example.testapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TestviewScreen : AppCompatActivity() {

    private lateinit var recyclerTests: RecyclerView
    private val testListesi = arrayListOf<TestModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testview_screen)

        recyclerTests = findViewById(R.id.recyclerTests)
        recyclerTests.layoutManager = LinearLayoutManager(this)

        // Şimdilik örnek test verisi ekleyelim. Sen yapay zekadan gelenlerle değiştir
        testListesi.add(
            TestModel(
                id = 1,
                baslik = "Matematik Temel Test",
                testMetni = "1) Soru 1: ... \nA) Şık A \nB) Şık B \nC) Şık C \nD) Şık D \nE) Şık E"
            )
        )
        testListesi.add(
            TestModel(
                id = 2,
                baslik = "Fen Bilimleri Testi",
                testMetni = "1) Soru 1: ... \nA) Şık A \nB) Şık B \nC) Şık C \nD) Şık D \nE) Şık E"
            )
        )

        val adapter = TestAdapter(testListesi) { secilenTest ->
            val intent = Intent(this, TestingScreen::class.java)
            intent.putExtra("test_data", secilenTest)
            startActivity(intent)
        }
        recyclerTests.adapter = adapter
    }
}

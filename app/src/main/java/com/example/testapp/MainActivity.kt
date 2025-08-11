package com.example.testapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

class MainActivity : AppCompatActivity() {

    private lateinit var buttonUpload: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var btnCreateTest: Button
    private lateinit var btnViewTests: Button
    private lateinit var editPrompt: EditText

    private var pdfTextContent: String = ""
    private val PICK_PDF_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonUpload = findViewById(R.id.btnUpload)
        progressBar = findViewById(R.id.progressBar)
        btnCreateTest = findViewById(R.id.btnCreateTest)
        btnViewTests = findViewById(R.id.btnViewTests)
        editPrompt = findViewById(R.id.editPrompt)

        progressBar.visibility = ProgressBar.GONE

        // PDF yükleme butonu
        buttonUpload.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "application/pdf"
            startActivityForResult(intent, PICK_PDF_REQUEST)
        }

        // Test oluştur butonu
        btnCreateTest.setOnClickListener {
            val prompt = editPrompt.text.toString()
            if (pdfTextContent.isNotEmpty() && prompt.isNotEmpty()) {
                Toast.makeText(this, "Test oluşturma işlemi başlıyor...", Toast.LENGTH_SHORT).show()
                // Burada yapay zeka API çağrısı yapılabilir
            } else {
                Toast.makeText(this, "PDF ve prompt gerekli", Toast.LENGTH_SHORT).show()
            }
        }

        // Testleri görüntüle butonu
        btnViewTests.setOnClickListener {
            val intent = Intent(this, TestviewScreen::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                progressBar.visibility = ProgressBar.VISIBLE
                Thread {
                    try {
                        val inputStream = contentResolver.openInputStream(uri)
                        val document = PDDocument.load(inputStream)
                        val stripper = PDFTextStripper()
                        pdfTextContent = stripper.getText(document)
                        document.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    runOnUiThread {
                        progressBar.visibility = ProgressBar.GONE
                        Toast.makeText(this, "PDF yüklendi", Toast.LENGTH_SHORT).show()
                    }
                }.start()
            }
        }
    }
}

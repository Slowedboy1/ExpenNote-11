package com.example.expennote_11

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    //สร้างจัวแปรสำหรับ UI
    private lateinit var etAmount: EditText
    private lateinit var etNote : EditText
    private lateinit var btnAdd : Button
    private lateinit var btnClear : Button
    private lateinit var tvLastItem : TextView
    private lateinit var tvTotal : TextView

    //ตัวแปรสำหรับเก็บค่ายอดรวมทั้งหมด
    private var total : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //เชื่อมโยงตัวแปร เข้ากับ ID XML
        etAmount = findViewById<EditText>(R.id.etAmount)
        etNote = findViewById<EditText>(R.id.etNote)
        btnAdd = findViewById<Button>(R.id.btnAdd)
        btnClear = findViewById<Button>(R.id.btnClear)
        tvLastItem = findViewById<TextView>(R.id.tvLastItem)
        tvTotal = findViewById<TextView>(R.id.tvTotal)

        //กำหนดการทำงานเมื่อผู้ใช้กดปุ่มเริ่ม
        btnAdd.setOnClickListener {
            //สร้างตัวแปรไว้เก็บข้อมูล
            val amountText = etAmount.text.toString().trim()
            val noteText = etNote.text.toString().trim()

            //ทำการตรวจสอบผู้ใช้กรอกหรือป่าว
            if (amountText.isEmpty()) {
                Toast.makeText(this, "กรุณากรอกจำนวนวเงิน", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountText.toDoubleOrNull()
            if (amount == null){
                //แจ้งเตือนให้กรอกตัวเลข
                Toast.makeText(this, "กรุณากรอกเฉพาะตัวเลข", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //เอาจำนวนมารวมกัน
            total += amount

            //ตรวจสอบว่ามีการกรอกคำอธิบายมั้ย ถ้าไม่มี ให้เป็น ไม่ระบุ
            val showNote = if (noteText.isNotEmpty()) noteText else "ไม่ระบุ"

            tvLastItem.text = "รายการล่าสุด: $showNote - %.2f บาท".format(amount)
            tvTotal.text = "ยอดรวมวันนี้: %.2f บาท".format(total)

            etAmount.text.clear()
            etNote.text.clear()
        }
        btnClear.setOnClickListener {
            total = 0.0

            etAmount.text.clear()
            etNote.text.clear()

            tvLastItem.text ="รายการล่าสุด: -"
            tvTotal.text = "ยอดรวมวันนี้: 0.00บาท"


        }
    }
}
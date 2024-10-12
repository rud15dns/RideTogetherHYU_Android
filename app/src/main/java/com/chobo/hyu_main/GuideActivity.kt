package com.chobo.hyu_main

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class GuideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)

        // 뒤로 가기 버튼을 찾아서 클릭 리스너 설정
        val backButton: ImageView = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            // HomeFragment로 전환
            val intent = Intent(this, MainActivity::class.java) // MainActivity를 지정
            intent.putExtra("fragmentToLoad", "home") // 어떤 Fragment를 로드할지 전달
            startActivity(intent)
            finish() // 현재 Activity 종료
        }
    }
}

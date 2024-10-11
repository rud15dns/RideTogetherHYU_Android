package com.chobo.hyu_main

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager // WindowManager import 추가
import com.chobo.hyu_main.databinding.DialogCustomBinding
class CustomDialog(context: Context) : Dialog(context) {

    // 리스너 인터페이스 정의
    interface ItemClickListener {
        fun onYesClick()
        fun onNoClick()
    }

    // 리스너 객체 설정
    private var itemClickListener: ItemClickListener? = null

    fun setItemClickListener(listener: ItemClickListener) {
        this.itemClickListener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 타이틀바 숨기기
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        // 투명한 배경 설정
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        // 다이얼로그 바깥쪽 클릭시 종료되도록 함 (Cancel the dialog when you touch outside)
        setCanceledOnTouchOutside(true)



        val binding = DialogCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 다이얼로그 크기 조절
        val window = window
        val params = window?.attributes
        val displayMetrics = context.resources.displayMetrics
        params?.width = (displayMetrics.widthPixels * 0.83).toInt() // 화면 너비의 90%로 설정
        params?.height = (displayMetrics.heightPixels * 0.2).toInt()
        window?.attributes = params

        // "예" 버튼 클릭 설정
        binding.okButton.setOnClickListener {
            itemClickListener?.onYesClick() // "예" 클릭 시 동작 설정
            dismiss() // 다이얼로그 닫기
        }

        // "아니오" 버튼 클릭 설정
        binding.cancelButton.setOnClickListener {
            itemClickListener?.onNoClick() // "아니오" 클릭 시 동작 설정
            dismiss() // 다이얼로그 닫기
        }
    }

}

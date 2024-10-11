package com.chobo.hyu_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.view.WindowManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AlertDialog

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment의 레이아웃을 인플레이트 합니다.
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 플러스 버튼을 찾아서 클릭 리스너 설정 (FloatingActionButton 사용 가정)
        val plusButton: FloatingActionButton = view.findViewById(R.id.fab_plus)
        plusButton.setOnClickListener {
            // 다이얼로그를 표시하는 함수 호출
            showMatchingDialog()
        }

        return view
    }

    // 매칭을 진행할지 묻는 다이얼로그 표시
    private fun showMatchingDialog() {

        val AlertDialog = CustomDialog(requireContext())
        // AlertDialog를 생성하고 메시지를 설정합니다.

//        여기 안됨
        AlertDialog.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        val layoutParams = AlertDialog.window?.attributes
        layoutParams?.dimAmount = 0.5f // 흐림 정도 설정 (0.0 - 1.0)
        AlertDialog.window?.attributes = layoutParams

        AlertDialog.setItemClickListener(object : CustomDialog.ItemClickListener {
            override fun onYesClick(){
                Toast.makeText(requireContext(), "매칭을 진행합니다", Toast.LENGTH_SHORT).show()
            }

            override fun onNoClick() {
                // "아니오" 버튼 클릭 시 실행할 동작
                Toast.makeText(requireContext(), "매칭을 취소합니다.", Toast.LENGTH_SHORT).show()
            }
        })

//
        AlertDialog.show()

    }
}

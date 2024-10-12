package com.chobo.hyu_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView // ImageView import 추가
import androidx.fragment.app.FragmentManager // FragmentManager import 추가

class GuideFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_guide, container, false)

        // 뒤로 가기 버튼을 찾아서 클릭 리스너 설정
        // 뒤로 가기 버튼 클릭 리스너 설정
        val backButton: ImageView = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            requireActivity().onBackPressed() // 뒤로 가기
        }

        return view
    }
}

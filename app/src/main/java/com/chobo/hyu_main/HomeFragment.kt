package com.chobo.hyu_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.view.WindowManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.tabs.TabLayout
import androidx.core.content.ContextCompat
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import android.widget.Button
import android.content.Intent
import androidx.navigation.fragment.findNavController // Navigation Controller import 추가

class HomeFragment : Fragment() {
    private lateinit var containerLayout: ViewGroup
    private lateinit var tabLayout: TabLayout
    private var isPlusButtonClicked = false

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


            // 클릭 상태에 따라 아이콘 변경
            if (isPlusButtonClicked) {
                plusButton.setImageResource(R.drawable.ic_plus) // 기본 아이콘으로 변경
                plusButton.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.nav_icon_color) // 원래 색상으로 변경
                isPlusButtonClicked = false // 상태 초기화
            } else {
                plusButton.setImageResource(R.drawable.ic_cancel) // 클릭 시 아이콘으로 변경
                plusButton.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.gray) // 클릭 시 색상 변경
                isPlusButtonClicked = true // 상태 변경
            }

            showMatchingDialog(plusButton) // plusButton을 전달하여 클릭 상태를 유지
        }

        val buttonGuide: Button = view.findViewById(R.id.button_guide)
        buttonGuide.setOnClickListener {
            // GuideActivity로 전환
            val intent = Intent(requireContext(), GuideActivity::class.java)
            startActivity(intent) // 새 Activity 시작
        }

        // TabLayout을 참조합니다.
        tabLayout = view.findViewById(R.id.tab_layout)
        setupTabLayout()

        // ShapeAppearanceModel을 사용하여 하단 모서리만 둥글게 설정
        val shapeAppearanceModel = ShapeAppearanceModel().toBuilder()
            .setBottomLeftCorner(CornerFamily.ROUNDED, 16f)  // 왼쪽 하단 모서리 둥글게
            .setBottomRightCorner(CornerFamily.ROUNDED, 16f)  // 오른쪽 하단 모서리 둥글게
            .setTopLeftCorner(CornerFamily.CUT, 0f)          // 상단 모서리는 직선
            .setTopRightCorner(CornerFamily.CUT, 0f)         // 상단 모서리는 직선
            .build()

        // MaterialShapeDrawable에 ShapeAppearanceModel 적용
        val materialShapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
            fillColor = ContextCompat.getColorStateList(requireContext(), R.color.white) // 배경색 설정
        }

        // TabLayout에 배경으로 설정
        tabLayout.background = materialShapeDrawable

        // 카드 레이아웃을 참조합니다.
        containerLayout = view.findViewById(R.id.card_container)

        // 기본적으로 출발 카드 표시
        showDepartureCards()



        return view
    }

    private fun setupTabLayout() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> showDepartureCards() // "학교에서 역까지" 탭 선택
                    1 -> showReturnCards()    // "역에서 학교까지" 탭 선택
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun showDepartureCards() {
        containerLayout.removeAllViews() // 기존 카드 제거

        // 출발 카드 추가
        val card1 = createCardView("출발 임박", "정문 출발", "제가 절반 부담할게요... 벤382", "3/4", 75, R.color.red)
        containerLayout.addView(card1)

        val card2 = createCardView("3분 후 출발", "컨벤션센터 출발", "모든 비용 부담합니다... 벤742", "3/4", 75, R.color.blue)
        containerLayout.addView(card2)

        val card3 = createCardView("15분 후 출발", "제2공학관 출발", "모든 비용 부담합니다... 벤742", "3/4", 75, R.color.blue)
        containerLayout.addView(card3)
    }

    private fun showReturnCards() {
        containerLayout.removeAllViews() // 기존 카드 제거

        // 반납 카드 추가
        val card1 = createCardView("5분 후 출발", "정문에서 내림", "모든 비용 부담합니다... 벤852", "4/4", 100, R.color.red)
        containerLayout.addView(card1)

        val card2 = createCardView("7분 후 반납", "기숙사에서 내림", "모든 비용 부담합니다... 벤962", "2/4", 50, R.color.blue)
        containerLayout.addView(card2)
    }

    // 매칭을 진행할지 묻는 다이얼로그 표시
    private fun showMatchingDialog(plusButton: FloatingActionButton) {
        val AlertDialog = CustomDialog(requireContext())
        AlertDialog.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        val layoutParams = AlertDialog.window?.attributes
        layoutParams?.dimAmount = 0.5f // 흐림 정도 설정 (0.0 - 1.0)
        AlertDialog.window?.attributes = layoutParams

        AlertDialog.setItemClickListener(object : CustomDialog.ItemClickListener {
            override fun onYesClick() {
                Toast.makeText(requireContext(), "매칭을 진행합니다", Toast.LENGTH_SHORT).show()
            }

            override fun onNoClick() {
                Toast.makeText(requireContext(), "매칭을 취소합니다.", Toast.LENGTH_SHORT).show()
            }
        })

        AlertDialog.setOnDismissListener {
            plusButton.setImageResource(R.drawable.ic_plus) // 기본 아이콘으로 변경
            plusButton.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.nav_icon_color)
            isPlusButtonClicked = false // 상태 초기화
        }

        AlertDialog.show()
    }

    // 재사용 가능한 카드를 생성하는 함수
    private fun createCardView(
        statusLabel: String,
        title: String,
        description: String,
        seatInfo: String,
        progress: Int,
        statusColorRes: Int
    ): View {
        // 카드 레이아웃을 인플레이트
        val cardView = layoutInflater.inflate(R.layout.card_item, containerLayout, false) as CardView

        // 데이터 바인딩
        val statusTextView = cardView.findViewById<TextView>(R.id.status_label)
        val titleTextView = cardView.findViewById<TextView>(R.id.card_title)
        val descriptionTextView = cardView.findViewById<TextView>(R.id.card_description)
        val seatInfoTextView = cardView.findViewById<TextView>(R.id.card_seat_info)
        val progressBar = cardView.findViewById<ProgressBar>(R.id.card_progress)


        // 설정
        statusTextView.text = statusLabel
        titleTextView.text = title
        descriptionTextView.text = description
        seatInfoTextView.text = seatInfo
        progressBar.progress = progress

        // 상태 레이블의 배경 색상을 동적으로 설정
        val background = ContextCompat.getDrawable(requireContext(), R.drawable.status_background)
        background?.setTint(ContextCompat.getColor(requireContext(), statusColorRes))
        statusTextView.background = background

        return cardView
    }
}

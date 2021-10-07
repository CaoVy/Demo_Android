package com.example.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    private var newMutableList = mutableListOf<UserData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageId = mutableListOf(
            R.drawable.img_a,
            R.drawable.img_b,
            R.drawable.img_c,
            R.drawable.img_d,
            R.drawable.img_e,
            R.drawable.img_f,
            R.drawable.img_g,
            R.drawable.img_h,
            R.drawable.img_i,
            R.drawable.img_k,
            R.drawable.img_l
        )
        val textViewId = mutableListOf(
            "Cầu Thê Húc cong cong hình đuôi tôm",
            "Dạo quanh ngắm cảnh Hồ Hoàn Kiếm – Hà Nội",
            "Tham quan Quảng trường Ba Đình – ghé thăm Lăng Bác",
            "Vẻ đẹp rực rỡ của đền Ngọc Sơn về đêm",
            "Nhà hát lớn Hà Nội",
            "Thung lũng Mường Hoa",
            "Chợ phiên Bắc Hà – nét đẹp văn hoá vùng núi Việt Nam",
            "Chinh phục đỉnh Fansipan – Nóc nhà Đông Dương",
            "Có một Sa Pa rực rỡ về đêm",
            "Đèo Mã Pí Lèng ngoạn mục nổi tiếng ở Hà Giang",
            "Mùa hoa tam giác mạch tháng 10 tràn ngập cả vùng trời Hà Giang"
        )

        newMutableList = mutableListOf()
        getShow(imageId, textViewId)

        val adapter = UserAdapter(newMutableList)
        recyclerUser.adapter = adapter

        val swipeGesture = object : SwipeGesTure(this.requireContext()) {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        adapter.deleted(viewHolder.adapterPosition)
                        Snackbar.make(
                            recyclerUser, "You Deleted Item",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }

                    ItemTouchHelper.RIGHT -> {
                        val position = newMutableList[viewHolder.adapterPosition]
                        adapter.addItem(newMutableList.size, position)
                        Snackbar.make(
                            recyclerUser, "You Add Item",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeGesture)
        itemTouchHelper.attachToRecyclerView(recyclerUser)
    }

    private fun getShow(dataImage: MutableList<Int>, dataText: MutableList<String>) {
        for (i in dataImage.indices) {
            val userData = UserData(dataImage[i], dataText[i])
            newMutableList.add(userData)
        }
    }
}

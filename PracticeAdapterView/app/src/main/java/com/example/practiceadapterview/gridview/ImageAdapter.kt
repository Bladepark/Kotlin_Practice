package com.example.practiceadapterview.gridview

import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.practiceadapterview.R

class ImageAdapter : BaseAdapter() {
    override fun getCount(): Int {
        // 항목의 총 개수를 반환하기 위해 mThumbIds 배열의 크기를 반환
        return mThumbIds.size
    }

    override fun getItem(position: Int): Any {
        // 특정 위치의 항목을 반환하기 위해 mThumbIds 배열의 지정된 위치의 항목을 반환
        return mThumbIds[position]
    }

    override fun getItemId(position: Int): Long {
        // 특정 위치의 항목 아이디를 반환하는 것인데, 여기서는 배열의 위치(순서)를 항목의 아이디로 간주함
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        /*
        - getView()는 getView 메소드는 첫번째 파라미터로 주어진 위치의 항목 뷰를 반환하는 것이므로,mThumbIds배열의
        position 위치에 있는 이미지 리소스를 ImageView의 이미지로 설정하고, 이 설정된 ImageView 객체를 그리드 뷰의 항목뷰로 반환한다.

        - getView() 메소드의 두번째 파라미터인 convertView는 이전에 생성된 항목뷰 (여기서는 ImageView)를 의미한다.
        만약 해당 위치의 항목뷰가 처음 만들어지는 경우라면, 새로운 이미지뷰 객체를 만들고 크기와 스케일타입, 패팅을 설정한다.
        만약 이전에 이미 만들어진 것이라면, 이를 재사용한다.

        - 이미지 뷰의 scaleType은 원본 이미지를 이미지 뷰에 맞게 확대 및 축소시킬 때, 어떻게 처리할 지를 지정하는 것인데,
        여기서 CENTER_CROP은 종횡비를 유지하여 스케일링하며 뷰의 크기 이상으로 채우게 됨을 의미한다. 따라서 이미지 일부가 잘릴 수 있다.
        */

        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(parent!!.context)
            imageView.layoutParams = AbsListView.LayoutParams(200, 200)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(8, 8, 8, 8)
        } else {
            imageView = convertView as ImageView
        }

        imageView.setImageResource(mThumbIds[position])
        return imageView
    }

    private val mThumbIds = arrayOf<Int>(
        R.drawable.alarm, R.drawable.alarm,
        R.drawable.arrow, R.drawable.chat,
        R.drawable.heart, R.drawable.left_arrow,
        R.drawable.lv1, R.drawable.lv2,
        R.drawable.lv3, R.drawable.lv4,
        R.drawable.lv5, R.drawable.lv6,
        R.drawable.alarm, R.drawable.alarm,
        R.drawable.arrow, R.drawable.chat,
        R.drawable.heart, R.drawable.left_arrow,
        R.drawable.lv1, R.drawable.lv2,
        R.drawable.lv3, R.drawable.lv4,
        R.drawable.lv5, R.drawable.lv6,
        R.drawable.alarm, R.drawable.alarm,
        R.drawable.arrow, R.drawable.chat,
        R.drawable.heart, R.drawable.left_arrow,
        R.drawable.lv1, R.drawable.lv2,
        R.drawable.lv3, R.drawable.lv4,
        R.drawable.lv5, R.drawable.lv6,
        R.drawable.alarm, R.drawable.alarm,
        R.drawable.arrow, R.drawable.chat,
        R.drawable.heart, R.drawable.left_arrow,
        R.drawable.lv1, R.drawable.lv2,
        R.drawable.lv3, R.drawable.lv4,
        R.drawable.lv5, R.drawable.lv6
        )
}
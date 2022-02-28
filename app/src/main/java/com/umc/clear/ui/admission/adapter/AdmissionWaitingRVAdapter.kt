package com.umc.clear.ui.admission.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.clear.data.entities.dataResult
import com.umc.clear.databinding.FragmentAdmissionBinding
import com.umc.clear.databinding.ItemAdmissionWaitingPageBinding
import com.umc.clear.databinding.ItemAdmissionWaitingPageContentBinding
import com.umc.clear.databinding.ItemAdmissionWaitingPageDateBinding
import com.umc.clear.ui.admission.view.AdmissionFragment
import java.util.*
import kotlin.collections.ArrayList

class AdmissionWaitingRVAdapter(val data: ArrayList<dataResult>, val seq: ArrayList<String>, val dialBinding: FragmentAdmissionBinding, val frag: AdmissionFragment): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var date = ""
    override fun getItemViewType(position: Int): Int {
        return if (seq[position].length == 19) {
            0
        }
        else {
            1
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when(viewType) {
            1-> {
                val binding = ItemAdmissionWaitingPageContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ContentHolder(binding)
            }
            else-> {
                val binding = ItemAdmissionWaitingPageDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DateHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(seq[position].length) {
            19-> {
                (holder as AdmissionWaitingRVAdapter.DateHolder).init(position)
            }
            else-> {
                (holder as AdmissionWaitingRVAdapter.ContentHolder).init(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return seq.size
    }

    inner class ContentHolder(val binding: ItemAdmissionWaitingPageContentBinding): RecyclerView.ViewHolder(binding.root) {
        fun init(pos: Int) {
            val contData = data[seq[pos].toInt()]
            val timeArr = contData.cleanedAt!!.split(" ")[1].split(":")
            binding.itemWaitingContentTimeDesTv.text = timeArr[0] + ":" + timeArr[1] + "에 신청한 사진"

            initListener(timeArr, contData)
        }

        fun initListener(timeArr: List<String>, data: dataResult) {
            binding.itemWaitingContentCl.setOnClickListener {
                dialBinding.itemAdmisDialTitleTv.text = timeArr[0] + ":" + timeArr[1] + "에 신청한 사진"

                Glide.with(frag.requireContext()).load(data.beforePicUrl).into(dialBinding.itemAdmisDialBeforeIv)
                Glide.with(frag.requireContext()).load(data.afterPicUrl).into(dialBinding.itemAdmisDialAfterIv)
                dialBinding.itemAdmisDialCommTv.text = data.contents

                dialBinding.itemAdmisDialCl.visibility = View.VISIBLE
                dialBinding.admisBlurTv.visibility = View.VISIBLE
            }
        }
    }

    inner class DateHolder(val binding: ItemAdmissionWaitingPageDateBinding): RecyclerView.ViewHolder(binding.root) {
        fun init(pos: Int) {
            val dateArr = seq[pos].split(" ")[0].split("-")
            var cal = Calendar.getInstance()
            cal.set(dateArr[0].toInt(), dateArr[1].toInt() - 1, dateArr[2].toInt())
            val day = getDay(cal.get(Calendar.DAY_OF_WEEK))

            binding.admisWaitingDateTv.text = cal.get(Calendar.YEAR).toString() + "년 " +
                    (cal.get(Calendar.MONTH) + 1).toString() + "월 " +
                    cal.get(Calendar.DATE).toString() + "일 (" +
                    day + ")"
        }

        fun getDay(day: Int): String {
            return when (day) {
                1-> {
                    "일"
                }
                2-> {
                    "월"
                }
                3-> {
                    "화"
                }
                4-> {
                    "수"
                }
                5-> {
                    "목"
                }
                6-> {
                    "금"
                }
                else-> {
                    "토"
                }
            }
        }
    }
}
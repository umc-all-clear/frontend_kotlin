package com.umc.clear.ui.admission.adapter

import android.Manifest
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.umc.clear.data.entities.*
import com.umc.clear.data.remote.RetroService
import com.umc.clear.databinding.ItemAdmissionAdmisPageBinding
import com.umc.clear.databinding.ItemAdmissionWaitingPageBinding
import com.umc.clear.ui.admission.view.AdmissionFragment
import com.umc.clear.utils.PrefApp
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*

class AdmissionContentVPAdapter(val data: ArrayList<Int>, val mainCont: Context, val frag: AdmissionFragment): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface listener {
        fun onClick(binding: ItemAdmissionAdmisPageBinding, isBefore: Boolean)
    }

    lateinit var itemListener: listener

    fun setListener(listenData: listener) {
        itemListener = listenData
    }

    override fun getItemViewType(position: Int): Int {
        return data[position]
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                val binding = ItemAdmissionAdmisPageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AdmissionHolder(binding)
            }
            else -> {
                val binding = ItemAdmissionWaitingPageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                WaitingHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (data[position]) {
            1 -> {
                (holder as AdmissionContentVPAdapter.AdmissionHolder).init()
            }
            else -> {
                (holder as AdmissionContentVPAdapter.WaitingHolder).init()
            }
        }
    }

    override fun getItemCount(): Int {
        ContextCompat.checkSelfPermission(mainCont, Manifest.permission.READ_EXTERNAL_STORAGE)
        return 2
    }

    inner class AdmissionHolder(val binding: ItemAdmissionAdmisPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun init() {
            binding.itemAdmisBeforeDesIv.setOnClickListener {
                itemListener.onClick(binding, true)
            }
            binding.itemAdmisBeforeIv.setOnClickListener {
                itemListener.onClick(binding, true)
            }
            binding.itemAdmisAfterDesIv.setOnClickListener {
                itemListener.onClick(binding, false)
            }
            binding.itemAdmisAfterIv.setOnClickListener {
                itemListener.onClick(binding, false)
            }

            binding.itemAdmisAdmissionIv.setOnClickListener {
                if (binding.itemAdmisBeforeIv.tag == "true" && binding.itemAdmisAfterIv.tag == "true") {
                    if (binding.itemAdmisCommEt.text.isNotEmpty()) {
                        var conn = RetroService
                        conn.setAdData(frag)

                        var before: MultipartBody.Part? = null
                        var after: MultipartBody.Part? = null

                        val email = jsonReq(PrefApp.pref.getString("email"))
                        val comm = jsonReqCont(binding.itemAdmisCommEt.text.toString())

                        before = MultipartBody.Part.createFormData(
                            "beforePic",
                            getFile(frag.beforeUri)!!.getName() + ".jpeg",
                            RequestBody.create(MediaType.parse("image/*"), getFile(frag.beforeUri)!!)
                        )

                        after = MultipartBody.Part.createFormData(
                            "afterPic",
                            getFile(frag.afterUri)!!.getName() + ".jpeg",
                            RequestBody.create(MediaType.parse("image/*"), getFile(frag.afterUri)!!)
                        )
                        conn.reqAddmission(ReqAdmission(before, after, email, comm))

                    }
                }
            }
        }

        fun getFile(uri: Uri): File? {
            val context = mainCont.applicationContext
            val contentResolver = context.contentResolver ?: return null

            val filePath = (context.applicationInfo.dataDir + File.separator
                    + System.currentTimeMillis())
            val file = File(filePath)
            val inputStream = contentResolver.openInputStream(uri) ?: return null
            val outputStream: OutputStream = FileOutputStream(file)
            val buf = ByteArray(1024)
            var len: Int
            while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
            outputStream.close()
            inputStream.close()

            return file
        }
    }


    inner class WaitingHolder(val binding: ItemAdmissionWaitingPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun init() {
            val originDataArr = PrefApp.glob.getDataArr()
            var filteredDataArr = ArrayList<dataResult>()
            for (i in originDataArr) {
                if (!i.waited!!) {
                    filteredDataArr.add(i)
                }
            }

            var date = ""
            var seqArr = ArrayList<String>()
            var arrCount = -1
            for (i in filteredDataArr) {
                arrCount++
                val strArr = i.cleanedAt!!.split(" ")
                if (date != strArr[0] || date == "") {
                    date = strArr[0]
                    seqArr.add(i.cleanedAt!!)
                }
                seqArr.add(arrCount.toString())

            }

            binding.itemAdmisWaitingRv.adapter = AdmissionWaitingRVAdapter(filteredDataArr, seqArr)
        }
    }
}

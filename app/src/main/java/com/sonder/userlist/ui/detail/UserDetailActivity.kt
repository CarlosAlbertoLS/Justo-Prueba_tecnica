package com.sonder.userlist.ui.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.sonder.userlist.databinding.ActivityUserDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserDetailActivity : AppCompatActivity() {
    lateinit var gender: String

    companion object {
        const val EXTRA_GENDER = "gender"
        fun created(context: Context) =
            Intent(context, UserDetailActivity::class.java)
    }

    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gender = intent.getStringExtra(EXTRA_GENDER).orEmpty()
        initUI(gender)
        detailViewModel.progressBarVisibility.observe(this) { visibility ->
            binding.pbUserDetail.visibility = visibility
        }
    }

    private fun initUI(gender: String) {
        detailViewModel.getUserByGender(gender)
        detailViewModel.itemDetail.observe(this) { data ->
            Glide.with(this)
                .load(data.component1().picture.large)
                .into(binding.ivThumbnail)
            Glide.with(this)
                .load(data.component1().picture.medium)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(180)))
                .into(binding.ivUserPhotoDetail)
            binding.tvUserNameDetail.text = " ${data.component1().name.first} ${data.component1().name.last}"
            binding.tvEmailDetail.text = data.component1().email
            binding.tvGenderDetail.text = data.component1().gender
            binding.tvPhoneDetail.text = data.component1().phone
            binding.tvMapDetail.text =
                " ${data.component1().location.city}," +
                        " ${data.component1().location.street.name}, " +
                        "${data.component1().location.street.number}, ${data.component1().location.postcode} "

            val phone = data.component1().phone
            binding.btnCall.setOnClickListener { call(phone) }

            val coordinates = "${data.component1().location.coordinates.latitude}, ${data.component1().location.coordinates.longitude}"
            binding.btnMaps.setOnClickListener { startMaps(coordinates) }
        }
    }

    private fun startMaps(coordinates: String) {
        val gmmIntentUri = Uri.parse("geo:$coordinates?z=8")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

            startActivity(mapIntent)

    }

    private fun call(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phone")
        }
        startActivity(intent)
    }
}
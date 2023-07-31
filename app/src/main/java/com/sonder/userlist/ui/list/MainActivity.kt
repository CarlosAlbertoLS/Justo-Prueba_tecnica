package com.sonder.userlist.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.GridLayoutManager
import com.sonder.userlist.databinding.ActivityMainBinding
import com.sonder.userlist.ui.adapter.UserAdapter
import com.sonder.userlist.ui.detail.UserDetailActivity
import com.sonder.userlist.ui.detail.UserDetailActivity.Companion.EXTRA_GENDER
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userListViewModel: UserListViewModel by viewModels()
    private lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userListViewModel.itemList.observe(this){items ->
            adapter.updateList(items)
        }
        userListViewModel.progressBarVisibility.observe(this) { visibility ->
            screenSplash.setKeepOnScreenCondition{ visibility }
        }
        userListViewModel.users()
        initUI()
    }

    private fun initUI() {
        adapter = UserAdapter(){gender ->
            openDetail(gender)
        }
        binding.rvUserList.setHasFixedSize(true)
        binding.rvUserList.layoutManager = GridLayoutManager(this,2)
        binding.rvUserList.adapter = adapter
    }

    private fun openDetail(gender: String) {
        startActivity(UserDetailActivity.created(this).apply {
            putExtra(EXTRA_GENDER, gender)
        })
    }
}
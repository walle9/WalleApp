package com.compose.walleapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.walleapp.model.entity.UserInfoEntity
import com.compose.walleapp.model.service.UserInfoManage
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UserViewModel(context: Context) : ViewModel() {

    private val userInfoManage = UserInfoManage(context)

    var userInfo: UserInfoEntity? = null
        private set

    //是否已经登录
    val logged: Boolean
        get() = userInfo != null

    init {
        //其实这里可以使用 datastore 的对象存储,直接存储整个对象
        viewModelScope.launch {
            var username = userInfoManage.username.firstOrNull()
            userInfo = if (username?.isNotEmpty() == true) {
                UserInfoEntity(username)
            } else {
                null
            }
        }
    }

    /**
     * 登录
     */
    fun login(onClose: () -> Unit) {
        //模拟网络数据回传
        userInfo = UserInfoEntity("user001")
        viewModelScope.launch {
            userInfoManage.save(userInfo!!.username)
        }

        onClose()
    }

    /**
     * 退出登录
     */
    fun logout() {

        viewModelScope.launch {
            userInfoManage.clear()
            userInfo=null
        }
    }
}
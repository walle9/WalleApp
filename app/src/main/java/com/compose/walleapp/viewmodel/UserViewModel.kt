package com.compose.walleapp.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.walleapp.model.entity.UserInfoEntity
import com.compose.walleapp.model.service.UserInfoManage
import com.compose.walleapp.model.service.UserService
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UserViewModel(context: Context) : ViewModel() {

    private val userService = UserService.instance()

    private val userInfoManage = UserInfoManage(context)


    var userName by mutableStateOf("")

    var password by mutableStateOf("")


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
    suspend fun login(onClose: () -> Unit) {

        val res = userService.signIn(userName, password)
        if (res.code ==0 && res.data!=null) {
            userInfo = res.data
            //userInfoManage.save(userName)
            onClose()
        } else {
            val massage = res.massage
        }




    }

    /**
     * 退出登录
     */
    fun logout() {

        viewModelScope.launch {
            userInfoManage.clear()
            userInfo = null
        }
    }
}
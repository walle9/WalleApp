package com.compose.walleapp.compositionLocal

import androidx.compose.runtime.compositionLocalOf
import com.compose.walleapp.viewmodel.UserViewModel

val localUserViewModel = compositionLocalOf <UserViewModel>{ error("User View Model Context Not Found") }
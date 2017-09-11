package com.sys1yagi.mastodon.android.ui.navigation.home.toot

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReplyTo(val id: Long, val acct: String) : Parcelable

package com.sys1yagi.mastodon.android.ui.main

import com.google.gson.Gson
import com.sys1yagi.mastodon.android.TestApplication
import com.sys1yagi.mastodon.android.extensions.toIosZonedDateTime
import com.sys1yagi.mastodon.android.testtool.AssetsUtil
import com.sys1yagi.mastodon4j.api.entity.Status
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(22), manifest = "./src/main/AndroidManifest.xml", application = TestApplication::class, qualifiers = "en")
class TimelineStatusTest {

    @Before
    fun setUp(){
    }

    @Test
    fun elapsed() {
        val context = RuntimeEnvironment.application
        val json = AssetsUtil.readFromAssets("status.json")
        val status: Status = Gson().fromJson(json, Status::class.java)
        val timelineStatus = TimelineStatus(status)

        // now
        run {
            val now = "2017-04-14T06:11:41.893Z".toIosZonedDateTime().toInstant().toEpochMilli()
            assertThat(timelineStatus.elapsed(context, now)).isEqualTo("Now")
        }

        // sec
        run {
            val now = "2017-04-14T06:11:44.893Z".toIosZonedDateTime().toInstant().toEpochMilli()
            assertThat(timelineStatus.elapsed(context, now)).isEqualTo("3s")
        }

        // min
        run {
            val now = "2017-04-14T06:13:44.893Z".toIosZonedDateTime().toInstant().toEpochMilli()
            assertThat(timelineStatus.elapsed(context, now)).isEqualTo("2m")
        }

        // hour
        run {
            val now = "2017-04-14T20:13:44.893Z".toIosZonedDateTime().toInstant().toEpochMilli()
            assertThat(timelineStatus.elapsed(context, now)).isEqualTo("14h")
        }

        // day
        run {
            val now = "2017-04-20T06:13:44.893Z".toIosZonedDateTime().toInstant().toEpochMilli()
            assertThat(timelineStatus.elapsed(context, now)).isEqualTo("6d")
        }
    }
}

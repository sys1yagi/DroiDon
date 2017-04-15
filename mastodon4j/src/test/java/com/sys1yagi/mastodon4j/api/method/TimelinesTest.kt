package com.sys1yagi.mastodon4j.api.method

import com.google.gson.Gson
import com.sys1yagi.kmockito.invoked
import com.sys1yagi.kmockito.mock
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.testtool.AssetsUtil
import okhttp3.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class TimelinesTest {

    @Test
    fun public() {
        val client: MastodonClient = mock()
        val response: Response = Response.Builder()
                .code(200)
                .request(Request.Builder().url("https://test.com/").build())
                .protocol(Protocol.HTTP_1_1)
                .body(ResponseBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        AssetsUtil.readFromAssets("public_timeline.json")
                ))
                .build()
        client.get(anyString()).invoked.thenReturn(response)
        client.getSerializer().invoked.thenReturn(Gson())

        val timelines = Timelines(client)
        val statuses = timelines.public()
        assertThat(statuses.size).isEqualTo(20)
    }

}

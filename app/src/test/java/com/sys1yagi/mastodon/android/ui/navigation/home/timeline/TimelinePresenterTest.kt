package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import com.google.gson.Gson
import com.sys1yagi.kmockito.mock
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.entity.Status
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.assertj.core.api.Assertions.assertThat

@RunWith(RobolectricTestRunner::class)
class TimelinePresenterTest {

    val gson = Gson()

    fun status(id: Long) = gson.fromJson("{id:$id}", Status::class.java)

    @Test
    fun onTimeline() {
        val presenter = TimelinePresenter(mock(), mock(), "mstdn.jp", mock(), mock())

        assertThat(presenter.viewModel.statuses).isEmpty()


        presenter.onTimeline(Pageable<Status>(listOf(status(1), status(2), status(3)), null))
        assertThat(presenter.viewModel.statuses).hasSize(3)

        presenter.onTimeline(Pageable<Status>(listOf(status(4), status(5), status(1), status(2)), null))
        assertThat(presenter.viewModel.statuses).hasSize(7)
    }
}

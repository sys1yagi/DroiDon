package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import com.sys1yagi.mastodon4j.api.Link
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat

class TimelineViewModelTest {
    @Test
    fun mergeLink() {
        val viewModel = TimelineViewModel()
        assertThat(viewModel.link).isNull()

        // first
        val link = Link("", "", "", 10L, 0L)
        viewModel.mergeLink(link, true)

        assertThat(viewModel.link).isNotNull()
        assertThat(viewModel.link?.maxId).isEqualTo(10L)

        // merge next
        run {
            val newLink = Link("", "", "", 11L, 20L)
            viewModel.mergeLink(newLink, true)

            assertThat(viewModel.link).isNotNull()
            assertThat(viewModel.link?.maxId).isEqualTo(11L)
            assertThat(viewModel.link?.sinceId).isEqualTo(0L)
        }

        // merge prev
        run {
            val newLink = Link("", "", "", 0L, 20L)
            viewModel.mergeLink(newLink, false)

            assertThat(viewModel.link).isNotNull()
            assertThat(viewModel.link?.maxId).isEqualTo(11L)
            assertThat(viewModel.link?.sinceId).isEqualTo(20L)
        }
    }
}

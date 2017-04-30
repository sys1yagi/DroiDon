package com.sys1yagi.mastodon.android.extensions

import com.sys1yagi.mastodon4j.api.Range
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat

class RangeExtensionsKtTest {
    @Test
    fun isNextPage() {
        run {
            val range = Range()
            assertThat(range.isNextPage()).isFalse()
        }
        run {
            val range = Range(maxId = 10L)
            assertThat(range.isNextPage()).isTrue()
        }
        run {
            val range = Range(sinceId = 10L)
            assertThat(range.isNextPage()).isFalse()
        }
        run {
            val range = Range(maxId = 10L, sinceId = 20L)
            assertThat(range.isNextPage()).isFalse()
        }
    }

    @Test
    fun isPrevPage() {
        run {
            val range = Range()
            assertThat(range.isPrevPage()).isFalse()
        }
        run {
            val range = Range(sinceId = 10L)
            assertThat(range.isPrevPage()).isTrue()
        }
        run {
            val range = Range(maxId = 10L)
            assertThat(range.isPrevPage()).isFalse()
        }
        run {
            val range = Range(maxId = 10L, sinceId = 20L)
            assertThat(range.isPrevPage()).isFalse()
        }
    }
}

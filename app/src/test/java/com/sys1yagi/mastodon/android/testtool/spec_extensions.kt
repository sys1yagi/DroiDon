package com.sys1yagi.mastodon.android.testtool

fun given(description: String, given: Given.() -> Unit) {
    given.invoke(Given())
}

class Given {
    fun on(description: String, on: On.() -> Unit) {
        on.invoke(On())
    }
}

class On {
    fun it(description: String, it: It.() -> Unit) {
        it.invoke(It())
    }
}

class It {

}

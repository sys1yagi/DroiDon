package com.sys1yagi.mastodon.android.ui.entrypoint

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.ActivityEntryPointBinding
import com.sys1yagi.mastodon.android.extensions.contentViewBinding
import com.sys1yagi.mastodon.android.extensions.gone
import com.sys1yagi.mastodon.android.extensions.visible
import dagger.android.AndroidInjection
import javax.inject.Inject

class EntryPointActivity : AppCompatActivity(), EntryPointContract.View {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, EntryPointActivity::class.java)
        }
    }

    @Inject
    lateinit var presenter: EntryPointContract.Presenter

    val binding: ActivityEntryPointBinding by contentViewBinding(R.layout.activity_entry_point)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        binding.apply {

        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun showMessage(message: String) {
        binding.message.text = message
    }

    override fun showError(message: String) {
        binding.message.text = "error:$message"
    }
}

package com.sys1yagi.mastodon.android.ui.auth.setinstancename

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.ActivitySetInstanceNameBinding
import com.sys1yagi.mastodon.android.extensions.contentViewBinding
import com.sys1yagi.mastodon.android.extensions.gone
import com.sys1yagi.mastodon.android.extensions.visible
import dagger.android.AndroidInjection
import javax.inject.Inject

class SetInstanceNameActivity : AppCompatActivity(), SetInstanceNameContract.View {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, SetInstanceNameActivity::class.java)
        }
    }

    @Inject
    lateinit var presenter: SetInstanceNameContract.Presenter

    val binding: ActivitySetInstanceNameBinding by contentViewBinding(R.layout.activity_set_instance_name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
        }
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

    override fun showError(message: String) {
        binding.progressBar.gone()
        binding.errorText.visible()
        binding.errorText.text = message
    }
}

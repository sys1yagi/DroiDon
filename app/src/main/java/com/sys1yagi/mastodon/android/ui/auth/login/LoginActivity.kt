package com.sys1yagi.mastodon.android.ui.auth.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxTextView
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.ActivityLoginBinding
import com.sys1yagi.mastodon.android.extensions.contentViewBinding
import com.sys1yagi.mastodon.android.extensions.getRequired
import com.sys1yagi.mastodon.android.extensions.gone
import com.sys1yagi.mastodon.android.extensions.visible
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    companion object {
        const val ARGS_INSTANCE_NAME = "instance_name"
        fun createIntent(context: Context, instanceName: String): Intent {
            return Intent(context, LoginActivity::class.java).apply {
                putExtra(ARGS_INSTANCE_NAME, instanceName)
            }
        }
    }

    @Inject
    lateinit var presenter: LoginContract.Presenter

    val instanceName by lazy<String> { intent.getRequired(ARGS_INSTANCE_NAME) }

    val binding: ActivityLoginBinding by contentViewBinding(R.layout.activity_login)

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.tag("moge").d("onCreate")
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Login"
        }
        binding.apply {
            startOauth.setOnClickListener {
                presenter.startOAuth()
            }
            getAccessToken.setOnClickListener {
                presenter.getAccessToken(code.text.toString())
            }
            RxTextView.afterTextChangeEvents(code)
                    .map { !it.editable().isNullOrEmpty() }
                    .subscribe {
                        getAccessToken.isEnabled = it
                    }
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

    override fun showLoginMessage(message: String) {
        binding.title.text = message
    }

    override fun showError(message: String) {
        binding.progressBar.gone()
        binding.errorText.visible()
//        binding.errorText.text = message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

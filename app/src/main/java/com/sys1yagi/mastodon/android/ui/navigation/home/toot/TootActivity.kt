package com.sys1yagi.mastodon.android.ui.navigation.home.toot

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.ActivityTootBinding
import com.sys1yagi.mastodon.android.extensions.contentViewBinding
import com.sys1yagi.mastodon.android.extensions.getRequired
import com.sys1yagi.mastodon.android.extensions.gone
import com.sys1yagi.mastodon.android.extensions.visible
import com.sys1yagi.mastodon.android.ui.navigation.NavigationActivity
import dagger.android.AndroidInjection
import io.reactivex.disposables.Disposables
import timber.log.Timber
import javax.inject.Inject

class TootActivity : AppCompatActivity(), TootContract.View {

    companion object {
        const val ARGS_INSTANCE_NAME = "instance_name"
        fun createIntent(context: Context, instanceName: String): Intent {
            return Intent(context, TootActivity::class.java).apply {
                putExtra(ARGS_INSTANCE_NAME, instanceName)
            }
        }
    }

    @Inject
    lateinit var presenter: TootContract.Presenter

    val primaryInstanceName by lazy<String> { intent.getRequired(NavigationActivity.ARGS_INSTANCE_NAME) }

    var disposable = Disposables.empty()

    val binding: ActivityTootBinding by contentViewBinding(R.layout.activity_toot)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.toot)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
        binding.apply {
            RxTextView.afterTextChangeEvents(status)
                    .map { it.editable()?.length ?: 0 }
                    .subscribe {
                        remain.text = "${500 - it}"
                    }

            toot.setOnClickListener {
                presenter.toot(status.text.toString())
            }
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
        disposable.dispose()
    }

    override fun onSupportNavigateUp(): Boolean {
        // TODO confirm when inputting
        finish()
        return true
    }

    override fun showError(message: String) {
        binding.progressBar.gone()
    }
}

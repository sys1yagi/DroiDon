package ${packageName}

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ${applicationId}.R
import ${applicationId}.databinding.Activity${className}Binding
import ${applicationId}.extensions.contentViewBinding
import ${applicationId}.extensions.gone
import ${applicationId}.extensions.visible
import dagger.android.AndroidInjection
import javax.inject.Inject

class ${className}Activity : AppCompatActivity(), ${className}Contract.View {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, ${className}Activity::class.java)
        }
    }

    @Inject
    lateinit var presenter: ${className}Contract.Presenter

    val binding: Activity${className}Binding by contentViewBinding(R.layout.activity${className?replace("[A-Z]", "_$0", "r")?lower_case})

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

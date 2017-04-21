package com.sys1yagi.mastodon.android.ui.navigation.settings

import android.support.v7.preference.PreferenceFragmentCompat
import android.widget.Toast
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.data.database.OrmaDatabaseProvider
import com.sys1yagi.mastodon.android.extensions.async
import javax.inject.Inject

class DebugSettingsInitializerImpl
@Inject
constructor(
        databaseProvider: OrmaDatabaseProvider
)
    : SettingsInitializerImpl() {

    val database = databaseProvider.database

    override fun initialize(fragment: PreferenceFragmentCompat) {
        super.initialize(fragment)
        val context = fragment.context
        fragment.addPreferencesFromResource(R.xml.preference_debug)

        val disposeTokenAndInstanceName = fragment.findPreference(context.getString(R.string.dispose_token_and_instance_name))
        disposeTokenAndInstanceName.setOnPreferenceClickListener {
            async {
                database.transactionAsCompletable {
                    database.deleteAll()
                }.await()
                Toast.makeText(context, "dispose", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }
}

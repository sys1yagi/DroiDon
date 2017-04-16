package com.sys1yagi.mastodon.android.ui.auth.setinstancename

import com.sys1yagi.kmockito.mock
import com.sys1yagi.kmockito.verify
import com.sys1yagi.mastodon.android.data.database.Credential
import com.sys1yagi.mastodon.android.data.database.OrmaDatabase
import com.sys1yagi.mastodon.android.data.database.OrmaDatabaseProvider
import com.sys1yagi.mastodon.android.testtool.RxTestSchedulerRule
import com.sys1yagi.mastodon.android.testtool.given
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class SetInstanceNameInteractorTest {

    @get:Rule
    val rule = RxTestSchedulerRule()

    lateinit var interactor: SetInstanceNameInteractor
    lateinit var output: SetInstanceNameContract.InteractorOutput
    lateinit var database: OrmaDatabase

    @Before
    fun setUp() {
        output = mock()
        database = OrmaDatabase.Builder(RuntimeEnvironment.application).name(null).build()
        interactor = SetInstanceNameInteractor(OrmaDatabaseProvider(database))
    }

    @After
    fun tearDown() {
        database.deleteAll()
    }

    @Test
    fun saveInstanceNameEmpty() {
        given("the credential records is empty") {
            assertThat(database.selectFromCredential().count()).isEqualTo(0)
            interactor.startInteraction(output)

            on("add instance name") {
                interactor.saveInstanceName("mstdn.jp")
                rule.triggerActions()

                it("onInstanceNameSaved() is called") {
                    output.verify().onInstanceNameSaved()
                }

                it("credential record is inserted") {
                    assertThat(database.selectFromCredential().count()).isEqualTo(1)

                }
            }
        }
    }

    @Test
    fun saveInstanceNameDuplicated(){
        given("there is a credential record") {
            database.insertIntoCredential(Credential().apply {
                instanceName = "mstdn.jp"
            })
            assertThat(database.selectFromCredential().count()).isEqualTo(1)
            interactor.startInteraction(output)

            on("insert same instance name credential") {
                interactor.saveInstanceName("mstdn.jp")
                rule.triggerActions()

                it("onInstanceNameSaved() is called") {
                    output.verify().onInstanceNameSaved()
                }

                it("credential record count is 1") {
                    assertThat(database.selectFromCredential().count()).isEqualTo(1)
                }
            }
        }
    }

    @Test
    fun saveInstanceNameMulti(){
        given("there is a credential record") {
            database.insertIntoCredential(Credential().apply {
                instanceName = "mstdn.jp"
            })
            assertThat(database.selectFromCredential().count()).isEqualTo(1)
            interactor.startInteraction(output)

            on("insert other instance name credential") {
                interactor.saveInstanceName("pawoo.net")
                rule.triggerActions()

                it("onInstanceNameSaved() is called") {
                    output.verify().onInstanceNameSaved()
                }

                it("credential record count is 2") {
                    assertThat(database.selectFromCredential().count()).isEqualTo(2)
                }
            }
        }
    }
}

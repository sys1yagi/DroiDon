package com.sys1yagi.mastodon.android.data.database

import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.PrimaryKey
import com.github.gfx.android.orma.annotation.Table

@Table("credential")
class Credential {
    @PrimaryKey(autoincrement = true)
    var id: Long = 0L

    @Column("instance_name", indexed = true)
    var instanceName: String = ""

    @Column("client_id")
    var clientId: String = ""

    @Column("client_secret")
    var clientSecret: String = ""

}

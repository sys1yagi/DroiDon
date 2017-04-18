package com.sys1yagi.mastodon.android.data.database

import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.OnConflict
import com.github.gfx.android.orma.annotation.PrimaryKey
import com.github.gfx.android.orma.annotation.Table

@Table("access_token")
class AccessToken {
    @PrimaryKey(autoincrement = true)
    var id: Long = 0L

    @Column("instance_name", indexed = true, unique = true, uniqueOnConflict = OnConflict.REPLACE)
    var instanceName: String = ""

    @Column("access_token")
    var accessToken: String = ""
}

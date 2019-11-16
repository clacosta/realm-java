package com.example.realmjava.model.migrations

import io.realm.DynamicRealm
import io.realm.RealmMigration

class Migration : RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {

        val schema = realm.schema

        var version = oldVersion

        if (version == 1L) {
            schema.create("Phone")
                .addField("id", Long::class.java)
                .addField("model", String::class.java)
                .addField("manufacturer", String::class.java)
                .addPrimaryKey("id")
            version++
        }

    }

}
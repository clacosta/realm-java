package com.example.realmjava.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Car() : RealmObject() {

    @PrimaryKey
    var id: Long = 0
    var model: String? = null
    var plate: String? = null

}
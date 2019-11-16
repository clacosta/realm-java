package com.example.realmjava.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.realmjava.R
import com.example.realmjava.model.Car
import com.example.realmjava.model.Person
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Random
        val nextInt = Random.nextInt(0, 10000)
        val nextDouble = Random.nextDouble(0.0, 2.0)

        //Realm init
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("person.realm")
            .deleteRealmIfMigrationNeeded()
            .build()
        val realm = Realm.getInstance(config)

        //Get Next Id
        var maxIdPerson: Number? = realm.where(Person::class.java).max("id")
        val nextIdPerson: Long = if (maxIdPerson == null) 1 else maxIdPerson.toLong() + 1

        //Create
        realm.beginTransaction()
        val person = realm.createObject(Person::class.java, nextIdPerson)
        person.name = "Claudio Costa"
        person.age = 39
        person.height = nextDouble
        realm.commitTransaction()

        //Read equalTo
        val equalTo = realm.where(Person::class.java)
            .equalTo("id", nextIdPerson.minus(1)).findFirst()
        equalTo?.let {
            Log.i("@@@", "equalTo: ${it.id} - ${it.name} - ${it.age} - ${it.height}")
        }

        //Update
        equalTo?.let {
            realm.beginTransaction()
            it.name = "Claudio Costa ($nextInt)"
            realm.commitTransaction()
        }

        //Read findAll
        val findAll = realm.where(Person::class.java)
            .findAll()
        findAll.forEach {
            Log.i("@@@", "findAll: ${it.id} - ${it.name} - ${it.age} - ${it.height}")
        }

        //Read lessThan
        val lessThan = realm.where(Person::class.java)
            .lessThan("id", 3)
            .findAll()
        lessThan.forEach {
            Log.i("@@@", "lessThan: ${it.id} - ${it.name} - ${it.age} - ${it.height}")
        }

        //Delete
        val deletePerson = realm.where(Person::class.java)
            .equalTo("id", nextIdPerson.minus(1)).findFirst()
        deletePerson?.let {
            realm.beginTransaction()
            it.deleteFromRealm()
            realm.commitTransaction()
        }

        //Get Next Id
        var maxIdCar: Number? = realm.where(Car::class.java).max("id")
        val nextIdCar: Long = if (maxIdCar == null) 1 else maxIdCar.toLong() + 1

        //Create
        realm.beginTransaction()
        val car = realm.createObject(Car::class.java, nextIdCar)
        car.model = "Carro ($nextInt)"
        car.plate = "$nextInt"
        realm.commitTransaction()

        //Read findAll
        val findAllCars = realm.where(Car::class.java)
            .findAll()
        findAllCars.forEach {
            Log.i("@@@", "findAll: ${it.id} - ${it.model} - ${it.plate}")
        }

    }

}

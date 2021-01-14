package com.nurulziyan.finalprojectoop_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nurulziyan.finalprojectoop_2.adapter.NurulAdapter
import com.nurulziyan.finalprojectoop_2.model.NurulUser
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.activity_nurul.*

class NurulActivity : AppCompatActivity() {
    lateinit var NurulAdapter:NurulAdapter
    lateinit var realm:Realm
    val lm =LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nurul)
        initview()

        btn_add.setOnClickListener {
            realm.beginTransaction()
            var count =0
            realm.where(NurulUser::class.java).findAll().let {
                for (i in it){
                    count ++
                }
            }
            try {
                val user = realm.createObject(NurulUser::class.java)
                user.setId(count+1)
                user.setNama(et_nama.text.toString())
                user.setNim(et_nim.text.toString())
                et_nama.setText("")
                et_nim.setText("")
                realm.commitTransaction()
                getAllUser()
            }catch (e: RealmException){

            }
        }
        btn_update.setOnClickListener {
            realm.beginTransaction()
            realm.where(NurulUser::class.java).equalTo("id",et_id.text.toString().toInt()).findFirst().let {
                it!!.setNama(et_nama.text.toString())
                it!!.setNim(et_nim.text.toString())
            }
            realm.commitTransaction()
            getAllUser()
        }

        btn_delete.setOnClickListener {
            realm.beginTransaction()
            realm.where(NurulUser::class.java).equalTo("id",et_id.text.toString().toInt()).findFirst().let {
                it!!.deleteFromRealm()
            }
            realm.commitTransaction()
            getAllUser()
        }

    }
    private fun initview(){
        rv_user.layoutManager = lm
        NurulAdapter = NurulAdapter(this)
        rv_user.adapter = NurulAdapter
        Realm.init(applicationContext)
        realm = Realm.getDefaultInstance()
        getAllUser()
    }
    private fun getAllUser(){
        realm.where(NurulUser::class.java).findAll().let {
            NurulAdapter.setUser(it)
        }
    }
}
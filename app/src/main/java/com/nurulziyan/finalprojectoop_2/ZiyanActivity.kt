package com.nurulziyan.finalprojectoop_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nurulziyan.finalprojectoop_2.adapter.ZiyanAdapter
import com.nurulziyan.finalprojectoop_2.model.ZiyanDosen
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.activity_ziyan.*

class ZiyanActivity : AppCompatActivity() {
    lateinit var ZiyanAdapter: ZiyanAdapter //
    lateinit var realm: Realm //pemanggilan library realm
    val lm = LinearLayoutManager(this)//defaul dari adapter , contex this karena konteknnya beda-beda
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ziyan)
        initview()

        btn_add.setOnClickListener {//botton untuk tambah data
            realm.beginTransaction()
            var count = 0
            realm.where(ZiyanDosen::class.java).findAll().let {
                for (i in it) {
                    count++
                }
            }
            try {

                if(validate()){
                    val dosens = realm.createObject(ZiyanDosen::class.java)
                    val nama = et_nama.text.toString().trim()
                    val nipy = et_nipy.text.toString().trim()
                    dosens.setId(count + 1)
                    dosens.setNama(nama)
                    dosens.setNipy(nipy)
                    et_nama.setText("")//untuk menginputkan edit data
                    et_nipy.setText("")
                    realm.commitTransaction()
                    getAllDosen()
                }

            } catch (e: RealmException) {

            }
        }

        btn_update.setOnClickListener {
            realm.beginTransaction()
            realm.where(ZiyanDosen::class.java).equalTo("id",et_id.text.toString().toInt()).findFirst().let { it ->
                it!!.setNama(et_nama.text.toString())
                it!!.setNipy(et_nipy.text.toString())//it !! =
            }

            realm.commitTransaction()
            getAllDosen() //
        }

        btn_delete.setOnClickListener {
            realm.beginTransaction()
            realm.where(ZiyanDosen::class.java).equalTo("id", et_id.text.toString().toInt()).findFirst().let {
                it!!.deleteFromRealm()
            }
            realm.commitTransaction()
            getAllDosen()
        }
    }

    private fun initview() {
        //mengatur tampilan recyclerview
        rv_dosen.layoutManager = lm
        ZiyanAdapter = ZiyanAdapter(this)
        rv_dosen.adapter = ZiyanAdapter
        Realm.init(applicationContext)
        realm = Realm.getDefaultInstance()
        getAllDosen()

    }

    private fun getAllDosen() { //menyambungkan  antara tabel dan adapter
        realm.where(ZiyanDosen::class.java).findAll().let {
            ZiyanAdapter.setDosen(it)
        }
    }

    private fun setNameError(err: String?){et_nama.error = err}
    private fun setNIPYError(err: String?){et_nipy.error = err}

    private fun validate() : Boolean { //memastikan nama dan nipy harus diisi
        setNameError(null)
        setNIPYError(null)

        if(et_nama.text.toString().trim().isEmpty()){
            setNameError("Nama tidak boleh kosong!")
            return false
        }

        if(et_nipy.text.toString().trim().isEmpty()){
            setNIPYError("NIPY tidak boleh kosong")
            return false
        }

        return true
    }
}
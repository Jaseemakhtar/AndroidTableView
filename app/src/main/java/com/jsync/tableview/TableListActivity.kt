package com.jsync.tableview

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import io.realm.annotations.RealmModule

class TableListActivity : ListActivity() {
    companion object{
        const val REALM_OBJECT_NAME = "ron"
    }

    lateinit var list: List<String>
    lateinit var dbClass: Class<*>
    lateinit var listQualifiedNames: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_list)
/*
        when(intent.getStringExtra(DatabaseActivity.WHICH_DB)){
            DatabaseActivity.CALLS_DB -> dbClass = CallsRealmModule::class.java

            DatabaseActivity.CONTACTS_DB -> dbClass = ContactsRealmModule::class.java

            DatabaseActivity.PROFILE_DB -> dbClass = ProfileRealmModule::class.java

            DatabaseActivity.AUX_DB -> dbClass = AuxiliaryRealmModule::class.java
        }*/

        getClassNames()
        val arrayAdapter = ArrayAdapter<String>(this, R.layout.tables_list_row, R.id.txtViewTableName, list)
        listAdapter = arrayAdapter
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        val dbViewIntent = Intent(this, TableDataViewActivity::class.java)
        dbViewIntent.putExtra(REALM_OBJECT_NAME, listQualifiedNames[position])
        startActivity(dbViewIntent)
    }

    private fun getClassNames() {
        list = mutableListOf()
        listQualifiedNames = mutableListOf()
        val annotation = dbClass.getAnnotation(RealmModule::class.java)
        annotation?.let{
            it.classes.forEach {itCl ->
                itCl.let { it1 ->
                    it1.simpleName?.let { it2 -> (list as MutableList<String>).add(it2) }
                    it1.qualifiedName?.let{ it3 -> (listQualifiedNames as MutableList<String>).add(it3)}
                }
            }
        }
    }
}

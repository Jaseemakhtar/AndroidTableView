package com.jsync.tableview

import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.qtalk.app.debug.TableListActivity

class TableDataViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_data_view)

        val tableName = intent.getStringExtra(TableListActivity.REALM_OBJECT_NAME)
        val classTable =  classLoader.loadClass(tableName)

        classTable.declaredFields.forEach {
            createHeaderCell(it.toString().substring(it.toString().lastIndexOf('.') + 1))
        }


    }

    private fun createHeaderCell(txt: String){

        val r = resources
        val px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                4f,
                r.displayMetrics
        ).toInt()

        val cell = AppCompatTextView(this)
        val layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(px, px, px, px)
        cell.layoutParams = layoutParams
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cell.setTextAppearance(android.R.style.TextAppearance_Medium)
        }else{
            cell.setTextAppearance(this, android.R.style.TextAppearance_Medium)
        }
        cell.setTypeface(cell.typeface, Typeface.BOLD)
        cell.text = txt
        cell.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray))
        //headerLayoutRow.addView(cell)
    }
}

package com.example.comicreaderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.example.comicreaderapp.Common.Common
import com.example.comicreaderapp.adapter.MyComicAdapter
import com.example.comicreaderapp.models.Comic
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.activity_search_filter.*
import kotlinx.android.synthetic.main.filter.*
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList

class SearchFilterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_filter)

        bottom_bar.inflateMenu(R.menu.main_menu)
        bottom_bar.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId)
            {
                R.id.action_filter -> showOptionDialog()
                R.id.action_search -> showSearchDialog()
            }
            true
        }

        recycle_filter_search.setHasFixedSize(true)
        recycle_filter_search.layoutManager = GridLayoutManager(this, 2)
    }

    private fun showSearchDialog() {
        val alertDialog = AlertDialog.Builder(this@SearchFilterActivity)
        alertDialog.setTitle("Search name")

        val inflater = this.layoutInflater
        val search_layout = inflater.inflate(R.layout.search, null)

        val edt_search = search_layout.findViewById<View>(R.id.edt_search) as EditText

        alertDialog.setView(search_layout)

        alertDialog.setNegativeButton("Cancle", {dialogInterface, i-> dialogInterface.dismiss()} )

        alertDialog.setPositiveButton("Confirm", { dialogInterface, i ->
            fetchSearch(edt_search.text.toString())
        })
        alertDialog.show()

    }

    private fun fetchSearch(name: String) {

        val comic_search = ArrayList<Comic>()
        for (comic in Common.comicList)
        {
            if (comic.Name != null)
            {
                if(comic.Name!!.uppercase().contains(name.uppercase()))
                    comic_search.add(comic)
            }
        }

        if (comic_search.size>0)
        {
            recycle_filter_search.adapter = MyComicAdapter(this@SearchFilterActivity, comic_search)
        }
        else
        {
            Toast.makeText(this@SearchFilterActivity, "No result", Toast.LENGTH_SHORT).show()
        }

    }

    private fun showOptionDialog() {

        val alertDialog = AlertDialog.Builder(this@SearchFilterActivity)
        alertDialog.setTitle("Select category")

        val inflater = this.layoutInflater
        val filter_layout = inflater.inflate(R.layout.filter, null)

        val chipGroup = filter_layout.findViewById<View>(R.id.chip_group) as ChipGroup

        val autoCompleteView = filter_layout.findViewById<View>(R.id.edt_filter) as AutoCompleteTextView
        autoCompleteView.threshold = 3
        autoCompleteView.setAdapter(ArrayAdapter(this, android.R.layout.select_dialog_item, Common.categories))

        autoCompleteView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            // clear text
            autoCompleteView.setText("")

            //add chip

            val chip = inflater.inflate(R.layout.chip_item, null, false) as Chip
            chip.text = (view as TextView).text
            chip.setOnCloseIconClickListener{view -> chip_group.removeView(view)}

            chipGroup.addView(chip)

        }

        alertDialog.setView(filter_layout)
        alertDialog.setNegativeButton("Cancle", {dialogInterface, i-> dialogInterface.dismiss()} )

        alertDialog.setPositiveButton("Confirm",  {dialogInterface, i->
            val filter_key = ArrayList<String>()
            val filter_query = StringBuilder("")
            for(j in 0 until chipGroup.childCount)
            {
                val chip = chipGroup.getChildAt(j) as Chip
                filter_key.add(chip.text.toString())

            }

            //sort after get key

            Collections.sort(filter_key)

            for (key in filter_key)
                filter_query.append(key).append(",")

            filter_query.setLength(filter_query.length - 1)

            fetchFilterCategory(filter_query.toString())

        })

        alertDialog.show()

    }

    private fun fetchFilterCategory(filter_query: String) {
        val comic_filter = ArrayList<Comic>()
        for(comic in Common.comicList)
        {
            if(comic.Category!= null)
            {
                if (comic.Category!!.contains(filter_query))
                    comic_filter.add(comic)
            }
        }
        if (comic_filter.size > 0)
        {
            recycle_filter_search.adapter = MyComicAdapter(this@SearchFilterActivity, comic_filter)

        }
        else
        {
            Toast.makeText(this@SearchFilterActivity, "No result", Toast.LENGTH_SHORT).show()
        }

    }
}

package com.example.comicreaderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.comicreaderapp.Common.Common
import com.example.comicreaderapp.Interface.IBannerLoadDoneListener
import com.example.comicreaderapp.Interface.IComicLoadDoneListener
import com.example.comicreaderapp.adapter.MyComicAdapter
import com.example.comicreaderapp.models.Comic
import com.example.comicreaderapp.service.PicassoImageLoadingService
import com.google.firebase.database.*
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import ss.com.bannerslider.Slider

class MainActivity : AppCompatActivity(), IBannerLoadDoneListener, IComicLoadDoneListener {

    //DB
    internal lateinit var banner_ref : DatabaseReference
    lateinit var comic_ref : DatabaseReference

    //Listener

    lateinit var iIBannerLoadDoneListener : IBannerLoadDoneListener
    lateinit var iComicLoadDoneListener: IComicLoadDoneListener

    lateinit var alertDialog:android.app.AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init Listener
        iIBannerLoadDoneListener = this
        iComicLoadDoneListener = this

        //init dialog

        alertDialog = SpotsDialog.Builder().setContext(this@MainActivity)
            .setCancelable(false)
            .setMessage("Please wait")
            .build()

        //get DB
        banner_ref = FirebaseDatabase.getInstance().getReference("Banners")
        comic_ref = FirebaseDatabase.getInstance().getReference("Comic")

        swipe_to_refresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark)
        swipe_to_refresh.setOnRefreshListener {
            loadBanner()
            loadComic()
        }
        swipe_to_refresh.post{
            loadBanner()
            loadComic()
        }

        Slider.init(PicassoImageLoadingService())

        recycle_comic.setHasFixedSize(true)
        recycle_comic.layoutManager = GridLayoutManager(this@MainActivity, 2)

        btn_search.setOnClickListener{
            startActivity(Intent(this@MainActivity, SearchFilterActivity::class.java))
        }

    }

    private fun loadComic() {

        alertDialog.show()
        comic_ref.addListenerForSingleValueEvent(object:ValueEventListener{
            var comic_load: MutableList<Comic> = ArrayList<Comic>()
            override fun onDataChange(snapshot: DataSnapshot) {
                for(comicsnapshot in snapshot.children)
                {
                    val comic = comicsnapshot.getValue(Comic::class.java)
                    comic_load.add(comic!!)
                }
                iComicLoadDoneListener.OnComicLoadDoneListener(comic_load)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity,"" + error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun loadBanner() {
        banner_ref.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val banner_list = ArrayList<String>()

                for (banner in snapshot.children){
                    val image = banner.getValue(String::class.java)
                    banner_list.add(image!!)
                }

                iIBannerLoadDoneListener.OnBannerLoadDoneListener(banner_list)

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity,"" + error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun OnBannerLoadDoneListener(banners: List<String>) {
//        sliderMain.setAdapter(MySliderAdapter(banners))
    }

    override fun OnComicLoadDoneListener(comicList: List<Comic>) {
        alertDialog.dismiss()

        Common.comicList= comicList

        recycle_comic.adapter = MyComicAdapter(baseContext,comicList)
        txt_comic.text = StringBuilder("New Comic (")
            .append(comicList.size)
            .append(")")

        if (swipe_to_refresh.isRefreshing)
            swipe_to_refresh.isRefreshing = false
    }

}
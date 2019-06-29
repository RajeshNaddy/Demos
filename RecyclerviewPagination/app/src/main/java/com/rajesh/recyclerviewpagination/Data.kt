package com.rajesh.recyclerviewpagination

import com.rajesh.recyclerviewpagination.pagination.Entity
import android.graphics.Movie



/**
 * Created by Rajesh on 25/06/2019
 **/

data class Data(val title : String) : Entity(){
    constructor() : this("")

    /**
     * Creating 10 dummy content for list.
     *
     * @param itemCount
     * @return
     */

    companion object{

        fun createDummyDatas(itemCount: Int): List<Data> {
            val datas = ArrayList<Data>()
            for (i in 0..9) {
                val movie = Data(
                    ("Data ${if(itemCount == 0 ) (itemCount + 1 + i).toString()
                    else (itemCount + i).toString()}")
                )
                datas.add(movie)
            }
            return datas
        }
    }

}
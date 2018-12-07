package com.condorlabs.popularmovies

import android.support.v7.widget.RecyclerView

/**
 * Created by gustavofc97 on 9/11/2018.
 */

abstract class BaseAdapter<Type : RecyclerView.ViewHolder, Data> : RecyclerView.Adapter<Type>() {

    abstract fun setData(data: List<Data>)
}

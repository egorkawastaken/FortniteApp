package main.common.base.recycler

import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<Data>(v: View) : RecyclerView.ViewHolder(v) {

    protected var currentItem: Data? = null

    @CallSuper
    open fun bind(data: Data) {
        currentItem = data
    }
}

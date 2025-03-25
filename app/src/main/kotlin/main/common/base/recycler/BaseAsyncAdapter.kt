package main.common.base.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAsyncAdapter<T, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : RecyclerView.Adapter<VH>() {

    private val differ = AsyncListDiffer(this, diffCallback)

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
    abstract override fun onBindViewHolder(holder: VH, position: Int)

    open fun getItems(): List<T> = differ.currentList
    open fun getItem(position: Int): T = getItems()[position]
    open fun getItemOrNull(position: Int): T? = getItems().getOrNull(position)
    override fun getItemCount(): Int = differ.currentList.size

    /**
     * Устанавливает новый список элементов с анимацией DiffUtil
     */
    open fun setItems(items: List<T>) = differ.submitList(items)

    /**
     * Устанавливает новый список элементов и выполняет [block] после завершения обновления
     */
    open fun setItems(items: List<T>, block: () -> Unit) {
        differ.submitList(items, block)
    }
}
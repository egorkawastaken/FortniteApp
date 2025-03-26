package main.presentation.profile.profile.adapter.stats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.fortniteapp.databinding.VStatsBinding
import main.common.base.recycler.BaseAsyncAdapter
import main.common.base.recycler.BaseViewHolder
import main.presentation.profile.model.ModeStatsUiModel
import java.util.Locale

class StatsAdapter : BaseAsyncAdapter<ModeStatsUiModel, StatsViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val binding = VStatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<ModeStatsUiModel>() {
            override fun areItemsTheSame(oldItem: ModeStatsUiModel, newItem: ModeStatsUiModel): Boolean {
                return oldItem.stat == newItem.stat
            }

            override fun areContentsTheSame(oldItem: ModeStatsUiModel, newItem: ModeStatsUiModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class StatsViewHolder(private val binding: VStatsBinding) : BaseViewHolder<ModeStatsUiModel>(binding.root) {

    override fun bind(data: ModeStatsUiModel) {
        super.bind(data)
        binding.title.text = data.stat
        binding.value.text = String.format(Locale.getDefault(), "%d", data.value)
    }
}

package main.presentation.profile.profile.adapter.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fortniteapp.databinding.VStatsCardBinding
import main.common.base.recycler.BaseAsyncAdapter
import main.common.base.recycler.BaseViewHolder
import main.presentation.profile.model.GameModeStatsUiModel
import main.presentation.profile.profile.adapter.stats.StatsAdapter

class StatsCardsAdapter : BaseAsyncAdapter<GameModeStatsUiModel, StatsCardViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsCardViewHolder {
        val binding = VStatsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatsCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatsCardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<GameModeStatsUiModel>() {
            override fun areContentsTheSame(oldItem: GameModeStatsUiModel, newItem: GameModeStatsUiModel): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: GameModeStatsUiModel, newItem: GameModeStatsUiModel): Boolean {
                return oldItem.mode == newItem.mode
            }

            override fun getChangePayload(oldItem: GameModeStatsUiModel, newItem: GameModeStatsUiModel): Any = Any()
        }
    }
}

class StatsCardViewHolder(private val binding: VStatsCardBinding) : BaseViewHolder<GameModeStatsUiModel>(binding.root) {

    private val statsAdapter = StatsAdapter()

    init {
        binding.statsRecycler.apply {
            adapter = statsAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)
            isNestedScrollingEnabled = false
        }
    }

    override fun bind(data: GameModeStatsUiModel) {
        super.bind(data)
        binding.statsTitle.text = data.mode
        statsAdapter.setItems(data.stats)
    }
}
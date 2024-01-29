package dev.subfly.baseandroidxmlktor.featureHome.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.subfly.baseandroidxmlktor.R
import dev.subfly.baseandroidxmlktor.core.util.getIcon
import dev.subfly.baseandroidxmlktor.databinding.LayoutCoinCardBinding
import dev.subfly.baseandroidxmlktor.core.domain.model.CoinHomeModel

class HomeContentAdapter(
    private val data: List<CoinHomeModel>,
    private val onClickItem: (String, String, Boolean) -> Unit,
) : RecyclerView.Adapter<HomeContentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutCoinCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            val model = data[position]
            layoutCoinCardInformationCoinName.text = model.name
            layoutCoinCardInformationCoinSymbol.text = model.symbol
            layoutCoinCardInformationCoinType.setImageResource(model.type.getIcon())
            layoutCoinCardActivityIndicator.setImageResource(
                if (model.isActive)
                    R.drawable.green_circle
                else
                    R.drawable.gray_circle

            )
            root.setOnClickListener {
                onClickItem(model.id, model.symbol, model.isActive)
            }
        }
    }

    inner class ViewHolder(
        val binding: LayoutCoinCardBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    )
}

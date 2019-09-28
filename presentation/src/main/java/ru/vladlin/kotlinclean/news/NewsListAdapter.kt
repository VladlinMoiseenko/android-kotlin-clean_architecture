package ru.vladlin.kotlinclean.news

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import ru.vladlin.kotlinclean.entities.NewsPublisher
import kotlinx.android.synthetic.main.item.view.*
import ru.vladlin.kotlinclean.R

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    var articles = mutableListOf<NewsPublisher>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(newsPublisherItem: NewsPublisher) {
            with(itemView) {
                title.text = newsPublisherItem.title
                author.text = newsPublisherItem.author
                description.text = newsPublisherItem.description
                Glide
                    .with(this)
                    .load(newsPublisherItem.urlToImage)
                    .into(urlToImage);

            }
        }
    }

    fun updateList(list: List<NewsPublisher>) {
        if (list.isNotEmpty()) {
            articles.clear()
            articles.addAll(list)
            notifyDataSetChanged()
        }
    }
}
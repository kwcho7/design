package kr.co.cools.coolsui.header_deco

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_header_deco.*
import kotlinx.android.synthetic.main.item_content.view.*
import kotlinx.android.synthetic.main.item_header.view.*
import kr.co.cools.coolsui.R
import kr.co.cools.design.deco.HeaderDecoration

class HeaderDecoActivity: AppCompatActivity() {

    companion object {
        val TAG = "HeaderDecoActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header_deco)
        recyclerView.adapter = ViewAdapter(listOf(
            Item.HeaderItem("title - 1"),
            Item.ContentItem("content - 1 - 1", "desc - 1"),
            Item.ContentItem("content - 1 - 2", "desc - 1"),
            Item.ContentItem("content - 1 - 3", "desc - 1"),


            Item.HeaderItem("title - 2"),
            Item.ContentItem("content - 2 - 1", "desc - 1"),
            Item.ContentItem("content - 2 - 2", "desc - 1"),
            Item.ContentItem("content - 2 - 3", "desc - 1")

            ,
            Item.HeaderItem("title - 3"),
            Item.ContentItem("content - 3 - 1", "desc - 1"),
            Item.ContentItem("content - 3 - 2", "desc - 1"),
            Item.ContentItem("content - 3 - 3", "desc - 1")
            ,
            Item.HeaderItem("title - 4"),
            Item.ContentItem("content - 4 - 1", "desc - 1"),
            Item.ContentItem("content - 4 - 2", "desc - 1"),
            Item.ContentItem("content - 4 - 3", "desc - 1")

            ,
            Item.HeaderItem("title - 5"),
            Item.ContentItem("content - 4 - 1", "desc - 1"),
            Item.ContentItem("content - 4 - 2", "desc - 1"),
            Item.ContentItem("content - 4 - 3", "desc - 1")

            ,
            Item.HeaderItem("title - 6"),
            Item.ContentItem("content - 4 - 1", "desc - 1"),
            Item.ContentItem("content - 4 - 2", "desc - 1"),
            Item.ContentItem("content - 4 - 3", "desc - 1")

            ,
            Item.HeaderItem("title - 7"),
            Item.ContentItem("content - 4 - 1", "desc - 1"),
            Item.ContentItem("content - 4 - 2", "desc - 1"),
            Item.ContentItem("content - 4 - 3", "desc - 1")


        ))
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter?.notifyDataSetChanged()

        recyclerView.addItemDecoration(object: HeaderDecoration() {
            override fun createHeaderViewHolder(context: Context, parent: RecyclerView, position: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(context).inflate(R.layout.item_header, parent, false) as ViewGroup
                return HeaderViewHolder(view).apply {
                    bind((parent.adapter as ViewAdapter).itemList[position] as Item.HeaderItem)
                }
            }

            override fun isHeaderItem(recyclerView: RecyclerView, position: Int): Boolean {
                return (recyclerView.adapter as ViewAdapter).itemList[position] is Item.HeaderItem
            }

        })
    }

    sealed class Item(val type: Type){
        class HeaderItem(val title: String): Item(Type.Header)
        class ContentItem(val title: String, val desc: String): Item(Type.Content)
    }

    enum class Type(val value: Int){
        Header(0),
        Content(1)
    }

    abstract class ItemViewHolder<T:Item>(rootView: ViewGroup): RecyclerView.ViewHolder(rootView) {
        abstract fun bind(item: T)
    }


    class ContentViewHolder(rootView: ViewGroup): ItemViewHolder<Item.ContentItem>(rootView) {
        override fun bind(item: Item.ContentItem){
            itemView.contentTextView.text = item.title
            itemView.descTextView.text = item.desc
        }
    }

    class HeaderViewHolder(rootView: ViewGroup): ItemViewHolder<Item.HeaderItem>(rootView) {
        override fun bind(item: Item.HeaderItem) {
            itemView.headerTitleTextView.text = item.title
        }
    }

    class ViewAdapter(val itemList: List<Item>): RecyclerView.Adapter<ItemViewHolder<*>>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder<*> {
            when(p1){
                Type.Header.value -> {
                    val view = LayoutInflater.from(p0.context).inflate(R.layout.item_header, p0, false) as ViewGroup
                    return HeaderViewHolder(view)
                }
                // content
                else -> {
                    val view = LayoutInflater.from(p0.context).inflate(R.layout.item_content, p0, false) as ViewGroup
                    return ContentViewHolder(view)
                }
            }
        }

        override fun getItemCount(): Int {
            return itemList.size
        }

        override fun getItemViewType(position: Int): Int {
            return itemList[position].type.value
        }

        override fun onBindViewHolder(p0: ItemViewHolder<*>, p1: Int) {
            when(p0){
                is HeaderViewHolder -> {
                    p0.bind(itemList[p1] as Item.HeaderItem)
                }
                is ContentViewHolder -> {
                    p0.bind(itemList[p1] as Item.ContentItem)
                }
            }
        }
    }
}
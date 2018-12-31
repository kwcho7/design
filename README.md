# design

# Header RecyclerView 
<img src=https://user-images.githubusercontent.com/44988459/50554513-01543380-0cff-11e9-9388-347a8bddd8ff.gif width=360/>

```java
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
```

viewHolder
```java
    class HeaderViewHolder(rootView: ViewGroup): ItemViewHolder<Item.HeaderItem>(rootView) {
        override fun bind(item: Item.HeaderItem) {
            itemView.headerTitleTextView.text = item.title
        }
    }
```


# Circular Progress
<img src=https://user-images.githubusercontent.com/44988459/50554525-57c17200-0cff-11e9-81bc-73791137485e.gif width=360/>

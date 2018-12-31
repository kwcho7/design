# design

# Header RecyclerView 
<img src=https://user-images.githubusercontent.com/44988459/50554513-01543380-0cff-11e9-9388-347a8bddd8ff.gif width=360/>

```java
    
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

        recyclerView.addItemDecoration(headerDecoration)
    }

    override fun onDestroy() {
        super.onDestroy()
        recyclerView.removeItemDecoration(headerDecoration)
    }

    class HeaderDecorationImpl: HeaderDecoration() {
        override fun createHeaderViewHolder(context: Context, parent: RecyclerView, position: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item_header, parent, false) as ViewGroup
            return HeaderViewHolder(view).apply {
                bind((parent.adapter as ViewAdapter).itemList[position] as Item.HeaderItem)
            }
        }

        override fun isHeaderItem(recyclerView: RecyclerView, position: Int): Boolean {
            return (recyclerView.adapter as ViewAdapter).itemList[position] is Item.HeaderItem
        }
    }
```

# Circular Progress
<img src=https://user-images.githubusercontent.com/44988459/50554525-57c17200-0cff-11e9-81bc-73791137485e.gif width=360/>

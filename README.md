# design

# Header RecyclerView 
<img src=https://user-images.githubusercontent.com/44988459/50554513-01543380-0cff-11e9-9388-347a8bddd8ff.gif width=360/>

```java
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header_deco)
        :
        :
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

```java
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        circular.min = 0
        circular.max = 100
        circular.setStrokeColors(intArrayOf(Color.BLUE, Color.CYAN, Color.BLUE), floatArrayOf(0f, 0.5f, 1f))

        seekBar.max = circular.max

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                circular.setValue(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }
````

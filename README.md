# design

Gradle
```gradle 
repositories {
    maven{
        url = 'https://dl.bintray.com/jeffjo/Design'
    }
}


dependencies {
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'

    implementation 'com.android.support:recyclerview-v7:28.0.0'
    implementation 'kr.co.cools.ui:design:0.0.1'
}

```

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


xml
```xml
    <kr.co.cools.design.progress.CircularProgress
            android:layout_width="200dp"
            android:layout_height="200dp"
            app:layout_constraintEnd_toEndOf="parent"
            android:layout_marginEnd="8dp" app:layout_constraintStart_toStartOf="parent"
            android:layout_marginStart="8dp" android:layout_marginTop="8dp" app:layout_constraintTop_toTopOf="parent"
            android:id="@+id/circular"/>
```

Available attribute for CircularProgress
```xml
    <declare-styleable name="CircularProgress">
        <attr name="strokeWidth" format="dimension"/>
        <attr name="strokeColor" format="color" />
        <attr name="strokeBgColor" format="color" />
        <attr name="minValue" format="integer" />
        <attr name="maxValue" format="integer" />
    </declare-styleable>
```


# License

    Copyright 2018 JeffJo

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

package kr.co.cools.design.deco

import android.content.Context
import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import java.lang.ref.WeakReference

abstract class HeaderDecoration: RecyclerView.ItemDecoration(){

    private var weakReference: WeakReference<RecyclerView.ViewHolder>? = null
    private var headerIndex = -1

    abstract fun createHeaderViewHolder(context: Context, parent: RecyclerView, position: Int): RecyclerView.ViewHolder
    abstract fun isHeaderItem(recyclerView: RecyclerView, position: Int): Boolean

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.getChildAt(0)?.also {
            parent.findContainingViewHolder(it)?.also { vh ->
                var position = vh.adapterPosition
                while(position >= 0){
                    if(isHeaderItem(parent, position)){
                        if(position != headerIndex){
                            weakReference = WeakReference(
                                    createHeaderViewHolder(parent.context, parent, position).apply {
                                        fixLayoutSize(parent, itemView)
                                    }
                            )
                            headerIndex = position
                        }
                        break
                    }
                    position--
                }
            }
        }
        var translateY = 0f
        parent.getChildAt(1)?.let {
            val viewHolder = parent.findContainingViewHolder(it)
            viewHolder?.let { vh ->
                val position = vh.adapterPosition
                if(isHeaderItem(parent, position)){
                    val moveY = (viewHolder.itemView.top - viewHolder.itemView.height).toFloat()
                    if(moveY < 0){
                        translateY = moveY
                    }
                }
            }
        }

        weakReference?.also { ref ->
            ref.get()?.also { viewHolder ->
                c.save()
                c.translate(0f, translateY)
                viewHolder.itemView.draw(c)
                c.restore()
            }
        }
    }

    /**
     * Properly measures and layouts the top sticky header.
     * @param parent ViewGroup: RecyclerView in this case.
     */
    private fun fixLayoutSize(parent: ViewGroup, view: View) {
        // Specs for parent (RecyclerView)
        val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)

        // Specs for children (headers)
        val childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec, parent.paddingLeft + parent.paddingRight, view.layoutParams.width)
        val childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec, parent.paddingTop + parent.paddingBottom, view.layoutParams.height)

        view.measure(childWidthSpec, childHeightSpec)

        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight())
    }
}

package routertest.skateboard.com.headerrecyclerview

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup

/**
 * Created by skateboard on 2018/2/6.
 */
class AdapterWraper<T : ViewHolder> : Adapter<ViewHolder>
{

    private lateinit var adapter: Adapter<T>

    private var headViews: SparseArray<View> = SparseArray(3)

    private var footViews: SparseArray<View> = SparseArray(3)

    val TYPE_DATA: Int = 100

    constructor(adapter: Adapter<T>)
    {
        this.adapter = adapter
    }


    fun addHeaderView(headView: View)
    {
        headViews.append(headViews.size(), headView)
    }

    fun addFootView(footView: View)
    {
        footViews.append(footViews.size(), footView)
    }


    override fun getItemViewType(position: Int): Int
    {
        return when
        {
            isHeadView(position) -> position
            isFootView(position) -> position
            else -> TYPE_DATA
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder
    {
        return when
        {
            isHeadView(viewType) -> WraperViewHolder(headViews[viewType])
            isFootView(viewType) -> WraperViewHolder(footViews[viewType-adapter.itemCount-headViews.size()])
            else -> adapter.onCreateViewHolder(parent, 0)
        }

    }


    private fun isHeadView(position: Int): Boolean
    {
        if (position < headViews.size())
        {
            return true
        }

        return false
    }

    private fun isFootView(position: Int): Boolean
    {

        if (position >= (headViews.size() + adapter.itemCount))
        {
            return true
        }

        return false
    }


    override fun getItemCount(): Int
    {
        return adapter.itemCount + headViews.size() + footViews.size()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {

        if (TYPE_DATA == getItemViewType(position))
        {
            adapter.onBindViewHolder(holder as T, position-headViews.size())
        }


    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?)
    {
        super.onAttachedToRecyclerView(recyclerView)
        val layoutManager=recyclerView?.layoutManager
        if(layoutManager!=null)
        {
            if(layoutManager is GridLayoutManager )
            {
                layoutManager.spanSizeLookup= object : GridLayoutManager.SpanSizeLookup()
                {
                    override fun getSpanSize(position: Int): Int
                    {
                        if(isHeadView(position))
                        {
                            return layoutManager.spanCount
                        }
                        else if(isFootView(position))
                        {
                            return layoutManager.spanCount
                        }
                        else
                        {
                            return 1
                        }
                    }
                }
            }

        }

    }

    override fun onViewAttachedToWindow(holder: ViewHolder?)
    {
        super.onViewAttachedToWindow(holder)
        if(holder!=null)
        {
            val position = holder.layoutPosition
            if(isHeadView(position) || isFootView(position))
            {

                val lp=holder.itemView.layoutParams
                if(lp!=null && lp is StaggeredGridLayoutManager.LayoutParams)
                {
                    lp.isFullSpan=true
                }
            }


        }

    }
}
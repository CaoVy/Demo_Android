package com.example.demo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_second2.*

class SecondFragment : Fragment() {

    private var mutableList = mutableListOf<ItemData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageNews = mutableListOf(
            R.drawable.ic_airplane_foreground,
            R.drawable.ic_baseline_train_24,
            R.drawable.ic_airplane_foreground,
            R.drawable.ic_airplane_foreground,
            R.drawable.ic_baseline_directions_boat_24,
            R.drawable.ic_airplane_foreground
        )

        val textNews = mutableListOf(
            "Hawaii -> SeVilla",
            "SeVilla -> Monaco",
            "Atlanta -> Marrakech",
            "Gevena -> Mexico City",
            "Reykjavik -> SeVilla",
            "Hawaii -> Reykjavik"
        )

        val adapters = ItemAdapter(mutableList)
        secondRecyclerView.adapter = adapters

        getShowItem(imageNews, textNews)

        val itemTouch = object : ItemTouchHelper.SimpleCallback(0,
            0) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val position = viewHolder.adapterPosition
                adapters.notifyItemMoved(position, target.adapterPosition)
                return false
            }

            override fun getMovementFlags(
                secondRecyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                return makeMovementFlags(
                    dragFlag,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                )
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                if (direction == ItemTouchHelper.LEFT) {
                    adapters.delete(viewHolder.adapterPosition)
                    Snackbar.make(
                        secondRecyclerView, "You Deleted item",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                if (direction == ItemTouchHelper.RIGHT) {
                    val position = mutableList[viewHolder.adapterPosition]
                    adapters.addItem(mutableList.size -1, position)
                    Snackbar.make(
                        secondRecyclerView, "You add item",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouch)
        itemTouchHelper.attachToRecyclerView(secondRecyclerView)
    }

    private fun getShowItem(dataImage: MutableList<Int>, dataText: MutableList<String>) {
        for (i in 0 until dataImage.size) {
            val itemViews = ItemData(
                dataImage.random(), dataText[i],
                "28 Oct, 2018 - 11:00Am",
                "Emirates Airways"
            )
            mutableList.add(itemViews)
        }
    }
}

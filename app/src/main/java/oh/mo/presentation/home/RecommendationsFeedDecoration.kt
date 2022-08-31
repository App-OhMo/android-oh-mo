package oh.mo.presentation.home

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import oh.mo.R

class RecommendationsFeedDecoration(private val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        if (parent.getChildAdapterPosition(view) % 2 == 0) {
            outRect.right =
                context.resources.getDimension(R.dimen.home_recyclerview_grid_margin).toInt() / 2
        } else {
            outRect.left =
                context.resources.getDimension(R.dimen.home_recyclerview_grid_margin).toInt() / 2
        }

        outRect.bottom =
            context.resources.getDimension(R.dimen.home_recyclerview_grid_margin).toInt()
    }
}
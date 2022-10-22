package oh.mo.utils

import android.widget.TextView
import oh.mo.R

fun TextView.setTextViewEnabled(boolean: Boolean) {
    when (boolean) {
        true -> {
            this.setTextColor(resources.getColor(R.color.blue_4e, null))
        }
        false -> {
            this.setTextColor(resources.getColor(R.color.grey_ae, null))
        }
    }
}

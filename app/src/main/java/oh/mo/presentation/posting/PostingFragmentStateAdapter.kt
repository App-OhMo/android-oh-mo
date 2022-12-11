package oh.mo.presentation.posting

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PostingFragmentStateAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity){

    var fm : List<Fragment> = listOf(CoordiChipFragment(), SelectWeatherFragment(), CoordiContentFragment())

    override fun getItemCount(): Int = fm.size

    override fun createFragment(position: Int): Fragment = fm[position]


}
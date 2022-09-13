package oh.mo.presentation.main

import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import oh.mo.R
import oh.mo.databinding.ActivityMainBinding
import oh.mo.presentation.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.activity_main

    override fun initStartView() {
        setBottomNavigation()
    }

    override fun initBinding() {}

    override fun initAfterBinding() {}

    private fun setBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fm_main_nav_host)
        val navController = navHostFragment?.findNavController()
        binding.navBar.setupWithNavController((navController ?: throw NullPointerException()))


    }
}
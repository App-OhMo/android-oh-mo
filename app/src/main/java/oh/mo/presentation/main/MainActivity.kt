package oh.mo.presentation.main

import android.view.WindowManager
import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import oh.mo.R
import oh.mo.databinding.ActivityMainBinding
import oh.mo.presentation.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.activity_main

    override fun initStartView() {
        setBottomNavigation()
        setTheme(R.style.statusBarThemeBlue)
    }

    override fun initBinding() { }

    override fun initAfterBinding() { }

    private fun setBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fm_main_nav_host)
        val navController = navHostFragment?.findNavController()
        binding.navBar.setupWithNavController((navController ?: throw NullPointerException()))


        navController.addOnDestinationChangedListener { _, dest, _ ->
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            when(dest.id) {
                R.id.fragment_profile -> {
                    binding.tbMain.apply {
                        window.statusBarColor = resources.getColor(R.color.white);
                        title = "프로필"
                        setTitleTextColor(resources.getColor(R.color.black_1a))
                        setBackgroundColor(resources.getColor(R.color.white))
                    }
                }
                R.id.fragment_home -> {
                    binding.tbMain.apply {
                        window.statusBarColor = resources.getColor(R.color.blue_64);
                        title = "오늘의 날씨"
                        setTitleTextColor(resources.getColor(R.color.white))
                        setBackgroundColor(resources.getColor(R.color.blue_64))
                    }
                }
            }

        }

    }
}
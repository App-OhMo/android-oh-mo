package oh.mo.presentation.main

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import oh.mo.R
import oh.mo.databinding.ActivityMainBinding
import oh.mo.presentation.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it)}) {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBottomNavigation()
        setInitToolbarIcon()
        setTheme(R.style.statusBarThemeBlue)
    }

    private fun setBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fm_main_nav_host)
        navController = navHostFragment?.findNavController() ?: throw NullPointerException()
        binding.navBar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, dest, _ ->
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            when(dest.id) {
                R.id.fragment_profile -> {
                    binding.tbMain.apply {
                        title = "프로필"
                        setWhiteToolBar()
                        visibleNav(true)
                    }
                }
                R.id.fragment_home -> {
                    binding.tbMain.apply {
                        title = "오늘의 날씨"
                        setBlueToolBar()
                        visibleNav(true)
                    }
                }
                R.id.fragment_posting -> {
                    binding.tbMain.apply {
                        setWhiteToolBar()
                        visibleNav(false)
                    }
                }
            }
        }
    }

    private fun Toolbar.setBlueToolBar() {
        window.statusBarColor = resources.getColor(R.color.blue_64);
        this.setTitleTextColor(resources.getColor(R.color.white))
        this.setBackgroundColor(resources.getColor(R.color.blue_64))
    }

    private fun Toolbar.setWhiteToolBar() {
        window.statusBarColor = resources.getColor(R.color.white);
        this.setTitleTextColor(resources.getColor(R.color.black_1a))
        this.setBackgroundColor(resources.getColor(R.color.white))
    }

    private fun visibleNav(visible: Boolean) {
        if(visible) {
            binding.tbMain.visibility = View.VISIBLE
            binding.navBar.visibility = View.VISIBLE
        } else {
            binding.tbMain.visibility = View.VISIBLE
            binding.navBar.visibility = View.GONE
        }
    }

    private fun setInitToolbarIcon() {
        binding.ivHomeSearch.setOnClickListener{
            navController.navigate(R.id.action_home_to_search)
            binding.tbMain.visibility = View.GONE
            binding.navBar.visibility = View.GONE
        }

        binding.ivHomePosting.setOnClickListener() {
            navController.navigate(R.id.action_home_to_posting)
            binding.tbMain.visibility = View.GONE
            binding.navBar.visibility = View.GONE
        }
    }

}
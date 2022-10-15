package oh.mo.presentation.main

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
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
        binding.navBar.setupWithNavController((navController))


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

    private fun setInitToolbarIcon() {
        binding.ivHomeSearch.setOnClickListener{
            navController.navigate(R.id.action_home_to_search)
        }

        binding.ivHomePosting.setOnClickListener() {
            //포스팅 아이콘 로직
        }
    }

}
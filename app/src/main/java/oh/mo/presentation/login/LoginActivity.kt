package oh.mo.presentation.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import oh.mo.R
import oh.mo.databinding.ActivityLoginBinding
import oh.mo.presentation.base.BaseActivity

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>({ ActivityLoginBinding.inflate(it) }) {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFunctions()
    }

    private fun initFunctions() {
        setSupportActionBar(binding.tbLogin)
        supportActionBar!!.apply {
            setDisplayShowTitleEnabled(false)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fm_login_nav_host)
        val navController = navHostFragment?.findNavController() ?: throw NullPointerException()

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.tbLogin.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, dest, _ ->
            if (dest.id != R.id.fragment_start) {
                binding.tbLogin.setNavigationIcon(R.drawable.back_icon)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fm_login_nav_host)
        return navController.navigateUp(appBarConfiguration) || super.onNavigateUp()
    }
}
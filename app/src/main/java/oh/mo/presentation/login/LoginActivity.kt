package oh.mo.presentation.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import oh.mo.R
import oh.mo.databinding.ActivityLoginBinding
import oh.mo.presentation.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>({ ActivityLoginBinding.inflate(it) }) {
    private val viewModel: LoginViewModel by viewModels()

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
        val navController = navHostFragment?.findNavController()
        setupActionBarWithNavController(navController ?: throw NullPointerException())
    }
}
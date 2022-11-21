package oh.mo.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import oh.mo.R
import oh.mo.presentation.login.LoginActivity
import oh.mo.presentation.main.MainActivity

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initFlowFunctions()

        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.postCheckToken()
        }, 1000)
    }

    private fun initFlowFunctions() {
        lifecycleScope.launchWhenStarted {
            viewModel.isTokenValid.collect { isTokenValid ->
                Log.d("isTokenValid", isTokenValid.toString())
                if (isTokenValid) {
                    startMainActivity()
                } else {
                    startLoginActivity()
                }
            }
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
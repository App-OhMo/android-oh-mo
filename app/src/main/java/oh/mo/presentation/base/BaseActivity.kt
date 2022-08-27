package oh.mo.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<T : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {
    lateinit var binding: T
    abstract val viewModel: VM
    abstract val layoutResourceId: Int

    abstract fun initStartView()
    abstract fun initBinding()
    abstract fun initAfterBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResourceId)

        binding.lifecycleOwner = this

        initStartView()
        initBinding()
        initAfterBinding()
    }
}
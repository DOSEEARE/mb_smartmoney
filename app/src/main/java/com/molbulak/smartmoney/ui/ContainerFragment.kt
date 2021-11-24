package com.molbulak.smartmoney.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.molbulak.smartmoney.App
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.Screens
import com.molbulak.smartmoney.databinding.FragmentContainerBinding
import com.molbulak.smartmoney.util.enums.FragmentType


class ContainerFragment(private val fragmentType: FragmentType) : Fragment(), BackButtonListener {
    private lateinit var binding: FragmentContainerBinding
    private lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = object : AppNavigator(requireActivity(), R.id.container, childFragmentManager) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentContainerBinding.inflate(inflater, container, false)
        when (fragmentType) {
            FragmentType.LOAN -> navigator.applyCommands(arrayOf(Replace(Screens.LoanScreen())))
            FragmentType.MORE -> navigator.applyCommands(arrayOf(Replace(Screens.MoreScreen())))
            FragmentType.NOTIFICATION -> navigator.applyCommands(arrayOf(Replace(Screens.NotificationScreen())))
            FragmentType.PROFILE -> navigator.applyCommands(arrayOf(Replace(Screens.ProfileScreen())))
            FragmentType.SUPPORT -> navigator.applyCommands(arrayOf(Replace(Screens.SupportScreen())))
        }
        return binding.root
    }

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.container)
        return if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            true
        } else {
            App.getRouter().exit()
            true
        }
    }

    override fun onResume() {
        super.onResume()
        App.INSTANCE.getNavigator().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.INSTANCE.getNavigator().removeNavigator()
    }

}

interface BackButtonListener {
    fun onBackPressed(): Boolean
}

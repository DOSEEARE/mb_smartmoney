package com.molbulak.smartmoney.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.molbulak.smartmoney.App
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.Screens
import com.molbulak.smartmoney.databinding.FragmentContainerBinding
import com.molbulak.smartmoney.util.enums.ContainerType


class ContainerFragment(val containerType: ContainerType) : Fragment(),
    BackButtonListener {
    private lateinit var binding: FragmentContainerBinding

    private lateinit var navigator: AppNavigator
    private lateinit var router: Router
    private lateinit var cicerone: Cicerone<Router>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentContainerBinding.inflate(inflater, container, false)
        navigator =
            object : AppNavigator(requireActivity(), binding.container.id, childFragmentManager) {
                override fun setupFragmentTransaction(
                    screen: FragmentScreen,
                    fragmentTransaction: FragmentTransaction,
                    currentFragment: Fragment?,
                    nextFragment: Fragment,
                ) {
                    fragmentTransaction.setCustomAnimations(
                        R.anim.slide_enter_next,
                        R.anim.slide_leave_next,
                        R.anim.slide_enter_back,
                        R.anim.slide_leave_back
                    )
                }
            }
        cicerone = App.localCicerone.getCicerone(containerType).cicerone
        router = App.localCicerone.getCicerone(containerType).router

        when (containerType) {
            ContainerType.LOAN -> navigator.applyCommands(arrayOf(Replace(Screens.LoanScreen())))
            ContainerType.MORE -> navigator.applyCommands(arrayOf(Replace(Screens.MoreScreen())))
            ContainerType.NOTICE -> navigator.applyCommands(arrayOf(Replace(Screens.NotificationScreen())))
            ContainerType.PROFILE -> navigator.applyCommands(arrayOf(Replace(Screens.ProfileScreen())))
            ContainerType.SUPPORT -> navigator.applyCommands(arrayOf(Replace(Screens.SupportScreen())))
        }
        return binding.root
    }

    override fun backPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(binding.container.id)
        return if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).backPressed()
        ) {
            true
        } else {
            router.exit()
            true
        }
    }

    override fun onResume() {
        super.onResume()
        cicerone.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        cicerone.getNavigatorHolder().removeNavigator()
    }

    
}

interface BackButtonListener {
    fun backPressed(): Boolean
}

package com.molbulak.smartmoney.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.Screens
import com.molbulak.smartmoney.base.HostFragment
import com.molbulak.smartmoney.cicerone.LocalCiceroneHolder
import com.molbulak.smartmoney.databinding.FragmentContainerBinding
import com.molbulak.smartmoney.util.enums.FragmentType
import org.koin.android.ext.android.inject


class ContainerFragment(private val fragmentType: FragmentType) : HostFragment(),
    BackButtonListener {
    private lateinit var binding: FragmentContainerBinding
    private val localCiceroneHolder: LocalCiceroneHolder by inject()

    private lateinit var navigator: AppNavigator
    private lateinit var cicerone: Cicerone<Router>
    lateinit var router: Router


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentContainerBinding.inflate(inflater, container, false)
        navigator =
            object : AppNavigator(requireActivity(), binding.container.id, childFragmentManager) {}
        cicerone = localCiceroneHolder.getCicerone(fragmentType.toString())
        router = localCiceroneHolder.getCicerone(fragmentType.toString()).router

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
    fun onBackPressed(): Boolean
}

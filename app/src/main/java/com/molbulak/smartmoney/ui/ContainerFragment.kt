package com.molbulak.smartmoney.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentContainerBinding.inflate(inflater, container, false)
        when (fragmentType) {
            FragmentType.LOAN -> App.getRouter().replaceScreen(Screens.LoanScreen())
            FragmentType.MORE -> App.getRouter().replaceScreen(Screens.MoreScreen())
            FragmentType.NOTIFICATION -> App.getRouter()
                .replaceScreen(Screens.NotificationScreen())
            FragmentType.PROFILE -> App.getRouter().replaceScreen(Screens.ProfileScreen())
            FragmentType.SUPPORT -> App.getRouter().replaceScreen(Screens.SupportScreen())
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


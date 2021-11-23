package com.molbulak.smartmoney.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.molbulak.smartmoney.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var binding: FragmentNotificationBinding
    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        binding = FragmentNotificationBinding.inflate(inflater, container, false)


        return binding.root
    }

}
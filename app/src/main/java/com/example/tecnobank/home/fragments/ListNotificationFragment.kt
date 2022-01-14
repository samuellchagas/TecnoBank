package com.example.tecnobank.home.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.tecnobank.databinding.ListNotificationFragmentBinding
import com.example.tecnobank.home.adapter.ViewPagerNotificationAdapter.Companion.POSITION_VIEW_PAGER_NOTIFICATION
import com.example.tecnobank.home.recyclerview.ListNotificationAdapter

class ListNotificationFragment : Fragment(), ListNotificationAdapter.ClickedNotificationListener {

    private var _binding: ListNotificationFragmentBinding? = null
    private val binding: ListNotificationFragmentBinding get() = _binding!!
    private var positionClick: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListNotificationFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.listNotification.adapter = ListNotificationAdapter(
            this@ListNotificationFragment, getNotificationByPage(
                requireArguments().getInt(POSITION_VIEW_PAGER_NOTIFICATION)
            ), requireArguments().getInt(
                POSITION_VIEW_PAGER_NOTIFICATION
            )
        )

    }

    fun getNotificationByPage(positionViewPager: Int): List<ListNotificationAdapter.NotificationItem> {
        return when (positionViewPager) {
            0 -> {
                thereIsNotificationForPage(PAGE_EVERY)
            }
            1 -> {
                thereIsNotificationForPage(PAGE_YOUR_ACCOUNT)
            }
            else -> {
                thereIsNotificationForPage(PAGE_PROMOTIONS)
            }
        }

    }

    private fun thereIsNotificationForPage(
        page: Int
    ): List<ListNotificationAdapter.NotificationItem> {
        return when (page) {
            PAGE_YOUR_ACCOUNT -> {
                binding.listNotification.isVisible = false
                binding.imageNotification.isVisible = true
                binding.textNotificationStatic.isVisible = true
                with(binding.textNotificationPage) {
                    isVisible = true
                    text = text.toString().replace(".", "'Sua Conta'")
                }
                listOf()
            }
            PAGE_PROMOTIONS -> {
                listOf(
                    ListNotificationAdapter
                        .NotificationItem(
                            "11/07/2021",
                            "Promoção Casa Nova Tecnobank",
                            "Concorra a uma casa dos sonhos, prêmios para renovar sua casa e muitos vale-compras",
                            "PARTICIPE!"
                        ),
                    ListNotificationAdapter
                        .NotificationItem(
                            "25/07/2021",
                            "Maquininhas TecnoBank",
                            "Saiba mais sobre as vantagens de se usar as melhores maquininhas do mercado ",
                            "Peça já!"
                        )
                )
            }
            else -> {
                listOf(
                    ListNotificationAdapter
                        .NotificationItem(
                            "11/07/2021",
                            "Promoção Casa Nova Tecnobank",
                            "Concorra a uma casa dos sonhos, prêmios para renovar sua casa e muitos vale-compras",
                            "PARTICIPE!"
                        ),
                    ListNotificationAdapter
                        .NotificationItem(
                            "25/07/2021",
                            "Maquininhas PagSeguro",
                            "Saiba mais sobre as vantagens de se usar as melhores maquininhas do mercado ",
                            "Peça já!"
                        )
                )
            }
        }
    }

    companion object {
        private const val PAGE_EVERY = 0
        private const val PAGE_YOUR_ACCOUNT = 1
        private const val PAGE_PROMOTIONS = 2
    }

    override fun clickNotificationListener(positionRecyclerView: Int) {
        if (positionRecyclerView == 1) {
            openWebPage("https://pagseguro.uol.com.br/campanha/maquinas-de-cartao/v2.html")
        }
    }

    fun openWebPage(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

}

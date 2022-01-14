package com.example.tecnobank.home.fragments.services

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tecnobank.R
import com.example.tecnobank.data.local.SharedPreferenceServices
import com.example.tecnobank.databinding.PageFunctionalitiesBinding
import com.example.tecnobank.home.adapter.ViewPagerServicesAdapter.Companion.POSITION_VIEW_PAGER_SERVICES
import com.example.tecnobank.home.fragments.HomeFragmentDirections
import com.example.tecnobank.home.recyclerview.ListServicesAdapter

class ServicesFragment : Fragment(), ListServicesAdapter.ClickedServiceListener {

    private var _binding: PageFunctionalitiesBinding? = null
    private val binding: PageFunctionalitiesBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PageFunctionalitiesBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.listServices.adapter =
            ListServicesAdapter(
                getServicesByPage(
                    requireArguments()
                        .getInt(POSITION_VIEW_PAGER_SERVICES)
                ), this@ServicesFragment,
                requireArguments().getInt(POSITION_VIEW_PAGER_SERVICES)
            )

    }

    fun getServicesByPage(positionViewPager: Int): List<ListServicesAdapter.ItemService> {
        return when (positionViewPager) {
            0 -> {
                getListMainServices()
            }
            1 -> {
                getListProdutsAndInvestments()
            }
            else -> {
                getListServices()
            }
        }
    }

    fun getListMainServices(): List<ListServicesAdapter.ItemService> {
        return listOf(
            ListServicesAdapter.ItemService(
                true, null,
                getString(R.string.tranfers_service),
                R.drawable.ic_transferencia
            ),
            ListServicesAdapter.ItemService(
                false, getString(R.string.title_info_cards),
                getString(R.string.cards_service),
                R.drawable.ic_cartoes
            ),
            ListServicesAdapter.ItemService(
                true, null,
                getString(R.string.pay_check_service),
                R.drawable.ic_pagar_contas
            ),
            ListServicesAdapter.ItemService(
                true, getString(R.string.title_info_reload),
                getString(R.string.reload_service),
                R.drawable.ic_recarga
            ),
            ListServicesAdapter.ItemService(
                true, null,
                getString(R.string.add_money_service),
                R.drawable.ic_adicionar_dinheiro
            ),
            ListServicesAdapter.ItemService(
                false, getString(R.string.title_info_pix),
                getString(R.string.pix_qr_code_service),
                R.drawable.ic_pix_qrcode
            )
        )
    }

    private fun getListProdutsAndInvestments(): List<ListServicesAdapter.ItemService> {
        return listOf(
            ListServicesAdapter.ItemService(
                true, null,
                getString(R.string.salary_portability_service),
                R.drawable.ic_portabilidade_salario
            ),
            ListServicesAdapter.ItemService(
                true, null,
                getString(R.string.applying_my_money_service),
                R.drawable.ic_aplicando_meu_dinheiro
            ),
            ListServicesAdapter.ItemService(
                true, null,
                getString(R.string.my_investiments_service),
                R.drawable.ic_meus_investimentos
            ),
            ListServicesAdapter.ItemService(
                true, getString(R.string.title_info_safe),
                getString(R.string.safe_service),
                R.drawable.ic_seguros
            ),
            ListServicesAdapter.ItemService(
                true, null,
                getString(R.string.learn_invest_service),
                R.drawable.ic_aprenda_a_investir
            )
        )
    }

    private fun getListServices(): List<ListServicesAdapter.ItemService> {
        return listOf(
            ListServicesAdapter.ItemService(
                true, null,
                getString(R.string.post_shell_service),
                R.drawable.ic_postos_shell
            ),
            ListServicesAdapter.ItemService(
                true, null,
                getString(R.string.bid_radar_service),
                R.drawable.ic_radar_ofertas
            ),
            ListServicesAdapter.ItemService(
                true, getString(R.string.title_info_shopping),
                getString(R.string.shopping_service),
                R.drawable.ic_shopping
            ),
            ListServicesAdapter.ItemService(
                true, null,
                getString(R.string.where_loot_money_service),
                R.drawable.ic_onde_sacar
            ),
            ListServicesAdapter.ItemService(
                true, null,
                getString(R.string.nominate_and_win_service),
                R.drawable.ic_indique_e_ganhe
            ),
            ListServicesAdapter.ItemService(
                false, null,
                getString(R.string.pay_with_qr_code),
                R.drawable.ic_pagar_com_qrcode
            )
        )
    }

    override fun clickServiceListener(positionRecyclerView: Int, positionViewPager: Int) {

        if ((positionViewPager == MAIN_SERVICES) && (positionRecyclerView == CARDS_FEATURE)) {
            findNavController().navigate(R.id.action_homeFragment_to_cardsFragment)
        } else if ((positionViewPager == MAIN_SERVICES) && (positionRecyclerView == PIX_FEATURE)) {
            findNavController().navigate(HomeFragmentDirections
                .actionHomeFragmentToPixQrCodeActivity(
                    providerSharedPreferenceService(providerSharedPreference()).passedByPixOnBoarding()
                )
            )
        }else if ((positionViewPager == 2) && (positionRecyclerView == 5)) {
            findNavController().navigate(R.id.action_homeFragment_to_payQrCodeActivity)
        }
    }

    private fun providerSharedPreferenceService(
        preferences: SharedPreferences
    ): SharedPreferenceServices {
        return SharedPreferenceServices(preferences)
    }

    private fun providerSharedPreference(): SharedPreferences {
        return requireContext().getSharedPreferences(
            R.string.preference_file_key.toString(), Context.MODE_PRIVATE
        )
    }

    companion object {
        private const val MAIN_SERVICES = 0
        private const val PIX_FEATURE = 5
        private const val CARDS_FEATURE = 1
    }
}

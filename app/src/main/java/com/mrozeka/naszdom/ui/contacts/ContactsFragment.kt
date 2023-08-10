package com.mrozeka.naszdom.ui.contacts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrozeka.naszdom.databinding.FragmentContactsBinding
import com.mrozeka.naszdom.helper.DialogHelper
import com.mrozeka.naszdom.helper.PermissionHelper
import com.mrozeka.naszdom.helper.safeLastIndex
import com.mrozeka.naszdom.pref.PrefRepository
import com.mrozeka.naszdom.ui.contacts.ContactsViewModel.State.FillView
import com.mrozeka.naszdom.ui.contacts.ContactsViewModel.State.ShowAddContactDialog
import com.mrozeka.naszdom.ui.contacts.ContactsViewModel.State.MakeACall
import com.mrozeka.naszdom.ui.contacts.ContactsViewModel.State.OnError
import com.mrozeka.naszdom.ui.contacts.ContactsViewModel.State.RefreshView

open class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null

    private val binding get() = _binding!!
    private val contactsViewModel: ContactsViewModel by viewModels {
        ContactsViewModelFactory(PrefRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        contactsViewModel.fillView()
        contactsViewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is FillView -> {
                    val contactList = binding.contactListView
                    val arrayAdapter = ContactAdapter(it.contacts) { contact ->
                        contactsViewModel.onContactClicked(
                            PermissionHelper.isCallPermissionGranted(requireContext()),
                            contact)
                    }
                    contactList.layoutManager = LinearLayoutManager(requireContext())
                    contactList.adapter = arrayAdapter
                }

                is OnError -> {
                    DialogHelper.withError(requireContext(), layoutInflater, it.msg)
                }

                is MakeACall -> {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse(it.tel)
                    startActivity(intent)
                }

                is ShowAddContactDialog -> DialogHelper.with2EditText(
                    requireContext(), layoutInflater, it.title, it.buttonTitle
                ) { et1, et2 ->
                    contactsViewModel.onSaveContact(et1, et2)
                }
                is RefreshView ->{
                    val adapter = (binding.contactListView.adapter as ContactAdapter)
                    adapter.dataSet += it.contact
                    adapter.notifyItemInserted(adapter.dataSet.safeLastIndex())
                }
                else -> {}
            }
        }
        binding.fragmentContactsFab.setOnClickListener {
            contactsViewModel.onAddContactButtonClicked()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
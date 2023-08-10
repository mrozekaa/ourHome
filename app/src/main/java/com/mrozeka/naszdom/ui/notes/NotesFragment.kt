package com.mrozeka.naszdom.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrozeka.naszdom.databinding.FragmentNotesBinding
import com.mrozeka.naszdom.helper.DialogHelper
import com.mrozeka.naszdom.helper.safeLastIndex
import com.mrozeka.naszdom.pref.PrefRepository

open class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null

    private val binding get() = _binding!!
    private val notesViewModel: NotesViewModel by viewModels {
        NotesViewModelFactory(PrefRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        notesViewModel.fillView()
        notesViewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is NotesViewModel.State.FillView -> {
                    val notesList = binding.notesListView
                    val arrayAdapter = NoteAdapter(it.notes) { }
                    notesList.layoutManager = LinearLayoutManager(requireContext())
                    notesList.adapter = arrayAdapter
                }
                is NotesViewModel.State.ShowAddNoteDialog -> DialogHelper.withEditText(
                    requireContext(), layoutInflater, it.title, it.buttonTitle
                ) { txt ->
                    notesViewModel.onSaveNote(txt)
                }
                is NotesViewModel.State.RefreshView ->{
                    val adapter = (binding.notesListView.adapter as NoteAdapter)
                    adapter.dataSet += it.note
                    adapter.notifyItemInserted(adapter.dataSet.safeLastIndex())
                }
                else -> {}
            }
        }
        binding.fragmentNotesFab.setOnClickListener {
            notesViewModel.onAddNoteButtonClicked()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
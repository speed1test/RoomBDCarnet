package com.example.roombdcarnet.ui.alumno

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roombdcarnet.R
import com.example.roombdcarnet.RegistroNotaApplication
import com.example.roombdcarnet.db.AlumnoEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AlumnosFragment : Fragment(), AlumnoListAdapter.OnAlumnoClickListener {
    companion object {
        fun newInstance() = AlumnosFragment()
    }

    private lateinit var viewModel: AlumnosViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = activity?.application as RegistroNotaApplication
        viewModel = ViewModelProvider(
            requireActivity(),
            AlumnoViewModelFactory(application.repository)
        ).get(AlumnosViewModel::class.java)
        return inflater.inflate(R.layout.alumnos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = AlumnoListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.alumnos.observe(viewLifecycleOwner, Observer { alumnos ->
            alumnos?.let { adapter.submitList(it) }
        })
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            viewModel.alumnoActual = null
            findNavController().navigate(R.id.action_nav_alumnos_to_nav_guardar_alumno)
        }
    }

    override fun onEditAlumnoClicked(alumno: AlumnoEntity) {
        viewModel.alumnoActual = alumno
        findNavController().navigate(R.id.action_nav_alumnos_to_nav_guardar_alumno)
    }

    override fun onDeleteAlumnoClicked(alumno: AlumnoEntity) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Estas seguro que deseas borrar el alumno con carnet: ${alumno.carnet}?")
            .setCancelable(false)
            .setPositiveButton("Si") { dialog, id ->
                viewModel.delete(alumno)
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
}
package com.example.roombdcarnet.ui.alumno

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roombdcarnet.R
import com.example.roombdcarnet.RegistroNotaApplication
import com.example.roombdcarnet.db.AlumnoEntity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateAlumnoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateAlumnoFragment : Fragment() {
    private lateinit var viewModel: AlumnosViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = activity?.application as RegistroNotaApplication
        viewModel = ViewModelProvider(
            requireActivity(),
            AlumnoViewModelFactory(application.repository)
        ).get(AlumnosViewModel::class.java)
        return inflater.inflate(R.layout.fragment_create_alumno, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val carnet: EditText = view.findViewById(R.id.carnet_input)
        val nombre: EditText = view.findViewById(R.id.nombre_input)
        val apellido: EditText = view.findViewById(R.id.apellido_input)
        val sexo: RadioGroup = view.findViewById(R.id.sexo_radio_group)
        val matGanadas: TextView = view.findViewById(R.id.mat_ganadas_input)
        val guardarButton: Button = view.findViewById(R.id.guardar_alumno)
        val alumno = viewModel.alumnoActual
        if (alumno != null) {
            carnet.isEnabled = false
            carnet.setText(alumno.carnet)
            nombre.setText(alumno.nombre)
            apellido.setText(alumno.apellido)
            sexo.check(if (alumno.sexo == 'M') R.id.masculino_radio else R.id.femenino_radio)
            matGanadas.text = alumno.matGanadas.toString()
        } else {
            matGanadas.text = "0"
            sexo.check(R.id.masculino_radio)
        }
        guardarButton.setOnClickListener {
            if (carnet.text.isNullOrEmpty() ||
                nombre.text.isNullOrEmpty() ||
                apellido.text.isNullOrEmpty()
            ) {
                Toast.makeText(
                    context, "Todos los campos son requeridos",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            if (alumno != null) {
                viewModel.update(
                    AlumnoEntity(
                        alumno.carnet,
                        nombre.text.toString(),
                        apellido.text.toString(),
                        if (sexo.checkedRadioButtonId == R.id.masculino_radio) 'M' else 'F',
                        alumno.matGanadas
                    )
                )
            } else {
                viewModel.insert(
                    AlumnoEntity(
                        carnet.text.toString(),
                        nombre.text.toString(),
                        apellido.text.toString(),
                        if (sexo.checkedRadioButtonId == R.id.masculino_radio) 'M' else 'F',
                        0
                    )
                )
            }
            findNavController().navigateUp()
        }
    }


}
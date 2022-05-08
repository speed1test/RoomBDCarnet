package com.example.roombdcarnet.ui.alumno

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.roombdcarnet.db.AlumnoEntity
import com.example.roombdcarnet.db.repositories.RegistroNotaRepository
import kotlinx.coroutines.launch

class AlumnosViewModel(private val repository: RegistroNotaRepository) : ViewModel() {
    val alumnos: LiveData<List<AlumnoEntity>> = repository.alumnos
    var alumnoActual: AlumnoEntity? = null
    fun insert(alumno: AlumnoEntity) = viewModelScope.launch {
        repository.insert(alumno)
    }
    fun update(alumno: AlumnoEntity) = viewModelScope.launch {
        repository.update(alumno)
    }
    fun delete(alumno: AlumnoEntity) = viewModelScope.launch {
        repository.delete(alumno)
    }
}
class AlumnoViewModelFactory(private val repository: RegistroNotaRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlumnosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlumnosViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
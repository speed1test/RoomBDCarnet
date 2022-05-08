package com.example.roombdcarnet.db.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.roombdcarnet.RegistroNotasDB
import com.example.roombdcarnet.db.AlumnoEntity
import com.example.roombdcarnet.db.MateriaEntity
import com.example.roombdcarnet.db.NotaEntity


class RegistroNotaRepository (private val db: RegistroNotasDB
){
    val alumnos: LiveData<List<AlumnoEntity>> = db.alumnoDao().getAll()

    @WorkerThread
    suspend fun insert(alumno: AlumnoEntity) {
        db.alumnoDao().insert(alumno)
    }@Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(alumno: AlumnoEntity) {
        db.alumnoDao().update(alumno)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(alumno: AlumnoEntity) {
        db.alumnoDao().delete(alumno)
    }
    /***************************
     * Materia repository
     ***************************/
    val materias: LiveData<List<MateriaEntity>> = db.materiaDao().getAll()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(materia: MateriaEntity) {
        db.materiaDao().insert(materia)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(materia: MateriaEntity) {
        db.materiaDao().update(materia)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(materia: MateriaEntity) {
        db.materiaDao().delete(materia)
    }
    /***************************
     * Nota repository
     ***************************/
    val notas: LiveData<List<NotaEntity>> = db.notaDao().getAll()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(nota: NotaEntity) {
        db.notaDao().insert(nota)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(nota: NotaEntity) {
        db.notaDao().update(nota)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(nota: NotaEntity) {
        db.notaDao().delete(nota)
    }
}


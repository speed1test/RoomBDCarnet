package com.example.roombdcarnet.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlumnoDao {
    @Query("Select * from alumno")
    fun getAll():LiveData<List<AlumnoEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(alumno: AlumnoEntity)

    @Update
    suspend fun update(alumno: AlumnoEntity)

    @Delete
    suspend fun delete(alumno: AlumnoEntity)

    @Query("Delete from alumno")
    suspend fun deleteAll()

}
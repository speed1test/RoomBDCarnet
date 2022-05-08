package com.example.roombdcarnet.db

import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface MateriaDao {
    @Query("SELECT * FROM materia")
    fun getAll(): LiveData<List<MateriaEntity>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(materia: MateriaEntity)
    @Update
    fun update(materia: MateriaEntity)
    @Delete
    fun delete(materia: MateriaEntity)
    @Query("DELETE FROM materia")
    suspend fun deleteAll()
}
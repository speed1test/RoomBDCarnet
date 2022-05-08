package com.example.roombdcarnet.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotaDao {
    @Query("SELECT * FROM nota")
    fun getAll(): LiveData<List<NotaEntity>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(nota: NotaEntity)
    @Update
    fun update(nota: NotaEntity)
    @Delete
    fun delete(nota: NotaEntity)
    @Query("DELETE FROM nota")
    suspend fun deleteAll()
}
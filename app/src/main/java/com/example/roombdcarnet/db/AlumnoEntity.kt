package com.example.roombdcarnet.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alumno")
data class AlumnoEntity (
    @PrimaryKey
    val carnet: String,
    val nombre: String,
    val apellido:String,
    val sexo:Char,
    @ColumnInfo(name="mat_ganadas",defaultValue = "0")
    val matGanadas:Int
)
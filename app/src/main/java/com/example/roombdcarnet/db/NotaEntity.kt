package com.example.roombdcarnet.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE


@Entity(
    tableName = "nota",
    primaryKeys = ["carnet", "cod_materia", "ciclo"],
    foreignKeys = [
        ForeignKey(
            entity = AlumnoEntity::class,
            parentColumns = ["carnet"],
            childColumns = ["carnet"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = MateriaEntity::class,
            parentColumns = ["cod_materia"],
            childColumns = ["cod_materia"],
            onDelete = CASCADE
        )
    ]
)
data class NotaEntity(
    @ColumnInfo(name = "carnet")
    val carnetAlumno:String,
    @ColumnInfo(name = "cod_materia")
    val codigoMateria:String,
    val ciclo:String,
    @ColumnInfo(name = "nota_final")
    val notaFinal:Float
)
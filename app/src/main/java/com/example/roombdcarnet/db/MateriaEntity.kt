package com.example.roombdcarnet.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "materia")
data class MateriaEntity(
    @PrimaryKey
    @ColumnInfo(name = "cod_materia")
    val codigo: String,
    @ColumnInfo(name = "nom_materia")
    val nombre: String,
    @ColumnInfo(name = "unidades_val")
    val unidadesValorativas: Int
)
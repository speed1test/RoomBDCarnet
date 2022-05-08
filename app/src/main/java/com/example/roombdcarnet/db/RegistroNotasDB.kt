package com.example.roombdcarnet

import android.content.Context
import androidx.room.Database
import androidx.room.Room

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roombdcarnet.db.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        AlumnoEntity::class,
        MateriaEntity::class,
        NotaEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RegistroNotasDB : RoomDatabase() {
    abstract fun alumnoDao(): AlumnoDao
    abstract fun materiaDao(): MateriaDao
    abstract fun notaDao(): NotaDao
    private class AlumnoDBCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database)
                }
            }
        }

        suspend fun populateDatabase(db: RegistroNotasDB) {
            // Limpiar base
            db.alumnoDao().deleteAll()
            db.materiaDao().deleteAll()
            db.notaDao().deleteAll()
            val VAcarnet = listOf("OO12035", "OF12044", "GG11098", "CC12021")
            val VAnombre = listOf("Carlos", "Pedro", "Sara", "Gabriela")
            val VAapellido = listOf("Orantes", "Ortiz", "Gonzales", "Coto")
            val VAsexo = listOf('M', 'M', 'F', 'F')
            val VMcodmateria = listOf("MAT115", "PRN115", "IEC115", "TSI115")
            val VMnommateria = listOf(
                "Matematica I",
                "Programacion I",
                "Ingenieria Economica",
                "Teoria de Sistemas"
            )
            val VMunidadesval = listOf(4, 4, 4, 4)
            val VNcarnet =
                listOf("OO12035", "OF12044", "GG11098", "CC12021", "OO12035", "GG11098", "OF12044")
            val VNcodmateria =
                listOf("MAT115", "PRN115", "IEC115", "TSI115", "IEC115", "MAT115", "PRN115")
            val VNciclo = listOf("12016", "12016", "22016", "22016", "22016", "12016", "22016")
            val VNnotafinal = listOf(7f, 5f, 8f, 7f, 6f, 10f, 7f)
            for (i in 0..3) {
                db.alumnoDao().insert(
                    AlumnoEntity(
                        VAcarnet[i],
                        VAnombre[i],
                        VAapellido[i],
                        VAsexo[i],
                        0
                    )
                )
            }
            for (i in 0..3) {
                db.materiaDao().insert(
                    MateriaEntity(
                        VMcodmateria[i],
                        VMnommateria[i],
                        VMunidadesval[i]
                    )
                )
            }
            for (i in 0..6) {
                db.notaDao().insert(
                    NotaEntity(
                        VNcarnet[i],
                        VNcodmateria[i],
                        VNciclo[i],
                        VNnotafinal[i]
                    )
                )
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: RegistroNotasDB? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): RegistroNotasDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RegistroNotasDB::class.java,
                    "registro_nota_db"
                )
                    .addCallback(AlumnoDBCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
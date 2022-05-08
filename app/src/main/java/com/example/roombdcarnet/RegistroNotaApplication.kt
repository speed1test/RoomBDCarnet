package com.example.roombdcarnet

import android.app.Application
import com.example.roombdcarnet.db.repositories.RegistroNotaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RegistroNotaApplication:Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { RegistroNotasDB.getDatabase(this, applicationScope) }
    val repository by lazy { RegistroNotaRepository(database) }
}
package com.example.roombdcarnet.ui.alumno

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roombdcarnet.R
import com.example.roombdcarnet.db.AlumnoEntity

class AlumnoListAdapter(onAlumnoClickListener: OnAlumnoClickListener) :
    ListAdapter<AlumnoEntity, AlumnoListAdapter.AlumnoViewHolder>(AlumnoComparator()) {
    private val mOnAlumnoClickListener = onAlumnoClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumnoViewHolder {
        return AlumnoViewHolder.create(parent, mOnAlumnoClickListener)
    }
    override fun onBindViewHolder(holder: AlumnoViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }
    class AlumnoViewHolder(itemView: View, onAlumnoClickListener: OnAlumnoClickListener) :
        RecyclerView.ViewHolder(itemView) {

        private val onAlumnoClickListener = onAlumnoClickListener
        private val carnet: TextView = itemView.findViewById(R.id.item_id)
        private val nombre: TextView = itemView.findViewById(R.id.item_name)
        private val updateButton: ImageButton = itemView.findViewById(R.id.update_button)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button)
        fun bind(alumno: AlumnoEntity) {
            carnet.text = alumno.carnet
            nombre.text = "${alumno.apellido} ${alumno.nombre}"
            updateButton.setOnClickListener {
                onAlumnoClickListener.onEditAlumnoClicked(alumno)
            }
            deleteButton.setOnClickListener {
                onAlumnoClickListener.onDeleteAlumnoClicked(alumno)
            }
        }
        companion object {
            fun create(parent: ViewGroup, onAlumnoClickListener: OnAlumnoClickListener):
                    AlumnoViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.reciclerview_item, parent, false)
                return AlumnoViewHolder(view, onAlumnoClickListener)
            }
        }
    }
    class AlumnoComparator : DiffUtil.ItemCallback<AlumnoEntity>() {
        override fun areItemsTheSame(oldItem: AlumnoEntity, newItem: AlumnoEntity): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: AlumnoEntity, newItem: AlumnoEntity):
                Boolean {
            return oldItem.carnet == newItem.carnet
        }
    }
    interface OnAlumnoClickListener {
        fun onEditAlumnoClicked(alumno: AlumnoEntity)
        fun onDeleteAlumnoClicked(alumno: AlumnoEntity)
    }
}
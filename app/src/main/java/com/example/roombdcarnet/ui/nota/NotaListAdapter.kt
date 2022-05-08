package com.example.roombdcarnet.ui.nota

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roombdcarnet.R
import com.example.roombdcarnet.db.NotaEntity
class NotaListAdapter(onNotaClickListener: OnNotaClickListener) : ListAdapter<NotaEntity,
        NotaListAdapter.NotaViewHolder>(NotaComparator()) {
    private val mOnNotaClickListener = onNotaClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        return NotaViewHolder.create(parent, mOnNotaClickListener)
    }
    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }
    class NotaViewHolder(itemView: View, onNotaClickListener: OnNotaClickListener) :
        RecyclerView.ViewHolder(itemView) {

        private val onNotaClickListener = onNotaClickListener
        private val codigo: TextView = itemView.findViewById(R.id.item_id)
        private val nombre: TextView = itemView.findViewById(R.id.item_name)
        private val updateButton: ImageButton = itemView.findViewById(R.id.update_button)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button)
        fun bind(nota: NotaEntity) {
            codigo.text = "${nota.carnetAlumno}|${nota.codigoMateria}"
            nombre.text = nota.ciclo
            updateButton.setOnClickListener {
                onNotaClickListener.onEditNotaClicked(nota)
            }
            deleteButton.setOnClickListener {
                onNotaClickListener.onDeleteNotaClicked(nota)
            }
        }
        companion object {
            fun create(parent: ViewGroup, onNotaClickListener: OnNotaClickListener):
                    NotaViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.reciclerview_item, parent, false)
                return NotaViewHolder(view, onNotaClickListener)
            }
        }
    }
    class NotaComparator : DiffUtil.ItemCallback<NotaEntity>() {
        override fun areItemsTheSame(oldItem: NotaEntity, newItem: NotaEntity): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: NotaEntity, newItem: NotaEntity): Boolean {
            return oldItem.carnetAlumno == newItem.carnetAlumno &&
                    oldItem.codigoMateria == newItem.codigoMateria &&
                    oldItem.ciclo == newItem.ciclo
        }
    }
    interface OnNotaClickListener {
        fun onEditNotaClicked(nota: NotaEntity)
        fun onDeleteNotaClicked(nota: NotaEntity)
    }
}
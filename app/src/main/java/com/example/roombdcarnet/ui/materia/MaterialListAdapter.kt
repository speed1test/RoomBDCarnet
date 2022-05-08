package com.example.roombdcarnet.ui.materia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roombdcarnet.R
import com.example.roombdcarnet.db.MateriaEntity

class MateriaListAdapter(onMateriaClickListener: OnMateriaClickListener) :
    ListAdapter<MateriaEntity, MateriaListAdapter.MateriaViewHolder>(MateriaComparator()) {
    private val mOnMateriaClickListener = onMateriaClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriaViewHolder {
        return MateriaViewHolder.create(parent, mOnMateriaClickListener)
    }

    override fun onBindViewHolder(holder: MateriaViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class MateriaViewHolder(itemView: View, onMateriaClickListener: OnMateriaClickListener) :
        RecyclerView.ViewHolder(itemView) {
        private val onMateriaClickListener = onMateriaClickListener
        private val codigo: TextView = itemView.findViewById(R.id.item_id)
        private val nombre: TextView = itemView.findViewById(R.id.item_name)
        private val updateButton: ImageButton = itemView.findViewById(R.id.update_button)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button)
        fun bind(materia: MateriaEntity) {
            codigo.text = materia.codigo
            nombre.text = materia.nombre
            updateButton.setOnClickListener {
                onMateriaClickListener.onEditMateriaClicked(materia)
            }
            deleteButton.setOnClickListener {
                onMateriaClickListener.onDeleteMateriaClicked(materia)
            }
        }

        companion object {
            fun create(parent: ViewGroup, onMateriaClickListener: OnMateriaClickListener):
                    MateriaViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.reciclerview_item, parent, false)
                return MateriaViewHolder(view, onMateriaClickListener)
            }
        }
    }

    class MateriaComparator : DiffUtil.ItemCallback<MateriaEntity>() {
        override fun areItemsTheSame(oldItem: MateriaEntity, newItem: MateriaEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MateriaEntity, newItem: MateriaEntity):
                Boolean {
            return oldItem.codigo == newItem.codigo
        }
    }

    interface OnMateriaClickListener {
        fun onEditMateriaClicked(materia: MateriaEntity)
        fun onDeleteMateriaClicked(materia: MateriaEntity)
    }
}
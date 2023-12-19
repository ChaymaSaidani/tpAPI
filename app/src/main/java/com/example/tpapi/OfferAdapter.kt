package com.example.tpapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class OfferAdapter(var offers: List<Offer>) : ListAdapter<Offer, OfferAdapter.OfferViewHolder>(OfferDiffCallback()) {

    inner class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val code: TextView = itemView.findViewById(R.id.textViewCode)
        val intitule: TextView = itemView.findViewById(R.id.textViewIntitule)
        val specialite: TextView = itemView.findViewById(R.id.textViewSpecialite)
        val societe: TextView = itemView.findViewById(R.id.textViewSociete)
        val nbPostes: TextView = itemView.findViewById(R.id.textViewNbPostes)
        val pays: TextView = itemView.findViewById(R.id.textViewPays)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return OfferViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val currentOffer = getItem(position)
        holder.code.text = "Code: ${currentOffer.code}"
        holder.intitule.text = "Intitule: ${currentOffer.intitulé}"
        holder.specialite.text = "Specialité: ${currentOffer.specialité}"
        holder.societe.text = "Société: ${currentOffer.société}"
        holder.nbPostes.text = "Nb Postes: ${currentOffer.nbpostes}"
        holder.pays.text = "Pays: ${currentOffer.pays}"
    }

    private class OfferDiffCallback : DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem == newItem
        }
    }
    fun insert(offer: Offer) {
        submitList(currentList + offer)
    }


}


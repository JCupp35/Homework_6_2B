package com.cupp.msu.criminalintentchapter9a

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cupp.msu.criminalintentchapter9a.databinding.ListItemCrimeBinding
import com.cupp.msu.criminalintentchapter9a.databinding.PoliceListItemCrimesBinding

class CrimeHolder (
    private val binding: ListItemCrimeBinding

): RecyclerView.ViewHolder(binding.root) {

    fun bind(crime: Crime) {

        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()


        binding.root.setOnClickListener {

            Toast.makeText(

                binding.root.context,
                "${crime.title} clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    class CrimeHolderPolice(
        private val binding: PoliceListItemCrimesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(crime: Crime) {
            binding.crimeTitle.text = crime.title
            binding.crimeDate.text = crime.date.toString()
            binding.contactPolice.text = crime.requiresPolice.toString()

            binding.root.setOnClickListener {

                Toast.makeText(

                    binding.root.context,
                    "${crime.title} clicked",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        class CrimeListAdapter(private val crimes: List<Crime>) :
            RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): RecyclerView.ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                return if (viewType == 1) {
                    // Crime requires police
                    val binding = PoliceListItemCrimesBinding.inflate(inflater, parent, false)
                    CrimeHolderPolice(binding)
                } else {
                    // Normal crime
                    val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
                    CrimeHolder(binding)
                }

            }

            // check if crime requires police and returns a 1 or 0 for different view types
            override fun getItemViewType(position: Int): Int {
                return if (crimes[position].requiresPolice) {
                    1 //requires police
                } else {
                    0 //normal crime
                }
            }


            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val crime = crimes[position]
                when (holder) {
                    is CrimeHolder -> (holder as CrimeHolder).bind(crime)
                    is CrimeHolderPolice -> (holder as CrimeHolderPolice).bind(crime)

                }

            }

            override fun getItemCount() = crimes.size


        }
    }
}
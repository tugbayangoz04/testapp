package com.example.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TestAdapter(
    private val testListesi: List<TestModel>,
    private val onItemClick: (TestModel) -> Unit
) : RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    inner class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val baslikTextView: TextView = itemView.findViewById(R.id.textTestBaslik)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_test, parent, false)
        return TestViewHolder(view)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val test = testListesi[position]
        holder.baslikTextView.text = test.baslik
        holder.itemView.setOnClickListener {
            onItemClick(test)
        }
    }

    override fun getItemCount(): Int = testListesi.size
}

package fr.groggy.racecontrol.tv.ui

import android.annotation.SuppressLint
import androidx.leanback.widget.DiffCallback

open class DataClassByIdDiffCallback<I, A> (val id: (A) -> I) : DiffCallback<A>() {

    override fun areItemsTheSame(oldItem: A & Any, newItem: A & Any): Boolean =
        id(oldItem) == id(newItem)

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: A & Any, newItem: A & Any): Boolean =
        oldItem == newItem

}

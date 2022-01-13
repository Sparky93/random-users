package com.example.randomuser.presentation.list

import androidx.recyclerview.widget.DiffUtil
import com.example.randomuser.data.model.RandomUser

class ListCallback(
    private val oldList: List<RandomUser>,
    private val newList: List<RandomUser>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (name, location, dob, picture) = oldList[oldItemPosition]
        val (name1, location1, dob1, picture1) = newList[newItemPosition]

        return name == name1 && location == location1 && dob == dob1 && picture == picture1
    }
}
package com.dsm.dataroomexample.adapter

import com.dsm.dataroomexample.model.User

interface AdapterListener {
    fun onEditItemClick(user: User)
    fun onDeleteItemClick(user: User)
}
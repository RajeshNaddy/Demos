package com.rajesh.recyclerview.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rajesh.recyclerview.R
import com.rajesh.recyclerview.utility.loadImage
import com.rajesh.recyclerview.view.ui.UserUI


class UserListAdapter(private val context: Context, private val userList: ArrayList<UserUI>,
                      val userItemClickListener: UserItemClickListener) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private val TYPE_1 = 0
    private val TYPE_2 = 1

    override fun getItemViewType(position: Int): Int {
        return when(position % 2){
            0 -> TYPE_1
            else -> TYPE_2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =  when(viewType){
            TYPE_1 -> LayoutInflater.from(parent.context).inflate(R.layout.row_user_type_1, parent, false)
            else -> LayoutInflater.from(parent.context).inflate(R.layout.row_user_type_2, parent, false)
        }
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    inner class UserViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        var txtName: TextView = itemview.findViewById(R.id.txt_user_name)
        var txtEmail: TextView = itemview.findViewById(R.id.txt_user_email)
        var imgProfile: ImageView = itemview.findViewById(R.id.img_user)

        fun bind(user: UserUI) {
            txtName.text =  "${user.firstName.toUpperCase()} ${user.lastName.toUpperCase()}"
            txtEmail.text = "${user.userEmail}"

            imgProfile.loadImage(user.profileLarge, R.drawable.ic_profile)

            itemView.setOnClickListener { userItemClickListener.onItemClick(adapterPosition, user) }
        }
    }

    interface UserItemClickListener{
        fun onItemClick(position: Int, user: UserUI)
    }

}
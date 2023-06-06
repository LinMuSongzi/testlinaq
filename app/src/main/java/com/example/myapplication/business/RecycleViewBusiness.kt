package com.example.myapplication.business

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.DataBindingViewHolder
import com.example.myapplication.databinding.AdapterUserBinding
import com.example.myapplication.itf.IUser

class RecycleViewBusiness {


    companion object {


        fun RecyclerView.build() {
            var chooseBean: IUser? = null
            layoutManager = LinearLayoutManager(null, LinearLayoutManager.VERTICAL, false)
            adapter = object : RecyclerView.Adapter<DataBindingViewHolder<AdapterUserBinding>>() {

                val list = dataList
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<AdapterUserBinding> {
                    return DataBindingViewHolder(AdapterUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
                }

                override fun getItemCount() = list.size

                override fun onBindViewHolder(holder: DataBindingViewHolder<AdapterUserBinding>, position: Int) {
//                    val b = chooseBean
                    holder.dataBinding.bean = list[position].apply {
                        holder.dataBinding.root.setOnClickListener {
                            chooseBean?.choose = false
                            choose = true
                            notifyItemChanged(position)
                            chooseBean?.apply {
                                notifyItemChanged(list.indexOf(chooseBean))
                            }
                            chooseBean = this
//                            notifyDataSetChanged()
                        }
                    }
                }

                override fun getItemId(position: Int): Long {
                    return position.toLong()
                }

            }.apply {
                setHasStableIds(true)
            }
            itemAnimator = null
//            itemAnimator = object : RecyclerView.ItemAnimator() {
//                override fun animateDisappearance(viewHolder: ViewHolder, preLayoutInfo: ItemHolderInfo, postLayoutInfo: ItemHolderInfo?): Boolean {
//                    TODO("Not yet implemented")
//                }
//
//                override fun animateAppearance(viewHolder: ViewHolder, preLayoutInfo: ItemHolderInfo?, postLayoutInfo: ItemHolderInfo): Boolean {
//                    TODO("Not yet implemented")
//                }
//
//                override fun animatePersistence(viewHolder: ViewHolder, preLayoutInfo: ItemHolderInfo, postLayoutInfo: ItemHolderInfo): Boolean {
//                    TODO("Not yet implemented")
//                }
//
//                override fun animateChange(oldHolder: ViewHolder, newHolder: ViewHolder, preLayoutInfo: ItemHolderInfo, postLayoutInfo: ItemHolderInfo): Boolean {
//                    TODO("Not yet implemented")
//                }
//
//                override fun runPendingAnimations() {
//                    TODO("Not yet implemented")
//                }
//
//                override fun endAnimation(item: ViewHolder) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun endAnimations() {
//                    TODO("Not yet implemented")
//                }
//
//                override fun isRunning(): Boolean {
//                    TODO("Not yet implemented")
//                }
//
//            }
        }


        val dataList: List<UserInfo>
            get() = mutableListOf<UserInfo>().apply {

                add(UserInfo(userName = "哈哈", userAge = 11, userSex = 0, address = "北京"))
                add(UserInfo(userName = "哦哦", userAge = 12, userSex = 0, address = "东京"))
                add(UserInfo(userName = "嗯嗯", userAge = 12, userSex = 0, address = "西京"))
                add(UserInfo(userName = "QQ", userAge = 13, userSex = 0, address = "南京"))
                add(UserInfo(userName = "偷偷", userAge = 29, userSex = 0, address = "西安"))
                add(UserInfo(userName = "一样", userAge = 21, userSex = 0, address = "南安"))
                add(UserInfo(userName = "方法", userAge = 31, userSex = 0, address = "北安"))
                add(UserInfo(userName = "不变", userAge = 41, userSex = 0, address = "东安"))
                add(UserInfo(userName = "这种", userAge = 17, userSex = 0, address = "广州"))
                add(UserInfo(userName = "欧尼", userAge = 13, userSex = 0, address = "广南"))
                add(UserInfo(userName = "破破", userAge = 19, userSex = 0, address = "泰西"))
            }


    }


    class UserInfo(override var userName: String, override var userAge: Int, override var userSex: Int, override var address: String, override var choose: Boolean = false) : IUser {


    }


}
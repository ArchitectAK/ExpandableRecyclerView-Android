package com.cogitator.expandable_recyclerview

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.item_parent_child_listing.view.*


/**
 * @author Ankit Kumar on 14/09/2018
 */
class RecyclerViewAdapter(val dummyParentDataItems: MutableList<ParentDataItem>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerViewAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_parent_child_listing, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dummyParentDataItems.size
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        val dummyParentDataItem = dummyParentDataItems[position]
        holder.itemView.tv_parentName.text = dummyParentDataItem.parentName

        holder.bind(dummyParentDataItem)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var context: Context? = null

        override fun onClick(view: View?) {
            if (view?.id == R.id.tv_parentName) {
                if (itemView.ll_child_items?.visibility == View.VISIBLE) {
                    itemView.ll_child_items?.visibility = View.GONE
                } else {
                    itemView.ll_child_items?.visibility = View.VISIBLE
                }
            } else {
                val textViewClicked = view as TextView
                Toast.makeText(context, "" + textViewClicked.text.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(dummyParentDataItem: ParentDataItem) {
            context = itemView.context
            itemView.ll_child_items?.visibility = View.GONE
            val intMaxNoOfChild = dummyParentDataItem.childDataItems.size
//            val intMaxSizeTemp = dummyParentDataItem.childDataItems.size
//            if (intMaxSizeTemp > intMaxNoOfChild)
//                intMaxNoOfChild = intMaxSizeTemp

            for (indexView in 0 until intMaxNoOfChild) {
                val textView = TextView(context)
                textView.id = indexView
                textView.setPadding(0, 20, 0, 20)
                textView.gravity = Gravity.CENTER
                textView.background = ContextCompat.getDrawable(context!!, R.drawable.background_sub_module_text)
                val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                textView.setOnClickListener(this)
                itemView.ll_child_items?.addView(textView, layoutParams)
            }
            itemView.tv_parentName?.setOnClickListener(this)


            val noOfChildTextViews = itemView.ll_child_items?.childCount

            val noOfChild = dummyParentDataItem.childDataItems.size

            if (noOfChild < noOfChildTextViews!!) {
                for (index in noOfChild until noOfChildTextViews) {
                    val currentTextView = itemView.ll_child_items!!.getChildAt(index) as TextView
                    currentTextView.visibility = View.GONE
                }
            }
            for (textViewIndex in 0 until noOfChild) {
                val currentTextView = itemView.ll_child_items!!.getChildAt(textViewIndex) as TextView
                currentTextView.text = dummyParentDataItem.childDataItems[textViewIndex].childName
            }
        }
    }

}
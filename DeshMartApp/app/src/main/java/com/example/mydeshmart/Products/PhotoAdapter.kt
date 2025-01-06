package com.example.mydeshmart.Products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mydeshmart.R

class PhotoAdapter(private val photoList: MutableList<Product>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private var listener: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return PhotoViewHolder(itemView)
    }

    // Adjust getItemCount() to handle odd and even cases correctly
    override fun getItemCount(): Int {
        return if (photoList.size % 2 == 0) {
            photoList.size / 2
        } else {
            photoList.size / 2 + 1
        }
    }

    // Update the data in the adapter
    fun updateData(newPhotos: List<Product>) {
        photoList.clear()
        photoList.addAll(newPhotos)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        // Calculate positions for two items in each row
        val firstOne = 2 * position
        val secondOne = firstOne + 1

        // First photo
        val currentPhoto = photoList[firstOne]
        holder.tvText1.text = currentPhoto.title
        holder.tvPrice1.text = "${currentPhoto.price}$"
        Glide.with(holder.itemView.context)
            .load(currentPhoto.image)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA))  // Cache only the image data
            .into(holder.ivImage1)

        holder.ivImage1.setOnClickListener {
            listener?.invoke(currentPhoto)
        }

        // Second photo (if exists)
        if (secondOne < photoList.size) {
            val currentPhoto1 = photoList[secondOne]
            holder.tvText2.text = currentPhoto1.title
            holder.tvPrice2.text = "${currentPhoto1.price}$"
            Glide.with(holder.itemView.context)
                .load(currentPhoto1.image)
                .into(holder.ivImage2)

            holder.ivImage2.setOnClickListener {
                listener?.invoke(currentPhoto1)
            }
        } else {
            // Hide or reset the second photo views if not available
            holder.tvText2.text = ""
            holder.ivImage2.setImageDrawable(null)
            holder.ivImage2.setOnClickListener(null)
        }
    }

    // Set listener for item click
    fun setOnItemClickListener(listener: (Product) -> Unit) {
        this.listener = listener
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvText1: TextView = itemView.findViewById(R.id.tvText1)
        val ivImage1: ImageView = itemView.findViewById(R.id.ivImage1)
        val tvText2: TextView = itemView.findViewById(R.id.tvText2)
        val ivImage2: ImageView = itemView.findViewById(R.id.ivImage2)
        val tvPrice1: TextView = itemView.findViewById(R.id.tvPrice1)
        val tvPrice2: TextView = itemView.findViewById(R.id.tvPrice2)

        // Bind data for each item (first and second products)
        fun bind(
            photo1: Product,
            photo2: Product?,
            listener: ((Product) -> Unit)?
        ) {
            tvText1.text = photo1.title
            tvPrice1.text = "${photo1.price}$"
            Glide.with(itemView.context).load(photo1.image).into(ivImage1)

            if (photo2 != null) {
                tvText2.text = photo2.title
                tvPrice2.text = "${photo2.price}$"
                Glide.with(itemView.context).load(photo2.image).into(ivImage2)
            } else {
                tvText2.text = ""
                ivImage2.setImageDrawable(null)
                ivImage2.setOnClickListener(null)
            }

            itemView.setOnClickListener {
                listener?.invoke(photo1)
            }
        }
    }
}

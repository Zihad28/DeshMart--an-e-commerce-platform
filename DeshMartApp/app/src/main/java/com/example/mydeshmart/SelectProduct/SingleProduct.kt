package com.example.mydeshmart.SelectProduct

import android.os.Parcel
import android.os.Parcelable

data class SingleProduct(
    val title: String,
    val price: Double,
    val description: String,
    val image: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString()?:"",
        parcel.readString()?:""
    ) {

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(title)
        parcel.writeDouble(price)
        parcel.writeString(description)
        parcel.writeString(image)
    }

    companion object CREATOR : Parcelable.Creator<SingleProduct> {
        override fun createFromParcel(parcel: Parcel): SingleProduct {
            return SingleProduct(parcel)
        }

        override fun newArray(size: Int): Array<SingleProduct?> {
            return arrayOfNulls(size)
        }
    }
}


package com.cbedoya.jsonplaceholder.viewmodel

import android.os.Parcel
import android.os.Parcelable

data class PostViewModel (
    val title: String,
    val description: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostViewModel> {
        override fun createFromParcel(parcel: Parcel): PostViewModel {
            return PostViewModel(parcel)
        }

        override fun newArray(size: Int): Array<PostViewModel?> {
            return arrayOfNulls(size)
        }
    }
}
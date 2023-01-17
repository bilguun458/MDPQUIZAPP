package edu.miu.quiz.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
@Entity(tableName = "quiz")
data class Quiz(
    @PrimaryKey
    val id: Long,
    val question: String,
    val a: String,
    val b: String,
    val c: String,
    val d: String,
    val answer: String,
):Serializable, Parcelable
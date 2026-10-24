package io.github.mobdevchimp.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val dailyStreak: Int = 0,
    val dateJoined: LocalDate = LocalDate.now()
)

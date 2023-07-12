package ro.alexmamo.firestorechatgpt.domain.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Question(
    var prompt: String? = null,
    @ServerTimestamp
    var addedAt: Date? = null,
    var addedBy: String? = null,
    var response: String? = null,
)
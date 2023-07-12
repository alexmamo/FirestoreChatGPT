package ro.alexmamo.firestorechatgpt.di

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ro.alexmamo.firestorechatgpt.core.QUESTIONS
import ro.alexmamo.firestorechatgpt.data.repository.ChatRepositoryImpl
import ro.alexmamo.firestorechatgpt.domain.repository.ChatRepository

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {
    @Provides
    fun provideQuestionsRef(): ChatRepository = ChatRepositoryImpl(
        questionsRef = Firebase.firestore.collection(QUESTIONS)
    )
}
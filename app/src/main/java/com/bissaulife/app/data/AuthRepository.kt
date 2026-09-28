package com.bissaulife.app.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    val usuarioAtual: FirebaseUser?
        get() = auth.currentUser

    val estaLogado: Boolean
        get() = auth.currentUser != null

    fun idUsuario(): String = auth.currentUser?.uid ?: ""

    fun emailUsuario(): String = auth.currentUser?.email ?: ""

    suspend fun cadastrar(email: String, senha: String): Result<FirebaseUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, senha).await()
            val user = result.user
            if (user != null) Result.success(user)
            else Result.failure(Exception("Usuario nao criado"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, senha: String): Result<FirebaseUser> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, senha).await()
            val user = result.user
            if (user != null) Result.success(user)
            else Result.failure(Exception("Login falhou"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() {
        auth.signOut()
    }
}

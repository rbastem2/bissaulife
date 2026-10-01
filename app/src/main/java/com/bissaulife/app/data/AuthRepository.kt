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

    // Retorna se o email foi verificado
    fun emailVerificado(): Boolean = auth.currentUser?.isEmailVerified ?: false

    // Recarrega os dados do usuario
    suspend fun recarregarUsuario(): Boolean {
        return try {
            auth.currentUser?.reload()?.await()
            true
        } catch (e: Exception) {
            false
        }
    }

    // Cadastra um novo usuario e envia email de verificacao
    suspend fun cadastrar(email: String, senha: String): Result<FirebaseUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, senha).await()
            val user = result.user
            if (user != null) {
                try {
                    user.sendEmailVerification().await()
                } catch (e: Exception) {
                }
                Result.success(user)
            } else {
                Result.failure(Exception("Usuario nao criado"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Reenvia o email de verificacao
    suspend fun reenviarVerificacao(): Result<Unit> {
        return try {
            val user = auth.currentUser
            if (user != null) {
                user.sendEmailVerification().await()
                Result.success(Unit)
            } else {
                Result.failure(Exception("Usuario nao logado"))
            }
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

    // Envia email de recuperacao de senha
    suspend fun recuperarSenha(email: String): Result<Unit> {
        return try {
            auth.sendPasswordResetEmail(email.trim()).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() {
        auth.signOut()
    }
}

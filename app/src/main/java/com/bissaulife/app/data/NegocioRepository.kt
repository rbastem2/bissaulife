package com.bissaulife.app.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class NegocioRepository {

    private val db = FirebaseFirestore.getInstance()
    private val colecao = db.collection("negocios")

    // Cadastrar novo negocio (vai como "pendente")
    suspend fun cadastrar(negocio: Negocio): Result<String> {
        return try {
            val doc = colecao.document()
            val novo = negocio.copy(
                id = doc.id,
                status = "pendente",
                dataCadastro = System.currentTimeMillis()
            )
            doc.set(novo).await()
            Result.success(doc.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Listar negocios aprovados (para o app do usuario final)
    suspend fun listarAprovados(): Result<List<Negocio>> {
        return try {
            val snapshot = colecao
                .whereEqualTo("status", "aprovado")
                .orderBy("destaque", Query.Direction.DESCENDING)
                .get()
                .await()

            val lista = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Negocio::class.java)
            }
            Result.success(lista)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Listar negocios por categoria
    suspend fun listarPorCategoria(categoria: String): Result<List<Negocio>> {
        return try {
            val snapshot = colecao
                .whereEqualTo("status", "aprovado")
                .whereEqualTo("categoria", categoria)
                .orderBy("destaque", Query.Direction.DESCENDING)
                .get()
                .await()

            val lista = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Negocio::class.java)
            }
            Result.success(lista)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Listar negocios pendentes (para o painel admin)
    suspend fun listarPendentes(): Result<List<Negocio>> {
        return try {
            val snapshot = colecao
                .whereEqualTo("status", "pendente")
                .orderBy("dataCadastro", Query.Direction.DESCENDING)
                .get()
                .await()

            val lista = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Negocio::class.java)
            }
            Result.success(lista)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Aprovar negocio
    suspend fun aprovar(id: String): Result<Unit> {
        return try {
            colecao.document(id).update(
                "status", "aprovado",
                "dataAprovacao", System.currentTimeMillis()
            ).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Rejeitar negocio
    suspend fun rejeitar(id: String): Result<Unit> {
        return try {
            colecao.document(id).update("status", "rejeitado").await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Ativar/desativar destaque (patrocinado)
    suspend fun definirDestaque(id: String, destaque: Boolean, plano: String): Result<Unit> {
        return try {
            colecao.document(id).update(
                "destaque", destaque,
                "planoDestaque", plano
            ).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

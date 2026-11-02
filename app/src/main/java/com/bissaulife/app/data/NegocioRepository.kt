package com.bissaulife.app.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class NegocioRepository {

    private val db = FirebaseFirestore.getInstance()
    private val colecao = db.collection("negocios")

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

    suspend fun listarTodos(): Result<List<Negocio>> {
        return try {
            val snapshot = colecao.get().await()
            val lista = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Negocio::class.java)
            }
            Result.success(lista)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ATUALIZAR DADOS (NOVO)
    suspend fun atualizar(negocio: Negocio): Result<Unit> {
        return try {
            // Atualiza todos os campos menos o ID e o Status (para nao quebrar a logica)
            val dados = mapOf(
                "nome" to negocio.nome,
                "categoria" to negocio.categoria,
                "subcategoria" to negocio.subcategoria,
                "descricao" to negocio.descricao,
                "telefone" to negocio.telefone,
                "whatsapp" to negocio.whatsapp,
                "endereco" to negocio.endereco,
                "preco" to negocio.preco,
                "imagemUrl" to negocio.imagemUrl
            )
            colecao.document(negocio.id).update(dados).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun aprovar(id: String): Result<Unit> {
        return try {
            colecao.document(id).update("status", "aprovado", "dataAprovacao", System.currentTimeMillis()).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun rejeitar(id: String): Result<Unit> {
        return try {
            colecao.document(id).update("status", "rejeitado").await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun arquivar(id: String): Result<Unit> {
        return try {
            colecao.document(id).update("status", "arquivado").await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun restaurar(id: String): Result<Unit> {
        return try {
            colecao.document(id).update("status", "aprovado").await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deletar(id: String): Result<Unit> {
        return try {
            colecao.document(id).delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun definirDestaque(id: String, destaque: Boolean, plano: String): Result<Unit> {
        return try {
            colecao.document(id).update("destaque", destaque, "planoDestaque", plano).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

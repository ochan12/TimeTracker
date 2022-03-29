package com.example.timetracker.persistance.remote

import com.example.timetracker.persistance.TaskSource
import com.example.timetracker.task.Task
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class TaskRemoteSource @Inject constructor(
    val db: FirebaseFirestore
) : TaskSource {
    override fun getTask(id: String): Single<Task> {
        var observable: Single<Task> = Single.never()
        val ref = db.collection(Collections().tasks).whereEqualTo("id", id).limit(1).get();
        ref.addOnCompleteListener {
            observable =
                Single.just(it.result?.documents?.toList()?.get(0)?.toObject(Task::class.java)!!)
        }
        return observable
    }

    override fun loadTasks(user: String): Observable<List<Task>> {
        TODO("Not yet implemented")
    }

    override fun loadTasks(): Observable<Task> {
        TODO("Not yet implemented")
    }

    override fun saveTask(task: Task): String {
        TODO("Not yet implemented")
    }

}
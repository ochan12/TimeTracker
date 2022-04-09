package com.example.timetracker.persistance.remote

import com.example.timetracker.helpers.Taggable
import com.example.timetracker.persistance.TaskSource
import com.example.timetracker.task.Task
import com.example.timetracker.task.TimeInterval
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Observable
import org.joda.time.DateTime
import javax.inject.Inject
import kotlin.collections.HashMap

class TaskRemoteSource @Inject constructor(
    val db: FirebaseFirestore
) : TaskSource(), Taggable {
    override fun getTask(id: String): Observable<Task?> {
        val ref = db.collection(this.collection).document(id).get()
        return Observable.create { emitter ->
            ref.addOnCompleteListener { doc ->
                val task = doc.result.toObject(Task::class.java)
                if (task != null) {
                    task.setId(doc.result.id)
                }

                emitter.onNext(task)
            }
        }
    }

    override fun loadTasks(user: String): Observable<List<Task>> {
        val ref = db.collection(this.collection).whereEqualTo("userId", user).get()
        return Observable.create { emitter ->
            ref.addOnCompleteListener {
                it.result?.map { doc ->
                    val task = doc.toObject(Task::class.java)
                    task.setId(doc.id)
                    task
                }?.let { it1 -> emitter.onNext(it1) }
            }
        }
    }

    override fun loadTasks(): Observable<Task> {
        TODO("Not yet implemented")
    }

    override fun saveTask(task: Task): Observable<String> {
        if(task.getId() !== null && task.getId() != ""){
            return Observable.create { emitter ->
                db.collection(this.collection).document(task.getId()!!).set(task).addOnCompleteListener {
                    emitter.onNext(task.getId()!!)
                }
            }
        }
        return Observable.create { emitter ->
            db.collection(this.collection).add(task).addOnCompleteListener {
                emitter.onNext(it.result?.id!!)
            }
        }
    }

    override fun convertToSourceTask(task: Task): HashMap<String, Any?> {
        val map = HashMap<String, Any?>()
        map["startTime"] = task.getStartTime()
        map["endTime"] = task.getEndTime()
        map["description"] = task.getDescription()
        map["timeIntervals"] = task.getTimeIntervals().map {
            val timeIntervalMap = HashMap<String, Long?>()
            timeIntervalMap["startTime"] = it.getStartTime()
            timeIntervalMap["endTime"] = it.getEndTime()
        }
        map["userId"] = task.getUserId()
        map["space"] = task.getSpace()
        map["id"] = "${task.getUserId()}_${task.getSpace()}_${DateTime().millis}"
        return map
    }

    override fun <T : HashMap<String, Any?>> convertToAppTask(task: T): Task {
        val newTask = Task()
        newTask.setDescription(task["description"] as String)
        newTask.setStartTime(task["startTime"] as Long)
        newTask.setEndTime(task["endTime"] as Long)
        newTask.setSpace(task["space"] as String)
        newTask.setTimeIntervals(
            (task["timeIntervals"] as List<HashMap<String, Long>>).map {
                TimeInterval(
                    it["startTime"],
                    it["endTime"],
                )
            } as MutableList<TimeInterval>,
        )
        return newTask
    }
}
package com.example.myapplication_caching.domain.back_ground

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myapplication_caching.data.db.FilmsDatabase
import com.example.myapplication_caching.data.db.getDataBase
import com.example.myapplication_caching.data.db.model.FilmDatabaseModel
import com.example.myapplication_caching.data.mappers.asDatabaseModel
import com.example.myapplication_caching.data.models.FilmsNetworkModel
import com.example.myapplication_caching.domain.repo.AppRepository
import java.util.concurrent.TimeUnit


class RepositoryWorker(private val context: Context, private val repository: AppRepository) {

    private val workManager = WorkManager.getInstance(context)

    fun start() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val request = PeriodicWorkRequestBuilder<CachingWorker>(3, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()
        workManager.enqueueUniquePeriodicWork(
            "my_caching_worker",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }

}


class CachingWorker (private val context: Context, params: WorkerParameters) : CoroutineWorker(context, params){
    override suspend fun doWork(): Result {
        val repository = AppRepository(getDataBase(context = context))
        val result = repository.getDataMultiApis()
        if (result.isSuccess) {
            val data = result.getOrNull()
            if (data != null) {
                val dataList = ArrayList<FilmDatabaseModel>()
                val database = getDataBase(applicationContext)
                dataList.addAll(data.first.asDatabaseModel())
                dataList.addAll(data.first.asDatabaseModel())
                database.filmsDao.saveToDatabase(* dataList.toTypedArray())

                Log.i("Worker", "doWork: ${data.first.toString()}")
                return Result.success()
            }
        }
        return Result.failure()
    }

}

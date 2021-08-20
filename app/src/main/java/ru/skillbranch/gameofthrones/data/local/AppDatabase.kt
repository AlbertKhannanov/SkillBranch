package ru.skillbranch.gameofthrones.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database;
import ru.skillbranch.gameofthrones.data.local.dao.CharacterDao
import ru.skillbranch.gameofthrones.data.local.dao.HouseDao
import ru.skillbranch.gameofthrones.data.local.dao.HouseWithCharacterDao
import ru.skillbranch.gameofthrones.data.local.models.CharacterDb
import ru.skillbranch.gameofthrones.data.local.models.HouseDb
import ru.skillbranch.gameofthrones.data.local.models.relations.HouseCharacterCrossRef
import kotlin.jvm.Volatile;

@Database(entities = [HouseDb::class, CharacterDb::class, HouseCharacterCrossRef::class], version = 6)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getHouseDao(): HouseDao
    abstract fun getCharacterDao(): CharacterDao
    abstract fun getHouseWithCharacterDao(): HouseWithCharacterDao

    companion object {
        private const val DATABASE_NAME = "skillbranch"

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}


package br.tech.cirdan.iota.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.tech.cirdan.iota.data.model.Anime

@Database(entities = [Anime::class, RemoteKeys::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun animeDao(): AnimeDAO
    abstract fun remoteKeysDao(): RemoteKeysDAO
}

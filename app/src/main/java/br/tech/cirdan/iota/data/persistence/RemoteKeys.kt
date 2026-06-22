package br.tech.cirdan.iota.data.persistence

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey val itemId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)

@Dao
interface RemoteKeysDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE itemId = :id")
    suspend fun remoteKeysId(id: Int): RemoteKeys?

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()

    @Query("SELECT COUNT(*) FROM remote_keys")
    suspend fun count(): Int
}

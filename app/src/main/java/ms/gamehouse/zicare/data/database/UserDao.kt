package ms.gamehouse.zicare.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ms.gamehouse.zicare.data.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: User)

    @Query("SELECT * FROM tb_user WHERE isLogin = 1")
    suspend fun getLoggedUser(): User
}
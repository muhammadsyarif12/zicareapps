package ms.gamehouse.zicare.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.UUID

@Entity(
    tableName = "tb_user"
)

data class User(
    @PrimaryKey
    @SerializedName("userName")
    val userName: String,
    @SerializedName("userFullName")
    val userFullName: String,
    @SerializedName("userEmail")
    val userEmail: String,
    @SerializedName("userPassword")
    val userPassword: String,
    @SerializedName("isLogin")
    val isLogin: Boolean
)

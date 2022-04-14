

package com.team.smileapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.team.smileapplication.data.model.User

/**
 * Interface for database access on Repo related operations.
 */


@Dao
abstract class UMSDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUsers(vararg users: User)

     @Insert(onConflict = OnConflictStrategy.REPLACE)
      abstract fun insertUsers( users: List<User>)

      @Query(" SELECT * FROM User")
      abstract fun loadUsers(): LiveData<List<User>>



}

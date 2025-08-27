package com.example.myapplication.database

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performBlocking
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class FavoriteMovieDao_Impl(
  __db: RoomDatabase,
) : FavoriteMovieDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfFavoriteMovie: EntityInsertAdapter<FavoriteMovie>

  private val __deleteAdapterOfFavoriteMovie: EntityDeleteOrUpdateAdapter<FavoriteMovie>
  init {
    this.__db = __db
    this.__insertAdapterOfFavoriteMovie = object : EntityInsertAdapter<FavoriteMovie>() {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `favorite_movie` (`id`,`title`,`poster_url`,`release_year`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: FavoriteMovie) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.title)
        statement.bindText(3, entity.posterUrl)
        statement.bindText(4, entity.releaseYear)
      }
    }
    this.__deleteAdapterOfFavoriteMovie = object : EntityDeleteOrUpdateAdapter<FavoriteMovie>() {
      protected override fun createQuery(): String = "DELETE FROM `favorite_movie` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: FavoriteMovie) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
  }

  public override fun insertAll(vararg movie: FavoriteMovie): Unit = performBlocking(__db, false,
      true) { _connection ->
    __insertAdapterOfFavoriteMovie.insert(_connection, movie)
  }

  public override fun delete(movie: FavoriteMovie): Unit = performBlocking(__db, false, true) {
      _connection ->
    __deleteAdapterOfFavoriteMovie.handle(_connection, movie)
  }

  public override fun getAll(): List<FavoriteMovie> {
    val _sql: String = "SELECT * FROM favorite_movie"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfReleaseYear: Int = getColumnIndexOrThrow(_stmt, "release_year")
        val _result: MutableList<FavoriteMovie> = mutableListOf()
        while (_stmt.step()) {
          val _item: FavoriteMovie
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpPosterUrl: String
          _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          val _tmpReleaseYear: String
          _tmpReleaseYear = _stmt.getText(_columnIndexOfReleaseYear)
          _item = FavoriteMovie(_tmpId,_tmpTitle,_tmpPosterUrl,_tmpReleaseYear)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}

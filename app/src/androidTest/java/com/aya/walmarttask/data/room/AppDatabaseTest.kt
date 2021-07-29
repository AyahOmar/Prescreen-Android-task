package com.aya.walmarttask.data.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aya.walmarttask.data.Dao.AnimeDao
import com.aya.walmarttask.data.model.Anime
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest: TestCase(){

    private  lateinit var db: AppDatabase
    private lateinit var dao: AnimeDao


    @Before
    public override fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()

        //Temporary for testing
        db= Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.animeDao()
    }

    // close DB after tesring
    @After
    fun closeDP(){

        db.close()
    }


    @Test
    fun writeAndReadAnime() = runBlocking{

        //Test Data
        val animeList = listOf<Anime>(   Anime(
                1 , "test string", "test string img",
                "test title", true ,"test synopsis",
                "movie",1,4.7,"2002-10-03T00:00:00+00:00",
                "2007-02-08T00:00:00+00:00",1111,"G"),
                Anime(
                        2 , "test string", "test string img",
                        "test title", true ,"test synopsis",
                        "movie",1,4.7,"2002-10-03T00:00:00+00:00",
                        "2007-02-08T00:00:00+00:00",1111,"G"))


        dao.insertAll(animeList)

        val animes = dao.getAll()
        assertThat(animes.contains(animes).toString() ,true)


    }
}
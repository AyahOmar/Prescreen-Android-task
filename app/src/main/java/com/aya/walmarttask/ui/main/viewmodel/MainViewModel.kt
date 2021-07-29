package com.aya.walmarttask.ui.main.viewmodel

import androidx.lifecycle.*
import com.aya.walmarttask.data.model.Anime
import com.aya.walmarttask.data.repository.MainRepository
import com.aya.walmarttask.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (private val mainRepository: MainRepository) : ViewModel() {

    private val animes = MutableLiveData<Resource<List<Anime>>>()



    //Fetch data from Api for the first time then caching the data using room database
    //Home screen data
    private fun fetchAnimes() {
        viewModelScope.launch {
            animes.postValue(Resource.loading(null))
            try {
                val animesFromDb = mainRepository.getAnimeDB()
                if (animesFromDb.isEmpty()) {
                    val animesFromApi = mainRepository.getAnime("naruto").results
                    val animesToInsertInDB = mutableListOf<Anime>()

                    for (apiAnime in animesFromApi) {
                        val anime = Anime(
                            apiAnime.mal_id,
                            apiAnime.url,
                            apiAnime.image_url,
                            apiAnime.title,
                            apiAnime.airing,
                            apiAnime.synopsis,
                            apiAnime.type,
                            apiAnime.episodes,
                            apiAnime.score,
                            apiAnime.start_date,
                            apiAnime.end_date,
                            apiAnime.members,
                            apiAnime.rated
                        )
                        animesToInsertInDB.add(anime)
                    }

                    mainRepository.insertANimeDB(animesToInsertInDB)

                    animes.postValue(Resource.success(animesToInsertInDB))

                } else {
                    animes.postValue(Resource.success(animesFromDb))
                }


            } catch (e: Exception) {
                animes.postValue(Resource.error(null,e.message.toString()))
            }
        }
    }

    fun getAnimes(): LiveData<Resource<List<Anime>>> {
        fetchAnimes()
        return animes
    }



    fun searchAnime(query: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getAnime(query)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}





//getAnimes
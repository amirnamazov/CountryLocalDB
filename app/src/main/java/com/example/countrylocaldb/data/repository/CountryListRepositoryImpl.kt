package com.example.countrylocaldb.data.repository

import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity
import com.example.countrylocaldb.data.data_source.remote.api.CountryListApi
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import com.example.countrylocaldb.domain.repository.CountryListRepository
import io.objectbox.Box
import io.objectbox.query.Query
import retrofit2.Response
import javax.inject.Inject


class CountryListRepositoryImpl @Inject constructor(
    private val api: CountryListApi,
    private val box: Box<PeopleEntity>
) : CountryListRepository {

    override suspend fun getCountryList(): Response<CountryListDTO> = api.getData()

    override fun putCountriesToBox(entities: List<PeopleEntity>) = box.put(entities)

    override fun getQuery(): Query<PeopleEntity> = box.query().build()

//    init {
//        CoroutineScope(Dispatchers.Unconfined).launch {
//            delay(5000)
//            box.remove(PeopleEntity(1))
//            delay(2000)
//            box.put(PeopleEntity(1, "A", "B"))
//
//            box.put(CountryEntity())
//            println("232432321  8989  ${box.all.size}")
//        }
//    }
}
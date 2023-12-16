package com.example.countrylocaldb.data.repository

import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity_
import com.example.countrylocaldb.data.data_source.remote.api.CountryListApi
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import com.example.countrylocaldb.domain.repository.CountryListRepository
import io.objectbox.Box
import io.objectbox.query.Query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


class CountryListRepositoryImpl @Inject constructor(
    private val api: CountryListApi,
    private val box: Box<CountryEntity>
) : CountryListRepository {

    override suspend fun getCountryList(): Response<CountryListDTO> = api.getData()

    override fun putCountriesToBox(entities: List<CountryEntity>) = box.put(entities)

    override fun getQueryCountry(): Query<CountryEntity> = box.query().build()

    init {
        CoroutineScope(Dispatchers.Default).launch {
            delay(2000)

            val builder = box.query(CountryEntity_.countryId.equal(1))
//            builder.link(CountryEntity_.cityList).apply(CityEntity_.cityId.equal(1))
            val list = builder.build().find()

//            builder.build().findFirst()!!.cityList.filter {  }

            val result = box.all.flatMap {
                it.cityList
//                    .flatMap { it.peopleList }
            }


//            val result = box.query()
//                .eager(CityEntity_.peopleList)
//                .build()
//                .find()

            println("65765765    ${result}")
        }
    }
}
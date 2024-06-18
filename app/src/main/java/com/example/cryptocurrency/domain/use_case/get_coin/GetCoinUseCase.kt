package com.example.cryptocurrency.domain.use_case.get_coin

import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.data.remote.dto.toCoin
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetail
import com.example.cryptocurrency.domain.repository.CoinRepository
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoinDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke(coindId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coinDetail = repository.getCoinById(coindId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coinDetail))
        } catch (e: HttpException) { // errors
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) { //no internet connection
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Check your internet connection. "))
        }
    }

}
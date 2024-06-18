package com.example.cryptocurrency.domain.repository

import com.example.cryptocurrency.data.remote.dto.CoinDto
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDetailDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto

}
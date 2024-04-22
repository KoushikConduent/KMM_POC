package com.kmm_poc.common.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginEntity(
    @SerialName("userName")
    val userName: String,
    @SerialName("recentTransactionList")
    val recentTransactionList: List<RecentTransaction>,
    @SerialName("accountStatus")
    val accountStatus: String,
    @SerialName("accountBalance")
    val accountBalance: String,
    @SerialName("accountNumber")
    val accountNumber: String,
    @SerialName("TopUpAmount")
    val topUpAmount: String,
    @SerialName("LowBalanceThrershold")
    val lowBalanceThreshold: String,
    @SerialName("AutoPay")
    val autoPay: String,
    @SerialName("status")
    val status: String,
    @SerialName("errorMessageCode")
    val errorMessageCode: String
)

@Serializable
data class RecentTransaction(
    @SerialName("topUp")
    val topUp: String,
    @SerialName("NorthBound")
    val northBound: String
)
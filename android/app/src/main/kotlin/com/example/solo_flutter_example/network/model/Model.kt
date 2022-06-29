package com.example.solo_flutter_example.network.model

data class Model(
    val address: Address,
    val address_id: Any,
    val dob: String,
    val email_address: String,
    val email_verified: Boolean,
    val first_name: String,
    val lastLogin: Long,
    val last_name: String,
    val loginCount: Int,
    val phone_number: String,
    val phone_verified: Any,
    val picture: Any,
    val source: String,
    val ssn: Any,
    val ssn_last_four: String,
    val state: String,
    val stripe_account_id: String,
    val user_id: Int
)
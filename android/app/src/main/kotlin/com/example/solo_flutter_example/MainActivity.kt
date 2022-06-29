package com.example.solo_flutter_example

import android.util.Log
import androidx.annotation.NonNull
import com.example.solo_flutter_example.network.api.API
import com.example.solo_flutter_example.network.api.RetrofitHelper
import com.example.solo_flutter_example.network.model.Model
import com.google.gson.JsonElement
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity: FlutterActivity() {

    private val CHANNEL = "com.example.solo_flutter_example/getUserDetails"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
                call, result ->
            // This method is invoked on the main thread.
            // TODO
    val arguments= call.arguments as Map<String,String>
            val token=arguments["access_token"]


            val quotesApi = RetrofitHelper.getInstance().create(API::class.java)
            // launching a new coroutine
            GlobalScope.launch(Dispatchers.Main) {

               val resu = token?.let { getHeaderMap(it) }?.let { quotesApi.getQuotes(it) }
                if (resu!= null) {

                    val model: Model? = resu?.body()
                  //  val name=model?.first_name.toString()
                    Log.d("logmeee: ", model?.first_name.toString())

                    val map = mapOf("first_name" to "${ model?.first_name.toString()}")

                   // result.success(model?.first_name.toString())
                    result.success(map)

                }
            }
        }
    }
    private fun getHeaderMap(tokan: String): Map<String, String> {
        val headerMap = mutableMapOf<String, String>()
        headerMap["Content-Type"] = "application/json"
        headerMap["accept"] = "application/json"
        headerMap["Authorization"] = "Bearer $tokan"
        return headerMap
    }
}

package com.nanaland.util.log

import android.util.Log
import com.nanaland.util.network.NetworkResult
import com.nanaland.BuildConfig

object LogUtil {
    fun printNetworkLog(request: Any?, response: NetworkResult<Any>, name: String) {
        when (response) {
            is NetworkResult.Success -> {
                printLog("NetworkResultLog", "$name Success\n[request]\n" +
                        "$request\n[response code]\n" +
                        "${response.code}\n[response data]\n" +
                        "${response.data}\n-")
            }
            is NetworkResult.Error -> {
                printLog("NetworkResultLog", "$name Error\n[request]\n" +
                        "$request\n[response code]\n" +
                        "${response.code}\n" +
                        "[response data]\n" +
                        "${response.message}\n-")
            }
            is NetworkResult.Exception -> {
                printLog("NetworkResultLog", "$name Exception\n" +
                        "[request]\n" +
                        "$request\n" +
                        "[error]\n" +
                        "${response.e}\n" +
                        "-")
            }
        }
    }

    private fun printLog(tag: String, message: String) {
        if (BuildConfig.DEBUG) Log.e(tag, message) else Unit
    }
}
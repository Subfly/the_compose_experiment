package dev.subfly.kmpandroidcmpktor.util.measure

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

private const val END_PONT = "http://YOUR_MACHINE_LOCAL_IP/upload"

object FileUploader {
    private val client by lazy {
        HttpClient(Android) {

        }
    }

    fun uploadFile(file: File) {
        CoroutineScope(Dispatchers.IO).launch {
            client.post {
                url("$END_PONT/RENAME_BY_APP__ADD_TEST_TYPE_${file.name}")
                setBody(
                    MultiPartFormDataContent(
                        formData {
                            append(
                                "file",
                                file.readBytes(),
                                Headers.build {
                                    append(
                                        HttpHeaders.ContentType,
                                        "text/csv"
                                    )
                                    append(
                                        HttpHeaders.ContentDisposition,
                                        "filename=\"${file.name}\""
                                    )
                                }
                            )
                        }
                    )
                )
            }
            file.delete()
        }
    }
}

package com.mdevor.littlelemon.testhelpers.mockhelper

import java.io.FileNotFoundException
import java.net.URL

fun mockResponse(filePath: String): String {
    val resourceUrl: URL? = ClassLoader.getSystemResource(filePath)
    return resourceUrl?.readText() ?: throw FileNotFoundException("File path: $filePath")
}
package com.mdevor.littlelemon.data.datasource

import android.content.Context
import com.mdevor.littlelemon.data.local.sharedpref.LittleLemonSharedPrefs
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class LittleLemonSharedPrefsTest {

    private val contextMock = mockk<Context>(relaxed = true)

    @Test
    fun `WHEN build THEN assert getSharedPreferences is called expected params`() {
        // WHEN
        LittleLemonSharedPrefs.build(contextMock)

        // THEN
        verify(exactly = 1) {
            contextMock.getSharedPreferences("littlelemon_shared_prefs", Context.MODE_PRIVATE)
        }
    }
}
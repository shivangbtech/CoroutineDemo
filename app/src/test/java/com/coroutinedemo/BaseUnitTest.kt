package com.coroutinedemo

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import com.coroutinedemo.network.NetworkClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.Answer
import org.powermock.api.mockito.PowerMockito.*
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.Retrofit

@RunWith(PowerMockRunner::class)
@PowerMockIgnore("javax.net.ssl.*")
@PrepareForTest(
    Log::class,
    Handler::class,
    Looper::class,
    TextUtils::class,
    BuildConfig::class
)
abstract class BaseUnitTest {

    lateinit var context: Context
    lateinit var mockWebServer: MockWebServer
    lateinit var retrofit: Retrofit

    @Before
    @Throws(Exception::class)
    open fun setUp() {
        // Initializes the mock environment
        MockitoAnnotations.initMocks(this)

        // Initializes the mock webserver
        mockWebServer = MockWebServer()
        startMockWebserver()

        // Mocks the generic android dependencies such as Context, Looper, etc.
        mockAndroidDependencies()

        // Mocks android logs
        mockLogs()

        // Initializes the retrofit dependencies
        initDependencies()
    }

    @After
    open fun tearDown() {
        stopMockWebserver()
    }

    /**
     * Method which starts the mockwebserver
     */
    private fun startMockWebserver() {
        mockWebServer.start(1245)
    }

    /**
     * Method which stops the mock webserver
     */
    private fun stopMockWebserver() {
        mockWebServer.shutdown()
    }


    /**
     * This function will mock all the android Log related dependencies
     */
    private fun mockLogs() {
        mockStatic(Log::class.java)
        val logAnswer = Answer<Void> { invocation ->
            val tag = invocation.arguments[0] as String
            val msg = invocation.arguments[1] as String
            println(invocation.method.name.toUpperCase() + "/[" + tag + "] " + msg)
            null
        }
        doAnswer(logAnswer).`when`(Log::class.java, "d", anyString(), anyString())
        doAnswer(logAnswer).`when`(Log::class.java, "i", anyString(), anyString())
        doAnswer(logAnswer).`when`(Log::class.java, "w", anyString(), anyString())
        doAnswer(logAnswer).`when`(Log::class.java, "e", anyString(), anyString())
        doAnswer(logAnswer).`when`(Log::class.java, "wtf", anyString(), anyString())

        doAnswer { invocation ->
            val s = invocation.arguments[0] as String
            s.isEmpty()
        }.`when`(TextUtils::class.java, "isEmpty", anyString())

    }

    /**
     * This method initializes the retrofit module
     */
    private fun initDependencies() {
        val serverUrl = mockWebServer.url("/").toString()
        retrofit = NetworkClient.getNetworkClient()
    }

    private fun mockAndroidDependencies() {
        context = mock(Context::class.java)
        mockStatic(Looper::class.java)
        mockStatic(Handler::class.java)
        mockStatic(TextUtils::class.java)
        `when`(Looper.getMainLooper()).thenReturn(null)
        whenNew(Handler::class.java).withAnyArguments().thenReturn(null)
    }
}
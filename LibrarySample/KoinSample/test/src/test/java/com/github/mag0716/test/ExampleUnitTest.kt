package com.github.mag0716.test

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : KoinTest {

    val helloService: HelloService by inject()

    @Before
    fun setUp() {
        startKoin(listOf(AppModule.appModule))
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun hello() {
        assertThat(helloService.hello(), `is`("hello(test)"))
    }
}

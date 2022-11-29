package com.mykhailo.vasylenko.common.tests.event

import com.mykhailo.vasylenko.common.event.DataEvent
import io.mockk.clearAllMocks
import io.mockk.junit5.MockKExtension
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
@DisplayName("NavigationDataEvent callback verification")
internal class DataEventTest {

    private val arbitraryCallbackData = "123"
    private val toDoCallback: (String) -> Unit = spyk()

    @BeforeEach
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `should trigger a callback when consume function invoked`() {
        //Given a data event
        val event = DataEvent(arbitraryCallbackData)
        //When the consume function called
        event.consume(toDoCallback)
        //Then the callback should be triggered
        verify { toDoCallback.invoke(arbitraryCallbackData) }
    }

    @Test
    fun `should trigger a callback only once when consume function invoked multiple times`() {
        //Given a data event
        val event = DataEvent(arbitraryCallbackData)
        //When the consume function called multiple times
        repeat(3) {
            event.consume(toDoCallback)
        }
        //Then the callback should be triggered only once
        verify(exactly = 1) { toDoCallback(arbitraryCallbackData) }
    }

}
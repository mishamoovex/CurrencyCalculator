package com.mykhailo.vasylenko.common.tests.util

import androidx.lifecycle.ViewModel
import com.mykhailo.vasylenko.common.vm.executeAction
import com.mykhailo.vasylenko.common.util.UnconfinedTestDispatcherExtension
import io.mockk.clearAllMocks
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(UnconfinedTestDispatcherExtension::class)
@DisplayName("Execute ViewModel action function")
internal class ExecuteActionTest {

    private class TestViewModel : ViewModel()

    private lateinit var viewModel: ViewModel
    private val callback: () -> Unit = spyk()

    @BeforeEach
    fun setup() {
        viewModel = TestViewModel()
    }

    @AfterEach
    fun clean() {
        clearAllMocks()
    }

    @Test
    fun `should trigger onLoading callback when execution starts`() = runTest {
        //When the executeAction method launched
        viewModel.executeAction(
            onLoading = { callback.invoke() },
            toDo = {}
        )
        //Than the onLoading callback should be triggered
        verify { callback.invoke() }
    }

    @Test
    fun `should trigger toDo callback when execution starts`() = runTest {
        //When the executeAction method launched
        viewModel.executeAction(
            toDo = { callback.invoke() }
        )
        //Than the to DO callback should be triggered
        verify { callback.invoke() }
    }

    @Test
    fun `should trigger onError callback when exception thrown`() = runTest {
        //When the executeAction method launched
        viewModel.executeAction(
            onError = { callback.invoke() },
            toDo = { throw  IllegalArgumentException() }
        )
        //Than the onError callback should be triggered
        verify { callback.invoke() }
    }

}
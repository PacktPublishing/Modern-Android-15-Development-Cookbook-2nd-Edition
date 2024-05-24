package com.madona.chapter_two

import com.madona.chapter_two.app_two.AnimationViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AnimationViewModelTest {

    private lateinit var viewModel: AnimationViewModel

    @Before
    fun setup() {
        viewModel = AnimationViewModel()
    }

    @Test
    fun initialState_isNotRotated(): Unit = runTest {
        // Verify initial state
        assertFalse(viewModel.isRotated.first())
    }

    @Test
    fun toggleRotation_changesState(): Unit = runTest {
        // Toggle rotation and verify state change
        viewModel.toggleRotation()
        assertTrue(viewModel.isRotated.first())

        // Toggle again to verify state change back to initial
        viewModel.toggleRotation()
        assertFalse(viewModel.isRotated.first())
    }
}
package com.example.employeedirectory.directory

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.employeedirectory.core.data.AmazonawsRepository
import com.example.employeedirectory.core.data.Employee
import com.example.employeedirectory.core.data.EmployeeType
import com.example.employeedirectory.core.network.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.*
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class DirectoryViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: DirectoryViewModel
    private lateinit var repository: AmazonawsRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = DirectoryViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getDirectory returns success with employees`() = runTest {
        val employees = listOf(
            Employee(
                uuid = "1",
                fullName = "John Doe",
                phoneNumber = "123-456-7890",
                emailAddress = "john@example.com",
                biography = "A developer",
                photoUrlSmall = "https://example.com/john_small.jpg",
                photoUrlLarge = "https://example.com/john_large.jpg",
                team = "Engineering",
                employeeType = EmployeeType.FULL_TIME
            ),
            Employee(
                uuid = "2",
                fullName = "Jane Smith",
                phoneNumber = "987-654-3210",
                emailAddress = "jane@example.com",
                biography = "A designer",
                photoUrlSmall = "https://example.com/jane_small.jpg",
                photoUrlLarge = "https://example.com/jane_large.jpg",
                team = "Design",
                employeeType = EmployeeType.PART_TIME
            )
        )
        coEvery { repository.getEmployees() } returns Result.Success(employees)

        viewModel.getDirectory()
        advanceUntilIdle()

        val state = viewModel.state.first()
        assertEquals(ScreenState.SUCCESS, state)

        val directory = viewModel.directory.first()
        assertEquals(employees.sortedBy { it.fullName }, directory)
    }

    @Test
    fun `getDirectory returns empty`() = runTest {
        coEvery { repository.getEmployees() } returns Result.Success(emptyList())

        viewModel.getDirectory()
        advanceUntilIdle()

        val state = viewModel.state.first()
        assertEquals(ScreenState.EMPTY, state)

        val directory = viewModel.directory.first()
        assertTrue(directory.isEmpty())
    }

    @Test
    fun `getDirectory returns error`() = runTest {
        val errorMessage = "Error"
        coEvery { repository.getEmployees() } returns Result.Error(IOException(errorMessage))

        viewModel.getDirectory()
        advanceUntilIdle()

        val state = viewModel.state.first() as ScreenState.FAILURE
        assertEquals(errorMessage, state.message)

        val directory = viewModel.directory.first()
        assertTrue(directory.isEmpty())
    }

    @Test
    fun `refreshDirectory updates isRefreshing state`() = runTest {
        val employees = listOf(
            Employee(
                uuid = "1",
                fullName = "John Doe",
                phoneNumber = "123-456-7890",
                emailAddress = "john@example.com",
                biography = "A developer",
                photoUrlSmall = "https://example.com/john_small.jpg",
                photoUrlLarge = "https://example.com/john_large.jpg",
                team = "Engineering",
                employeeType = EmployeeType.FULL_TIME
            ),
            Employee(
                uuid = "2",
                fullName = "Jane Smith",
                phoneNumber = "987-654-3210",
                emailAddress = "jane@example.com",
                biography = "A designer",
                photoUrlSmall = "https://example.com/jane_small.jpg",
                photoUrlLarge = "https://example.com/jane_large.jpg",
                team = "Design",
                employeeType = EmployeeType.PART_TIME
            )
        )
        coEvery { repository.getEmployees() } returns Result.Success(employees)

        viewModel.refreshDirectory()
        advanceUntilIdle()

        val isRefreshing = viewModel.isRefreshing.first()
        assertFalse(isRefreshing)
    }
}
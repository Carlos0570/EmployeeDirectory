package com.example.employeedirectory.directory

import androidx.lifecycle.ViewModel
import com.example.employeedirectory.core.data.AmazonawsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.employeedirectory.core.data.Employee
import com.example.employeedirectory.core.network.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DirectoryViewModel @Inject constructor(
    private val repository: AmazonawsRepository
) : ViewModel() {

    private var _directory = MutableStateFlow<List<Employee>>(emptyList())
    val directory: StateFlow<List<Employee>> = _directory

    private var _state = MutableStateFlow<ScreenState>(ScreenState.LOADING)
    val state = _state

    private var _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        getDirectory()
    }

    fun getDirectory() {
        viewModelScope.launch{
            _state.value = ScreenState.LOADING
            when (val result = repository.getEmployees()) {
                is Result.Success -> {
                    if (result.data.isEmpty()) {
                        _state.value = ScreenState.EMPTY
                        _directory.value = emptyList()
                    } else {
                        _state.value = ScreenState.SUCCESS
                        _directory.value = result.data.sortedBy { it.fullName }
                    }
                }

                is Result.Error -> {
                    _state.value = ScreenState.FAILURE(result.errorMessage)
                    _directory.value = emptyList()
                }
            }
        }
    }

    fun refreshDirectory() {
        _isRefreshing.value = true
        getDirectory()
        _isRefreshing.value = false
    }
}
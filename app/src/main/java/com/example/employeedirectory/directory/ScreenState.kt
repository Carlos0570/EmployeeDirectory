package com.example.employeedirectory.directory

sealed class ScreenState {
    data object LOADING : ScreenState()
    data object SUCCESS : ScreenState()
    data object EMPTY : ScreenState()
    data class FAILURE(val message: String? = "Error") : ScreenState()
}
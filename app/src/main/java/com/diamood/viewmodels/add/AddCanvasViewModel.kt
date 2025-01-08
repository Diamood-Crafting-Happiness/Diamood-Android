package com.diamood.viewmodels.add

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddCanvasViewModel @Inject internal constructor(
) : ViewModel() {

    private val _available: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val available = _available.asStateFlow()
}

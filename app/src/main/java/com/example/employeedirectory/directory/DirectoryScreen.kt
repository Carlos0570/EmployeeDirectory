package com.example.employeedirectory.directory

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.employeedirectory.R
import com.example.employeedirectory.core.data.Employee
import com.example.employeedirectory.core.data.EmployeeType
import com.example.employeedirectory.ui.animation.EmptyAnimation
import com.example.employeedirectory.ui.animation.LoadingAnimation
import com.example.employeedirectory.ui.animation.WarningAnimation
import com.example.employeedirectory.ui.theme.Black
import com.example.employeedirectory.ui.theme.Blue
import com.example.employeedirectory.ui.theme.Pink
import com.example.employeedirectory.ui.theme.Yellow
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.Dispatchers

@Composable
fun DirectoryScreen(viewModel: DirectoryViewModel) {
    val state by viewModel.state.collectAsState()
    when (state) {
        ScreenState.SUCCESS -> SuccessScreen(viewModel)
        ScreenState.EMPTY -> EmptyAnimation { viewModel.refreshDirectory() }
        ScreenState.LOADING -> LoadingAnimation()
        is ScreenState.FAILURE -> WarningAnimation { viewModel.refreshDirectory() }
    }
}

@Composable
fun SuccessScreen(viewModel: DirectoryViewModel) {
    val directory by viewModel.directory.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val refreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
    SwipeRefresh(state = refreshState, onRefresh = viewModel::refreshDirectory) {
        LazyColumn(
            Modifier.padding(start = 6.dp, end = 6.dp, top = 6.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(directory) {
                EmployeeCard(employee = it)
            }
        }
    }
}

@Composable
private fun EmployeeCard(employee: Employee) {
    Card(border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary)) {
        Row(
            Modifier
                .background(MaterialTheme.colorScheme.background)
                .wrapContentSize()
        ) {
            ProfilePhoto(employee.photoUrlSmall)
            EmployeeDetails(employee)
        }
    }
}

@Composable
private fun ProfilePhoto(photoURL: String?) {
    val imageRequest = ImageRequest
        .Builder(LocalContext.current)
        .data(photoURL)
        .dispatcher(Dispatchers.IO)
        .diskCacheKey(photoURL)
        .diskCachePolicy(CachePolicy.ENABLED)
        .build()

    AsyncImage(
        model = imageRequest,
        placeholder = null,
        error = painterResource(R.drawable.profile),
        contentDescription = stringResource(R.string.item_poster),
        modifier = Modifier
            .height(130.dp)
            .width(150.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun EmployeeDetails(employee: Employee) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(start = 6.dp, end = 6.dp, top = 12.dp, bottom = 12.dp)
    ) {
        Text(
            text = employee.fullName,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            fontWeight = FontWeight.SemiBold,
            color = Black
        )
        Text(
            text = employee.team,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.height(6.dp))
        employee.biography?.let { biography ->
            Text(
                text = biography,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                color = MaterialTheme.colorScheme.onPrimary,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 16.sp
            )
        }

        EmployeeTypeTag(employee.employeeType)
    }
}

@Composable
private fun EmployeeTypeTag(employeeType: EmployeeType) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End
    ) {
        when (employeeType) {
            EmployeeType.FULL_TIME -> {
                Text(
                    text = stringResource(R.string.full_time),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .background(
                            Pink,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(vertical = 2.dp, horizontal = 4.dp)
                )
            }

            EmployeeType.PART_TIME -> {
                Text(
                    text = stringResource(R.string.part_time),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .background(
                            Blue,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(vertical = 2.dp, horizontal = 4.dp)
                )
            }

            EmployeeType.CONTRACTOR -> {
                Text(
                    text = stringResource(R.string.contractor),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .background(
                            Yellow,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(vertical = 2.dp, horizontal = 4.dp)
                )
            }
        }
    }
}
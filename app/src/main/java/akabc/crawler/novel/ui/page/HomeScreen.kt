@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package akabc.crawler.novel.ui.page

import akabc.crawler.novel.R
import akabc.crawler.novel.ui.theme.Novel_CrawlerTheme
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(onHomeToOptSF: () -> Unit, onHomeToMark: () -> Unit, onHomeToHistory: () -> Unit) {
    val snackBarState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = { HomeTopBar(onHomeToHistory, onHomeToMark) },
        snackbarHost = { SnackbarHost(snackBarState) },
    ) {
        HomeContext(it, onHomeToOptSF, snackBarState)
    }
}


@Composable
fun HomeTopBar(onHomeToHistory: () -> Unit, onHomeToMark: () -> Unit) {
    TopAppBar(
        { Text(stringResource(R.string.home)) },
        actions = {
            IconButton(onClick = {
                onHomeToMark()
            }) {
                Icon(Icons.Filled.Favorite, null)
            }
            IconButton(onClick = {
                onHomeToHistory()
            }) {
                Icon(painterResource(R.drawable.history), null)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Settings, null)
            }
        }
    )
}

@Composable
fun HomeContext(
    paddingValues: PaddingValues,
    onHomeToOptSF: () -> Unit,
    snackBarState: SnackbarHostState,
) {
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            IconWithTextButton(R.drawable.ic_action_sfacg, R.string.home_sfacg) {
                onHomeToOptSF()
            }
            IconWithTextButton(R.drawable.ic_action_ciweimao, R.string.home_ciweimao) {
                scope.launch {
                    snackBarState.showSnackbar("暂未支持刺猬猫", withDismissAction = true)
                }
            }
        }

    }

}

@Composable
fun IconWithTextButton(
    @DrawableRes icon: Int,
    @StringRes text: Int,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick, shape = RoundedCornerShape(16.dp), colors = ButtonDefaults.buttonColors(
            MaterialTheme.colorScheme.primaryContainer, MaterialTheme.colorScheme.primary
        )
    ) {
        Icon(
            painterResource(icon), null,
            Modifier
                .width(30.dp)
                .height(30.dp), Color.Unspecified
        )
        Text(stringResource(text), fontSize = 28.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeActivityPagePreview() {
    Novel_CrawlerTheme {
        HomeScreen({},{}) {}
    }
}
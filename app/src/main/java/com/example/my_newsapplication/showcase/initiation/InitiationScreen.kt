package com.example.my_newsapplication.showcase.initiation

import androidx.benchmark.perfetto.ExperimentalPerfettoTraceProcessorApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.my_newsapplication.showcase.Distance.AveragePadding2
import com.example.my_newsapplication.showcase.Distance.PageNavigationWidth
import com.example.my_newsapplication.showcase.common.TheNewsButton
import com.example.my_newsapplication.showcase.common.TheNewsTextButton
import com.example.my_newsapplication.showcase.initiation.elements.InitiationPage
import com.example.my_newsapplication.showcase.initiation.elements.PageNavigation
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class, ExperimentalPerfettoTraceProcessorApi::class)
@Composable
fun InitiationScreen(
    event: (InitiationEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val buttonsState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }
        HorizontalPager(state = pagerState) { index ->
            InitiationPage(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AveragePadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageNavigation(
                modifier = Modifier.width(PageNavigationWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage

            )

        Row(verticalAlignment = Alignment.CenterVertically) {
            val scope = rememberCoroutineScope()

            if(buttonsState.value[0].isNotEmpty()){
                TheNewsTextButton(
                    text = buttonsState.value[0],
                    onClickAction = {
                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                        }
                    }
                )
                    
                }
            TheNewsButton(
                text = buttonsState.value[1],
                onClickAction = {
                    scope.launch {
                        if (pagerState.currentPage == 1) {
                            event(InitiationEvent.StoreAppEntry)
                        } else {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1
                            )
                        }
                    }
                }
            )
                
            }

            }
        Spacer(modifier = Modifier.weight(0.5f))
        }
    }






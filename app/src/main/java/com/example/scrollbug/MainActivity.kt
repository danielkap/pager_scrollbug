package com.example.scrollbug

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.example.scrollbug.databinding.ActivityMainBinding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val greeting = findViewById<ComposeView>(R.id.greeting)
        greeting.setContent {
            MaterialTheme {
                MainColumn()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}

@ExperimentalPagerApi
@Composable
fun MainColumn() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(state = scrollState)
            .height(1000.dp)
    ) {
        Spacer(modifier = Modifier.height(700.dp))
        MyHorizontalPager()
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@ExperimentalPagerApi
@Composable
fun MyHorizontalPager() {
    val items = listOf("Freddy Foo", "Bertie Bar", "Bobby Baz")
    val pagerState =
        rememberPagerState(pageCount = items.size, infiniteLoop = true)

    HorizontalPager(
        modifier = Modifier.height(200.dp),
        state = pagerState,
        verticalAlignment = Alignment.Top
    ) { page ->
        Box(
            Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(Color.Cyan)
        ) {
            Text(items[page])
        }
    }
}
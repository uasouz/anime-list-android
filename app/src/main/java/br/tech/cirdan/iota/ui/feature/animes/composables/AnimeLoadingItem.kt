package br.tech.cirdan.iota.ui.feature.animes.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.tech.cirdan.iota.data.model.Anime
import br.tech.cirdan.iota.R
import coil3.compose.AsyncImage

@Composable
fun AnimeLoadingItem() {
    Box(
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)).fillMaxSize()
    ) {
        Card() {
            Row {

            }
        }
    }
}

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
fun AnimeListItem(anime: Anime) {
    Box(
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)).fillMaxSize()
    ) {
        Card() {
            Row {
                AsyncImage(model = anime.image, contentDescription = null)
                Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_small)))
                Column {
                    Text(
                        text = anime.title,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_small)))
                    Text(text = "Rank: "+ anime.rank)
                    Text(text = "Score: "+ anime.score)
                    Text(text = "Genres: "+ anime.genres.joinToString{it.value})
                }
            }
        }
    }
}

package com.speechify.composeuichallenge.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.speechify.composeuichallenge.data.Book

@Composable
fun BooksItemView(
    book: Book,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth() .height(120.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        // Book Cover
        AsyncImage(
            model = book.imageUrl,
            contentDescription = book.name,
            modifier = Modifier
                .fillMaxHeight() // Fill the 120.dp height
                .aspectRatio(3f / 4f) // Requirement: 3:4 aspect ratio
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier.width(12.dp))

        // Book Info
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = book.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = book.author,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = "${book.rating} (${book.reviewCount})",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier.width(12.dp))


        // Details Button
        Button(
            onClick = onClick,
            modifier = Modifier.align(Alignment.CenterVertically),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Details")
        }

    }
}


@Preview(showBackground = true)
@Composable
fun NewsCardWithoutImagePreview() {
    MaterialTheme {
        BooksItemView(
            book = Book(
                id = "0197cfb0-da30-70ed-a6f5-21331ea1b2cc",
                name = "La isla del tesoro",
                imageUrl = "https://catalog.cdn.speechify.com/thumbnails/0197cfb0-da30-70ed-a6f5-21331ea1b2cc/0197cfb0-da30-70ed-a6f5-21331ea1b2cc.jpg?width=720&height=1080",
                author = "Robert Louis Stevenson",
                description = "\"La isla del tesoro\" by Robert Louis Stevenson is a classic adventure novel written in the late 19th century. The story follows young Jim Hawkins as he embarks on a thrilling journey filled with pirates, treasure maps, and the quest for buried treasure after discovering an old pirate's chest. Central to the narrative is the character of Long John Silver, a cunning one-legged pirate with a complex character who poses both a threat and a temptation to Jim.  At the start of the novel, Jim Hawkins recounts the peculiar events surrounding the arrival of a mysterious old sailor, known as the Captain, at the Admiral Benbow inn. The Captain, a rough and intimidating man, quickly establishes a sense of foreboding with his strange behavior and unsettling songs about treasure. As Jim's curiosity is piqued, he learns of the Captain's hidden treasures and is drawn into a world of betrayal and adventure, especially after nighttime encounters with other shady characters eager to claim the fabled pirate treasure for themselves. The opening sections set a thrilling tone for the adventure that is to unfold, introducing a combination of danger, suspense, and the allure of exploration on the high seas. (This is an automatically generated summary.)",
                rating = 3.85F,
                reviewCount = 299,
            ),
            onClick = { /* Preview action */ }
        )
    }
}


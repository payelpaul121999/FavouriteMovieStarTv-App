package com.payelpaul.favstarmovieseries.model.artist

//@Entity(tableName = "artist")
data class ArtistDto(
//    @PrimaryKey(autoGenerate = true)
    val artistId:Int,
    val id: Int,
    val name: String,
    val popularity: Double,
    val profile_path: String
)

fun List<ArtistDto>.toArtist() = map { Artist(it,ArtistDataType.DATA) }

enum class ArtistDataType(val value : Int){
   DATA(0),SHIMMER(1)
}

data class Artist(
    val artistDto: ArtistDto?= null,
    val artistDataType: ArtistDataType = ArtistDataType.SHIMMER
)

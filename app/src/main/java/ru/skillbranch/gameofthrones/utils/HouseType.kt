package ru.skillbranch.gameofthrones.utils

import ru.skillbranch.gameofthrones.R

enum class HouseType(
    val title: String,
    val fullTitle: String,
    val icon: Int,
    val coastOfArms: Int,
    val primaryColor: Int,
    val accentColor: Int,
    val darkColor: Int
) {
    STARK(
        "Stark",
        "House Stark of Winterfell",
        R.drawable.stark_icon,
        R.drawable.stark_coast_of_arms,
        R.color.stark_primary,
        R.color.stark_accent,
        R.color.stark_dark
    ),
    LANNISTER(
        "Lannister",
        "House Lannister of Casterly Rock",
        R.drawable.lannister_icon,
        R.drawable.lannister__coast_of_arms,
        R.color.lannister_primary,
        R.color.lannister_accent,
        R.color.lannister_dark
    ),
    TARGARYEN(
        "Targaryen",
        "House Targaryen of King's Landing",
        R.drawable.targaryen_icon,
        R.drawable.targaryen_coast_of_arms,
        R.color.targaryen_primary,
        R.color.targaryen_accent,
        R.color.targaryen_dark
    ),
    BARATHEON(
        "Baratheon",
        "House Baratheon of Dragonstone",
        R.drawable.baratheon_icon,
        R.drawable.baratheon_coast_of_arms,
        R.color.baratheon_primary,
        R.color.baratheon_accent,
        R.color.baratheon_dark
    ),
    GREYJOY(
        "Greyjoy",
        "House Greyjoy of Pyke",
        R.drawable.greyjoy_icon,
        R.drawable.greyjoy_coast_of_arms,
        R.color.greyjoy_primary,
        R.color.greyjoy_accent,
        R.color.greyjoy_dark
    ),
    MARTELL(
        "Martell",
        "House Nymeros Martell of Sunspear",
        R.drawable.martel_icon,
        R.drawable.martel_coast_of_arms,
        R.color.martel_primary,
        R.color.martel_accent,
        R.color.martel_dark
    ),
    TYRELL(
        "Tyrell",
        "House Tyrell of Highgarden",
        R.drawable.tyrel_icon,
        R.drawable.tyrel_coast_of_arms,
        R.color.tyrel_primary,
        R.color.tyrel_accent,
        R.color.tyrel_dark
    );

    companion object {
        fun fromString(title: String): HouseType {
            return when (title) {
                "House Stark of Winterfell", "Stark" -> STARK
                "House Lannister of Casterly Rock", "Lannister" -> LANNISTER
                "House Targaryen of King's Landing", "Targaryen" -> TARGARYEN
                "House Baratheon of Dragonstone", "Baratheon" -> BARATHEON
                "House Greyjoy of Pyke", "Greyjoy" -> GREYJOY
                "House Nymeros Martell of Sunspear", "Martell" -> MARTELL
                "House Tyrell of Highgarden", "Tyrell" -> TYRELL
                else -> throw IllegalStateException()
            }
        }
    }

}

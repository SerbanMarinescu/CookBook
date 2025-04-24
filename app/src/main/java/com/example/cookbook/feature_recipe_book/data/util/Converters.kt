package com.example.cookbook.feature_recipe_book.data.util

import androidx.room.TypeConverter
import com.example.cookbook.feature_recipe_book.util.Category
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class Converters {

    @TypeConverter
    fun fromDuration(duration: Duration): Long = duration.inWholeMilliseconds

    @TypeConverter
    fun toDuration(millis: Long): Duration = millis.toDuration(DurationUnit.MILLISECONDS)

    @TypeConverter
    fun fromCategory(category: Category): String = category.name

    @TypeConverter
    fun toCategory(categoryName: String): Category = Category.valueOf(categoryName)
}
package com.example.my_newsapplication.data.local_database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.my_newsapplication.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source: Source): String{
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): Source{
        return source.split(",").let { sourceArray ->
            Source(sourceArray[0], sourceArray[1])
        }
    }
}
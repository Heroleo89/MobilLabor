package com.example.mobillab.repo.database

import androidx.room.TypeConverter
import com.example.mobillab.model.Location
import com.example.mobillab.model.Origin

class Converter {
    companion object{
        private const val SPLIT_CHAR = ";;"
    }

    @TypeConverter
    fun toLocation(locationString: String): Location? {

        if(locationString.isEmpty())
            return null

        val values = locationString.split(SPLIT_CHAR)

        if(values.size == 2 )
            return Location(values[0],values[1])

        return null
    }

    @TypeConverter
    fun fromLocation(location: Location?): String {
        location?.let {
            return location.name + SPLIT_CHAR + location.url
        }
        return ""
    }

    @TypeConverter
    fun toOrigin(originString: String): Origin? {

        if(originString.isEmpty())
            return null

        val values = originString.split(SPLIT_CHAR)

        if(values.size == 2 )
            return Origin(values[0],values[1])

        return null
    }

    @TypeConverter
    fun fromOrigin(origin: Origin?): String {
        origin?.let {
            return origin.name + SPLIT_CHAR + origin.url
        }
        return ""
    }


}
package com.example.mobillab.repo.database

abstract class Database {
    abstract fun characterDao(): CharacterDAO
}
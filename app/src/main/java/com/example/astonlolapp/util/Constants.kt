package com.example.astonlolapp.util

object Constants {

    const val BASE_URL = "http://10.0.2.2:8080/"

    const val HERO_DATABASE_TABLE = "hero_database_table"
    const val REMOTE_KEYS_TABLE = "remote_keys_table"
    const val HERO_DATABASE = "hero_database"

    const val COMICS_DATABASE_TABLE = "comics_database_table"

    const val ITEMS_PAGE_SIZE = 5

    const val HERO_PREFERENCES_NAME = "hero_preferences"
    const val ON_BOARDING_PREFERENCES_KEY = "on_boarding_completed"
    const val SIGNED_IN_PREFERENCES_KEY = "on_boarding_completed"

    const val COMICS_PAGE_COUNT = 9
    const val LAST_ON_COMICS_PAGE = 8


    //Notification
    const val VERBOSE_NOTIFICATION_CHANNEL_NAME = "Verbose WorkManager Notifications"
    const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
        "Shows notifications whenever work starts"
    const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
    const val NOTIFICATION_ID = 1
    const val NOTIFICATION_TITLE= "WorkRequest Starting"

    const val WORK_MANAGER_REPEAT_INTERVAL = 24L

    const val SERVER_CLIENT_ID = "918660918687-csdb0i7um4pq5o5q4eg6s5r8jf12e99f.apps.googleusercontent.com"
}
package com.bignerdranch.android.viewpager_practice;

import java.util.UUID;

class StoredId {
    private static UUID sUUID = null;

    public static UUID getUUID() {
        return sUUID;
    }

    public static void setUUID(UUID UUID) {
        sUUID = UUID;
    }
}

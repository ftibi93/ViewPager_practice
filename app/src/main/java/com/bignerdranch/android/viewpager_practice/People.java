package com.bignerdranch.android.viewpager_practice;

import java.util.UUID;

public class People {
    private String mName;
    private UUID mId;

    public People() {
        mId = UUID.randomUUID();
    }

    public People(UUID id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public UUID getId() {
        return mId;
    }
}

package com.example.week1_android_studio;

public class Game1MemoryCard {
    private String imageId;
    private boolean isFaceUp;
    private boolean isMatched;

    public Game1MemoryCard(String imageId, boolean isFaceUp, boolean isMatched) {
        this.imageId = imageId;
        this.isFaceUp = isFaceUp;
        this.isMatched = isMatched;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void setFaceUp(boolean faceUp) {
        isFaceUp = faceUp;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }
}

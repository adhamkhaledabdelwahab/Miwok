package com.example.miwok;

/**
 * {@link Word} represnts a vocabulary word that the user wants to learn.
 * It contains a english translation and a Miwok translation for that word.
 */
public class Word {

    /** English translation for the word */
    private String englishTranslation;

    /** Miwok tranlation for the word */
    private String miwokTranslation;

    /** Word description image resource*/
    private int imageResource;

    /** Word Sound resource*/
    private int soundResourceId;

    /**
     * A constructor for Miwok phrases which has no description image
     * @param eng is the English translation
     * @param mwk is the Miwok translation
     * @param sound is the Miwok translation description sound
     */
    public Word(String eng, String mwk, int sound) {
        englishTranslation = eng;
        miwokTranslation = mwk;
        imageResource = -1;
        soundResourceId = sound;
    }

    /**
     * A constructor for Miwok numbers, colors and family members which has a description image
     * @param eng is the English translation
     * @param mwk is the Miwok translation
     * @param img is the Miwok translation description image
     * @param sound is the Miwok translation description sound
     */
    public Word(String eng, String mwk, int img, int sound){
        englishTranslation = eng;
        miwokTranslation = mwk;
        imageResource = img;
        soundResourceId = sound;
    }

    /**
     * Get the english translation of the word.
     */
    public String getEnglishTranslation() {
        return englishTranslation;
    }

    /**
     * Get the miwok translation of the word.
     */
    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    /**
     * Get the word description image resource
     */
    public int getImageResource() {
        return imageResource;
    }

    /**
     * Get the word sound resource
     */
    public int getSoundResourceId() {
        return soundResourceId;
    }
}

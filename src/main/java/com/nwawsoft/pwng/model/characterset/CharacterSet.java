package com.nwawsoft.pwng.model.characterset;

import com.nwawsoft.util.datastructures.StringList;

/**
 * Represents a CharacterSet.
 * CharacterSets have a list of characters they contain and a name and may have a countryCode and suffixes.
 */
public class CharacterSet {
    private final String chars;
    private final String name;
    private final String countryCode;
    private final StringList suffixes;
    private final String fileName;

    /**
     * Minimal CharacterSet.
     * Basically a wrapper for CharacterSet(String, String, String, StringList, String) for testing and as fallback.
     *
     * @param chars a String containing all allowed characters for this set (may not be null, empty or only one
     *              character long).
     */
    public CharacterSet(final String chars) {
        this(chars, "UNNAMED", null, null, null);
    }

    /**
     * Instantiates a new CharacterSet.
     *
     * @param chars       a String containing all allowed characters for this set (may not be null, empty or only one
     *                    character long).
     * @param name        the name of the CharacterSet (may not be null or empty).
     * @param countryCode the countryCode of the CharacterSet (may be null or empty).
     * @param suffixes    the suffix(es) of the CharacterSet (may be null or empty).
     * @param fileName    the fileName of the CharacterSet (may be null or empty).
     */
    public CharacterSet(final String chars, final String name, final String countryCode, final StringList suffixes,
                        final String fileName) {
        this.chars = chars;
        this.name = name;
        this.countryCode = countryCode;
        this.suffixes = suffixes;
        this.fileName = fileName;
    }

    public String getChars() {
        return chars;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public StringList getSuffixes() {
        return suffixes;
    }

    public String getFileName() {
        return fileName;
    }

    /**
     * Returns whether the CharacterSet has a non-null and non-empty countryCode.
     *
     * @return true if the CharacterSet has a countryCode. Else false.
     */
    public boolean hasCountryCode() {
        return (countryCode != null && !countryCode.equals(""));
    }
}

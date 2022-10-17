package org.flower.tiktok;

public enum Voice {

    ENGLISH_GHOST_FACE("en_us_ghostface"),
    ENGLISH_CHEWBACCA("en_us_chewbacca"),
    ENGLISH_C3PO("en_us_c3po"),
    ENGLISH_STITCH("en_us_stitch"),
    ENGLISH_STORMTROOPER("en_us_stormtrooper"),
    ENGLISH_ROCKET("en_us_rocket"),
    ENGLISH_AU_MALE("en_au_002"),
    ENGLISH_AU_FEMALE("en_au_001"),
    ENGLISH_UK_MALE_1("en_uk_001"),
    ENGLISH_UK_MALE_2("en_uk_003"),
    ENGLISH_US_MALE_1("en_us_006"),
    ENGLISH_US_MALE_2("en_us_007"),
    ENGLISH_US_MALE_3("en_us_009"),
    ENGLISH_US_MALE_4("en_us_010"),
    ENGLISH_US_FEMALE_1("en_us_001"),
    ENGLISH_US_FEMALE_2("en_us_002"),
    ENGLISH_FEMALE_ALTO("en_female_f08_salut_damour"),
    ENGLISH_MALE_TENOR("en_male_m03_lobby"),
    ENGLISH_FEMALE_WARMY_BREEZE("en_female_f08_warmy_breeze"),
    ENGLISH_MALE_SUNSHINE_SOON("en_male_m03_sunshine_soon"),
    ENGLISH_MALE_NARRATOR("en_male_narration"),
    ENGLISH_MALE_WACKY("en_male_funny"),
    ENGLISH_FEMALE_PEACEFUL("en_female_emotional"),

    FRENCH_MALE_1("fr_001"),
    FRENCH_MALE_2("fr_002"),

    GERMAN_MALE("de_002"),
    GERMAN_FEMALE("de_001"),

    SPANISH_MALE("es_002"),
    SPANISH_AMERICA_MALE("es_mx_002"),

    PORTUGUESE_BR_MALE("br_005"),
    PORTUGUESE_BR_FEMALE_1("br_001"),
    PORTUGUESE_BR_FEMALE_2("br_003"),
    PORTUGUESE_BR_FEMALE_3("br_004"),

    INDONESIAN_FEMALE("id_001"),

    JAPANESE_MALE("jp_006"),
    JAPANESE_FEMALE_1("jp_001"),
    JAPANESE_FEMALE_2("jp_003"),
    JAPANESE_FEMALE_3("jp_005"),

    KOREAN_MALE_1("kr_002"),
    KOREAN_MALE_2("kr_004"),
    KOREAN_FEMALE("kr_003");

    public final String tiktokId;

    Voice(String tiktokId){
        this.tiktokId = tiktokId;
    }

}

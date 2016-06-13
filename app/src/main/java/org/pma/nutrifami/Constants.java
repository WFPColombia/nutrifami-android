package org.pma.nutrifami;

/**
 * Created by Peter Juras on 01.06.16.
 */

public final class Constants {
    public final static String MODULE_ID = "MODULE_ID";
    public final static String LESSON_ID = "LESSON_ID";
    public final static String LESSON_OVERVIEW = "LESSON_OVERVIEW";
    public final static String UNITS_POSITION = "UNITS_POSITION";
    public final static String CALLED_FROM_LOGIN = "CALLED_FROM_LOGIN";

    // Disables the local module cache when disabled for testing purposes
    public final static boolean LOCAL_CACHE = false;

    private final static String MODULES_TEST_URL = "https://nutrifami.blob.core.windows.net/training-data/modules.json";
    private final static String MODULES_PRODUCTION_URL = "https://nutrifami.azureedge.net/training-data/modules.json";
    public final static String MODULES_URL = MODULES_TEST_URL;
    public final static String MODULES_FILE_NAME = "modules.json";
}

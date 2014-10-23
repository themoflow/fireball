package Physics.Play.logic;

import android.content.Context;

/**
 * Created by morantornesella-brooks on 10/16/14.
 */
public class SavedContext {

    private static Context context;

    public static void setContext(Context c) {
        context = c;
    }

    public static Context getContext() {
        return context;
    }
}

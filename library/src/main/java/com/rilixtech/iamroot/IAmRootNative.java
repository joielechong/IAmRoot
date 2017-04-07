package com.rilixtech.iamroot;

import com.rilixtech.iamroot.util.QLog;

/**
 * Created by mat on 19/06/15.
 */
public class IAmRootNative {

    static boolean libraryLoaded = false;

    /**
     * Loads the C/C++ libraries statically
     */
    static {
        try {
            System.loadLibrary("tool-checker");
            libraryLoaded = true;
        }
        catch (UnsatisfiedLinkError e){
            QLog.e(e);
        }
    }

    public boolean wasNativeLibraryLoaded(){
        return libraryLoaded;
    }

    public native int checkForRoot(Object[] pathArray);
    public native int setLogDebugMessages(boolean logDebugMessages);

}

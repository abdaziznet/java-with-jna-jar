package com.ivteknologi;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.ivteknologi.CLibrary.UserResponse;

public class FingerCore {
    private static final Integer NO_ERR = 0;
    private static final Integer ERR = 1;

    private static Integer glbTimeout;

    public Integer fs_init(Integer timeout) {
        Integer err = NO_ERR;
        if (timeout == 0) {
            Helper.infobox("Please fill timeout", "ERROR");
            return ERR;
        }

        glbTimeout = timeout;
        final CLibrary cLibrary = (CLibrary) Native.load(Helper.getLocationLib(), CLibrary.class);
        err = cLibrary.fs_init();
        return err;
    }

    public Integer fs_send(String nip) {
        int err = NO_ERR;
        if (nip.isEmpty()) {
            Helper.infobox("Please fill nip", "ERROR");
            return ERR;
        }

        final CLibrary cLibrary = (CLibrary) Native.load(Helper.getLocationLib(), CLibrary.class);
        err = cLibrary.fs_send(glbTimeout, nip);
        return err;
    }

    public Integer fs_receive(UserResponse.ByReference result) {
        int err = NO_ERR;
        final CLibrary cLibrary = (CLibrary) Native.load(Helper.getLocationLib(), CLibrary.class);
        err = cLibrary.fs_receive(result);
        return err;
    }

    public Integer fs_cancel(String reason) {
        int err = NO_ERR;
        if (reason.isEmpty()) {
            Helper.infobox("Please fill reason cancellation", "ERROR");
            return ERR;
        }

        final CLibrary cLibrary = (CLibrary) Native.load(Helper.getLocationLib(),CLibrary.class);
        Memory messageMemory = new Memory(reason.length());
        messageMemory.write(0,reason.getBytes(),0,reason.length());
        Pointer pointer = messageMemory;
        err = cLibrary.fs_cancel(pointer);
        return err;
    }
}

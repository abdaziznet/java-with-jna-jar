package com.ivteknologi;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public interface CLibrary extends Library {

    public static class UserResponse extends Structure {
        public static class ByReference extends UserResponse implements Structure.ByReference {}

        public String nip;

        public String name;

        public int score;

        public int respCode;

        @Override
        protected List getFieldOrder() {
            return Arrays.asList(new String[]{"nip", "name", "score", "respCode"});
        }
    }

    public int fs_init();

    public int fs_send(int timeout, String nip);

    public int fs_receive(UserResponse.ByReference result);

    public int fs_cancel(Pointer message);
}

package com.ivteknologi;

public class Main {
    public static void main(String[] args) {
        FingerCore fingerCore = new FingerCore();
        int err = 1;
        err = fingerCore.fs_init(30);
        if (err == 0)
        {
            err = 1;
            err = fingerCore.fs_send("1234567");
            if (err == 0){
                final CLibrary.UserResponse.ByReference resp = new CLibrary.UserResponse.ByReference();
                resp.respCode = 1;
                err = 1;
                err = fingerCore.fs_receive(resp);
                if (err == 0)
                {
                    System.out.println("NIP: " + resp.nip);
                    System.out.println("Name: " + resp.name);
                    System.out.println("Score: " + resp.score);
                    System.out.println("response code: " + resp.respCode);
                    System.out.println("SUKSES");
                }
                else{
                    System.out.println("GAGAL");
                }
            }
            else{
                System.out.println("GAGAL");
            }
        }
        else{
            System.out.println("GAGAL");
        }
    }
}

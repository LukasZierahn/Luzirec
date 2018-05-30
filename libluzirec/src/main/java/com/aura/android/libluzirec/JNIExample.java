package com.aura.android.libluzirec;

public class JNIExample
{
    static
    {
        System.loadLibrary("native-lib");
    }

    public native String stringFromJNI();
}

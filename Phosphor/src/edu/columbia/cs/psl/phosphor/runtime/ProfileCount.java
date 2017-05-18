package edu.columbia.cs.psl.phosphor.runtime;

/**
 * Created by jianyu on 5/18/17.
 */
public class ProfileCount {
    static boolean isLoaded = false;
    static boolean firstLoading = true;

    static {
        System.load("/home/jianyu/libdebug.so");
    }

    public static native void printStatus();

    public static native void invoke(String s);

    public static int count = 0;

    public static void invoke_insn(String s) {
        if (count < 1000) {
            count += 1;
            return;
        }
        if (firstLoading) {
            firstLoading = false;
            invoke(s);
            isLoaded = true;
        } else if (isLoaded) {
            invoke(s);
        }
    }
    public static void main(String[] args) {
        invoke_insn("aa");
        invoke_insn("aaa");
        printStatus();
    }
}

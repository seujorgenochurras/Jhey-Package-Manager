package io.github.seujorgenochurras.command.cli.utils.ansi;

public class LoadingAnimation {
    private LoadingAnimation(){}

    //There's absolute no reason to do this with JNI
    //I'm just learning c and decided to do it with JNI
    //I'll probably change this though because of the os problems
    public static native void animateLoading(int durationMillis);

    static {
        System.load("/home/little-jhey/Desktop/projetos/cli-test/jpm-cli/src/c-code/src/loading/loadingAnim.so");
    }
}

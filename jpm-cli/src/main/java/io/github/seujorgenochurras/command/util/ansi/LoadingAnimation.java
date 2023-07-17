package io.github.seujorgenochurras.command.util.ansi;

public class LoadingAnimation {
    private LoadingAnimation() {
    }

    private static boolean stopAnimations = false;

    private static final String CSI = "\u001b[";

    private static Thread animationThread;

    public static void startAnimation(long animationFrameLifeCycle) {
        stopAnimations = false;
        animationThread = new Thread(() -> startAnimation(new LoadingAnimationStage(), animationFrameLifeCycle));
        animationThread.start();
    }

    public static void stopAllAnimations() {
        stopAnimations = true;
        System.out.print(CSI + "2J" + CSI + "2;;H");
        animationThread = null;
    }

    private static void startAnimation(LoadingAnimationStage loadingAnimationStage, long stageLifeMillis) {
        sleep(stageLifeMillis);
        if (!stopAnimations) {
            loadingAnimationStage.printAndEvolveStage();
            startAnimation(loadingAnimationStage, stageLifeMillis);
        }

    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static final class LoadingAnimationStage {
        private static final String[] animationStages = {
                "⠁", "⠃", "⠇",
                "⠦", "⠴", "⠸",
                "⠙", "⠉"
        };
        private int currentStageIndex = 0;

        String getCurrentStage() {
            return animationStages[currentStageIndex];
        }

        void printAndEvolveStage() {
            if (currentStageIndex == animationStages.length - 1) {
                currentStageIndex = 0;
            }
            System.out.println(CSI + "1A" + getCurrentStage() + CSI + "K");
            currentStageIndex++;
        }
    }
}

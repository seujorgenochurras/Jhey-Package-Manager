#include "LoadingStage.h"
#include "io_github_seujorgenochurras_command_cli_utils_ansi_LoadingAnimation.h"

#ifdef __unix__
#include <unistd.h>
#define globalSleep(seconds) usleep(seconds * 1000000)
#elif defined(_WIN32)
#include <Windows.h>
#define globalSleep(seconds) Sleep(seconds * 1000)
#endif

#include <stdio.h>
#include <wchar.h>
#include <locale.h>

int charStageArr[] = {0x2801, 0x2803, 0x2807,
                      0x2826, 0x2834, 0x2838, 0x2819, 0x2809};

#define sizeOfIntArr(arr) sizeof(arr) / sizeof(arr[0])

void updateLoadingCharStatus(LoadingStage *loadingStagePtr)
{
    if (loadingStagePtr->currentCharIndex == sizeOfIntArr(charStageArr) - 1)
    {
        loadingStagePtr->currentCharStage = charStageArr[0];
        loadingStagePtr->currentCharIndex = 0;
    }
    else
    {
        loadingStagePtr->currentCharIndex++;
        loadingStagePtr->currentCharStage = charStageArr[loadingStagePtr->currentCharIndex];
    }
}

LoadingStage newLoadingStage()
{
    LoadingStage newLoadingStage;
    newLoadingStage.currentCharStage = charStageArr[0];
    newLoadingStage.currentCharIndex = 0;
    return newLoadingStage;
}
void animateLoading(LoadingStage *loadingStagePtr, int speed){
        wprintf(L"\e[1A\e[K%lc \n", loadingStagePtr->currentCharStage);
        updateLoadingCharStatus(loadingStagePtr);
        globalSleep(0.05);
        animateLoading(loadingStagePtr, speed);
}


JNIEXPORT void JNICALL Java_io_github_seujorgenochurras_command_cli_utils_ansi_LoadingAnimation_animateLoading
(JNIEnv *, jclass javaclass, jint durationMillis)
{
    setlocale(LC_CTYPE, "");
    LoadingStage loadingStage = newLoadingStage();
    animateLoading(&loadingStage, durationMillis);
}


#ifdef __unix__
    #include <unistd.h>
    #define globalSleep(seconds) usleep(seconds * 1000000)
#elif defined(_WIN32)
    #include <Windows.h>
    #define globalSleep(seconds) Sleep(seconds * 1000)
#endif

#include <stdio.h>
#include "loading/LoadingStage.h"
#include <wchar.h>
#include <locale.h>

#define true 1
#define false 0

void animateLoading(LoadingStage *loadingStagePtr){
        wprintf(L"\e[1A\e[K%lc \n", loadingStagePtr->currentCharStage);
        updateLoadingCharStatus(loadingStagePtr);
        globalSleep(0.05);
        animateLoading(loadingStagePtr);
}

int main(){
    setlocale(LC_CTYPE, "");
    LoadingStage loadingStage = newLoadingStage();
    animateLoading(&loadingStage);
    return 0;
}

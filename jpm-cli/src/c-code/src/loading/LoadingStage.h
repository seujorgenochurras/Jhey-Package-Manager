#ifndef LOADINGSTAGE
#define LOADINGSTAGE

typedef struct LoadingStage
{
    unsigned int currentCharStage;
    int currentCharIndex;
}LoadingStage;


void updateLoadingCharStatus(LoadingStage *loadingStagePtr);
LoadingStage newLoadingStage();

#endif
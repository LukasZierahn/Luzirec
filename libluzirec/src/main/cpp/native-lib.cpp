#include <jni.h>
#include <string>


extern "C" JNIEXPORT jstring
JNICALL Java_com_aura_android_libluzirec_SpeakerRecognitionJNI_stringFromJNI(JNIEnv *env, jobject)
{
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


#include <jni.h>

JNIEXPORT jstringJNICALL
Java_com_test_myapplication_NativeUtil_compressBitmap__Landroid_graphics_Bitmap_2IIILbyte_3_093_2Z(
        JNIEnv
*env,
jclass type, jobject
bit,
jint w, jint
h,
jint quality,
        jbyteArray
fileNameBytes_,
jboolean optimize
)
{
jbyte *fileNameBytes = (*env)->GetByteArrayElements(env, fileNameBytes_, NULL);

// TODO

(*env)->
ReleaseByteArrayElements(env, fileNameBytes_, fileNameBytes,
0);

return (*env)->
NewStringUTF(env, returnValue
JNIEXPORT jstring
);
}
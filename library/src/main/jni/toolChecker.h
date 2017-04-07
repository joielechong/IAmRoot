/****************************************************************************
 * File:   toolChecker.h
 * Author: Matthew Rollings
 * Date:   19/06/2015
 *
 * Description : Root checking JNI NDK code
 *
 ****************************************************************************/

extern "C" {

#include <jni.h>

void Java_com_rilixtech_iamroot_IAmRootNative_setLogDebugMessages( JNIEnv* env, jobject thiz, jboolean debug);
  
int Java_com_rilixtech_iamroot_IAmRootNative_checkForRoot( JNIEnv* env, jobject thiz , jobjectArray pathsArray );

}
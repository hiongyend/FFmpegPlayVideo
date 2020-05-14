# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)


# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
#设置生成的动态库目录 生成的so在当前项目不需要手动拷贝到libs或jniLibs，生成apk会自动引入，这里生成的可以供其他项目使用
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/libs/${ANDROID_ABI})
#设置jni库目录
set(JNI_LIBS_DIR ${CMAKE_SOURCE_DIR}/../../../libs)


add_library( video
        SHARED
        video.cpp)

find_library( log-lib
        log )


add_library( avcodec SHARED IMPORTED)
set_target_properties(
        avcodec
        PROPERTIES IMPORTED_LOCATION
        ${JNI_LIBS_DIR}/${ANDROID_ABI}/libavcodec.so)

add_library(avdevice SHARED IMPORTED)
set_target_properties(
        avdevice
        PROPERTIES IMPORTED_LOCATION
        ${JNI_LIBS_DIR}/${ANDROID_ABI}/libavdevice.so)

add_library(avfilter SHARED IMPORTED)
set_target_properties(
        avfilter
        PROPERTIES IMPORTED_LOCATION
        ${JNI_LIBS_DIR}/${ANDROID_ABI}/libavfilter.so)

add_library(avformat SHARED IMPORTED)
set_target_properties(
        avformat
        PROPERTIES IMPORTED_LOCATION
        ${JNI_LIBS_DIR}/${ANDROID_ABI}/libavformat.so)

add_library(avutil SHARED IMPORTED)
set_target_properties(
        avutil
        PROPERTIES IMPORTED_LOCATION
        ${JNI_LIBS_DIR}/${ANDROID_ABI}/libavutil.so)

add_library(postproc SHARED IMPORTED)
set_target_properties(
        postproc
        PROPERTIES IMPORTED_LOCATION
        ${JNI_LIBS_DIR}/${ANDROID_ABI}/libpostproc.so)

add_library(swresample SHARED IMPORTED)
set_target_properties(
        swresample
        PROPERTIES IMPORTED_LOCATION
        ${JNI_LIBS_DIR}/${ANDROID_ABI}/libswresample.so)

add_library(swscale SHARED IMPORTED)
set_target_properties(
        swscale
        PROPERTIES IMPORTED_LOCATION
        ${JNI_LIBS_DIR}/${ANDROID_ABI}/libswscale.so)
#添加头文件
include_directories(${CMAKE_SOURCE_DIR}/include)

target_link_libraries(
        video
        avcodec
        avdevice
        avfilter
        avformat
        avutil
        postproc
        swresample
        swscale
        android
        ${log-lib} )
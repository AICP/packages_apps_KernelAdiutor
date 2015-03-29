LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES := $(call all-subdir-java-files)

LOCAL_STATIC_JAVA_LIBRARIES := \
    android-support-v4 \
    android-support-v13 \
    android-support-v7-cardview \
    android-support-v7-recyclerview \
    android-support-v7-appcompat


LOCAL_PACKAGE_NAME := KernelAdiutor

LOCAL_PRIVILEGED_MODULE := true

LOCAL_MODULE_TAGS := optional

include $(BUILD_PACKAGE)

LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
 
LOCAL_MODULE    := gdx-freetype
LOCAL_C_INCLUDES := freetype-2.4.8/include 
 
LOCAL_CFLAGS := $(LOCAL_C_INCLUDES:%=-I%) -O2 -Wall -D__ANDROID__ -std=c99 -DFT2_BUILD_LIBRARY
LOCAL_CPPFLAGS := $(LOCAL_C_INCLUDES:%=-I%) -O2 -Wall -D__ANDROID__ -std=c99 -DFT2_BUILD_LIBRARY
LOCAL_LDLIBS := -lm
LOCAL_ARM_MODE  := arm
 
LOCAL_SRC_FILES :=  
include $(BUILD_SHARED_LIBRARY)

# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#-------------------------------------------定制化区域----------------------------------------------


#---------------------------------1.实体类---------------------------------
-keep class com.xinze.xinze.module.about.modle.** { *; }
-keep class com.xinze.xinze.module.add.modle.** { *; }
-keep class com.xinze.xinze.module.certification.modle.** { *; }
-keep class com.xinze.xinze.module.invite.model.** { *; }
-keep class com.xinze.xinze.module.login.modle.** { *; }
-keep class com.xinze.xinze.module.main.modle.** { *; }
-keep class com.xinze.xinze.module.message.model.** { *; }
-keep class com.xinze.xinze.module.order.modle.** { *; }
-keep class com.xinze.xinze.module.register.modle.** { *; }
-keep class com.xinze.xinze.module.regular.module.** { *; }
-keep class com.xinze.xinze.module.select.module.** { *; }
-keep class com.xinze.xinze.module.transport.module.** { *; }
-keep class com.xinze.xinze.module.trucks.model.** { *; }
-keep class com.xinze.xinze.widget.bean.** { *; }
-keep class com.xinze.xinze.config.** { *; }
-keep class com.xinze.xinze.http.** { *; }
-keep class com.xinze.xinze.utils{ *; }
-keep class com.xinze.xinze.utils.ReturnResult{*;}
-keep class com.xinze.xinze.utils.JsonMapper{*;}


#-------------------------------------------------------------------------


#---------------------------------2.第三方包-------------------------------
# glide 的混淆代码
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# banner 的混淆代码
-keep class com.youth.banner.** {
    *;
 }

 # retrofit2 的混淆代码
 -keep class retrofit2.** { *; }
 -dontwarn retrofit2.**
 -keepattributes Exceptions
 -dontwarn okio.**
 -dontwarn javax.annotation.**


 # Retain generic type information for use by reflection by converters and adapters.
 -keepattributes Signature
 # Retain service method parameters.
 -keepclassmembers,allowshrinking,allowobfuscation interface * {
     @retrofit2.http.* <methods>;
 }
 # Ignore annotation used for build tooling.
 -dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

  # okhttp3 的混淆代码
 -dontwarn okhttp3.**
 -dontwarn okio.**
 -dontwarn javax.annotation.**
 -dontwarn org.conscrypt.**
 # A resource is loaded with a relative path so the package of this class must be preserved.
 -keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.{*;}
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}

# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
-dontnote rx.internal.util.PlatformDependent

# gson的混淆代码
-keep class com.google.gson.** {*;}
-keep class com.google.**{*;}
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.qiancheng.carsmangersystem.**{*;}

#换肤框架的混淆文件
-keep class solid.ren.skinlibrary.** {*;}
-dontwarn solid.ren.skinlibrary.**


#地址选择器 的混淆代码
-keepattributes InnerClasses,Signature
-keep class cn.qqtheme.framework.entity.** { *;}

#权限 的混淆代码
-keepclassmembers class * {
    @pub.devrel.easypermissions.AfterPermissionGranted <methods>;
}

#下拉刷新上拉加载更多控件 混淆代码
-keep class com.github.mmin18.** {*;}

-dontwarn android.support.v8.renderscript.*
-keepclassmembers class android.support.v8.renderscript.RenderScript {
  native *** rsn*(...);
  native *** n*(...);
}

-keep class com.wang.avi.** { *; }
-keep class com.wang.avi.indicators.** { *; }

  # 图片剪切库 的混淆代码
 -dontwarn com.yalantis.ucrop**
 -keep class com.yalantis.ucrop** { *; }
 -keep interface com.yalantis.ucrop** { *; }

 #eventBus 的混淆代码
 -keepattributes *Annotation*
 -keepclassmembers class ** { @org.greenrobot.eventbus.Subscribe <methods>; }
 -keep enum org.greenrobot.eventbus.ThreadMode { *; }
 -keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent { <init>(java.lang.Throwable); }

 #butterknife 的混淆代码
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
#jackson 的混淆代码
-dontwarn org.codehaus.jackson.**
-keep class org.codehaus.jackson.** {*; }
-keep interface org.codehaus.jackson.** { *; }
-keep public class * extends org.codehaus.jackson.**

-keep class com.fasterxml.jackson.** { *; }
-dontwarn com.fasterxml.jackson.databind.**

 #比如我们要向activity传递对象使用了Serializable接口的时候，这时候这个类及类里面#的所有内容都不能混淆
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
*;
}
#-------------------------------------------------------------------------


#---------------------------------3.与js互相调用的类------------------------


#-------------------------------------------------------------------------


#---------------------------------4.反射相关的类和方法-----------------------


#----------------------------------------------------------------------------

#-------------------------------------------基本不用动区域--------------------------------------------

#---------------------------------------------------------------------------------------------------


#---------------------------------基本指令区----------------------------------
#代码混淆的压缩比例，值在0-7之间
-optimizationpasses 5
#混淆后类名都为小写
-dontusemixedcaseclassnames
#指定不去忽略非公共的库的类
-dontskipnonpubliclibraryclasses
#指定不去忽略非公共的库的类的成员
-dontskipnonpubliclibraryclassmembers
#不做预校验的操作
-dontpreverify
#生成原类名和混淆后的类名的映射文件
-verbose
-printmapping proguardMapping.txt

#指定混淆是采用的算法
-optimizations !code/simplification/cast,!field/*,!class/merging/*
#不混淆Annotation
-keepattributes *Annotation*,InnerClasses
#不混淆泛型
-keepattributes Signature
#抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------


#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.app.Fragment
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {
    *;
 }
-keepclasseswithmembernames class * {
    native <methods>;
 }
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
 }
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
 }
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace(); java.lang.Object readResolve();
 }
 -keep class **.R$* {
     *;
 }
 -keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
 }



# 保留support下的所有类及其内部类
-keep class android.support.** {*;}

  -keep public class * extends android.support.v4.**
  -keep public class * extends android.support.v7.**

 #保留所有的v4包中接口不被混淆
 -keep interface android.support.v4.** { *; }
 -keep interface android.support.v7.** { *; }

 #保留系统中继实现v4/v7包的接口，不被混淆
 -keep public class * implements android.support.v4.**
 -keep public class * implements android.support.v7.**
 #保留系统中v4/v7包的内部类，不被混淆
 -keep class android.support.v4.internal.** { *; }
 -keep class android.support.v7.internal.** { *; }

 -dontwarn android.support.**


#自定义View构造方法不混淆
-keepclasseswithmembers class * { public <init>(android.content.Context); }
-keepclasseswithmembers class * { public <init>(android.content.Context,android.util.AttributeSet); }
-keepclasseswithmembers class * { public <init>(android.content.Context,android.util.AttributeSet,int); }

#release版不打印log
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** e(...);
    public static *** w(...);
 }

#Design包不混淆
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

-ignorewarning

 #----------------------------------------------------------------------------


 #---------------------------------webview------------------------------------
 -keepclassmembers class fqcn.of.javascript.interface.for.Webview {
     public *;
 }
 -keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
 }
 -keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
 }
#----------------------------------------------------------------------------




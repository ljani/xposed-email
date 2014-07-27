# Email - Kill attachment limits

This [Xposed](http://xposed.info) module removes the default 5MB attachment limit ("Can't attach file over 5MB.") of the [AOSP](https://android.googlesource.com/platform/packages/apps/Email)/[Play Store](https://play.google.com/store/apps/details?id=com.google.android.email) email application. It will not override attachment limits set by email server administrator, only the default artificial limits set by the email application are changed.

## Compatibility

- [Xposed](http://repo.xposed.info/module/de.robv.android.xposed.installer) v54 or later
- Currently only a recent version of the email application is supported
    - The version must be based on the [UnifiedEmail](https://android.googlesource.com/platform/packages/apps/UnifiedEmail/) package and contain [this commit](https://android.googlesource.com/platform/packages/apps/UnifiedEmail/+/3cd4f40dab2c0810cb5ecd77256abf022863b2b5). Thus the `minSdkVersion` is set to `19`.
    - Contributions for other versions are welcome
    - To see if your version is supported, just install the module and see the log of Xposed Installer for any errors

## Install

- Install [Xposed](http://repo.xposed.info/module/de.robv.android.xposed.installer)
- Install this module
- Activate the module in Xposed
- Reboot

## Binaries
- Download binaries from [the Xposed Module Repository](http://repo.xposed.info/module/io.github.ljani.email)

## Links
- [XDA Forum Discussion](http://forum.xda-developers.com/xposed/modules/mod-email-kill-attachment-limits-t2830509)

## Compiling

- Install the [Android SDK](http://developer.android.com/sdk) and version `20.0.0` of the build tools
- Install [Xposed as an addon](http://forum.xda-developers.com/showpost.php?p=41904291) to the Android SDK
- Install [Gradle](http://www.gradle.org/)

### Debug version

- Run the `assembleDebug` task using `gradle assembleDebug`

### Release version

- Create file `gradle/signing-release.gradle` with following contents:

```
storeFile file('android.keystore')
storePassword 'password'
keyAlias 'xposed-email'
keyPassword 'password'
```

- Run the `assembleRelease` task using `gradle assembleRelease`

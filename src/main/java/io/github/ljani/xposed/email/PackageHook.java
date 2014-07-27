package io.github.ljani.xposed.email;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XCallback;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public class PackageHook implements IXposedHookLoadPackage {
    private static final List<String> TARGET_PACKAGES = Arrays.asList("com.android.email", "com.google.android.email");
    private static final String TARGET_CLASS = "com.android.mail.providers.Settings";
    private static final String TARGET_METHOD = "getMaxAttachmentSize";

    private final XCallback getMaxAttachmentSizeReplacement = new GetMaxAttachmentSizeReplacement();

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (TARGET_PACKAGES.contains(loadPackageParam.packageName)) {
            try {
                XposedHelpers.findAndHookMethod(TARGET_CLASS, loadPackageParam.classLoader, TARGET_METHOD, getMaxAttachmentSizeReplacement);
            } catch (RuntimeException e) {
                XposedBridge.log(e);
                XposedBridge.log("xposed-email: Couldn't find the target method, your email.apk is probably too old. Patches welcome.");
            }
        }
    }

    private static class GetMaxAttachmentSizeReplacement extends XC_MethodReplacement {
        private static final String MAX_ATTACHMENT_SIZE_FIELD_NAME = "maxAttachmentSize";

        @Override
        protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
            int maxAttachmentSize = XposedHelpers.getIntField(methodHookParam.thisObject, MAX_ATTACHMENT_SIZE_FIELD_NAME);
            return (maxAttachmentSize <= 0) ? Integer.MAX_VALUE : maxAttachmentSize;
        }
    }
}
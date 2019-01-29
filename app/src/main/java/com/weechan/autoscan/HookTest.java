package com.weechan.autoscan;

import android.content.Context;
import android.widget.Toast;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookTest implements IXposedHookLoadPackage{
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("Loaded app: " + lpparam.packageName);

        if (lpparam.packageName.equals("com.hongxi.charge")){
            XposedHelpers.findAndHookMethod("com.hongxi.charge.ui.main.MainActivity", lpparam.classLoader, "onBackPressed", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("TAG " + "BEFORE ON CRATE");
                    Toast.makeText((Context) param.thisObject, "GOod LUCK MYYY GSDF", Toast.LENGTH_SHORT).show();
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("TAG " + "AFTER ON CREATE");
                    XposedBridge.log("TAG " + param.toString());
                    Toast.makeText((Context) param.thisObject, "GOod LUCK MYYY GSDF", Toast.LENGTH_SHORT).show();
                    super.afterHookedMethod(param);
                }
            });
        }
    }
}
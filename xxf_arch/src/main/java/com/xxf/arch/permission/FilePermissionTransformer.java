package com.xxf.arch.permission;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * @author youxuan  E-mail:youxuan@icourt.cc
 * @version 2.3.1
 * @Description 文件读写权限
 * @date createTime：2018/9/3
 */
public class FilePermissionTransformer extends XXFPermissionTransformer {

    private FilePermissionTransformer(@NonNull Context context, @NonNull String rejectNotice) {
        super(context, rejectNotice);
    }

    public FilePermissionTransformer(@NonNull Context context, boolean rejecctJumpPermissionSetting) {
        super(context, "请开启文件权限", rejecctJumpPermissionSetting);
    }

    public FilePermissionTransformer(@NonNull Context context) {
        super(context,"请开启文件权限");
    }
}

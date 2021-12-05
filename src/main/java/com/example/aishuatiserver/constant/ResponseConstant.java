package com.example.aishuatiserver.constant;

import com.example.aishuatiserver.util.BaseResponsePackageUtil;

import java.util.Map;

public class ResponseConstant {

    // User
    public static final Map<String, Object> X_USER_NOT_FOUND = BaseResponsePackageUtil.errorMessage("用户不存在", 404);
    public static final Map<String, Object> X_USER_FORBIDDEN = BaseResponsePackageUtil.errorMessage("用户已被禁用", 403);
    public static final Map<String, Object> X_USER_LOGIN_FIRST = BaseResponsePackageUtil.errorMessage("请先登录");
    public static final Map<String, Object> X_USER_WRONG_PASSWORD = BaseResponsePackageUtil.errorMessage("用户名或密码错误");
    public static final Map<String, Object> X_USER_ALREADY_EXISTS = BaseResponsePackageUtil.errorMessage("用户已存在");
    public static final Map<String, Object> X_ALREADY_EXISTS = BaseResponsePackageUtil.errorMessage("请勿重复添加");

    public static final Map<String, Object> V_USER_LOGIN_SUCCESS = BaseResponsePackageUtil.succeedMessage("登录成功");
    public static final Map<String, Object> V_USER_LOGOUT_SUCCESS = BaseResponsePackageUtil.succeedMessage("退出成功");
    public static final Map<String, Object> V_USER_REGISTER_SUCCESS = BaseResponsePackageUtil.succeedMessage("注册成功");
    public static final Map<String, Object> V_USER_SEND_SUCCESS = BaseResponsePackageUtil.succeedMessage("发送成功");

    public static final Map<String, Object> X_ACCESS_DENIED = BaseResponsePackageUtil.errorMessage("访问受限", 403);

    // Upload
    public static final Map<String, Object> X_UPLOAD_FAILED = BaseResponsePackageUtil.errorMessage("上传失败");
    public static final Map<String, Object> V_UPLOAD_SUCCESS = BaseResponsePackageUtil.succeedMessage("上传成功");

    // Solution
    public static final Map<String, Object> X_LANGUAGE_NOT_SUPPORTED = BaseResponsePackageUtil.errorMessage("该语言不支持");
    public static final Map<String, Object> X_CODE_IS_TOO_SHORT = BaseResponsePackageUtil.errorMessage("代码过短");

    // Contest
    public static final Map<String, Object> X_CONTEST_WRONG_PASSWORD = BaseResponsePackageUtil.errorMessage("比赛密码不正确");
    public static final Map<String, Object> X_CONTEST_IS_NOT_PRIVATE = BaseResponsePackageUtil.errorMessage("该比赛不是私有比赛");
    public static final Map<String, Object> X_CONTEST_HAS_NOT_STARTED = BaseResponsePackageUtil.errorMessage("比赛还未开始", -1);
    public static final Map<String, Object> V_CONTEST_REGISTER_SUCCESS = BaseResponsePackageUtil.succeedMessage("注册成功");


    // Any error
    public static final Map<String, Object> X_SYSTEM_ERROR = BaseResponsePackageUtil.errorMessage("系统错误", 500);
    public static final Map<String, Object> X_CAPTCHA_WRONG = BaseResponsePackageUtil.errorMessage("验证码错误");
    public static final Map<String, Object> X_NOT_FOUND = BaseResponsePackageUtil.errorMessage("资源不存在", 404);
    public static final Map<String, Object> X_BAD_REQUEST = BaseResponsePackageUtil.errorMessage("请求参数错误", 400);
    public static final Map<String, Object> X_UPDATE_FAILED = BaseResponsePackageUtil.errorMessage("更新失败");
    public static final Map<String, Object> X_DELETE_FAILED = BaseResponsePackageUtil.errorMessage("删除失败");
    public static final Map<String, Object> X_QUERY_FAILED = BaseResponsePackageUtil.errorMessage("请先提交答案");
    public static final Map<String, Object> X_ADD_FAILED = BaseResponsePackageUtil.errorMessage("新增失败");
    public static final Map<String, Object> X_REPLACE_PWD = BaseResponsePackageUtil.errorMessage("输入两次密码不一致");

    // Any success
    public static final Map<String, Object> V_UPDATE_SUCCESS = BaseResponsePackageUtil.succeedMessage("更新成功");
    public static final Map<String, Object> V_DELETE_SUCCESS = BaseResponsePackageUtil.succeedMessage("删除成功");
    public static final Map<String, Object> V_ADD_SUCCESS = BaseResponsePackageUtil.succeedMessage("新增成功");
    public static final Map<String, Object> V_SUBMIT_SUCCESS = BaseResponsePackageUtil.succeedMessage("新增成功");

    // Judgehost
    public static final Map<String, Object> X_JUDGEHOST_DUE = BaseResponsePackageUtil.errorMessage("验证过期", 700);
    public static final Map<String, Object> X_JUDGERIGHT = BaseResponsePackageUtil.errorMessage("答案正确",200);
    public static final Map<String, Object> X_JUDGEWORING = BaseResponsePackageUtil.errorMessage("答案错误",200);

    // Other
    public static final Map<String, Object> X_CUT_HAIR_BY_SELF = BaseResponsePackageUtil.errorMessage("不能给自己剪头发！");
}


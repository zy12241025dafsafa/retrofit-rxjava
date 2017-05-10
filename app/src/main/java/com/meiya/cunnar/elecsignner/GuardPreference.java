package com.meiya.cunnar.elecsignner;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * save some light-level data in guard could app
 * cache
 *
 * @author zhangy
 */
public class GuardPreference {

    static GuardPreference pref;
    Context context;
    SharedPreferences mySharedPreferences;
    private final String TOKEN = "token";
    private final String ENVIRONMENT_TYPE = "environment_type";
    private final String CURRENT_ADDRESS = "current_address";
    private final String CURRENT_LAT = "current_lat";
    private final String CURRENT_LON = "current_long";
    private static final String ACCOUNT = "account2";
    private static final String PASSWORD = "password2";
    private static final String IS_LOGIN_SUCCESS = "is_login_success";
    private static final String ILLEGAL_PARK_PERMISSION_PASS_SHOW = "ILLEGAL_PARK_PERMISSION_PASS_SHOW";
    private static final String USERID = "userid";
    private final String TAG = "GuardPreference";
    private static final String MACHINE_ID = "machine_id";
    private static final String DELIVERY_METHOD = "delivery_method";
    private static final String CURRENT_DUMP_TIME = "current_dump_time";
    private static final String SHOW_CHECK_RESULT_PAGE = "show_check_result";
    private static final String CURRENT_RECORD_TIP = "current_tip";
    private static final String ROLE = "role";
    private static final String PUSH_MESSAGE_CONFIG = "push_msg_config";
    public static final String SHOW_WELCOME_PICS = "show_welcome_pic";
    public static final String VERSION = "version";
    public static final String USERINFO = "userinfo";
    public static final String SERVER_TIME_OFFSET = "server_offset";
    public static final String PROVINCE = "province";
    public static final String DISTRICT = "district";
    public static final String CITY = "city";
    private static final String IS_GESTURE_WRONG = "isGestureWrong";
    private static final String HOME_TIME = "home_time";
    private static final String IS_FIRST_TIME_SET_GESTURE_LOCK = "is_first_time_set_gesture_lock";
    public static final String EXEC_TOAST_STATUS = "exec_toast_status";
    public static final String USER_HEAD = "user_head";

    public static GuardPreference getinstance(Context context) {
        if (pref == null) {
            pref = new GuardPreference(context);
        }
        return pref;
    }

    public GuardPreference(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
        mySharedPreferences = context.getSharedPreferences("guard_cloud_pref", Activity.MODE_PRIVATE);
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public String getToken() {
        return mySharedPreferences.getString(TOKEN, "");
    }

    public void saveUserInfo(String userInfo) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(USERINFO, userInfo);
        editor.commit();
    }

    public String getUserInfo() {
        return mySharedPreferences.getString(USERINFO, "");
    }



    public void setCurrentAddress(String address) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(CURRENT_ADDRESS, address);
        editor.commit();
    }

    public String getCurrentAddress() {
        return mySharedPreferences.getString(CURRENT_ADDRESS, "");
    }

    public void setPushMsgConfig(String pushConfig) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(PUSH_MESSAGE_CONFIG, pushConfig);
        editor.commit();
    }

    public String getPushMsgConfig() {
        return mySharedPreferences.getString(PUSH_MESSAGE_CONFIG, "");
    }

    public void setCurrentLat(float lat) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putFloat(CURRENT_LAT, lat);
        editor.commit();
    }

    public float getCurrentLat() {
        return mySharedPreferences.getFloat(CURRENT_LAT, 0);
    }

    public void setCurrentLon(float lon) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putFloat(CURRENT_LON, lon);
        editor.commit();
    }

    public float getCurrentLon() {
        return mySharedPreferences.getFloat(CURRENT_LON, 0);
    }


    public void setCurrentProvince(String province) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putString(PROVINCE, province).commit();
        }
    }

    public String getCurrentProvince() {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getString(PROVINCE, "");
        }
        return "";
    }

    public void setCurrentCity(String province) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putString(CITY, province).commit();
        }
    }

    public String getCurrentCity() {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getString(CITY, "");
        }
        return "";
    }

    public void setCurrentDistrict(String province) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putString(DISTRICT, province).commit();
        }
    }

    public String getCurrentDistrict() {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getString(DISTRICT, "");
        }
        return "";
    }

    public void setLastCrashTime(long time) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putLong(CURRENT_DUMP_TIME, time).commit();
        }
    }

    public long getLastCrashTime() {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getLong(CURRENT_DUMP_TIME, System.currentTimeMillis());
        }
        return 0;
    }

    public void setMachineId(String machineId) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putString(MACHINE_ID, machineId).commit();
        }
    }

    public String getMachineId() {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getString(MACHINE_ID, "");
        }
        return "";
    }

    public void setShowImg(boolean hasShow) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putBoolean(SHOW_WELCOME_PICS, hasShow).commit();
        }
    }

    public boolean getShowImg() {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getBoolean(SHOW_WELCOME_PICS, false);
        }
        return false;
    }

    //保存登录是否成功的状态
    public void saveLoginState(boolean state) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putBoolean(IS_LOGIN_SUCCESS, state).commit();
        }
    }

    public boolean getLoginState() {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getBoolean(IS_LOGIN_SUCCESS, false);
        }
        return false;
    }


    public void setIllegalProfileShow(String username,boolean state) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putBoolean(username, state).commit();
        }
    }

    public boolean getIllegalProfileShow(String username) {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getBoolean(username, false);
        }
        return false;
    }

    public void setIllegalParkPassStatus(String user,boolean state) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putBoolean(user, state).commit();
        }
    }

    public boolean getIllegalParkPassStatus(String user) {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getBoolean(user, false);
        }
        return false;
    }

    public void saveUSERID(int uid) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putInt(USERID, uid).commit();
        }
    }

    public int getUSERID() {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getInt(USERID, 0);
        }
        return 0;
    }

    public void setCheckResultShow(String username, boolean state) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putBoolean(username, state).commit();
        }
    }

    public boolean getCheckResultShow(String username) {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getBoolean(username, false);
        }
        return false;
    }

    public static class Account {
        public String account;
        public String password;

        public Account(final String acc, final String psw) {
            account = acc;
            password = psw;
        }

        public String getUserName() {
            return account;
        }

        public String getPassword() {
            return password;
        }

        // check whether the account is valid or not?
        public boolean isValid() {
            //			if (Utility.isEmptyString(account)
            //					|| Utility.isEmptyString(password))
            //				return false;
            return true;
        }
    }


    public void setExecToastStatus(boolean tip) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putBoolean(EXEC_TOAST_STATUS, tip).commit();
        }
    }

    public boolean getExecToastStatus() {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getBoolean(EXEC_TOAST_STATUS, false);
        }
        return false;
    }


    public void setCurrentRecordNotip(boolean tip) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putBoolean(CURRENT_RECORD_TIP, tip).commit();
        }
    }

    public boolean getCurrentRecordNotip() {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getBoolean(CURRENT_RECORD_TIP, false);
        }
        return false;
    }

    public void setVersion(final String version) {
        try {
            final Editor editor = mySharedPreferences.edit();
            if (editor != null) {
                editor.putString(VERSION, version);
                editor.commit();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public String getVersion() {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getString(VERSION, "");
        }
        return "";
    }


    public void setServerTimeOffset(long offset) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putLong(SERVER_TIME_OFFSET, offset).commit();
        }
    }

    public long getServerTimeOffset() {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getLong(SERVER_TIME_OFFSET, 0);
        }
        return 0;
    }

    public void setIsGestureWrongFlag(boolean isWrong) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putBoolean(IS_GESTURE_WRONG, isWrong).commit();
        }
    }

    public boolean getIsGestureWrongFlag() {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getBoolean(IS_GESTURE_WRONG, false);
        }
        return false;
    }

    public void saveSettingValue(String key, int value) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putInt(key, value).commit();
        }
    }

    public int getSettingValue(String key, int defaultValue) {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getInt(key, defaultValue);
        }
        return defaultValue;
    }

    public void saveCurrentHomeTime(long time) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putLong(HOME_TIME, time).commit();
        }
    }

    public long getCurrentHomeTime() {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getLong(HOME_TIME, 0);
        }
        return 0;
    }

    public void setIsFirstTimeSetGestureLock(boolean isFirstTime) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putBoolean(IS_FIRST_TIME_SET_GESTURE_LOCK, isFirstTime).commit();
        }
    }

    public boolean getIsFirstTimeSetGestureLock() {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getBoolean(IS_FIRST_TIME_SET_GESTURE_LOCK, true);
        }
        return true;
    }

    public void setUserHead(int logoId) {
        if (mySharedPreferences != null) {
            mySharedPreferences.edit().putInt(USER_HEAD, logoId).commit();
        }
    }

    public int getUserHead() {
        if (mySharedPreferences != null) {
            return mySharedPreferences.getInt(USER_HEAD, 0);
        }
        return 0;
    }

}

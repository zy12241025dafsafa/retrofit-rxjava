package com.meiya.cunnar.elecsignner;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */

public class LoginBean {


    /**
     * success : true
     * data : {"username":"admin","type":0,"card":"111111111111111111","realName":"超级管理员","pcsCode":"350200000000","birthday":"1111-11-11","area":"思明区","areaCode":"思明区","police":"厦门市公安局","policeId":2991,"noReads":0,"status":1,"telephone":"13333333336","logo":69342,"userGroups":[],"fsrw":false,"fjzs":true,"pt":true,"pttgx":true,"pttgg":true,"integrity":true,"resetPwd":false,"telStatus":true,"firstLogin":false,"firstNickname":false}
     * response_time : 1493884217965
     * jsessionid : 53083ced-e559-4481-8bcc-c44ea0750014
     */

    private boolean success;
    private DataBean data;
    private long response_time;
    private String jsessionid;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public long getResponse_time() {
        return response_time;
    }

    public void setResponse_time(long response_time) {
        this.response_time = response_time;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public static class DataBean {
        /**
         * username : admin
         * type : 0
         * card : 111111111111111111
         * realName : 超级管理员
         * pcsCode : 350200000000
         * birthday : 1111-11-11
         * area : 思明区
         * areaCode : 思明区
         * police : 厦门市公安局
         * policeId : 2991
         * noReads : 0
         * status : 1
         * telephone : 13333333336
         * logo : 69342
         * userGroups : []
         * fsrw : false
         * fjzs : true
         * pt : true
         * pttgx : true
         * pttgg : true
         * integrity : true
         * resetPwd : false
         * telStatus : true
         * firstLogin : false
         * firstNickname : false
         */

        private String username;
        private int type;
        private String card;
        private String realName;
        private String pcsCode;
        private String birthday;
        private String area;
        private String areaCode;
        private String police;
        private int policeId;
        private int noReads;
        private int status;
        private String telephone;
        private int logo;
        private boolean fsrw;
        private boolean fjzs;
        private boolean pt;
        private boolean pttgx;
        private boolean pttgg;
        private boolean integrity;
        private boolean resetPwd;
        private boolean telStatus;
        private boolean firstLogin;
        private boolean firstNickname;
        private List<?> userGroups;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getPcsCode() {
            return pcsCode;
        }

        public void setPcsCode(String pcsCode) {
            this.pcsCode = pcsCode;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getPolice() {
            return police;
        }

        public void setPolice(String police) {
            this.police = police;
        }

        public int getPoliceId() {
            return policeId;
        }

        public void setPoliceId(int policeId) {
            this.policeId = policeId;
        }

        public int getNoReads() {
            return noReads;
        }

        public void setNoReads(int noReads) {
            this.noReads = noReads;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public int getLogo() {
            return logo;
        }

        public void setLogo(int logo) {
            this.logo = logo;
        }

        public boolean isFsrw() {
            return fsrw;
        }

        public void setFsrw(boolean fsrw) {
            this.fsrw = fsrw;
        }

        public boolean isFjzs() {
            return fjzs;
        }

        public void setFjzs(boolean fjzs) {
            this.fjzs = fjzs;
        }

        public boolean isPt() {
            return pt;
        }

        public void setPt(boolean pt) {
            this.pt = pt;
        }

        public boolean isPttgx() {
            return pttgx;
        }

        public void setPttgx(boolean pttgx) {
            this.pttgx = pttgx;
        }

        public boolean isPttgg() {
            return pttgg;
        }

        public void setPttgg(boolean pttgg) {
            this.pttgg = pttgg;
        }

        public boolean isIntegrity() {
            return integrity;
        }

        public void setIntegrity(boolean integrity) {
            this.integrity = integrity;
        }

        public boolean isResetPwd() {
            return resetPwd;
        }

        public void setResetPwd(boolean resetPwd) {
            this.resetPwd = resetPwd;
        }

        public boolean isTelStatus() {
            return telStatus;
        }

        public void setTelStatus(boolean telStatus) {
            this.telStatus = telStatus;
        }

        public boolean isFirstLogin() {
            return firstLogin;
        }

        public void setFirstLogin(boolean firstLogin) {
            this.firstLogin = firstLogin;
        }

        public boolean isFirstNickname() {
            return firstNickname;
        }

        public void setFirstNickname(boolean firstNickname) {
            this.firstNickname = firstNickname;
        }

        public List<?> getUserGroups() {
            return userGroups;
        }

        public void setUserGroups(List<?> userGroups) {
            this.userGroups = userGroups;
        }
    }
}

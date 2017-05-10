package com.meiya.cunnar.elecsignner;

import java.util.List;

public class AttachUserResult {

    String username;
    String telephone;
    String pcsCode;
    int type;//用户身份，0--物建人， 1-群防人员
    String card;
    String realName;
    String sex;
    String policeCode;//警号
    String residencet;//籍贯
    String domicile;//经常居住地
    String degree;//文化程度
    String degreeCode;
    String league;//政治面貌
    String leagueCode;
    String cardBack;  //身份证背面
    String cardFront;//身份证正面
    String cardPhoto;//个人近照

    String cardBackOpenKey;
    String cardFrontOpenKey;
    String cardPhotoOpenKey;

    int authType;//认证类型 0---正常二代身份证检测，1--在线人像检测
    int	authResult;//活体检测通过与否的结果 0--成功 1--失败

  //	String cardBack64;  //身份证背面
    //	String cardFront64;//身份证正面
    //	String cardPhoto64;//个人近照
    String workUnit;//工作单位
    String occupation;//职业
    String occupationCode;
    String referrer; //推荐人电话
    String birthday; //生日 yyyy-MM-dd
    String nation;//民族
    String nationCode;
    String major; //专业
    String majorCode;
    String specialty; //特长
    String resume; //主要简历
    String area; //所在地区
    String areaCode; //所在地区
    String police;//所属派出所
    String policeId;//所属派出所Code
    String noReads;//未读消息
    List<UserGroup> userGroups;
    String totalIntegral;//总积分
    long createdTime;
    boolean fsrw;//群防义务反扒队员是否可以发送任务
    boolean fjzs;//物建人是否可以查看辅警实时位置
    boolean integrity;//资料完整性
    int uncheckedCount;//未审核注册人数
    int logo;//头像fileid;

    private boolean pt; //物件人是否可以进行任务管理
    private boolean pttgx;//任务个性模板是否可以发布
    private boolean pttgg;//任务公共模板是否可以发布

    //zhangy add 20160711
    long mileageCount;//里程数
    //zhangy add 20160712 for car illegal park module permission
    boolean jtwfcj;//民警是否有车辆违法采集权限
    boolean resetPwd;//是否需要修改密码
    boolean telStatus; //民警是否需要确认手机号码
    boolean firstLogin;//是否第一次显示注册结果反馈界面
    boolean firstNickname;//是否第一次显示设置昵称界面

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public int getAuthType() {
        return authType;
    }

    public void setAuthType(int authType) {
        this.authType = authType;
    }

    public int getAuthResult() {
        return authResult;
    }

    public void setAuthResult(int authResult) {
        this.authResult = authResult;
    }

    public String getCardBackOpenKey() {
        return cardBackOpenKey;
    }

    public void setCardBackOpenKey(String cardBackOpenKey) {
        this.cardBackOpenKey = cardBackOpenKey;
    }

    public String getCardFrontOpenKey() {
        return cardFrontOpenKey;
    }

    public void setCardFrontOpenKey(String cardFrontOpenKey) {
        this.cardFrontOpenKey = cardFrontOpenKey;
    }

    public String getCardPhotoOpenKey() {
        return cardPhotoOpenKey;
    }

    public void setCardPhotoOpenKey(String cardPhotoOpenKey) {
        this.cardPhotoOpenKey = cardPhotoOpenKey;
    }

    public boolean isResetPwd() {
        return resetPwd;
    }

    public void setResetPwd(boolean resetPwd) {
        this.resetPwd = resetPwd;
    }

    public boolean isIntegrity() {
        return integrity;
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

    public boolean isJtwfcj() {
        return jtwfcj;
    }

    public void setJtwfcj(boolean jtwfcj) {
        this.jtwfcj = jtwfcj;
    }

    public long getMileageCount() {
        return mileageCount;
    }

    public void setMileageCount(long mileageCount) {
        this.mileageCount = mileageCount;
    }

    public Boolean getPt() {
        return pt;
    }

    public void setPt(Boolean pt) {
        this.pt = pt;
    }

    public Boolean getPttgx() {
        return pttgx;
    }

    public void setPttgx(Boolean pttgx) {
        this.pttgx = pttgx;
    }

    public Boolean getPttgg() {
        return pttgg;
    }

    public void setPttgg(Boolean pttgg) {
        this.pttgg = pttgg;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }


    public int getUncheckedCount() {
        return uncheckedCount;
    }

    public void setUncheckedCount(int uncheckedCount) {
        this.uncheckedCount = uncheckedCount;
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

    public String getPcsCode() {
        return pcsCode;
    }

    public void setPcsCode(String pcsCode) {
        this.pcsCode = pcsCode;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDegreeCode() {
        return degreeCode;
    }

    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
    }

    public String getLeagueCode() {
        return leagueCode;
    }

    public void setLeagueCode(String leagueCode) {
        this.leagueCode = leagueCode;
    }

    public String getOccupationCode() {
        return occupationCode;
    }

    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(String totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public String getNoReads() {
        return noReads;
    }

    public void setNoReads(String noReads) {
        this.noReads = noReads;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPoliceCode() {
        return policeCode;
    }

    public void setPoliceCode(String policeCode) {
        this.policeCode = policeCode;
    }

    public String getResidencet() {
        return residencet;
    }

    public void setResidencet(String residencet) {
        this.residencet = residencet;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getCardBack() {
        return cardBack;
    }

    public void setCardBack(String cardBack) {
        this.cardBack = cardBack;
    }

    public String getCardFront() {
        return cardFront;
    }

    public void setCardFront(String cardFront) {
        this.cardFront = cardFront;
    }

    public String getCardPhoto() {
        return cardPhoto;
    }

    public void setCardPhoto(String cardPhoto) {
        this.cardPhoto = cardPhoto;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

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

    public String getPolice() {
        return police;
    }

    public void setPolice(String police) {
        this.police = police;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public boolean getIntegrity() {
        return integrity;
    }

    public void setIntegrity(boolean integrity) {
        this.integrity = integrity;
    }

    public boolean isTelStatus() {
        return telStatus;
    }

    public void setTelStatus(boolean telStatus) {
        this.telStatus = telStatus;
    }

    public boolean isFirstNickname() {
        return firstNickname;
    }

    public void setFirstNickname(boolean firstNickname) {
        this.firstNickname = firstNickname;
    }

    @Override public String toString() {
        return "AttachUserResult{" +
            "username='" + username + '\'' +
            ", telephone='" + telephone + '\'' +
            ", pcsCode='" + pcsCode + '\'' +
            ", type=" + type +
            ", card='" + card + '\'' +
            ", realName='" + realName + '\'' +
            ", sex='" + sex + '\'' +
            ", policeCode='" + policeCode + '\'' +
            ", residencet='" + residencet + '\'' +
            ", domicile='" + domicile + '\'' +
            ", degree='" + degree + '\'' +
            ", degreeCode='" + degreeCode + '\'' +
            ", league='" + league + '\'' +
            ", leagueCode='" + leagueCode + '\'' +
            ", cardBack='" + cardBack + '\'' +
            ", cardFront='" + cardFront + '\'' +
            ", cardPhoto='" + cardPhoto + '\'' +
            ", cardBackOpenKey='" + cardBackOpenKey + '\'' +
            ", cardFrontOpenKey='" + cardFrontOpenKey + '\'' +
            ", cardPhotoOpenKey='" + cardPhotoOpenKey + '\'' +
            ", workUnit='" + workUnit + '\'' +
            ", occupation='" + occupation + '\'' +
            ", occupationCode='" + occupationCode + '\'' +
            ", referrer='" + referrer + '\'' +
            ", birthday='" + birthday + '\'' +
            ", nation='" + nation + '\'' +
            ", nationCode='" + nationCode + '\'' +
            ", major='" + major + '\'' +
            ", majorCode='" + majorCode + '\'' +
            ", specialty='" + specialty + '\'' +
            ", resume='" + resume + '\'' +
            ", area='" + area + '\'' +
            ", areaCode='" + areaCode + '\'' +
            ", police='" + police + '\'' +
            ", policeId='" + policeId + '\'' +
            ", noReads='" + noReads + '\'' +
            ", userGroups=" + userGroups +
            ", totalIntegral='" + totalIntegral + '\'' +
            ", createdTime=" + createdTime +
            ", fsrw=" + fsrw +
            ", fjzs=" + fjzs +
            ", integrity=" + integrity +
            ", uncheckedCount=" + uncheckedCount +
            ", logo=" + logo +
            ", pt=" + pt +
            ", pttgx=" + pttgx +
            ", pttgg=" + pttgg +
            ", mileageCount=" + mileageCount +
            ", jtwfcj=" + jtwfcj +
            ", resetPwd=" + resetPwd +
            ", telStatus=" + telStatus +
            '}';
    }
}

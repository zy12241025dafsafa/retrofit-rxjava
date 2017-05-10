package com.meiya.cunnar.elecsignner;

/**
 * 违法举报的详情
 *
 * @author huangxj
 */
public class IllegalReportBean {

    private int id;

    private String title;
    /**
     * 所属区编号
     */
    private int areaCode;
    /**
     * 所在地址
     */
    private String areaName;

    private String clueTypeBig;
    /**
     * 类型
     */
    private String clueType;
    private String clueBroadType;
    private String clueTypeName;
    private String clueBroadTypeName;


    /**
     * 举报人id
     */
    private int createUserId;

    /**
     * 举报人姓名
     */
    private String createUserName;

    /**
     * 描述
     */
    private String summary;

    /**
     * 文件id，多个以“|”号分离
     */
    private String fileIds;

    /**
     * 格式化的 创建时间
     */
    private String createTimeStr;

    /**
     * 创建时间
     */
    private long createdTime;

    /**
     * 格式 ： 经度，纬度
     */
    private String gps;

    /**
     * 状态 : 0-未处理、其他处理中、4,5-已处理
     */
    private int clueStatus;

    String filepaths;

    int subTaskId;
    int source;

    public String getFilepaths() {
        return filepaths;
    }

    public void setFilepaths(String filepaths) {
        this.filepaths = filepaths;
    }

    public String getClueTypeName() {
        return clueTypeName;
    }

    public void setClueTypeName(String clueTypeName) {
        this.clueTypeName = clueTypeName;
    }

    public String getClueBroadTypeName() {
        return clueBroadTypeName;
    }

    public void setClueBroadTypeName(String clueBroadTypeName) {
        this.clueBroadTypeName = clueBroadTypeName;
    }

    public String getClueBroadType() {
        return clueBroadType;
    }

    public void setClueBroadType(String clueBroadType) {
        this.clueBroadType = clueBroadType;
    }

    public static final int STATUS_WAIT_DEAL = 0;
    public static final int STATUS_DEALING = 1;
    public static final int STATUS_DEALED_NORMAL = 2;
    public static final int STATUS_DEALED_REJECTED = 3;

    public String getClueTypeBig() {
        return clueTypeBig;
    }

    public void setClueTypeBig(String clueTypeBig) {
        this.clueTypeBig = clueTypeBig;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getClueType() {
        return clueType;
    }

    public void setClueType(String clueType) {
        this.clueType = clueType;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public int getClueStatus() {
        return clueStatus;
    }

    public void setClueStatus(int clueStatus) {
        this.clueStatus = clueStatus;
    }

    public int getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(int subTaskId) {
        this.subTaskId = subTaskId;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    /**
     * 获取状态的中文
     *
     * @return
     */
    public String getStatusStr() {

        String statusStr = null;

        switch (clueStatus) {

            case STATUS_WAIT_DEAL:
                statusStr = "未处理";
                break;
            case STATUS_DEALING:
                statusStr = "处理中";
                break;
            case STATUS_DEALED_NORMAL:
            case STATUS_DEALED_REJECTED:
                statusStr = "已处理";
                break;
            default:
                statusStr = "UNKNOW(value=" + clueStatus + ")";
        }
        return statusStr;
    }

}

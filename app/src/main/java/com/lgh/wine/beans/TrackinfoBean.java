package com.lgh.wine.beans;

/**
 * Created by niujingtong on 2018/8/4.
 * 模块：
 */
public class TrackinfoBean {
    /**
     * Date : 2018-04-14 17:07:00
     * StatusDescription : 到达处理中心,来自中国 郑州
     * Details : 美国
     * checkpoint_status : transit
     */

    private String Date;
    private String StatusDescription;
    private String Details;
    private String checkpoint_status;

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getStatusDescription() {
        return StatusDescription;
    }

    public void setStatusDescription(String StatusDescription) {
        this.StatusDescription = StatusDescription;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String Details) {
        this.Details = Details;
    }

    public String getCheckpoint_status() {
        return checkpoint_status;
    }

    public void setCheckpoint_status(String checkpoint_status) {
        this.checkpoint_status = checkpoint_status;
    }
}

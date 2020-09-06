package com.ieng.huaimi.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class NetBean implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 当前时间
     */
    private Date currentTime;
    /**
     * 当前接收包裹数
     */
    private Long currentRxPackets;
    /**
     * 当前发送包裹数
     */
    private Long currentTxPackets;
    /**
     * 当前接收字节数
     */
    private Long currentRxBytes;
    /**
     * 当前发送字节数
     */
    private Long currentTxBytes;
    /**
     * 接收总包裹数
     */
    private Long rxPackets;
    /**
     * 发送总包裹数
     */
    private Long txPackets;
    /**
     * 接收总字节数
     */
    private Long rxBytes;
    /**
     * 发送总字节数
     */
    private Long txBytes;

}

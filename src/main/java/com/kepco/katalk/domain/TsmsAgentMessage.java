package com.kepco.katalk.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TsmsAgentMessage.
 */
@Entity
@Table(name = "tsms_agent_message")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TsmsAgentMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    //    @Id
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    //    @SequenceGenerator(name = "sequenceGenerator")
    //    private Long id;

    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TSMS_AGENT_MESSAGE_SEQ")
    //    @SequenceGenerator(name = "TSMS_AGENT_MESSAGE_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TSMS_AGENT_MESSAGE_SEQ")
    @SequenceGenerator(name = "TSMS_AGENT_MESSAGE_SEQ", sequenceName = "TSMS_AGENT_MESSAGE_SEQ", allocationSize = 1)
    @Column(name = "message_seqno")
    private Long messageSeqno;

    @Column(name = "service_seqno", columnDefinition = "long default 1810013776")
    private Long serviceSeqno;

    @Column(name = "send_message")
    private String sendMessage;

    @Column(name = "subject")
    private String subject;

    @Column(name = "backup_message")
    private String backupMessage;

    @Column(name = "backup_process_code", columnDefinition = "varchar2(4) default '003'")
    private String backupProcessCode;

    @Column(name = "message_type", columnDefinition = "varchar2(3) default '002'")
    private String messageType;

    @Column(name = "contents_type", columnDefinition = "varchar2(3) default '004'")
    private String contentsType;

    @Column(name = "receive_mobile_no")
    private String receiveMobileNo;

    @Column(name = "callback_no")
    private String callbackNo;

    @Column(name = "job_type")
    private String jobType;

    @Column(name = "send_reserve_date", columnDefinition = "DATE DEFAULT SYSDATE")
    private LocalDateTime sendReserveDate = LocalDateTime.now();

    @Column(name = "template_code")
    private String templateCode;

    @Column(name = "mms_img_path1")
    private String mmsImgPath1;

    @Column(name = "mms_img_path2")
    private String mmsImgPath2;

    @Column(name = "mms_img_path3")
    private String mmsImgPath3;

    @Column(name = "img_attach_flag", columnDefinition = "varchar2(1) default 'N'")
    private String imgAttachFlag;

    @Column(name = "kko_btn_name")
    private String kkoBtnName;

    @Column(name = "kko_btn_url")
    private String kkoBtnUrl;

    @Column(name = "kko_btn_link1")
    private String kkoBtnLink1;

    @Column(name = "kko_btn_link2")
    private String kkoBtnLink2;

    @Column(name = "kko_btn_link3")
    private String kkoBtnLink3;

    @Column(name = "kko_btn_link4")
    private String kkoBtnLink4;

    @Column(name = "kko_btn_link5")
    private String kkoBtnLink5;

    @Column(name = "kko_img_path")
    private String kkoImgPath;

    @Column(name = "kko_img_link_url")
    private String kkoImgLinkUrl;

    @Column(name = "tax_level1_nm")
    private String taxLevel1Nm;

    @Column(name = "tax_level2_nm")
    private String taxLevel2Nm;

    @Column(name = "register_by")
    private String registerBy;

    @Column(name = "register_date", columnDefinition = "DATE DEFAULT SYSDATE")
    private LocalDateTime registerDate = LocalDateTime.now();

    @Column(name = "cust_backup_flag", columnDefinition = "varchar2(1) default 'N'")
    private String custBackupFlag;

    @Column(name = "cust_message_type", columnDefinition = "varchar2(1) default 'S'")
    private String custMessageType;

    @Column(name = "cust_backup_date")
    private LocalDate custBackupDate;

    @Column(name = "cust_data1")
    private String custData1;

    @Column(name = "cust_data2")
    private String custData2;

    @Column(name = "cust_data3")
    private String custData3;

    @Column(name = "cust_data4")
    private String custData4;

    @Column(name = "cust_data5")
    private String custData5;

    @Column(name = "cust_data6")
    private String custData6;

    @Column(name = "cust_data7")
    private String custData7;

    @Column(name = "cust_data8")
    private String custData8;

    @Column(name = "cust_data9")
    private String custData9;

    @Column(name = "cust_data10")
    private String custData10;

    @Column(name = "send_flag", columnDefinition = "varchar2(1) default 'N'")
    private String sendFlag;

    @Column(name = "send_date")
    private LocalDate sendDate;

    @Column(name = "resend_flag")
    private String resendFlag;

    @Column(name = "mms_img_server_path1")
    private String mmsImgServerPath1;

    @Column(name = "mms_img_server_path2")
    private String mmsImgServerPath2;

    @Column(name = "mms_img_server_path3")
    private String mmsImgServerPath3;

    @Column(name = "img_send_date")
    private LocalDate imgSendDate;

    @Column(name = "update_date")
    private LocalDate updateDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "kko_img_server_path")
    private String kkoImgServerPath;

    @Column(name = "intf_nm")
    private String intfNm;

    @Column(name = "sended_id")
    private String sendedId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    //    public Long getId() {
    //        return id;
    //    }
    //
    //    public void setId(Long id) {
    //        this.id = id;
    //    }

    //    public TsmsAgentMessage id(Long messageSeqno) {
    //        this.messageSeqno = messageSeqno;
    //        return this;
    //    }

    public Long getMessageSeqno() {
        return this.messageSeqno;
    }

    public TsmsAgentMessage messageSeqno(Long messageSeqno) {
        this.messageSeqno = messageSeqno;
        return this;
    }

    public void setMessageSeqno(Long messageSeqno) {
        this.messageSeqno = messageSeqno;
    }

    public Long getServiceSeqno() {
        return this.serviceSeqno;
    }

    public TsmsAgentMessage serviceSeqno(Long serviceSeqno) {
        this.serviceSeqno = serviceSeqno;
        return this;
    }

    public void setServiceSeqno(Long serviceSeqno) {
        this.serviceSeqno = serviceSeqno;
    }

    public String getSendMessage() {
        return this.sendMessage;
    }

    public TsmsAgentMessage sendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
        return this;
    }

    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }

    public String getSubject() {
        return this.subject;
    }

    public TsmsAgentMessage subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBackupMessage() {
        return this.backupMessage;
    }

    public TsmsAgentMessage backupMessage(String backupMessage) {
        this.backupMessage = backupMessage;
        return this;
    }

    public void setBackupMessage(String backupMessage) {
        this.backupMessage = backupMessage;
    }

    public String getBackupProcessCode() {
        return this.backupProcessCode;
    }

    public TsmsAgentMessage backupProcessCode(String backupProcessCode) {
        this.backupProcessCode = backupProcessCode;
        return this;
    }

    public void setBackupProcessCode(String backupProcessCode) {
        this.backupProcessCode = backupProcessCode;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public TsmsAgentMessage messageType(String messageType) {
        this.messageType = messageType;
        return this;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getContentsType() {
        return this.contentsType;
    }

    public TsmsAgentMessage contentsType(String contentsType) {
        this.contentsType = contentsType;
        return this;
    }

    public void setContentsType(String contentsType) {
        this.contentsType = contentsType;
    }

    public String getReceiveMobileNo() {
        return this.receiveMobileNo;
    }

    public TsmsAgentMessage receiveMobileNo(String receiveMobileNo) {
        this.receiveMobileNo = receiveMobileNo;
        return this;
    }

    public void setReceiveMobileNo(String receiveMobileNo) {
        this.receiveMobileNo = receiveMobileNo;
    }

    public String getCallbackNo() {
        return this.callbackNo;
    }

    public TsmsAgentMessage callbackNo(String callbackNo) {
        this.callbackNo = callbackNo;
        return this;
    }

    public void setCallbackNo(String callbackNo) {
        this.callbackNo = callbackNo;
    }

    public String getJobType() {
        return this.jobType;
    }

    public TsmsAgentMessage jobType(String jobType) {
        this.jobType = jobType;
        return this;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public LocalDateTime getSendReserveDate() {
        return this.sendReserveDate;
    }

    public TsmsAgentMessage sendReserveDate(LocalDateTime sendReserveDate) {
        this.sendReserveDate = sendReserveDate;
        return this;
    }

    public void setSendReserveDate(LocalDateTime sendReserveDate) {
        this.sendReserveDate = sendReserveDate;
    }

    @PrePersist
    public void sendReserveDate() {
        this.sendReserveDate = LocalDateTime.now();
        this.registerDate = LocalDateTime.now();
    }

    public String getTemplateCode() {
        return this.templateCode;
    }

    public TsmsAgentMessage templateCode(String templateCode) {
        this.templateCode = templateCode;
        return this;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getMmsImgPath1() {
        return this.mmsImgPath1;
    }

    public TsmsAgentMessage mmsImgPath1(String mmsImgPath1) {
        this.mmsImgPath1 = mmsImgPath1;
        return this;
    }

    public void setMmsImgPath1(String mmsImgPath1) {
        this.mmsImgPath1 = mmsImgPath1;
    }

    public String getMmsImgPath2() {
        return this.mmsImgPath2;
    }

    public TsmsAgentMessage mmsImgPath2(String mmsImgPath2) {
        this.mmsImgPath2 = mmsImgPath2;
        return this;
    }

    public void setMmsImgPath2(String mmsImgPath2) {
        this.mmsImgPath2 = mmsImgPath2;
    }

    public String getMmsImgPath3() {
        return this.mmsImgPath3;
    }

    public TsmsAgentMessage mmsImgPath3(String mmsImgPath3) {
        this.mmsImgPath3 = mmsImgPath3;
        return this;
    }

    public void setMmsImgPath3(String mmsImgPath3) {
        this.mmsImgPath3 = mmsImgPath3;
    }

    public String getImgAttachFlag() {
        return this.imgAttachFlag;
    }

    public TsmsAgentMessage imgAttachFlag(String imgAttachFlag) {
        this.imgAttachFlag = imgAttachFlag;
        return this;
    }

    public void setImgAttachFlag(String imgAttachFlag) {
        this.imgAttachFlag = imgAttachFlag;
    }

    public String getKkoBtnName() {
        return this.kkoBtnName;
    }

    public TsmsAgentMessage kkoBtnName(String kkoBtnName) {
        this.kkoBtnName = kkoBtnName;
        return this;
    }

    public void setKkoBtnName(String kkoBtnName) {
        this.kkoBtnName = kkoBtnName;
    }

    public String getKkoBtnUrl() {
        return this.kkoBtnUrl;
    }

    public TsmsAgentMessage kkoBtnUrl(String kkoBtnUrl) {
        this.kkoBtnUrl = kkoBtnUrl;
        return this;
    }

    public void setKkoBtnUrl(String kkoBtnUrl) {
        this.kkoBtnUrl = kkoBtnUrl;
    }

    public String getKkoBtnLink1() {
        return this.kkoBtnLink1;
    }

    public TsmsAgentMessage kkoBtnLink1(String kkoBtnLink1) {
        this.kkoBtnLink1 = kkoBtnLink1;
        return this;
    }

    public void setKkoBtnLink1(String kkoBtnLink1) {
        this.kkoBtnLink1 = kkoBtnLink1;
    }

    public String getKkoBtnLink2() {
        return this.kkoBtnLink2;
    }

    public TsmsAgentMessage kkoBtnLink2(String kkoBtnLink2) {
        this.kkoBtnLink2 = kkoBtnLink2;
        return this;
    }

    public void setKkoBtnLink2(String kkoBtnLink2) {
        this.kkoBtnLink2 = kkoBtnLink2;
    }

    public String getKkoBtnLink3() {
        return this.kkoBtnLink3;
    }

    public TsmsAgentMessage kkoBtnLink3(String kkoBtnLink3) {
        this.kkoBtnLink3 = kkoBtnLink3;
        return this;
    }

    public void setKkoBtnLink3(String kkoBtnLink3) {
        this.kkoBtnLink3 = kkoBtnLink3;
    }

    public String getKkoBtnLink4() {
        return this.kkoBtnLink4;
    }

    public TsmsAgentMessage kkoBtnLink4(String kkoBtnLink4) {
        this.kkoBtnLink4 = kkoBtnLink4;
        return this;
    }

    public void setKkoBtnLink4(String kkoBtnLink4) {
        this.kkoBtnLink4 = kkoBtnLink4;
    }

    public String getKkoBtnLink5() {
        return this.kkoBtnLink5;
    }

    public TsmsAgentMessage kkoBtnLink5(String kkoBtnLink5) {
        this.kkoBtnLink5 = kkoBtnLink5;
        return this;
    }

    public void setKkoBtnLink5(String kkoBtnLink5) {
        this.kkoBtnLink5 = kkoBtnLink5;
    }

    public String getKkoImgPath() {
        return this.kkoImgPath;
    }

    public TsmsAgentMessage kkoImgPath(String kkoImgPath) {
        this.kkoImgPath = kkoImgPath;
        return this;
    }

    public void setKkoImgPath(String kkoImgPath) {
        this.kkoImgPath = kkoImgPath;
    }

    public String getKkoImgLinkUrl() {
        return this.kkoImgLinkUrl;
    }

    public TsmsAgentMessage kkoImgLinkUrl(String kkoImgLinkUrl) {
        this.kkoImgLinkUrl = kkoImgLinkUrl;
        return this;
    }

    public void setKkoImgLinkUrl(String kkoImgLinkUrl) {
        this.kkoImgLinkUrl = kkoImgLinkUrl;
    }

    public String getTaxLevel1Nm() {
        return this.taxLevel1Nm;
    }

    public TsmsAgentMessage taxLevel1Nm(String taxLevel1Nm) {
        this.taxLevel1Nm = taxLevel1Nm;
        return this;
    }

    public void setTaxLevel1Nm(String taxLevel1Nm) {
        this.taxLevel1Nm = taxLevel1Nm;
    }

    public String getTaxLevel2Nm() {
        return this.taxLevel2Nm;
    }

    public TsmsAgentMessage taxLevel2Nm(String taxLevel2Nm) {
        this.taxLevel2Nm = taxLevel2Nm;
        return this;
    }

    public void setTaxLevel2Nm(String taxLevel2Nm) {
        this.taxLevel2Nm = taxLevel2Nm;
    }

    public String getRegisterBy() {
        return this.registerBy;
    }

    public TsmsAgentMessage registerBy(String registerBy) {
        this.registerBy = registerBy;
        return this;
    }

    public void setRegisterBy(String registerBy) {
        this.registerBy = registerBy;
    }

    public LocalDateTime getRegisterDate() {
        return this.registerDate;
    }

    public TsmsAgentMessage registerDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
        return this;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public String getCustBackupFlag() {
        return this.custBackupFlag;
    }

    public TsmsAgentMessage custBackupFlag(String custBackupFlag) {
        this.custBackupFlag = custBackupFlag;
        return this;
    }

    public void setCustBackupFlag(String custBackupFlag) {
        this.custBackupFlag = custBackupFlag;
    }

    public String getCustMessageType() {
        return this.custMessageType;
    }

    public TsmsAgentMessage custMessageType(String custMessageType) {
        this.custMessageType = custMessageType;
        return this;
    }

    public void setCustMessageType(String custMessageType) {
        this.custMessageType = custMessageType;
    }

    public LocalDate getCustBackupDate() {
        return this.custBackupDate;
    }

    public TsmsAgentMessage custBackupDate(LocalDate custBackupDate) {
        this.custBackupDate = custBackupDate;
        return this;
    }

    public void setCustBackupDate(LocalDate custBackupDate) {
        this.custBackupDate = custBackupDate;
    }

    public String getCustData1() {
        return this.custData1;
    }

    public TsmsAgentMessage custData1(String custData1) {
        this.custData1 = custData1;
        return this;
    }

    public void setCustData1(String custData1) {
        this.custData1 = custData1;
    }

    public String getCustData2() {
        return this.custData2;
    }

    public TsmsAgentMessage custData2(String custData2) {
        this.custData2 = custData2;
        return this;
    }

    public void setCustData2(String custData2) {
        this.custData2 = custData2;
    }

    public String getCustData3() {
        return this.custData3;
    }

    public TsmsAgentMessage custData3(String custData3) {
        this.custData3 = custData3;
        return this;
    }

    public void setCustData3(String custData3) {
        this.custData3 = custData3;
    }

    public String getCustData4() {
        return this.custData4;
    }

    public TsmsAgentMessage custData4(String custData4) {
        this.custData4 = custData4;
        return this;
    }

    public void setCustData4(String custData4) {
        this.custData4 = custData4;
    }

    public String getCustData5() {
        return this.custData5;
    }

    public TsmsAgentMessage custData5(String custData5) {
        this.custData5 = custData5;
        return this;
    }

    public void setCustData5(String custData5) {
        this.custData5 = custData5;
    }

    public String getCustData6() {
        return this.custData6;
    }

    public TsmsAgentMessage custData6(String custData6) {
        this.custData6 = custData6;
        return this;
    }

    public void setCustData6(String custData6) {
        this.custData6 = custData6;
    }

    public String getCustData7() {
        return this.custData7;
    }

    public TsmsAgentMessage custData7(String custData7) {
        this.custData7 = custData7;
        return this;
    }

    public void setCustData7(String custData7) {
        this.custData7 = custData7;
    }

    public String getCustData8() {
        return this.custData8;
    }

    public TsmsAgentMessage custData8(String custData8) {
        this.custData8 = custData8;
        return this;
    }

    public void setCustData8(String custData8) {
        this.custData8 = custData8;
    }

    public String getCustData9() {
        return this.custData9;
    }

    public TsmsAgentMessage custData9(String custData9) {
        this.custData9 = custData9;
        return this;
    }

    public void setCustData9(String custData9) {
        this.custData9 = custData9;
    }

    public String getCustData10() {
        return this.custData10;
    }

    public TsmsAgentMessage custData10(String custData10) {
        this.custData10 = custData10;
        return this;
    }

    public void setCustData10(String custData10) {
        this.custData10 = custData10;
    }

    public String getSendFlag() {
        return this.sendFlag;
    }

    public TsmsAgentMessage sendFlag(String sendFlag) {
        this.sendFlag = sendFlag;
        return this;
    }

    public void setSendFlag(String sendFlag) {
        this.sendFlag = sendFlag;
    }

    public LocalDate getSendDate() {
        return this.sendDate;
    }

    public TsmsAgentMessage sendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
        return this;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public String getResendFlag() {
        return this.resendFlag;
    }

    public TsmsAgentMessage resendFlag(String resendFlag) {
        this.resendFlag = resendFlag;
        return this;
    }

    public void setResendFlag(String resendFlag) {
        this.resendFlag = resendFlag;
    }

    public String getMmsImgServerPath1() {
        return this.mmsImgServerPath1;
    }

    public TsmsAgentMessage mmsImgServerPath1(String mmsImgServerPath1) {
        this.mmsImgServerPath1 = mmsImgServerPath1;
        return this;
    }

    public void setMmsImgServerPath1(String mmsImgServerPath1) {
        this.mmsImgServerPath1 = mmsImgServerPath1;
    }

    public String getMmsImgServerPath2() {
        return this.mmsImgServerPath2;
    }

    public TsmsAgentMessage mmsImgServerPath2(String mmsImgServerPath2) {
        this.mmsImgServerPath2 = mmsImgServerPath2;
        return this;
    }

    public void setMmsImgServerPath2(String mmsImgServerPath2) {
        this.mmsImgServerPath2 = mmsImgServerPath2;
    }

    public String getMmsImgServerPath3() {
        return this.mmsImgServerPath3;
    }

    public TsmsAgentMessage mmsImgServerPath3(String mmsImgServerPath3) {
        this.mmsImgServerPath3 = mmsImgServerPath3;
        return this;
    }

    public void setMmsImgServerPath3(String mmsImgServerPath3) {
        this.mmsImgServerPath3 = mmsImgServerPath3;
    }

    public LocalDate getImgSendDate() {
        return this.imgSendDate;
    }

    public TsmsAgentMessage imgSendDate(LocalDate imgSendDate) {
        this.imgSendDate = imgSendDate;
        return this;
    }

    public void setImgSendDate(LocalDate imgSendDate) {
        this.imgSendDate = imgSendDate;
    }

    public LocalDate getUpdateDate() {
        return this.updateDate;
    }

    public TsmsAgentMessage updateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public TsmsAgentMessage updateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getKkoImgServerPath() {
        return this.kkoImgServerPath;
    }

    public TsmsAgentMessage kkoImgServerPath(String kkoImgServerPath) {
        this.kkoImgServerPath = kkoImgServerPath;
        return this;
    }

    public void setKkoImgServerPath(String kkoImgServerPath) {
        this.kkoImgServerPath = kkoImgServerPath;
    }

    public String getIntfNm() {
        return this.intfNm;
    }

    public TsmsAgentMessage intfNm(String intfNm) {
        this.intfNm = intfNm;
        return this;
    }

    public void setIntfNm(String intfNm) {
        this.intfNm = intfNm;
    }

    public String getSendedId() {
        return this.sendedId;
    }

    public TsmsAgentMessage sendedId(String sendedId) {
        this.sendedId = sendedId;
        return this;
    }

    public void setSendedId(String sendedId) {
        this.sendedId = sendedId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TsmsAgentMessage)) {
            return false;
        }
        return messageSeqno != null && messageSeqno.equals(((TsmsAgentMessage) o).messageSeqno);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TsmsAgentMessage{" +
//            "id=" + getId() +
            ", messageSeqno=" + getMessageSeqno() +
            ", serviceSeqno=" + getServiceSeqno() +
            ", sendMessage='" + getSendMessage() + "'" +
            ", subject='" + getSubject() + "'" +
            ", backupMessage='" + getBackupMessage() + "'" +
            ", backupProcessCode='" + getBackupProcessCode() + "'" +
            ", messageType='" + getMessageType() + "'" +
            ", contentsType='" + getContentsType() + "'" +
            ", receiveMobileNo='" + getReceiveMobileNo() + "'" +
            ", callbackNo='" + getCallbackNo() + "'" +
            ", jobType='" + getJobType() + "'" +
            ", sendReserveDate='" + getSendReserveDate() + "'" +
            ", templateCode='" + getTemplateCode() + "'" +
            ", mmsImgPath1='" + getMmsImgPath1() + "'" +
            ", mmsImgPath2='" + getMmsImgPath2() + "'" +
            ", mmsImgPath3='" + getMmsImgPath3() + "'" +
            ", imgAttachFlag='" + getImgAttachFlag() + "'" +
            ", kkoBtnName='" + getKkoBtnName() + "'" +
            ", kkoBtnUrl='" + getKkoBtnUrl() + "'" +
            ", kkoBtnLink1='" + getKkoBtnLink1() + "'" +
            ", kkoBtnLink2='" + getKkoBtnLink2() + "'" +
            ", kkoBtnLink3='" + getKkoBtnLink3() + "'" +
            ", kkoBtnLink4='" + getKkoBtnLink4() + "'" +
            ", kkoBtnLink5='" + getKkoBtnLink5() + "'" +
            ", kkoImgPath='" + getKkoImgPath() + "'" +
            ", kkoImgLinkUrl='" + getKkoImgLinkUrl() + "'" +
            ", taxLevel1Nm='" + getTaxLevel1Nm() + "'" +
            ", taxLevel2Nm='" + getTaxLevel2Nm() + "'" +
            ", registerBy='" + getRegisterBy() + "'" +
         //   ", registerDate='" + getRegisterDate() + "'" +
            ", custBackupFlag='" + getCustBackupFlag() + "'" +
            ", custMessageType='" + getCustMessageType() + "'" +
            ", custBackupDate='" + getCustBackupDate() + "'" +
            ", custData1='" + getCustData1() + "'" +
            ", custData2='" + getCustData2() + "'" +
            ", custData3='" + getCustData3() + "'" +
            ", custData4='" + getCustData4() + "'" +
            ", custData5='" + getCustData5() + "'" +
            ", custData6='" + getCustData6() + "'" +
            ", custData7='" + getCustData7() + "'" +
            ", custData8='" + getCustData8() + "'" +
            ", custData9='" + getCustData9() + "'" +
            ", custData10='" + getCustData10() + "'" +
            ", sendFlag='" + getSendFlag() + "'" +
            ", sendDate='" + getSendDate() + "'" +
            ", resendFlag='" + getResendFlag() + "'" +
            ", mmsImgServerPath1='" + getMmsImgServerPath1() + "'" +
            ", mmsImgServerPath2='" + getMmsImgServerPath2() + "'" +
            ", mmsImgServerPath3='" + getMmsImgServerPath3() + "'" +
            ", imgSendDate='" + getImgSendDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            ", kkoImgServerPath='" + getKkoImgServerPath() + "'" +
            ", intfNm='" + getIntfNm() + "'" +
            ", sendedId='" + getSendedId() + "'" +
            "}";
    }
}

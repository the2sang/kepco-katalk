package com.kepco.katalk.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.kepco.katalk.IntegrationTest;
import com.kepco.katalk.domain.TsmsAgentMessage;
import com.kepco.katalk.repository.TsmsAgentMessageRepository;
import com.kepco.katalk.service.dto.TsmsAgentMessageDTO;
import com.kepco.katalk.service.mapper.TsmsAgentMessageMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TsmsAgentMessageResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TsmsAgentMessageResourceIT {

    private static final Long DEFAULT_MESSAGE_SEQNO = 1L;
    private static final Long UPDATED_MESSAGE_SEQNO = 2L;

    private static final Long DEFAULT_SERVICE_SEQNO = 1L;
    private static final Long UPDATED_SERVICE_SEQNO = 2L;

    private static final String DEFAULT_SEND_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_SEND_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_BACKUP_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_BACKUP_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_BACKUP_PROCESS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BACKUP_PROCESS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENTS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CONTENTS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVE_MOBILE_NO = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVE_MOBILE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CALLBACK_NO = "AAAAAAAAAA";
    private static final String UPDATED_CALLBACK_NO = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_JOB_TYPE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SEND_RESERVE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SEND_RESERVE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TEMPLATE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_MMS_IMG_PATH_1 = "AAAAAAAAAA";
    private static final String UPDATED_MMS_IMG_PATH_1 = "BBBBBBBBBB";

    private static final String DEFAULT_MMS_IMG_PATH_2 = "AAAAAAAAAA";
    private static final String UPDATED_MMS_IMG_PATH_2 = "BBBBBBBBBB";

    private static final String DEFAULT_MMS_IMG_PATH_3 = "AAAAAAAAAA";
    private static final String UPDATED_MMS_IMG_PATH_3 = "BBBBBBBBBB";

    private static final String DEFAULT_IMG_ATTACH_FLAG = "AAAAAAAAAA";
    private static final String UPDATED_IMG_ATTACH_FLAG = "BBBBBBBBBB";

    private static final String DEFAULT_KKO_BTN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_KKO_BTN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_KKO_BTN_URL = "AAAAAAAAAA";
    private static final String UPDATED_KKO_BTN_URL = "BBBBBBBBBB";

    private static final String DEFAULT_KKO_BTN_LINK_1 = "AAAAAAAAAA";
    private static final String UPDATED_KKO_BTN_LINK_1 = "BBBBBBBBBB";

    private static final String DEFAULT_KKO_BTN_LINK_2 = "AAAAAAAAAA";
    private static final String UPDATED_KKO_BTN_LINK_2 = "BBBBBBBBBB";

    private static final String DEFAULT_KKO_BTN_LINK_3 = "AAAAAAAAAA";
    private static final String UPDATED_KKO_BTN_LINK_3 = "BBBBBBBBBB";

    private static final String DEFAULT_KKO_BTN_LINK_4 = "AAAAAAAAAA";
    private static final String UPDATED_KKO_BTN_LINK_4 = "BBBBBBBBBB";

    private static final String DEFAULT_KKO_BTN_LINK_5 = "AAAAAAAAAA";
    private static final String UPDATED_KKO_BTN_LINK_5 = "BBBBBBBBBB";

    private static final String DEFAULT_KKO_IMG_PATH = "AAAAAAAAAA";
    private static final String UPDATED_KKO_IMG_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_KKO_IMG_LINK_URL = "AAAAAAAAAA";
    private static final String UPDATED_KKO_IMG_LINK_URL = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_LEVEL_1_NM = "AAAAAAAAAA";
    private static final String UPDATED_TAX_LEVEL_1_NM = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_LEVEL_2_NM = "AAAAAAAAAA";
    private static final String UPDATED_TAX_LEVEL_2_NM = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTER_BY = "AAAAAAAAAA";
    private static final String UPDATED_REGISTER_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_REGISTER_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REGISTER_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CUST_BACKUP_FLAG = "AAAAAAAAAA";
    private static final String UPDATED_CUST_BACKUP_FLAG = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_MESSAGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CUST_MESSAGE_TYPE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CUST_BACKUP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CUST_BACKUP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CUST_DATA_1 = "AAAAAAAAAA";
    private static final String UPDATED_CUST_DATA_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_DATA_2 = "AAAAAAAAAA";
    private static final String UPDATED_CUST_DATA_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_DATA_3 = "AAAAAAAAAA";
    private static final String UPDATED_CUST_DATA_3 = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_DATA_4 = "AAAAAAAAAA";
    private static final String UPDATED_CUST_DATA_4 = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_DATA_5 = "AAAAAAAAAA";
    private static final String UPDATED_CUST_DATA_5 = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_DATA_6 = "AAAAAAAAAA";
    private static final String UPDATED_CUST_DATA_6 = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_DATA_7 = "AAAAAAAAAA";
    private static final String UPDATED_CUST_DATA_7 = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_DATA_8 = "AAAAAAAAAA";
    private static final String UPDATED_CUST_DATA_8 = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_DATA_9 = "AAAAAAAAAA";
    private static final String UPDATED_CUST_DATA_9 = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_DATA_10 = "AAAAAAAAAA";
    private static final String UPDATED_CUST_DATA_10 = "BBBBBBBBBB";

    private static final String DEFAULT_SEND_FLAG = "AAAAAAAAAA";
    private static final String UPDATED_SEND_FLAG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SEND_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SEND_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RESEND_FLAG = "AAAAAAAAAA";
    private static final String UPDATED_RESEND_FLAG = "BBBBBBBBBB";

    private static final String DEFAULT_MMS_IMG_SERVER_PATH_1 = "AAAAAAAAAA";
    private static final String UPDATED_MMS_IMG_SERVER_PATH_1 = "BBBBBBBBBB";

    private static final String DEFAULT_MMS_IMG_SERVER_PATH_2 = "AAAAAAAAAA";
    private static final String UPDATED_MMS_IMG_SERVER_PATH_2 = "BBBBBBBBBB";

    private static final String DEFAULT_MMS_IMG_SERVER_PATH_3 = "AAAAAAAAAA";
    private static final String UPDATED_MMS_IMG_SERVER_PATH_3 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_IMG_SEND_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_IMG_SEND_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATE_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_BY = "BBBBBBBBBB";

    private static final String DEFAULT_KKO_IMG_SERVER_PATH = "AAAAAAAAAA";
    private static final String UPDATED_KKO_IMG_SERVER_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_INTF_NM = "AAAAAAAAAA";
    private static final String UPDATED_INTF_NM = "BBBBBBBBBB";

    private static final String DEFAULT_SENDED_ID = "AAAAAAAAAA";
    private static final String UPDATED_SENDED_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tsms-agent-messages";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TsmsAgentMessageRepository tsmsAgentMessageRepository;

    @Autowired
    private TsmsAgentMessageMapper tsmsAgentMessageMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTsmsAgentMessageMockMvc;

    private TsmsAgentMessage tsmsAgentMessage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TsmsAgentMessage createEntity(EntityManager em) {
        TsmsAgentMessage tsmsAgentMessage = new TsmsAgentMessage()
            .messageSeqno(DEFAULT_MESSAGE_SEQNO)
            .serviceSeqno(DEFAULT_SERVICE_SEQNO)
            .sendMessage(DEFAULT_SEND_MESSAGE)
            .subject(DEFAULT_SUBJECT)
            .backupMessage(DEFAULT_BACKUP_MESSAGE)
            .backupProcessCode(DEFAULT_BACKUP_PROCESS_CODE)
            .messageType(DEFAULT_MESSAGE_TYPE)
            .contentsType(DEFAULT_CONTENTS_TYPE)
            .receiveMobileNo(DEFAULT_RECEIVE_MOBILE_NO)
            .callbackNo(DEFAULT_CALLBACK_NO)
            .jobType(DEFAULT_JOB_TYPE)
            .sendReserveDate(DEFAULT_SEND_RESERVE_DATE)
            .templateCode(DEFAULT_TEMPLATE_CODE)
            .mmsImgPath1(DEFAULT_MMS_IMG_PATH_1)
            .mmsImgPath2(DEFAULT_MMS_IMG_PATH_2)
            .mmsImgPath3(DEFAULT_MMS_IMG_PATH_3)
            .imgAttachFlag(DEFAULT_IMG_ATTACH_FLAG)
            .kkoBtnName(DEFAULT_KKO_BTN_NAME)
            .kkoBtnUrl(DEFAULT_KKO_BTN_URL)
            .kkoBtnLink1(DEFAULT_KKO_BTN_LINK_1)
            .kkoBtnLink2(DEFAULT_KKO_BTN_LINK_2)
            .kkoBtnLink3(DEFAULT_KKO_BTN_LINK_3)
            .kkoBtnLink4(DEFAULT_KKO_BTN_LINK_4)
            .kkoBtnLink5(DEFAULT_KKO_BTN_LINK_5)
            .kkoImgPath(DEFAULT_KKO_IMG_PATH)
            .kkoImgLinkUrl(DEFAULT_KKO_IMG_LINK_URL)
            .taxLevel1Nm(DEFAULT_TAX_LEVEL_1_NM)
            .taxLevel2Nm(DEFAULT_TAX_LEVEL_2_NM)
            .registerBy(DEFAULT_REGISTER_BY)
            .registerDate(DEFAULT_REGISTER_DATE)
            .custBackupFlag(DEFAULT_CUST_BACKUP_FLAG)
            .custMessageType(DEFAULT_CUST_MESSAGE_TYPE)
            .custBackupDate(DEFAULT_CUST_BACKUP_DATE)
            .custData1(DEFAULT_CUST_DATA_1)
            .custData2(DEFAULT_CUST_DATA_2)
            .custData3(DEFAULT_CUST_DATA_3)
            .custData4(DEFAULT_CUST_DATA_4)
            .custData5(DEFAULT_CUST_DATA_5)
            .custData6(DEFAULT_CUST_DATA_6)
            .custData7(DEFAULT_CUST_DATA_7)
            .custData8(DEFAULT_CUST_DATA_8)
            .custData9(DEFAULT_CUST_DATA_9)
            .custData10(DEFAULT_CUST_DATA_10)
            .sendFlag(DEFAULT_SEND_FLAG)
            .sendDate(DEFAULT_SEND_DATE)
            .resendFlag(DEFAULT_RESEND_FLAG)
            .mmsImgServerPath1(DEFAULT_MMS_IMG_SERVER_PATH_1)
            .mmsImgServerPath2(DEFAULT_MMS_IMG_SERVER_PATH_2)
            .mmsImgServerPath3(DEFAULT_MMS_IMG_SERVER_PATH_3)
            .imgSendDate(DEFAULT_IMG_SEND_DATE)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateBy(DEFAULT_UPDATE_BY)
            .kkoImgServerPath(DEFAULT_KKO_IMG_SERVER_PATH)
            .intfNm(DEFAULT_INTF_NM)
            .sendedId(DEFAULT_SENDED_ID);
        return tsmsAgentMessage;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TsmsAgentMessage createUpdatedEntity(EntityManager em) {
        TsmsAgentMessage tsmsAgentMessage = new TsmsAgentMessage()
            .messageSeqno(UPDATED_MESSAGE_SEQNO)
            .serviceSeqno(UPDATED_SERVICE_SEQNO)
            .sendMessage(UPDATED_SEND_MESSAGE)
            .subject(UPDATED_SUBJECT)
            .backupMessage(UPDATED_BACKUP_MESSAGE)
            .backupProcessCode(UPDATED_BACKUP_PROCESS_CODE)
            .messageType(UPDATED_MESSAGE_TYPE)
            .contentsType(UPDATED_CONTENTS_TYPE)
            .receiveMobileNo(UPDATED_RECEIVE_MOBILE_NO)
            .callbackNo(UPDATED_CALLBACK_NO)
            .jobType(UPDATED_JOB_TYPE)
            .sendReserveDate(UPDATED_SEND_RESERVE_DATE)
            .templateCode(UPDATED_TEMPLATE_CODE)
            .mmsImgPath1(UPDATED_MMS_IMG_PATH_1)
            .mmsImgPath2(UPDATED_MMS_IMG_PATH_2)
            .mmsImgPath3(UPDATED_MMS_IMG_PATH_3)
            .imgAttachFlag(UPDATED_IMG_ATTACH_FLAG)
            .kkoBtnName(UPDATED_KKO_BTN_NAME)
            .kkoBtnUrl(UPDATED_KKO_BTN_URL)
            .kkoBtnLink1(UPDATED_KKO_BTN_LINK_1)
            .kkoBtnLink2(UPDATED_KKO_BTN_LINK_2)
            .kkoBtnLink3(UPDATED_KKO_BTN_LINK_3)
            .kkoBtnLink4(UPDATED_KKO_BTN_LINK_4)
            .kkoBtnLink5(UPDATED_KKO_BTN_LINK_5)
            .kkoImgPath(UPDATED_KKO_IMG_PATH)
            .kkoImgLinkUrl(UPDATED_KKO_IMG_LINK_URL)
            .taxLevel1Nm(UPDATED_TAX_LEVEL_1_NM)
            .taxLevel2Nm(UPDATED_TAX_LEVEL_2_NM)
            .registerBy(UPDATED_REGISTER_BY)
            .registerDate(UPDATED_REGISTER_DATE)
            .custBackupFlag(UPDATED_CUST_BACKUP_FLAG)
            .custMessageType(UPDATED_CUST_MESSAGE_TYPE)
            .custBackupDate(UPDATED_CUST_BACKUP_DATE)
            .custData1(UPDATED_CUST_DATA_1)
            .custData2(UPDATED_CUST_DATA_2)
            .custData3(UPDATED_CUST_DATA_3)
            .custData4(UPDATED_CUST_DATA_4)
            .custData5(UPDATED_CUST_DATA_5)
            .custData6(UPDATED_CUST_DATA_6)
            .custData7(UPDATED_CUST_DATA_7)
            .custData8(UPDATED_CUST_DATA_8)
            .custData9(UPDATED_CUST_DATA_9)
            .custData10(UPDATED_CUST_DATA_10)
            .sendFlag(UPDATED_SEND_FLAG)
            .sendDate(UPDATED_SEND_DATE)
            .resendFlag(UPDATED_RESEND_FLAG)
            .mmsImgServerPath1(UPDATED_MMS_IMG_SERVER_PATH_1)
            .mmsImgServerPath2(UPDATED_MMS_IMG_SERVER_PATH_2)
            .mmsImgServerPath3(UPDATED_MMS_IMG_SERVER_PATH_3)
            .imgSendDate(UPDATED_IMG_SEND_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateBy(UPDATED_UPDATE_BY)
            .kkoImgServerPath(UPDATED_KKO_IMG_SERVER_PATH)
            .intfNm(UPDATED_INTF_NM)
            .sendedId(UPDATED_SENDED_ID);
        return tsmsAgentMessage;
    }

    @BeforeEach
    public void initTest() {
        tsmsAgentMessage = createEntity(em);
    }

    @Test
    @Transactional
    void createTsmsAgentMessage() throws Exception {
        int databaseSizeBeforeCreate = tsmsAgentMessageRepository.findAll().size();
        // Create the TsmsAgentMessage
        TsmsAgentMessageDTO tsmsAgentMessageDTO = tsmsAgentMessageMapper.toDto(tsmsAgentMessage);
        restTsmsAgentMessageMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tsmsAgentMessageDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TsmsAgentMessage in the database
        List<TsmsAgentMessage> tsmsAgentMessageList = tsmsAgentMessageRepository.findAll();
        assertThat(tsmsAgentMessageList).hasSize(databaseSizeBeforeCreate + 1);
        TsmsAgentMessage testTsmsAgentMessage = tsmsAgentMessageList.get(tsmsAgentMessageList.size() - 1);
        assertThat(testTsmsAgentMessage.getMessageSeqno()).isEqualTo(DEFAULT_MESSAGE_SEQNO);
        assertThat(testTsmsAgentMessage.getServiceSeqno()).isEqualTo(DEFAULT_SERVICE_SEQNO);
        assertThat(testTsmsAgentMessage.getSendMessage()).isEqualTo(DEFAULT_SEND_MESSAGE);
        assertThat(testTsmsAgentMessage.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testTsmsAgentMessage.getBackupMessage()).isEqualTo(DEFAULT_BACKUP_MESSAGE);
        assertThat(testTsmsAgentMessage.getBackupProcessCode()).isEqualTo(DEFAULT_BACKUP_PROCESS_CODE);
        assertThat(testTsmsAgentMessage.getMessageType()).isEqualTo(DEFAULT_MESSAGE_TYPE);
        assertThat(testTsmsAgentMessage.getContentsType()).isEqualTo(DEFAULT_CONTENTS_TYPE);
        assertThat(testTsmsAgentMessage.getReceiveMobileNo()).isEqualTo(DEFAULT_RECEIVE_MOBILE_NO);
        assertThat(testTsmsAgentMessage.getCallbackNo()).isEqualTo(DEFAULT_CALLBACK_NO);
        assertThat(testTsmsAgentMessage.getJobType()).isEqualTo(DEFAULT_JOB_TYPE);
        assertThat(testTsmsAgentMessage.getSendReserveDate()).isEqualTo(DEFAULT_SEND_RESERVE_DATE);
        assertThat(testTsmsAgentMessage.getTemplateCode()).isEqualTo(DEFAULT_TEMPLATE_CODE);
        assertThat(testTsmsAgentMessage.getMmsImgPath1()).isEqualTo(DEFAULT_MMS_IMG_PATH_1);
        assertThat(testTsmsAgentMessage.getMmsImgPath2()).isEqualTo(DEFAULT_MMS_IMG_PATH_2);
        assertThat(testTsmsAgentMessage.getMmsImgPath3()).isEqualTo(DEFAULT_MMS_IMG_PATH_3);
        assertThat(testTsmsAgentMessage.getImgAttachFlag()).isEqualTo(DEFAULT_IMG_ATTACH_FLAG);
        assertThat(testTsmsAgentMessage.getKkoBtnName()).isEqualTo(DEFAULT_KKO_BTN_NAME);
        assertThat(testTsmsAgentMessage.getKkoBtnUrl()).isEqualTo(DEFAULT_KKO_BTN_URL);
        assertThat(testTsmsAgentMessage.getKkoBtnLink1()).isEqualTo(DEFAULT_KKO_BTN_LINK_1);
        assertThat(testTsmsAgentMessage.getKkoBtnLink2()).isEqualTo(DEFAULT_KKO_BTN_LINK_2);
        assertThat(testTsmsAgentMessage.getKkoBtnLink3()).isEqualTo(DEFAULT_KKO_BTN_LINK_3);
        assertThat(testTsmsAgentMessage.getKkoBtnLink4()).isEqualTo(DEFAULT_KKO_BTN_LINK_4);
        assertThat(testTsmsAgentMessage.getKkoBtnLink5()).isEqualTo(DEFAULT_KKO_BTN_LINK_5);
        assertThat(testTsmsAgentMessage.getKkoImgPath()).isEqualTo(DEFAULT_KKO_IMG_PATH);
        assertThat(testTsmsAgentMessage.getKkoImgLinkUrl()).isEqualTo(DEFAULT_KKO_IMG_LINK_URL);
        assertThat(testTsmsAgentMessage.getTaxLevel1Nm()).isEqualTo(DEFAULT_TAX_LEVEL_1_NM);
        assertThat(testTsmsAgentMessage.getTaxLevel2Nm()).isEqualTo(DEFAULT_TAX_LEVEL_2_NM);
        assertThat(testTsmsAgentMessage.getRegisterBy()).isEqualTo(DEFAULT_REGISTER_BY);
        assertThat(testTsmsAgentMessage.getRegisterDate()).isEqualTo(DEFAULT_REGISTER_DATE);
        assertThat(testTsmsAgentMessage.getCustBackupFlag()).isEqualTo(DEFAULT_CUST_BACKUP_FLAG);
        assertThat(testTsmsAgentMessage.getCustMessageType()).isEqualTo(DEFAULT_CUST_MESSAGE_TYPE);
        assertThat(testTsmsAgentMessage.getCustBackupDate()).isEqualTo(DEFAULT_CUST_BACKUP_DATE);
        assertThat(testTsmsAgentMessage.getCustData1()).isEqualTo(DEFAULT_CUST_DATA_1);
        assertThat(testTsmsAgentMessage.getCustData2()).isEqualTo(DEFAULT_CUST_DATA_2);
        assertThat(testTsmsAgentMessage.getCustData3()).isEqualTo(DEFAULT_CUST_DATA_3);
        assertThat(testTsmsAgentMessage.getCustData4()).isEqualTo(DEFAULT_CUST_DATA_4);
        assertThat(testTsmsAgentMessage.getCustData5()).isEqualTo(DEFAULT_CUST_DATA_5);
        assertThat(testTsmsAgentMessage.getCustData6()).isEqualTo(DEFAULT_CUST_DATA_6);
        assertThat(testTsmsAgentMessage.getCustData7()).isEqualTo(DEFAULT_CUST_DATA_7);
        assertThat(testTsmsAgentMessage.getCustData8()).isEqualTo(DEFAULT_CUST_DATA_8);
        assertThat(testTsmsAgentMessage.getCustData9()).isEqualTo(DEFAULT_CUST_DATA_9);
        assertThat(testTsmsAgentMessage.getCustData10()).isEqualTo(DEFAULT_CUST_DATA_10);
        assertThat(testTsmsAgentMessage.getSendFlag()).isEqualTo(DEFAULT_SEND_FLAG);
        assertThat(testTsmsAgentMessage.getSendDate()).isEqualTo(DEFAULT_SEND_DATE);
        assertThat(testTsmsAgentMessage.getResendFlag()).isEqualTo(DEFAULT_RESEND_FLAG);
        assertThat(testTsmsAgentMessage.getMmsImgServerPath1()).isEqualTo(DEFAULT_MMS_IMG_SERVER_PATH_1);
        assertThat(testTsmsAgentMessage.getMmsImgServerPath2()).isEqualTo(DEFAULT_MMS_IMG_SERVER_PATH_2);
        assertThat(testTsmsAgentMessage.getMmsImgServerPath3()).isEqualTo(DEFAULT_MMS_IMG_SERVER_PATH_3);
        assertThat(testTsmsAgentMessage.getImgSendDate()).isEqualTo(DEFAULT_IMG_SEND_DATE);
        assertThat(testTsmsAgentMessage.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testTsmsAgentMessage.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testTsmsAgentMessage.getKkoImgServerPath()).isEqualTo(DEFAULT_KKO_IMG_SERVER_PATH);
        assertThat(testTsmsAgentMessage.getIntfNm()).isEqualTo(DEFAULT_INTF_NM);
        assertThat(testTsmsAgentMessage.getSendedId()).isEqualTo(DEFAULT_SENDED_ID);
    }

    @Test
    @Transactional
    void createTsmsAgentMessageWithExistingId() throws Exception {
        // Create the TsmsAgentMessage with an existing ID
        tsmsAgentMessage.setId(1L);
        TsmsAgentMessageDTO tsmsAgentMessageDTO = tsmsAgentMessageMapper.toDto(tsmsAgentMessage);

        int databaseSizeBeforeCreate = tsmsAgentMessageRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTsmsAgentMessageMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tsmsAgentMessageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TsmsAgentMessage in the database
        List<TsmsAgentMessage> tsmsAgentMessageList = tsmsAgentMessageRepository.findAll();
        assertThat(tsmsAgentMessageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTsmsAgentMessages() throws Exception {
        // Initialize the database
        tsmsAgentMessageRepository.saveAndFlush(tsmsAgentMessage);

        // Get all the tsmsAgentMessageList
        restTsmsAgentMessageMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tsmsAgentMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].messageSeqno").value(hasItem(DEFAULT_MESSAGE_SEQNO.intValue())))
            .andExpect(jsonPath("$.[*].serviceSeqno").value(hasItem(DEFAULT_SERVICE_SEQNO.intValue())))
            .andExpect(jsonPath("$.[*].sendMessage").value(hasItem(DEFAULT_SEND_MESSAGE)))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT)))
            .andExpect(jsonPath("$.[*].backupMessage").value(hasItem(DEFAULT_BACKUP_MESSAGE)))
            .andExpect(jsonPath("$.[*].backupProcessCode").value(hasItem(DEFAULT_BACKUP_PROCESS_CODE)))
            .andExpect(jsonPath("$.[*].messageType").value(hasItem(DEFAULT_MESSAGE_TYPE)))
            .andExpect(jsonPath("$.[*].contentsType").value(hasItem(DEFAULT_CONTENTS_TYPE)))
            .andExpect(jsonPath("$.[*].receiveMobileNo").value(hasItem(DEFAULT_RECEIVE_MOBILE_NO)))
            .andExpect(jsonPath("$.[*].callbackNo").value(hasItem(DEFAULT_CALLBACK_NO)))
            .andExpect(jsonPath("$.[*].jobType").value(hasItem(DEFAULT_JOB_TYPE)))
            .andExpect(jsonPath("$.[*].sendReserveDate").value(hasItem(DEFAULT_SEND_RESERVE_DATE.toString())))
            .andExpect(jsonPath("$.[*].templateCode").value(hasItem(DEFAULT_TEMPLATE_CODE)))
            .andExpect(jsonPath("$.[*].mmsImgPath1").value(hasItem(DEFAULT_MMS_IMG_PATH_1)))
            .andExpect(jsonPath("$.[*].mmsImgPath2").value(hasItem(DEFAULT_MMS_IMG_PATH_2)))
            .andExpect(jsonPath("$.[*].mmsImgPath3").value(hasItem(DEFAULT_MMS_IMG_PATH_3)))
            .andExpect(jsonPath("$.[*].imgAttachFlag").value(hasItem(DEFAULT_IMG_ATTACH_FLAG)))
            .andExpect(jsonPath("$.[*].kkoBtnName").value(hasItem(DEFAULT_KKO_BTN_NAME)))
            .andExpect(jsonPath("$.[*].kkoBtnUrl").value(hasItem(DEFAULT_KKO_BTN_URL)))
            .andExpect(jsonPath("$.[*].kkoBtnLink1").value(hasItem(DEFAULT_KKO_BTN_LINK_1)))
            .andExpect(jsonPath("$.[*].kkoBtnLink2").value(hasItem(DEFAULT_KKO_BTN_LINK_2)))
            .andExpect(jsonPath("$.[*].kkoBtnLink3").value(hasItem(DEFAULT_KKO_BTN_LINK_3)))
            .andExpect(jsonPath("$.[*].kkoBtnLink4").value(hasItem(DEFAULT_KKO_BTN_LINK_4)))
            .andExpect(jsonPath("$.[*].kkoBtnLink5").value(hasItem(DEFAULT_KKO_BTN_LINK_5)))
            .andExpect(jsonPath("$.[*].kkoImgPath").value(hasItem(DEFAULT_KKO_IMG_PATH)))
            .andExpect(jsonPath("$.[*].kkoImgLinkUrl").value(hasItem(DEFAULT_KKO_IMG_LINK_URL)))
            .andExpect(jsonPath("$.[*].taxLevel1Nm").value(hasItem(DEFAULT_TAX_LEVEL_1_NM)))
            .andExpect(jsonPath("$.[*].taxLevel2Nm").value(hasItem(DEFAULT_TAX_LEVEL_2_NM)))
            .andExpect(jsonPath("$.[*].registerBy").value(hasItem(DEFAULT_REGISTER_BY)))
            .andExpect(jsonPath("$.[*].registerDate").value(hasItem(DEFAULT_REGISTER_DATE.toString())))
            .andExpect(jsonPath("$.[*].custBackupFlag").value(hasItem(DEFAULT_CUST_BACKUP_FLAG)))
            .andExpect(jsonPath("$.[*].custMessageType").value(hasItem(DEFAULT_CUST_MESSAGE_TYPE)))
            .andExpect(jsonPath("$.[*].custBackupDate").value(hasItem(DEFAULT_CUST_BACKUP_DATE.toString())))
            .andExpect(jsonPath("$.[*].custData1").value(hasItem(DEFAULT_CUST_DATA_1)))
            .andExpect(jsonPath("$.[*].custData2").value(hasItem(DEFAULT_CUST_DATA_2)))
            .andExpect(jsonPath("$.[*].custData3").value(hasItem(DEFAULT_CUST_DATA_3)))
            .andExpect(jsonPath("$.[*].custData4").value(hasItem(DEFAULT_CUST_DATA_4)))
            .andExpect(jsonPath("$.[*].custData5").value(hasItem(DEFAULT_CUST_DATA_5)))
            .andExpect(jsonPath("$.[*].custData6").value(hasItem(DEFAULT_CUST_DATA_6)))
            .andExpect(jsonPath("$.[*].custData7").value(hasItem(DEFAULT_CUST_DATA_7)))
            .andExpect(jsonPath("$.[*].custData8").value(hasItem(DEFAULT_CUST_DATA_8)))
            .andExpect(jsonPath("$.[*].custData9").value(hasItem(DEFAULT_CUST_DATA_9)))
            .andExpect(jsonPath("$.[*].custData10").value(hasItem(DEFAULT_CUST_DATA_10)))
            .andExpect(jsonPath("$.[*].sendFlag").value(hasItem(DEFAULT_SEND_FLAG)))
            .andExpect(jsonPath("$.[*].sendDate").value(hasItem(DEFAULT_SEND_DATE.toString())))
            .andExpect(jsonPath("$.[*].resendFlag").value(hasItem(DEFAULT_RESEND_FLAG)))
            .andExpect(jsonPath("$.[*].mmsImgServerPath1").value(hasItem(DEFAULT_MMS_IMG_SERVER_PATH_1)))
            .andExpect(jsonPath("$.[*].mmsImgServerPath2").value(hasItem(DEFAULT_MMS_IMG_SERVER_PATH_2)))
            .andExpect(jsonPath("$.[*].mmsImgServerPath3").value(hasItem(DEFAULT_MMS_IMG_SERVER_PATH_3)))
            .andExpect(jsonPath("$.[*].imgSendDate").value(hasItem(DEFAULT_IMG_SEND_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateBy").value(hasItem(DEFAULT_UPDATE_BY)))
            .andExpect(jsonPath("$.[*].kkoImgServerPath").value(hasItem(DEFAULT_KKO_IMG_SERVER_PATH)))
            .andExpect(jsonPath("$.[*].intfNm").value(hasItem(DEFAULT_INTF_NM)))
            .andExpect(jsonPath("$.[*].sendedId").value(hasItem(DEFAULT_SENDED_ID)));
    }

    @Test
    @Transactional
    void getTsmsAgentMessage() throws Exception {
        // Initialize the database
        tsmsAgentMessageRepository.saveAndFlush(tsmsAgentMessage);

        // Get the tsmsAgentMessage
        restTsmsAgentMessageMockMvc
            .perform(get(ENTITY_API_URL_ID, tsmsAgentMessage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tsmsAgentMessage.getId().intValue()))
            .andExpect(jsonPath("$.messageSeqno").value(DEFAULT_MESSAGE_SEQNO.intValue()))
            .andExpect(jsonPath("$.serviceSeqno").value(DEFAULT_SERVICE_SEQNO.intValue()))
            .andExpect(jsonPath("$.sendMessage").value(DEFAULT_SEND_MESSAGE))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT))
            .andExpect(jsonPath("$.backupMessage").value(DEFAULT_BACKUP_MESSAGE))
            .andExpect(jsonPath("$.backupProcessCode").value(DEFAULT_BACKUP_PROCESS_CODE))
            .andExpect(jsonPath("$.messageType").value(DEFAULT_MESSAGE_TYPE))
            .andExpect(jsonPath("$.contentsType").value(DEFAULT_CONTENTS_TYPE))
            .andExpect(jsonPath("$.receiveMobileNo").value(DEFAULT_RECEIVE_MOBILE_NO))
            .andExpect(jsonPath("$.callbackNo").value(DEFAULT_CALLBACK_NO))
            .andExpect(jsonPath("$.jobType").value(DEFAULT_JOB_TYPE))
            .andExpect(jsonPath("$.sendReserveDate").value(DEFAULT_SEND_RESERVE_DATE.toString()))
            .andExpect(jsonPath("$.templateCode").value(DEFAULT_TEMPLATE_CODE))
            .andExpect(jsonPath("$.mmsImgPath1").value(DEFAULT_MMS_IMG_PATH_1))
            .andExpect(jsonPath("$.mmsImgPath2").value(DEFAULT_MMS_IMG_PATH_2))
            .andExpect(jsonPath("$.mmsImgPath3").value(DEFAULT_MMS_IMG_PATH_3))
            .andExpect(jsonPath("$.imgAttachFlag").value(DEFAULT_IMG_ATTACH_FLAG))
            .andExpect(jsonPath("$.kkoBtnName").value(DEFAULT_KKO_BTN_NAME))
            .andExpect(jsonPath("$.kkoBtnUrl").value(DEFAULT_KKO_BTN_URL))
            .andExpect(jsonPath("$.kkoBtnLink1").value(DEFAULT_KKO_BTN_LINK_1))
            .andExpect(jsonPath("$.kkoBtnLink2").value(DEFAULT_KKO_BTN_LINK_2))
            .andExpect(jsonPath("$.kkoBtnLink3").value(DEFAULT_KKO_BTN_LINK_3))
            .andExpect(jsonPath("$.kkoBtnLink4").value(DEFAULT_KKO_BTN_LINK_4))
            .andExpect(jsonPath("$.kkoBtnLink5").value(DEFAULT_KKO_BTN_LINK_5))
            .andExpect(jsonPath("$.kkoImgPath").value(DEFAULT_KKO_IMG_PATH))
            .andExpect(jsonPath("$.kkoImgLinkUrl").value(DEFAULT_KKO_IMG_LINK_URL))
            .andExpect(jsonPath("$.taxLevel1Nm").value(DEFAULT_TAX_LEVEL_1_NM))
            .andExpect(jsonPath("$.taxLevel2Nm").value(DEFAULT_TAX_LEVEL_2_NM))
            .andExpect(jsonPath("$.registerBy").value(DEFAULT_REGISTER_BY))
            .andExpect(jsonPath("$.registerDate").value(DEFAULT_REGISTER_DATE.toString()))
            .andExpect(jsonPath("$.custBackupFlag").value(DEFAULT_CUST_BACKUP_FLAG))
            .andExpect(jsonPath("$.custMessageType").value(DEFAULT_CUST_MESSAGE_TYPE))
            .andExpect(jsonPath("$.custBackupDate").value(DEFAULT_CUST_BACKUP_DATE.toString()))
            .andExpect(jsonPath("$.custData1").value(DEFAULT_CUST_DATA_1))
            .andExpect(jsonPath("$.custData2").value(DEFAULT_CUST_DATA_2))
            .andExpect(jsonPath("$.custData3").value(DEFAULT_CUST_DATA_3))
            .andExpect(jsonPath("$.custData4").value(DEFAULT_CUST_DATA_4))
            .andExpect(jsonPath("$.custData5").value(DEFAULT_CUST_DATA_5))
            .andExpect(jsonPath("$.custData6").value(DEFAULT_CUST_DATA_6))
            .andExpect(jsonPath("$.custData7").value(DEFAULT_CUST_DATA_7))
            .andExpect(jsonPath("$.custData8").value(DEFAULT_CUST_DATA_8))
            .andExpect(jsonPath("$.custData9").value(DEFAULT_CUST_DATA_9))
            .andExpect(jsonPath("$.custData10").value(DEFAULT_CUST_DATA_10))
            .andExpect(jsonPath("$.sendFlag").value(DEFAULT_SEND_FLAG))
            .andExpect(jsonPath("$.sendDate").value(DEFAULT_SEND_DATE.toString()))
            .andExpect(jsonPath("$.resendFlag").value(DEFAULT_RESEND_FLAG))
            .andExpect(jsonPath("$.mmsImgServerPath1").value(DEFAULT_MMS_IMG_SERVER_PATH_1))
            .andExpect(jsonPath("$.mmsImgServerPath2").value(DEFAULT_MMS_IMG_SERVER_PATH_2))
            .andExpect(jsonPath("$.mmsImgServerPath3").value(DEFAULT_MMS_IMG_SERVER_PATH_3))
            .andExpect(jsonPath("$.imgSendDate").value(DEFAULT_IMG_SEND_DATE.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.updateBy").value(DEFAULT_UPDATE_BY))
            .andExpect(jsonPath("$.kkoImgServerPath").value(DEFAULT_KKO_IMG_SERVER_PATH))
            .andExpect(jsonPath("$.intfNm").value(DEFAULT_INTF_NM))
            .andExpect(jsonPath("$.sendedId").value(DEFAULT_SENDED_ID));
    }

    @Test
    @Transactional
    void getNonExistingTsmsAgentMessage() throws Exception {
        // Get the tsmsAgentMessage
        restTsmsAgentMessageMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTsmsAgentMessage() throws Exception {
        // Initialize the database
        tsmsAgentMessageRepository.saveAndFlush(tsmsAgentMessage);

        int databaseSizeBeforeUpdate = tsmsAgentMessageRepository.findAll().size();

        // Update the tsmsAgentMessage
        TsmsAgentMessage updatedTsmsAgentMessage = tsmsAgentMessageRepository.findById(tsmsAgentMessage.getId()).get();
        // Disconnect from session so that the updates on updatedTsmsAgentMessage are not directly saved in db
        em.detach(updatedTsmsAgentMessage);
        updatedTsmsAgentMessage
            .messageSeqno(UPDATED_MESSAGE_SEQNO)
            .serviceSeqno(UPDATED_SERVICE_SEQNO)
            .sendMessage(UPDATED_SEND_MESSAGE)
            .subject(UPDATED_SUBJECT)
            .backupMessage(UPDATED_BACKUP_MESSAGE)
            .backupProcessCode(UPDATED_BACKUP_PROCESS_CODE)
            .messageType(UPDATED_MESSAGE_TYPE)
            .contentsType(UPDATED_CONTENTS_TYPE)
            .receiveMobileNo(UPDATED_RECEIVE_MOBILE_NO)
            .callbackNo(UPDATED_CALLBACK_NO)
            .jobType(UPDATED_JOB_TYPE)
            .sendReserveDate(UPDATED_SEND_RESERVE_DATE)
            .templateCode(UPDATED_TEMPLATE_CODE)
            .mmsImgPath1(UPDATED_MMS_IMG_PATH_1)
            .mmsImgPath2(UPDATED_MMS_IMG_PATH_2)
            .mmsImgPath3(UPDATED_MMS_IMG_PATH_3)
            .imgAttachFlag(UPDATED_IMG_ATTACH_FLAG)
            .kkoBtnName(UPDATED_KKO_BTN_NAME)
            .kkoBtnUrl(UPDATED_KKO_BTN_URL)
            .kkoBtnLink1(UPDATED_KKO_BTN_LINK_1)
            .kkoBtnLink2(UPDATED_KKO_BTN_LINK_2)
            .kkoBtnLink3(UPDATED_KKO_BTN_LINK_3)
            .kkoBtnLink4(UPDATED_KKO_BTN_LINK_4)
            .kkoBtnLink5(UPDATED_KKO_BTN_LINK_5)
            .kkoImgPath(UPDATED_KKO_IMG_PATH)
            .kkoImgLinkUrl(UPDATED_KKO_IMG_LINK_URL)
            .taxLevel1Nm(UPDATED_TAX_LEVEL_1_NM)
            .taxLevel2Nm(UPDATED_TAX_LEVEL_2_NM)
            .registerBy(UPDATED_REGISTER_BY)
            .registerDate(UPDATED_REGISTER_DATE)
            .custBackupFlag(UPDATED_CUST_BACKUP_FLAG)
            .custMessageType(UPDATED_CUST_MESSAGE_TYPE)
            .custBackupDate(UPDATED_CUST_BACKUP_DATE)
            .custData1(UPDATED_CUST_DATA_1)
            .custData2(UPDATED_CUST_DATA_2)
            .custData3(UPDATED_CUST_DATA_3)
            .custData4(UPDATED_CUST_DATA_4)
            .custData5(UPDATED_CUST_DATA_5)
            .custData6(UPDATED_CUST_DATA_6)
            .custData7(UPDATED_CUST_DATA_7)
            .custData8(UPDATED_CUST_DATA_8)
            .custData9(UPDATED_CUST_DATA_9)
            .custData10(UPDATED_CUST_DATA_10)
            .sendFlag(UPDATED_SEND_FLAG)
            .sendDate(UPDATED_SEND_DATE)
            .resendFlag(UPDATED_RESEND_FLAG)
            .mmsImgServerPath1(UPDATED_MMS_IMG_SERVER_PATH_1)
            .mmsImgServerPath2(UPDATED_MMS_IMG_SERVER_PATH_2)
            .mmsImgServerPath3(UPDATED_MMS_IMG_SERVER_PATH_3)
            .imgSendDate(UPDATED_IMG_SEND_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateBy(UPDATED_UPDATE_BY)
            .kkoImgServerPath(UPDATED_KKO_IMG_SERVER_PATH)
            .intfNm(UPDATED_INTF_NM)
            .sendedId(UPDATED_SENDED_ID);
        TsmsAgentMessageDTO tsmsAgentMessageDTO = tsmsAgentMessageMapper.toDto(updatedTsmsAgentMessage);

        restTsmsAgentMessageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tsmsAgentMessageDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tsmsAgentMessageDTO))
            )
            .andExpect(status().isOk());

        // Validate the TsmsAgentMessage in the database
        List<TsmsAgentMessage> tsmsAgentMessageList = tsmsAgentMessageRepository.findAll();
        assertThat(tsmsAgentMessageList).hasSize(databaseSizeBeforeUpdate);
        TsmsAgentMessage testTsmsAgentMessage = tsmsAgentMessageList.get(tsmsAgentMessageList.size() - 1);
        assertThat(testTsmsAgentMessage.getMessageSeqno()).isEqualTo(UPDATED_MESSAGE_SEQNO);
        assertThat(testTsmsAgentMessage.getServiceSeqno()).isEqualTo(UPDATED_SERVICE_SEQNO);
        assertThat(testTsmsAgentMessage.getSendMessage()).isEqualTo(UPDATED_SEND_MESSAGE);
        assertThat(testTsmsAgentMessage.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testTsmsAgentMessage.getBackupMessage()).isEqualTo(UPDATED_BACKUP_MESSAGE);
        assertThat(testTsmsAgentMessage.getBackupProcessCode()).isEqualTo(UPDATED_BACKUP_PROCESS_CODE);
        assertThat(testTsmsAgentMessage.getMessageType()).isEqualTo(UPDATED_MESSAGE_TYPE);
        assertThat(testTsmsAgentMessage.getContentsType()).isEqualTo(UPDATED_CONTENTS_TYPE);
        assertThat(testTsmsAgentMessage.getReceiveMobileNo()).isEqualTo(UPDATED_RECEIVE_MOBILE_NO);
        assertThat(testTsmsAgentMessage.getCallbackNo()).isEqualTo(UPDATED_CALLBACK_NO);
        assertThat(testTsmsAgentMessage.getJobType()).isEqualTo(UPDATED_JOB_TYPE);
        assertThat(testTsmsAgentMessage.getSendReserveDate()).isEqualTo(UPDATED_SEND_RESERVE_DATE);
        assertThat(testTsmsAgentMessage.getTemplateCode()).isEqualTo(UPDATED_TEMPLATE_CODE);
        assertThat(testTsmsAgentMessage.getMmsImgPath1()).isEqualTo(UPDATED_MMS_IMG_PATH_1);
        assertThat(testTsmsAgentMessage.getMmsImgPath2()).isEqualTo(UPDATED_MMS_IMG_PATH_2);
        assertThat(testTsmsAgentMessage.getMmsImgPath3()).isEqualTo(UPDATED_MMS_IMG_PATH_3);
        assertThat(testTsmsAgentMessage.getImgAttachFlag()).isEqualTo(UPDATED_IMG_ATTACH_FLAG);
        assertThat(testTsmsAgentMessage.getKkoBtnName()).isEqualTo(UPDATED_KKO_BTN_NAME);
        assertThat(testTsmsAgentMessage.getKkoBtnUrl()).isEqualTo(UPDATED_KKO_BTN_URL);
        assertThat(testTsmsAgentMessage.getKkoBtnLink1()).isEqualTo(UPDATED_KKO_BTN_LINK_1);
        assertThat(testTsmsAgentMessage.getKkoBtnLink2()).isEqualTo(UPDATED_KKO_BTN_LINK_2);
        assertThat(testTsmsAgentMessage.getKkoBtnLink3()).isEqualTo(UPDATED_KKO_BTN_LINK_3);
        assertThat(testTsmsAgentMessage.getKkoBtnLink4()).isEqualTo(UPDATED_KKO_BTN_LINK_4);
        assertThat(testTsmsAgentMessage.getKkoBtnLink5()).isEqualTo(UPDATED_KKO_BTN_LINK_5);
        assertThat(testTsmsAgentMessage.getKkoImgPath()).isEqualTo(UPDATED_KKO_IMG_PATH);
        assertThat(testTsmsAgentMessage.getKkoImgLinkUrl()).isEqualTo(UPDATED_KKO_IMG_LINK_URL);
        assertThat(testTsmsAgentMessage.getTaxLevel1Nm()).isEqualTo(UPDATED_TAX_LEVEL_1_NM);
        assertThat(testTsmsAgentMessage.getTaxLevel2Nm()).isEqualTo(UPDATED_TAX_LEVEL_2_NM);
        assertThat(testTsmsAgentMessage.getRegisterBy()).isEqualTo(UPDATED_REGISTER_BY);
        assertThat(testTsmsAgentMessage.getRegisterDate()).isEqualTo(UPDATED_REGISTER_DATE);
        assertThat(testTsmsAgentMessage.getCustBackupFlag()).isEqualTo(UPDATED_CUST_BACKUP_FLAG);
        assertThat(testTsmsAgentMessage.getCustMessageType()).isEqualTo(UPDATED_CUST_MESSAGE_TYPE);
        assertThat(testTsmsAgentMessage.getCustBackupDate()).isEqualTo(UPDATED_CUST_BACKUP_DATE);
        assertThat(testTsmsAgentMessage.getCustData1()).isEqualTo(UPDATED_CUST_DATA_1);
        assertThat(testTsmsAgentMessage.getCustData2()).isEqualTo(UPDATED_CUST_DATA_2);
        assertThat(testTsmsAgentMessage.getCustData3()).isEqualTo(UPDATED_CUST_DATA_3);
        assertThat(testTsmsAgentMessage.getCustData4()).isEqualTo(UPDATED_CUST_DATA_4);
        assertThat(testTsmsAgentMessage.getCustData5()).isEqualTo(UPDATED_CUST_DATA_5);
        assertThat(testTsmsAgentMessage.getCustData6()).isEqualTo(UPDATED_CUST_DATA_6);
        assertThat(testTsmsAgentMessage.getCustData7()).isEqualTo(UPDATED_CUST_DATA_7);
        assertThat(testTsmsAgentMessage.getCustData8()).isEqualTo(UPDATED_CUST_DATA_8);
        assertThat(testTsmsAgentMessage.getCustData9()).isEqualTo(UPDATED_CUST_DATA_9);
        assertThat(testTsmsAgentMessage.getCustData10()).isEqualTo(UPDATED_CUST_DATA_10);
        assertThat(testTsmsAgentMessage.getSendFlag()).isEqualTo(UPDATED_SEND_FLAG);
        assertThat(testTsmsAgentMessage.getSendDate()).isEqualTo(UPDATED_SEND_DATE);
        assertThat(testTsmsAgentMessage.getResendFlag()).isEqualTo(UPDATED_RESEND_FLAG);
        assertThat(testTsmsAgentMessage.getMmsImgServerPath1()).isEqualTo(UPDATED_MMS_IMG_SERVER_PATH_1);
        assertThat(testTsmsAgentMessage.getMmsImgServerPath2()).isEqualTo(UPDATED_MMS_IMG_SERVER_PATH_2);
        assertThat(testTsmsAgentMessage.getMmsImgServerPath3()).isEqualTo(UPDATED_MMS_IMG_SERVER_PATH_3);
        assertThat(testTsmsAgentMessage.getImgSendDate()).isEqualTo(UPDATED_IMG_SEND_DATE);
        assertThat(testTsmsAgentMessage.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testTsmsAgentMessage.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testTsmsAgentMessage.getKkoImgServerPath()).isEqualTo(UPDATED_KKO_IMG_SERVER_PATH);
        assertThat(testTsmsAgentMessage.getIntfNm()).isEqualTo(UPDATED_INTF_NM);
        assertThat(testTsmsAgentMessage.getSendedId()).isEqualTo(UPDATED_SENDED_ID);
    }

    @Test
    @Transactional
    void putNonExistingTsmsAgentMessage() throws Exception {
        int databaseSizeBeforeUpdate = tsmsAgentMessageRepository.findAll().size();
        tsmsAgentMessage.setId(count.incrementAndGet());

        // Create the TsmsAgentMessage
        TsmsAgentMessageDTO tsmsAgentMessageDTO = tsmsAgentMessageMapper.toDto(tsmsAgentMessage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTsmsAgentMessageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tsmsAgentMessageDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tsmsAgentMessageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TsmsAgentMessage in the database
        List<TsmsAgentMessage> tsmsAgentMessageList = tsmsAgentMessageRepository.findAll();
        assertThat(tsmsAgentMessageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTsmsAgentMessage() throws Exception {
        int databaseSizeBeforeUpdate = tsmsAgentMessageRepository.findAll().size();
        tsmsAgentMessage.setId(count.incrementAndGet());

        // Create the TsmsAgentMessage
        TsmsAgentMessageDTO tsmsAgentMessageDTO = tsmsAgentMessageMapper.toDto(tsmsAgentMessage);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTsmsAgentMessageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tsmsAgentMessageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TsmsAgentMessage in the database
        List<TsmsAgentMessage> tsmsAgentMessageList = tsmsAgentMessageRepository.findAll();
        assertThat(tsmsAgentMessageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTsmsAgentMessage() throws Exception {
        int databaseSizeBeforeUpdate = tsmsAgentMessageRepository.findAll().size();
        tsmsAgentMessage.setId(count.incrementAndGet());

        // Create the TsmsAgentMessage
        TsmsAgentMessageDTO tsmsAgentMessageDTO = tsmsAgentMessageMapper.toDto(tsmsAgentMessage);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTsmsAgentMessageMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tsmsAgentMessageDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TsmsAgentMessage in the database
        List<TsmsAgentMessage> tsmsAgentMessageList = tsmsAgentMessageRepository.findAll();
        assertThat(tsmsAgentMessageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTsmsAgentMessageWithPatch() throws Exception {
        // Initialize the database
        tsmsAgentMessageRepository.saveAndFlush(tsmsAgentMessage);

        int databaseSizeBeforeUpdate = tsmsAgentMessageRepository.findAll().size();

        // Update the tsmsAgentMessage using partial update
        TsmsAgentMessage partialUpdatedTsmsAgentMessage = new TsmsAgentMessage();
        partialUpdatedTsmsAgentMessage.setId(tsmsAgentMessage.getId());

        partialUpdatedTsmsAgentMessage
            .serviceSeqno(UPDATED_SERVICE_SEQNO)
            .sendMessage(UPDATED_SEND_MESSAGE)
            .subject(UPDATED_SUBJECT)
            .backupMessage(UPDATED_BACKUP_MESSAGE)
            .backupProcessCode(UPDATED_BACKUP_PROCESS_CODE)
            .callbackNo(UPDATED_CALLBACK_NO)
            .mmsImgPath1(UPDATED_MMS_IMG_PATH_1)
            .imgAttachFlag(UPDATED_IMG_ATTACH_FLAG)
            .kkoBtnUrl(UPDATED_KKO_BTN_URL)
            .kkoBtnLink2(UPDATED_KKO_BTN_LINK_2)
            .kkoImgPath(UPDATED_KKO_IMG_PATH)
            .taxLevel1Nm(UPDATED_TAX_LEVEL_1_NM)
            .registerDate(UPDATED_REGISTER_DATE)
            .custBackupFlag(UPDATED_CUST_BACKUP_FLAG)
            .custData1(UPDATED_CUST_DATA_1)
            .custData7(UPDATED_CUST_DATA_7)
            .custData9(UPDATED_CUST_DATA_9)
            .sendDate(UPDATED_SEND_DATE)
            .mmsImgServerPath1(UPDATED_MMS_IMG_SERVER_PATH_1)
            .mmsImgServerPath2(UPDATED_MMS_IMG_SERVER_PATH_2)
            .mmsImgServerPath3(UPDATED_MMS_IMG_SERVER_PATH_3)
            .imgSendDate(UPDATED_IMG_SEND_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateBy(UPDATED_UPDATE_BY)
            .kkoImgServerPath(UPDATED_KKO_IMG_SERVER_PATH)
            .intfNm(UPDATED_INTF_NM);

        restTsmsAgentMessageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTsmsAgentMessage.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTsmsAgentMessage))
            )
            .andExpect(status().isOk());

        // Validate the TsmsAgentMessage in the database
        List<TsmsAgentMessage> tsmsAgentMessageList = tsmsAgentMessageRepository.findAll();
        assertThat(tsmsAgentMessageList).hasSize(databaseSizeBeforeUpdate);
        TsmsAgentMessage testTsmsAgentMessage = tsmsAgentMessageList.get(tsmsAgentMessageList.size() - 1);
        assertThat(testTsmsAgentMessage.getMessageSeqno()).isEqualTo(DEFAULT_MESSAGE_SEQNO);
        assertThat(testTsmsAgentMessage.getServiceSeqno()).isEqualTo(UPDATED_SERVICE_SEQNO);
        assertThat(testTsmsAgentMessage.getSendMessage()).isEqualTo(UPDATED_SEND_MESSAGE);
        assertThat(testTsmsAgentMessage.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testTsmsAgentMessage.getBackupMessage()).isEqualTo(UPDATED_BACKUP_MESSAGE);
        assertThat(testTsmsAgentMessage.getBackupProcessCode()).isEqualTo(UPDATED_BACKUP_PROCESS_CODE);
        assertThat(testTsmsAgentMessage.getMessageType()).isEqualTo(DEFAULT_MESSAGE_TYPE);
        assertThat(testTsmsAgentMessage.getContentsType()).isEqualTo(DEFAULT_CONTENTS_TYPE);
        assertThat(testTsmsAgentMessage.getReceiveMobileNo()).isEqualTo(DEFAULT_RECEIVE_MOBILE_NO);
        assertThat(testTsmsAgentMessage.getCallbackNo()).isEqualTo(UPDATED_CALLBACK_NO);
        assertThat(testTsmsAgentMessage.getJobType()).isEqualTo(DEFAULT_JOB_TYPE);
        assertThat(testTsmsAgentMessage.getSendReserveDate()).isEqualTo(DEFAULT_SEND_RESERVE_DATE);
        assertThat(testTsmsAgentMessage.getTemplateCode()).isEqualTo(DEFAULT_TEMPLATE_CODE);
        assertThat(testTsmsAgentMessage.getMmsImgPath1()).isEqualTo(UPDATED_MMS_IMG_PATH_1);
        assertThat(testTsmsAgentMessage.getMmsImgPath2()).isEqualTo(DEFAULT_MMS_IMG_PATH_2);
        assertThat(testTsmsAgentMessage.getMmsImgPath3()).isEqualTo(DEFAULT_MMS_IMG_PATH_3);
        assertThat(testTsmsAgentMessage.getImgAttachFlag()).isEqualTo(UPDATED_IMG_ATTACH_FLAG);
        assertThat(testTsmsAgentMessage.getKkoBtnName()).isEqualTo(DEFAULT_KKO_BTN_NAME);
        assertThat(testTsmsAgentMessage.getKkoBtnUrl()).isEqualTo(UPDATED_KKO_BTN_URL);
        assertThat(testTsmsAgentMessage.getKkoBtnLink1()).isEqualTo(DEFAULT_KKO_BTN_LINK_1);
        assertThat(testTsmsAgentMessage.getKkoBtnLink2()).isEqualTo(UPDATED_KKO_BTN_LINK_2);
        assertThat(testTsmsAgentMessage.getKkoBtnLink3()).isEqualTo(DEFAULT_KKO_BTN_LINK_3);
        assertThat(testTsmsAgentMessage.getKkoBtnLink4()).isEqualTo(DEFAULT_KKO_BTN_LINK_4);
        assertThat(testTsmsAgentMessage.getKkoBtnLink5()).isEqualTo(DEFAULT_KKO_BTN_LINK_5);
        assertThat(testTsmsAgentMessage.getKkoImgPath()).isEqualTo(UPDATED_KKO_IMG_PATH);
        assertThat(testTsmsAgentMessage.getKkoImgLinkUrl()).isEqualTo(DEFAULT_KKO_IMG_LINK_URL);
        assertThat(testTsmsAgentMessage.getTaxLevel1Nm()).isEqualTo(UPDATED_TAX_LEVEL_1_NM);
        assertThat(testTsmsAgentMessage.getTaxLevel2Nm()).isEqualTo(DEFAULT_TAX_LEVEL_2_NM);
        assertThat(testTsmsAgentMessage.getRegisterBy()).isEqualTo(DEFAULT_REGISTER_BY);
        assertThat(testTsmsAgentMessage.getRegisterDate()).isEqualTo(UPDATED_REGISTER_DATE);
        assertThat(testTsmsAgentMessage.getCustBackupFlag()).isEqualTo(UPDATED_CUST_BACKUP_FLAG);
        assertThat(testTsmsAgentMessage.getCustMessageType()).isEqualTo(DEFAULT_CUST_MESSAGE_TYPE);
        assertThat(testTsmsAgentMessage.getCustBackupDate()).isEqualTo(DEFAULT_CUST_BACKUP_DATE);
        assertThat(testTsmsAgentMessage.getCustData1()).isEqualTo(UPDATED_CUST_DATA_1);
        assertThat(testTsmsAgentMessage.getCustData2()).isEqualTo(DEFAULT_CUST_DATA_2);
        assertThat(testTsmsAgentMessage.getCustData3()).isEqualTo(DEFAULT_CUST_DATA_3);
        assertThat(testTsmsAgentMessage.getCustData4()).isEqualTo(DEFAULT_CUST_DATA_4);
        assertThat(testTsmsAgentMessage.getCustData5()).isEqualTo(DEFAULT_CUST_DATA_5);
        assertThat(testTsmsAgentMessage.getCustData6()).isEqualTo(DEFAULT_CUST_DATA_6);
        assertThat(testTsmsAgentMessage.getCustData7()).isEqualTo(UPDATED_CUST_DATA_7);
        assertThat(testTsmsAgentMessage.getCustData8()).isEqualTo(DEFAULT_CUST_DATA_8);
        assertThat(testTsmsAgentMessage.getCustData9()).isEqualTo(UPDATED_CUST_DATA_9);
        assertThat(testTsmsAgentMessage.getCustData10()).isEqualTo(DEFAULT_CUST_DATA_10);
        assertThat(testTsmsAgentMessage.getSendFlag()).isEqualTo(DEFAULT_SEND_FLAG);
        assertThat(testTsmsAgentMessage.getSendDate()).isEqualTo(UPDATED_SEND_DATE);
        assertThat(testTsmsAgentMessage.getResendFlag()).isEqualTo(DEFAULT_RESEND_FLAG);
        assertThat(testTsmsAgentMessage.getMmsImgServerPath1()).isEqualTo(UPDATED_MMS_IMG_SERVER_PATH_1);
        assertThat(testTsmsAgentMessage.getMmsImgServerPath2()).isEqualTo(UPDATED_MMS_IMG_SERVER_PATH_2);
        assertThat(testTsmsAgentMessage.getMmsImgServerPath3()).isEqualTo(UPDATED_MMS_IMG_SERVER_PATH_3);
        assertThat(testTsmsAgentMessage.getImgSendDate()).isEqualTo(UPDATED_IMG_SEND_DATE);
        assertThat(testTsmsAgentMessage.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testTsmsAgentMessage.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testTsmsAgentMessage.getKkoImgServerPath()).isEqualTo(UPDATED_KKO_IMG_SERVER_PATH);
        assertThat(testTsmsAgentMessage.getIntfNm()).isEqualTo(UPDATED_INTF_NM);
        assertThat(testTsmsAgentMessage.getSendedId()).isEqualTo(DEFAULT_SENDED_ID);
    }

    @Test
    @Transactional
    void fullUpdateTsmsAgentMessageWithPatch() throws Exception {
        // Initialize the database
        tsmsAgentMessageRepository.saveAndFlush(tsmsAgentMessage);

        int databaseSizeBeforeUpdate = tsmsAgentMessageRepository.findAll().size();

        // Update the tsmsAgentMessage using partial update
        TsmsAgentMessage partialUpdatedTsmsAgentMessage = new TsmsAgentMessage();
        partialUpdatedTsmsAgentMessage.setId(tsmsAgentMessage.getId());

        partialUpdatedTsmsAgentMessage
            .messageSeqno(UPDATED_MESSAGE_SEQNO)
            .serviceSeqno(UPDATED_SERVICE_SEQNO)
            .sendMessage(UPDATED_SEND_MESSAGE)
            .subject(UPDATED_SUBJECT)
            .backupMessage(UPDATED_BACKUP_MESSAGE)
            .backupProcessCode(UPDATED_BACKUP_PROCESS_CODE)
            .messageType(UPDATED_MESSAGE_TYPE)
            .contentsType(UPDATED_CONTENTS_TYPE)
            .receiveMobileNo(UPDATED_RECEIVE_MOBILE_NO)
            .callbackNo(UPDATED_CALLBACK_NO)
            .jobType(UPDATED_JOB_TYPE)
            .sendReserveDate(UPDATED_SEND_RESERVE_DATE)
            .templateCode(UPDATED_TEMPLATE_CODE)
            .mmsImgPath1(UPDATED_MMS_IMG_PATH_1)
            .mmsImgPath2(UPDATED_MMS_IMG_PATH_2)
            .mmsImgPath3(UPDATED_MMS_IMG_PATH_3)
            .imgAttachFlag(UPDATED_IMG_ATTACH_FLAG)
            .kkoBtnName(UPDATED_KKO_BTN_NAME)
            .kkoBtnUrl(UPDATED_KKO_BTN_URL)
            .kkoBtnLink1(UPDATED_KKO_BTN_LINK_1)
            .kkoBtnLink2(UPDATED_KKO_BTN_LINK_2)
            .kkoBtnLink3(UPDATED_KKO_BTN_LINK_3)
            .kkoBtnLink4(UPDATED_KKO_BTN_LINK_4)
            .kkoBtnLink5(UPDATED_KKO_BTN_LINK_5)
            .kkoImgPath(UPDATED_KKO_IMG_PATH)
            .kkoImgLinkUrl(UPDATED_KKO_IMG_LINK_URL)
            .taxLevel1Nm(UPDATED_TAX_LEVEL_1_NM)
            .taxLevel2Nm(UPDATED_TAX_LEVEL_2_NM)
            .registerBy(UPDATED_REGISTER_BY)
            .registerDate(UPDATED_REGISTER_DATE)
            .custBackupFlag(UPDATED_CUST_BACKUP_FLAG)
            .custMessageType(UPDATED_CUST_MESSAGE_TYPE)
            .custBackupDate(UPDATED_CUST_BACKUP_DATE)
            .custData1(UPDATED_CUST_DATA_1)
            .custData2(UPDATED_CUST_DATA_2)
            .custData3(UPDATED_CUST_DATA_3)
            .custData4(UPDATED_CUST_DATA_4)
            .custData5(UPDATED_CUST_DATA_5)
            .custData6(UPDATED_CUST_DATA_6)
            .custData7(UPDATED_CUST_DATA_7)
            .custData8(UPDATED_CUST_DATA_8)
            .custData9(UPDATED_CUST_DATA_9)
            .custData10(UPDATED_CUST_DATA_10)
            .sendFlag(UPDATED_SEND_FLAG)
            .sendDate(UPDATED_SEND_DATE)
            .resendFlag(UPDATED_RESEND_FLAG)
            .mmsImgServerPath1(UPDATED_MMS_IMG_SERVER_PATH_1)
            .mmsImgServerPath2(UPDATED_MMS_IMG_SERVER_PATH_2)
            .mmsImgServerPath3(UPDATED_MMS_IMG_SERVER_PATH_3)
            .imgSendDate(UPDATED_IMG_SEND_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateBy(UPDATED_UPDATE_BY)
            .kkoImgServerPath(UPDATED_KKO_IMG_SERVER_PATH)
            .intfNm(UPDATED_INTF_NM)
            .sendedId(UPDATED_SENDED_ID);

        restTsmsAgentMessageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTsmsAgentMessage.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTsmsAgentMessage))
            )
            .andExpect(status().isOk());

        // Validate the TsmsAgentMessage in the database
        List<TsmsAgentMessage> tsmsAgentMessageList = tsmsAgentMessageRepository.findAll();
        assertThat(tsmsAgentMessageList).hasSize(databaseSizeBeforeUpdate);
        TsmsAgentMessage testTsmsAgentMessage = tsmsAgentMessageList.get(tsmsAgentMessageList.size() - 1);
        assertThat(testTsmsAgentMessage.getMessageSeqno()).isEqualTo(UPDATED_MESSAGE_SEQNO);
        assertThat(testTsmsAgentMessage.getServiceSeqno()).isEqualTo(UPDATED_SERVICE_SEQNO);
        assertThat(testTsmsAgentMessage.getSendMessage()).isEqualTo(UPDATED_SEND_MESSAGE);
        assertThat(testTsmsAgentMessage.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testTsmsAgentMessage.getBackupMessage()).isEqualTo(UPDATED_BACKUP_MESSAGE);
        assertThat(testTsmsAgentMessage.getBackupProcessCode()).isEqualTo(UPDATED_BACKUP_PROCESS_CODE);
        assertThat(testTsmsAgentMessage.getMessageType()).isEqualTo(UPDATED_MESSAGE_TYPE);
        assertThat(testTsmsAgentMessage.getContentsType()).isEqualTo(UPDATED_CONTENTS_TYPE);
        assertThat(testTsmsAgentMessage.getReceiveMobileNo()).isEqualTo(UPDATED_RECEIVE_MOBILE_NO);
        assertThat(testTsmsAgentMessage.getCallbackNo()).isEqualTo(UPDATED_CALLBACK_NO);
        assertThat(testTsmsAgentMessage.getJobType()).isEqualTo(UPDATED_JOB_TYPE);
        assertThat(testTsmsAgentMessage.getSendReserveDate()).isEqualTo(UPDATED_SEND_RESERVE_DATE);
        assertThat(testTsmsAgentMessage.getTemplateCode()).isEqualTo(UPDATED_TEMPLATE_CODE);
        assertThat(testTsmsAgentMessage.getMmsImgPath1()).isEqualTo(UPDATED_MMS_IMG_PATH_1);
        assertThat(testTsmsAgentMessage.getMmsImgPath2()).isEqualTo(UPDATED_MMS_IMG_PATH_2);
        assertThat(testTsmsAgentMessage.getMmsImgPath3()).isEqualTo(UPDATED_MMS_IMG_PATH_3);
        assertThat(testTsmsAgentMessage.getImgAttachFlag()).isEqualTo(UPDATED_IMG_ATTACH_FLAG);
        assertThat(testTsmsAgentMessage.getKkoBtnName()).isEqualTo(UPDATED_KKO_BTN_NAME);
        assertThat(testTsmsAgentMessage.getKkoBtnUrl()).isEqualTo(UPDATED_KKO_BTN_URL);
        assertThat(testTsmsAgentMessage.getKkoBtnLink1()).isEqualTo(UPDATED_KKO_BTN_LINK_1);
        assertThat(testTsmsAgentMessage.getKkoBtnLink2()).isEqualTo(UPDATED_KKO_BTN_LINK_2);
        assertThat(testTsmsAgentMessage.getKkoBtnLink3()).isEqualTo(UPDATED_KKO_BTN_LINK_3);
        assertThat(testTsmsAgentMessage.getKkoBtnLink4()).isEqualTo(UPDATED_KKO_BTN_LINK_4);
        assertThat(testTsmsAgentMessage.getKkoBtnLink5()).isEqualTo(UPDATED_KKO_BTN_LINK_5);
        assertThat(testTsmsAgentMessage.getKkoImgPath()).isEqualTo(UPDATED_KKO_IMG_PATH);
        assertThat(testTsmsAgentMessage.getKkoImgLinkUrl()).isEqualTo(UPDATED_KKO_IMG_LINK_URL);
        assertThat(testTsmsAgentMessage.getTaxLevel1Nm()).isEqualTo(UPDATED_TAX_LEVEL_1_NM);
        assertThat(testTsmsAgentMessage.getTaxLevel2Nm()).isEqualTo(UPDATED_TAX_LEVEL_2_NM);
        assertThat(testTsmsAgentMessage.getRegisterBy()).isEqualTo(UPDATED_REGISTER_BY);
        assertThat(testTsmsAgentMessage.getRegisterDate()).isEqualTo(UPDATED_REGISTER_DATE);
        assertThat(testTsmsAgentMessage.getCustBackupFlag()).isEqualTo(UPDATED_CUST_BACKUP_FLAG);
        assertThat(testTsmsAgentMessage.getCustMessageType()).isEqualTo(UPDATED_CUST_MESSAGE_TYPE);
        assertThat(testTsmsAgentMessage.getCustBackupDate()).isEqualTo(UPDATED_CUST_BACKUP_DATE);
        assertThat(testTsmsAgentMessage.getCustData1()).isEqualTo(UPDATED_CUST_DATA_1);
        assertThat(testTsmsAgentMessage.getCustData2()).isEqualTo(UPDATED_CUST_DATA_2);
        assertThat(testTsmsAgentMessage.getCustData3()).isEqualTo(UPDATED_CUST_DATA_3);
        assertThat(testTsmsAgentMessage.getCustData4()).isEqualTo(UPDATED_CUST_DATA_4);
        assertThat(testTsmsAgentMessage.getCustData5()).isEqualTo(UPDATED_CUST_DATA_5);
        assertThat(testTsmsAgentMessage.getCustData6()).isEqualTo(UPDATED_CUST_DATA_6);
        assertThat(testTsmsAgentMessage.getCustData7()).isEqualTo(UPDATED_CUST_DATA_7);
        assertThat(testTsmsAgentMessage.getCustData8()).isEqualTo(UPDATED_CUST_DATA_8);
        assertThat(testTsmsAgentMessage.getCustData9()).isEqualTo(UPDATED_CUST_DATA_9);
        assertThat(testTsmsAgentMessage.getCustData10()).isEqualTo(UPDATED_CUST_DATA_10);
        assertThat(testTsmsAgentMessage.getSendFlag()).isEqualTo(UPDATED_SEND_FLAG);
        assertThat(testTsmsAgentMessage.getSendDate()).isEqualTo(UPDATED_SEND_DATE);
        assertThat(testTsmsAgentMessage.getResendFlag()).isEqualTo(UPDATED_RESEND_FLAG);
        assertThat(testTsmsAgentMessage.getMmsImgServerPath1()).isEqualTo(UPDATED_MMS_IMG_SERVER_PATH_1);
        assertThat(testTsmsAgentMessage.getMmsImgServerPath2()).isEqualTo(UPDATED_MMS_IMG_SERVER_PATH_2);
        assertThat(testTsmsAgentMessage.getMmsImgServerPath3()).isEqualTo(UPDATED_MMS_IMG_SERVER_PATH_3);
        assertThat(testTsmsAgentMessage.getImgSendDate()).isEqualTo(UPDATED_IMG_SEND_DATE);
        assertThat(testTsmsAgentMessage.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testTsmsAgentMessage.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testTsmsAgentMessage.getKkoImgServerPath()).isEqualTo(UPDATED_KKO_IMG_SERVER_PATH);
        assertThat(testTsmsAgentMessage.getIntfNm()).isEqualTo(UPDATED_INTF_NM);
        assertThat(testTsmsAgentMessage.getSendedId()).isEqualTo(UPDATED_SENDED_ID);
    }

    @Test
    @Transactional
    void patchNonExistingTsmsAgentMessage() throws Exception {
        int databaseSizeBeforeUpdate = tsmsAgentMessageRepository.findAll().size();
        tsmsAgentMessage.setId(count.incrementAndGet());

        // Create the TsmsAgentMessage
        TsmsAgentMessageDTO tsmsAgentMessageDTO = tsmsAgentMessageMapper.toDto(tsmsAgentMessage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTsmsAgentMessageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tsmsAgentMessageDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tsmsAgentMessageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TsmsAgentMessage in the database
        List<TsmsAgentMessage> tsmsAgentMessageList = tsmsAgentMessageRepository.findAll();
        assertThat(tsmsAgentMessageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTsmsAgentMessage() throws Exception {
        int databaseSizeBeforeUpdate = tsmsAgentMessageRepository.findAll().size();
        tsmsAgentMessage.setId(count.incrementAndGet());

        // Create the TsmsAgentMessage
        TsmsAgentMessageDTO tsmsAgentMessageDTO = tsmsAgentMessageMapper.toDto(tsmsAgentMessage);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTsmsAgentMessageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tsmsAgentMessageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TsmsAgentMessage in the database
        List<TsmsAgentMessage> tsmsAgentMessageList = tsmsAgentMessageRepository.findAll();
        assertThat(tsmsAgentMessageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTsmsAgentMessage() throws Exception {
        int databaseSizeBeforeUpdate = tsmsAgentMessageRepository.findAll().size();
        tsmsAgentMessage.setId(count.incrementAndGet());

        // Create the TsmsAgentMessage
        TsmsAgentMessageDTO tsmsAgentMessageDTO = tsmsAgentMessageMapper.toDto(tsmsAgentMessage);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTsmsAgentMessageMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tsmsAgentMessageDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TsmsAgentMessage in the database
        List<TsmsAgentMessage> tsmsAgentMessageList = tsmsAgentMessageRepository.findAll();
        assertThat(tsmsAgentMessageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTsmsAgentMessage() throws Exception {
        // Initialize the database
        tsmsAgentMessageRepository.saveAndFlush(tsmsAgentMessage);

        int databaseSizeBeforeDelete = tsmsAgentMessageRepository.findAll().size();

        // Delete the tsmsAgentMessage
        restTsmsAgentMessageMockMvc
            .perform(delete(ENTITY_API_URL_ID, tsmsAgentMessage.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TsmsAgentMessage> tsmsAgentMessageList = tsmsAgentMessageRepository.findAll();
        assertThat(tsmsAgentMessageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

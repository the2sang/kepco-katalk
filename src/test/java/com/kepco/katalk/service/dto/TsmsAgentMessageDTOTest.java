package com.kepco.katalk.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.kepco.katalk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TsmsAgentMessageDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TsmsAgentMessageDTO.class);
        TsmsAgentMessageDTO tsmsAgentMessageDTO1 = new TsmsAgentMessageDTO();
        tsmsAgentMessageDTO1.setId(1L);
        TsmsAgentMessageDTO tsmsAgentMessageDTO2 = new TsmsAgentMessageDTO();
        assertThat(tsmsAgentMessageDTO1).isNotEqualTo(tsmsAgentMessageDTO2);
        tsmsAgentMessageDTO2.setId(tsmsAgentMessageDTO1.getId());
        assertThat(tsmsAgentMessageDTO1).isEqualTo(tsmsAgentMessageDTO2);
        tsmsAgentMessageDTO2.setId(2L);
        assertThat(tsmsAgentMessageDTO1).isNotEqualTo(tsmsAgentMessageDTO2);
        tsmsAgentMessageDTO1.setId(null);
        assertThat(tsmsAgentMessageDTO1).isNotEqualTo(tsmsAgentMessageDTO2);
    }
}

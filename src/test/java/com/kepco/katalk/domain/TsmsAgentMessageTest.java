package com.kepco.katalk.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.kepco.katalk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TsmsAgentMessageTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TsmsAgentMessage.class);
        TsmsAgentMessage tsmsAgentMessage1 = new TsmsAgentMessage();
        tsmsAgentMessage1.setId(1L);
        TsmsAgentMessage tsmsAgentMessage2 = new TsmsAgentMessage();
        tsmsAgentMessage2.setId(tsmsAgentMessage1.getId());
        assertThat(tsmsAgentMessage1).isEqualTo(tsmsAgentMessage2);
        tsmsAgentMessage2.setId(2L);
        assertThat(tsmsAgentMessage1).isNotEqualTo(tsmsAgentMessage2);
        tsmsAgentMessage1.setId(null);
        assertThat(tsmsAgentMessage1).isNotEqualTo(tsmsAgentMessage2);
    }
}

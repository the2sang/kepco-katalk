package com.kepco.katalk.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TsmsAgentMessageMapperTest {

    private TsmsAgentMessageMapper tsmsAgentMessageMapper;

    @BeforeEach
    public void setUp() {
        tsmsAgentMessageMapper = new TsmsAgentMessageMapperImpl();
    }
}

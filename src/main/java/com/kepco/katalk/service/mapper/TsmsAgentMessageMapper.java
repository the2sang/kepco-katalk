package com.kepco.katalk.service.mapper;

import com.kepco.katalk.domain.*;
import com.kepco.katalk.service.dto.TsmsAgentMessageDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TsmsAgentMessage} and its DTO {@link TsmsAgentMessageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TsmsAgentMessageMapper extends EntityMapper<TsmsAgentMessageDTO, TsmsAgentMessage> {}

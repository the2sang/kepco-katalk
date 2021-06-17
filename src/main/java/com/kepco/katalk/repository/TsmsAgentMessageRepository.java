package com.kepco.katalk.repository;

import com.kepco.katalk.domain.TsmsAgentMessage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TsmsAgentMessage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TsmsAgentMessageRepository extends JpaRepository<TsmsAgentMessage, Long> {}

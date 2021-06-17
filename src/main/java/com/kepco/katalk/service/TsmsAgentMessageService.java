package com.kepco.katalk.service;

import com.kepco.katalk.domain.TsmsAgentMessage;
import com.kepco.katalk.repository.TsmsAgentMessageRepository;
import com.kepco.katalk.service.dto.TsmsAgentMessageDTO;
import com.kepco.katalk.service.mapper.TsmsAgentMessageMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TsmsAgentMessage}.
 */
@Service
@Transactional
public class TsmsAgentMessageService {

    private final Logger log = LoggerFactory.getLogger(TsmsAgentMessageService.class);

    private final TsmsAgentMessageRepository tsmsAgentMessageRepository;

    private final TsmsAgentMessageMapper tsmsAgentMessageMapper;

    public TsmsAgentMessageService(TsmsAgentMessageRepository tsmsAgentMessageRepository, TsmsAgentMessageMapper tsmsAgentMessageMapper) {
        this.tsmsAgentMessageRepository = tsmsAgentMessageRepository;
        this.tsmsAgentMessageMapper = tsmsAgentMessageMapper;
    }

    /**
     * Save a tsmsAgentMessage.
     *
     * @param tsmsAgentMessageDTO the entity to save.
     * @return the persisted entity.
     */
    public TsmsAgentMessageDTO save(TsmsAgentMessageDTO tsmsAgentMessageDTO) {
        log.debug("Request to save TsmsAgentMessage : {}", tsmsAgentMessageDTO);
        TsmsAgentMessage tsmsAgentMessage = tsmsAgentMessageMapper.toEntity(tsmsAgentMessageDTO);
        tsmsAgentMessage = tsmsAgentMessageRepository.save(tsmsAgentMessage);
        return tsmsAgentMessageMapper.toDto(tsmsAgentMessage);
    }

    /**
     * Partially update a tsmsAgentMessage.
     *
     * @param tsmsAgentMessageDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TsmsAgentMessageDTO> partialUpdate(TsmsAgentMessageDTO tsmsAgentMessageDTO) {
        log.debug("Request to partially update TsmsAgentMessage : {}", tsmsAgentMessageDTO);

        return tsmsAgentMessageRepository
            .findById(tsmsAgentMessageDTO.getMessageSeqno())
            .map(
                existingTsmsAgentMessage -> {
                    tsmsAgentMessageMapper.partialUpdate(existingTsmsAgentMessage, tsmsAgentMessageDTO);
                    return existingTsmsAgentMessage;
                }
            )
            .map(tsmsAgentMessageRepository::save)
            .map(tsmsAgentMessageMapper::toDto);
    }

    /**
     * Get all the tsmsAgentMessages.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TsmsAgentMessageDTO> findAll() {
        log.debug("Request to get all TsmsAgentMessages");
        return tsmsAgentMessageRepository
            .findAll()
            .stream()
            .map(tsmsAgentMessageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tsmsAgentMessage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TsmsAgentMessageDTO> findOne(Long id) {
        log.debug("Request to get TsmsAgentMessage : {}", id);
        return tsmsAgentMessageRepository.findById(id).map(tsmsAgentMessageMapper::toDto);
    }

    /**
     * Delete the tsmsAgentMessage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TsmsAgentMessage : {}", id);
        tsmsAgentMessageRepository.deleteById(id);
    }
}

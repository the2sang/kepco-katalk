package com.kepco.katalk.web.rest;

import com.kepco.katalk.repository.TsmsAgentMessageRepository;
import com.kepco.katalk.service.TsmsAgentMessageService;
import com.kepco.katalk.service.dto.TsmsAgentMessageDTO;
import com.kepco.katalk.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.kepco.katalk.domain.TsmsAgentMessage}.
 */
@RestController
@RequestMapping("/api")
public class TsmsAgentMessageResource {

    private final Logger log = LoggerFactory.getLogger(TsmsAgentMessageResource.class);

    private static final String ENTITY_NAME = "kepcoKaTalkTsmsAgentMessage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TsmsAgentMessageService tsmsAgentMessageService;

    private final TsmsAgentMessageRepository tsmsAgentMessageRepository;

    public TsmsAgentMessageResource(
        TsmsAgentMessageService tsmsAgentMessageService,
        TsmsAgentMessageRepository tsmsAgentMessageRepository
    ) {
        this.tsmsAgentMessageService = tsmsAgentMessageService;
        this.tsmsAgentMessageRepository = tsmsAgentMessageRepository;
    }

    /**
     * {@code POST  /tsms-agent-messages} : Create a new tsmsAgentMessage.
     *
     * @param tsmsAgentMessageDTO the tsmsAgentMessageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tsmsAgentMessageDTO, or with status {@code 400 (Bad Request)} if the tsmsAgentMessage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tsms-agent-messages")
    public ResponseEntity<TsmsAgentMessageDTO> createTsmsAgentMessage(@RequestBody TsmsAgentMessageDTO tsmsAgentMessageDTO)
        throws URISyntaxException {
        log.debug("REST request to save TsmsAgentMessage : {}", tsmsAgentMessageDTO);
        if (tsmsAgentMessageDTO.getMessageSeqno() != null) {
            throw new BadRequestAlertException("A new tsmsAgentMessage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TsmsAgentMessageDTO result = tsmsAgentMessageService.save(tsmsAgentMessageDTO);
        return ResponseEntity
            .created(new URI("/api/tsms-agent-messages/" + result.getMessageSeqno()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getMessageSeqno().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tsms-agent-messages/:id} : Updates an existing tsmsAgentMessage.
     *
     * @param id the id of the tsmsAgentMessageDTO to save.
     * @param tsmsAgentMessageDTO the tsmsAgentMessageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tsmsAgentMessageDTO,
     * or with status {@code 400 (Bad Request)} if the tsmsAgentMessageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tsmsAgentMessageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tsms-agent-messages/{id}")
    public ResponseEntity<TsmsAgentMessageDTO> updateTsmsAgentMessage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TsmsAgentMessageDTO tsmsAgentMessageDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TsmsAgentMessage : {}, {}", id, tsmsAgentMessageDTO);
        if (tsmsAgentMessageDTO.getMessageSeqno() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tsmsAgentMessageDTO.getMessageSeqno())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tsmsAgentMessageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TsmsAgentMessageDTO result = tsmsAgentMessageService.save(tsmsAgentMessageDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tsmsAgentMessageDTO.getMessageSeqno().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tsms-agent-messages/:id} : Partial updates given fields of an existing tsmsAgentMessage, field will ignore if it is null
     *
     * @param id the id of the tsmsAgentMessageDTO to save.
     * @param tsmsAgentMessageDTO the tsmsAgentMessageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tsmsAgentMessageDTO,
     * or with status {@code 400 (Bad Request)} if the tsmsAgentMessageDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tsmsAgentMessageDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tsmsAgentMessageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tsms-agent-messages/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TsmsAgentMessageDTO> partialUpdateTsmsAgentMessage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TsmsAgentMessageDTO tsmsAgentMessageDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TsmsAgentMessage partially : {}, {}", id, tsmsAgentMessageDTO);
        if (tsmsAgentMessageDTO.getMessageSeqno() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tsmsAgentMessageDTO.getMessageSeqno())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tsmsAgentMessageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TsmsAgentMessageDTO> result = tsmsAgentMessageService.partialUpdate(tsmsAgentMessageDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tsmsAgentMessageDTO.getMessageSeqno().toString())
        );
    }

    /**
     * {@code GET  /tsms-agent-messages} : get all the tsmsAgentMessages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tsmsAgentMessages in body.
     */
    @GetMapping("/tsms-agent-messages")
    public List<TsmsAgentMessageDTO> getAllTsmsAgentMessages() {
        log.debug("REST request to get all TsmsAgentMessages");
        return tsmsAgentMessageService.findAll();
    }

    /**
     * {@code GET  /tsms-agent-messages/:id} : get the "id" tsmsAgentMessage.
     *
     * @param id the id of the tsmsAgentMessageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tsmsAgentMessageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tsms-agent-messages/{id}")
    public ResponseEntity<TsmsAgentMessageDTO> getTsmsAgentMessage(@PathVariable Long id) {
        log.debug("REST request to get TsmsAgentMessage : {}", id);
        Optional<TsmsAgentMessageDTO> tsmsAgentMessageDTO = tsmsAgentMessageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tsmsAgentMessageDTO);
    }

    /**
     * {@code DELETE  /tsms-agent-messages/:id} : delete the "id" tsmsAgentMessage.
     *
     * @param id the id of the tsmsAgentMessageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tsms-agent-messages/{id}")
    public ResponseEntity<Void> deleteTsmsAgentMessage(@PathVariable Long id) {
        log.debug("REST request to delete TsmsAgentMessage : {}", id);
        tsmsAgentMessageService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

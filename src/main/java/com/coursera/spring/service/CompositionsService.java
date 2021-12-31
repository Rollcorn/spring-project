package com.coursera.spring.service;

import com.coursera.spring.base.CompositionBaseReq;
import com.coursera.spring.repository.CompositionsRepository;
import com.coursera.spring.util.MessageUtil;
import com.coursera.spring.web.model.Compositions;
import com.coursera.spring.web.view.CompositionToCompositionViewConverter;
import com.coursera.spring.web.view.CompositionView;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompositionsService
{
    private final CompositionsRepository compositionsRepository;
    private final CompositionToCompositionViewConverter compositionToCompositionViewConverter;
    private final MessageUtil messageUtil;

    public CompositionsService(CompositionsRepository compositionsRepository,
                                   CompositionToCompositionViewConverter compositionToCompositionViewConverter,
                                   MessageUtil messageUtil){
        this.compositionsRepository = compositionsRepository;
        this.compositionToCompositionViewConverter = compositionToCompositionViewConverter;
        this.messageUtil = messageUtil;
    }

    public CompositionView getTeam(Long id) {
        Compositions compositions = findCompositionOrThrow(id);
        return compositionToCompositionViewConverter.convert(compositions);
    }

    public Compositions findCompositionOrThrow(Long id) {
        return compositionsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.
                        getMessage("composition.NotFound", id)));
    }

    public Page<CompositionView> findAllTeam(Pageable pageable){
        Page<Compositions> teams = compositionsRepository.findAll(pageable);
        List<CompositionView> teamViews = new ArrayList<>();
        teams.forEach(team -> {
            CompositionView teamView = compositionToCompositionViewConverter.convert(team);
            teamViews.add(teamView);
        });
        return new PageImpl<>(teamViews, pageable, teams.getTotalElements());
    }

    public CompositionView create(CompositionBaseReq req) {
        Compositions compositions = new Compositions();
        this.prepare(compositions,req);
        Compositions compSave = compositionsRepository.save(compositions);
        return compositionToCompositionViewConverter.convert(compSave);
    }

    @Transactional
    public void delete(Long id) {
        try {
            compositionsRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("team.NotFound", id));
        }
    }

    public CompositionView update(Compositions compositions, CompositionBaseReq req){
        Compositions newComp = this.prepare(compositions,req);
        Compositions compSave = compositionsRepository.save(newComp);
        return compositionToCompositionViewConverter.convert(compSave);
    }

    public Compositions prepare(Compositions compositions, CompositionBaseReq compBaseReq){
        compositions.setTitle(compBaseReq.getTitle());
        compositions.setAuthor(compBaseReq.getAuthor());
        return compositions;
    }


}

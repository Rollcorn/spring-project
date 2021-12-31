package com.coursera.spring.web.controller;

import com.coursera.spring.base.CompositionBaseReq;
import com.coursera.spring.service.CompositionsService;
import com.coursera.spring.web.model.Compositions;

import com.coursera.spring.web.view.CompositionView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/compositions")
public class CompositionsController {
    private CompositionsService compositionsService;

    public CompositionsController(CompositionsService compositionsService) {
        this.compositionsService = compositionsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CompositionView create(@RequestBody @Valid CompositionBaseReq req) {
        return compositionsService.create(req);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CompositionView getTeam(@PathVariable Long id) {
        return compositionsService.getTeam(id);
    }

    @GetMapping
    @ResponseBody
    public Page<CompositionView> getAllTeam(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return compositionsService.findAllTeam(pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable Long id){
        compositionsService.delete(id);
    }

    @PutMapping("/{id}")
    public CompositionView updateTeam(@PathVariable(name = "id") Long id,
                               @RequestBody @Valid CompositionBaseReq req){
        Compositions compositions = compositionsService.findCompositionOrThrow(id);
        return compositionsService.update(compositions, req);
    }
}

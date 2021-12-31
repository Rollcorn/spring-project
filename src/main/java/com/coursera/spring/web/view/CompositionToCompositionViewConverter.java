package com.coursera.spring.web.view;

import com.coursera.spring.web.model.Compositions;
import com.sun.istack.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CompositionToCompositionViewConverter implements Converter<Compositions, CompositionView> {


    @Override
    public CompositionView convert( @NotNull Compositions compositions) {
        CompositionView view = new CompositionView();
        view.setId(compositions.getId());
        view.setTitle(compositions.getTitle());
        view.setAuthor(compositions.getAuthor());
        view.setGenre(compositions.getGenre());
        return view;
    }
}

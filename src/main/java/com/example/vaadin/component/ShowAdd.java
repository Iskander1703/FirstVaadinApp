package com.example.vaadin.component;
import com.example.vaadin.dto.ShowDto;
import com.example.vaadin.services.ShowDtoService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;


@SpringComponent
@UIScope
public class ShowAdd extends Dialog{

    private final ShowDtoService showDtoService;

    private ShowDto showDto;

    private ChangeHandler changeHandler;

    private Button save = new Button("Сохранить", VaadinIcon.CHECK.create(), a -> save(new ShowDto()));
    private Button cancel = new Button("Отмена");
    private TextField showId = new TextField("ID мероприятия");
    private TextField name = new TextField("Название мероприятия");
    private TextField city = new TextField("Город");
    private TextField AgeRestriction = new TextField("Возрастное ограничение");

    private Binder<ShowDto> binder = new Binder<>(ShowDto.class);


    @Autowired
    public ShowAdd(ShowDtoService showDtoService) {
        super();
        this.showDtoService = showDtoService;

        binder.forField(name).bind(ShowDto::getName, ShowDto::setName);
        binder.forField(city).bind(ShowDto::getCity, ShowDto::setCity);
        binder.forField(showId).bind(ShowDto::getShowIdText, ShowDto::setShowIdText);
        binder.forField(AgeRestriction).bind(ShowDto::getAgeRestrictionText, ShowDto::setAgeRestrictionText);

        this.getFooter().add(save);
        this.getFooter().add(cancel);
        VerticalLayout verticalLayout = new VerticalLayout(showId, name,
                city, AgeRestriction);
        this.add(verticalLayout);
        this.setHeaderTitle("Добавить мероприятие");

//        binder.bindInstanceFields(this);

        //При нажатии на кнопку отмены окно будет закрываться
        cancel.addClickListener(e->this.close());

        save.addClickListener(e -> {
            save(new ShowDto());
            this.close();
        });


    }

    //Сохранение
    void save(ShowDto showDto) {
        this.showDto = showDto;
        try {
            binder.writeBean(this.showDto);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        showDtoService.saveShow(this.showDto);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }
    public final void editShow(ShowDto s) {
        final boolean persisted = s.getShowIdText() != null;
        if (persisted) {
            // Find fresh entity for editing
            this.showDto = showDtoService.findShowById(s.getShowId());
        } else {
            this.showDto = s;
        }
        binder.setBean(this.showDto);
    }

}


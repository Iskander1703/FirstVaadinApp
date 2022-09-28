package com.example.vaadin.view;

import com.example.vaadin.component.ShowAdd;
import com.example.vaadin.component.ShowEditor;
import com.example.vaadin.dto.ShowDto;
import com.example.vaadin.services.ShowDtoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;

@Route("shows")
@PageTitle("Shows")
@PermitAll
public class ShowView extends VerticalLayout {
    private final ShowDtoService showDtoService;


    private final ShowEditor showEditor;

    private final ShowAdd showAdd;


    private Grid<ShowDto> adminGrid = new Grid<>(ShowDto.class, false);
    private Button addButton = new Button("Добавить", VaadinIcon.CHECK.create());

    private Button editButton = new Button("Редактировать");
    private Button deleteButton = new Button("Удалить", VaadinIcon.TRASH.create());
    private HorizontalLayout actions = new HorizontalLayout(addButton, editButton, deleteButton);

    @Autowired
    public ShowView(ShowDtoService showDtoService, ShowEditor showEditor, ShowAdd showAdd) {


        this.showDtoService = showDtoService;
        this.showEditor = showEditor;
        this.showAdd = showAdd;


        adminGrid.setItems(showDtoService.getAllShowDto());
        adminGrid.addColumn(a -> a.getShowId()).setHeader("ID Мероприятия");
        adminGrid.addColumn(a -> a.getName()).setHeader("Название мероприятия");
        adminGrid.addColumn(a -> a.getAgeRestriction()).setHeader("Возрастное ограничение");
        adminGrid.addColumn(a -> a.getCity()).setHeader("Город");

        //Установка кнопок удалить и изменить в неактивное положение
        deleteButton.setEnabled(false);
        editButton.setEnabled(false);


//        adminGrid.addItemClickListener(item -> {
//            deleteButton.setEnabled(true);
//            editButton.setEnabled(true);
//        });

        //Открытие окна редактирование по даблклику
        adminGrid.addItemDoubleClickListener(e -> {
            showEditor.editShow(e.getItem());
            showEditor.open();
        });

        //Сделать кнопки активными при выборе
        adminGrid.addSelectionListener(item ->
        {
            deleteButton.setEnabled(true);
            editButton.setEnabled(true);
        });

        //Кнопка добавить новое мероприятие
        addButton.addClickListener(a -> showAdd.open());

        //Клик по кнопке изменить мероприятие
        editButton.addClickListener(e -> {
            showEditor.editShow(this.adminGrid.getSelectionModel()
                    .getFirstSelectedItem().get());
            showEditor.open();
        });

        //Кнопка удалить мероприятие
        deleteButton.addClickListener(e ->
        {
            showDtoService.deleteShowById(this.adminGrid
                    .getSelectionModel()
                    .getFirstSelectedItem()
                    .get());

            listShowDto();
        });

        showEditor.setChangeHandler(() -> listShowDto());
        showAdd.setChangeHandler(() -> listShowDto());

        add(adminGrid, actions, showEditor);
    }

    //Перезагрузка списка всех мероприятий для того что бы
    //Показывать всегда актуальный список мероприятий
    void listShowDto() {
        adminGrid.setItems(showDtoService.getAllShowDto());
    }


}

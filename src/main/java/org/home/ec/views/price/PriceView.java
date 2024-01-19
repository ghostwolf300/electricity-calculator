package org.home.ec.views.price;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.home.ec.data.SamplePerson;
import org.home.ec.services.SamplePersonService;
import org.home.ec.views.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("Price")
@Route(value = "price", layout = MainLayout.class)
@Uses(Icon.class)
public class PriceView extends Composite<VerticalLayout> {

    public PriceView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        DatePicker dtPkrFromDate = new DatePicker();
        DatePicker dtPkrToDate = new DatePicker();
        Button btnRefresh = new Button();
        Button btnUpdate = new Button();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Grid priceGrid = new Grid(SamplePerson.class);
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow.setAlignItems(Alignment.END);
        layoutRow.setJustifyContentMode(JustifyContentMode.START);
        dtPkrFromDate.setLabel("From Date");
        dtPkrFromDate.setWidth("min-content");
        dtPkrToDate.setLabel("To Date");
        dtPkrToDate.setWidth("min-content");
        btnRefresh.setText("Refresh");
        btnRefresh.setWidth("min-content");
        btnRefresh.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnUpdate.setText("Update DB");
        btnUpdate.setWidth("min-content");
        btnUpdate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        priceGrid.setWidth("100%");
        priceGrid.getStyle().set("flex-grow", "0");
        setGridSampleData(priceGrid);
        getContent().add(layoutRow);
        layoutRow.add(dtPkrFromDate);
        layoutRow.add(dtPkrToDate);
        layoutRow.add(btnRefresh);
        layoutRow.add(btnUpdate);
        getContent().add(layoutColumn2);
        layoutColumn2.add(priceGrid);
    }

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
    }

    @Autowired()
    private SamplePersonService samplePersonService;
}

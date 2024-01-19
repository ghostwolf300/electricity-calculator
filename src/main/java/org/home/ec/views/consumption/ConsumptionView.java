package org.home.ec.views.consumption;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
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

import java.sql.Date;
import java.util.List;

import org.home.ec.data.Consumption;
import org.home.ec.data.SamplePerson;
import org.home.ec.services.ConsumptionService;
import org.home.ec.services.SamplePersonService;
import org.home.ec.views.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("Consumption")
@Route(value = "consumption", layout = MainLayout.class)
@Uses(Icon.class)
public class ConsumptionView extends Composite<VerticalLayout> implements ComponentEventListener<ClickEvent<Button>>{
	
	private DatePicker dtPkrFromDate;
    private DatePicker dtPkrToDate;
	private Button btnRefresh;
    private Button btnUpdate;

	@Autowired()
	private SamplePersonService samplePersonService;
	    
	@Autowired
	private ConsumptionService consumptionService;
	
    public ConsumptionView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        dtPkrFromDate = new DatePicker();
        dtPkrToDate = new DatePicker();
        btnRefresh = new Button();
        btnUpdate = new Button();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Grid consumptionGrid = new Grid(SamplePerson.class);
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
        btnRefresh.addClickListener(this);
        btnUpdate.setText("Update DB");
        btnUpdate.setWidth("min-content");
        btnUpdate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnUpdate.addClickListener(this);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        consumptionGrid.setWidth("100%");
        consumptionGrid.getStyle().set("flex-grow", "0");
        setGridSampleData(consumptionGrid);
        getContent().add(layoutRow);
        layoutRow.add(dtPkrFromDate);
        layoutRow.add(dtPkrToDate);
        layoutRow.add(btnRefresh);
        layoutRow.add(btnUpdate);
        getContent().add(layoutColumn2);
        layoutColumn2.add(consumptionGrid);
    }

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
    }

	@Override
	public void onComponentEvent(ClickEvent<Button> event) {
		if(event.getSource().equals(btnUpdate)) {
			System.out.println("Update database");
			if(!(dtPkrFromDate.isEmpty() || dtPkrToDate.isEmpty())) {
				consumptionService.updateData(Date.valueOf(dtPkrFromDate.getValue()),Date.valueOf(dtPkrToDate.getValue()));
			}
		}
		else if(event.getSource().equals(btnRefresh)) {
			System.out.println("Refresh view");
			if(!(dtPkrFromDate.isEmpty() || dtPkrToDate.isEmpty())) {
				List<Consumption> consumption=consumptionService.getConsumption(Date.valueOf(dtPkrFromDate.getValue()),Date.valueOf(dtPkrToDate.getValue()));
			}
		}
		
	}
    

}

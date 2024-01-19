package org.home.ec.views.data;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.home.ec.views.MainLayout;

@PageTitle("Data")
@Route(value = "data", layout = MainLayout.class)
@Uses(Icon.class)
public class DataView extends Composite<VerticalLayout> {

    public DataView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        DatePicker dtPkrFromDate = new DatePicker();
        DatePicker dtPkrToDate = new DatePicker();
        Button btnRefresh = new Button();
        Button btnUpdate = new Button();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Tabs tabs = new Tabs();
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
        tabs.setWidth("100%");
        setTabsSampleData(tabs);
        getContent().add(layoutRow);
        layoutRow.add(dtPkrFromDate);
        layoutRow.add(dtPkrToDate);
        layoutRow.add(btnRefresh);
        layoutRow.add(btnUpdate);
        getContent().add(layoutColumn2);
        layoutColumn2.add(tabs);
    }

    private void setTabsSampleData(Tabs tabs) {
        tabs.add(new Tab("Consumption"));
        tabs.add(new Tab("Price"));
    }
}

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
        DatePicker datePicker = new DatePicker();
        DatePicker datePicker2 = new DatePicker();
        Button buttonPrimary = new Button();
        Button buttonPrimary2 = new Button();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Tabs tabs = new Tabs();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow.setAlignItems(Alignment.END);
        layoutRow.setJustifyContentMode(JustifyContentMode.START);
        datePicker.setLabel("From Date");
        datePicker.setWidth("min-content");
        datePicker2.setLabel("To Date");
        datePicker2.setWidth("min-content");
        buttonPrimary.setText("Refresh");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary2.setText("Update DB");
        buttonPrimary2.setWidth("min-content");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        tabs.setWidth("100%");
        setTabsSampleData(tabs);
        getContent().add(layoutRow);
        layoutRow.add(datePicker);
        layoutRow.add(datePicker2);
        layoutRow.add(buttonPrimary);
        layoutRow.add(buttonPrimary2);
        getContent().add(layoutColumn2);
        layoutColumn2.add(tabs);
    }

    private void setTabsSampleData(Tabs tabs) {
        tabs.add(new Tab("Dashboard"));
        tabs.add(new Tab("Payment"));
        tabs.add(new Tab("Shipping"));
    }
}

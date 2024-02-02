package org.home.ec.views.reporting;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.home.ec.data.Consumption;
import org.home.ec.data.HourCost;
import org.home.ec.data.IDayCost;
import org.home.ec.data.IHourCost;
import org.home.ec.data.IPeriodCostEnergy;
import org.home.ec.data.ITransferCost;
import org.home.ec.data.Location;
import org.home.ec.services.LocationService;
import org.home.ec.services.ReportingService;
import org.home.ec.views.MainLayout;

@PageTitle("Reporting")
@Route(value = "reporting", layout = MainLayout.class)
@Uses(Icon.class)
public class ReportingView extends Composite<VerticalLayout> {
	
	private final LocationService locationService;
	private final ReportingService reportingService;
	
	private ComboBox<Location> cmbLocation;
	private DatePicker dtPckrFrom;
	private DatePicker dtPckrTo;
	private Button btnRefresh;
	private final Tabs tbsReporting;
	private final Tab tbHourReport;
	private final Tab tbDayReport;
	private final Tab tbPeriodReport;
	private Grid<IHourCost> gridHourReport;
	private Grid<IDayCost> gridDayReport;
	private Grid<IPeriodCostEnergy> gridPeriodReport;
	private Grid<ITransferCost> gridTransferCost;
	private final VerticalLayout tabContent;
	
    public ReportingView(ReportingService reportingService,LocationService locationService) {
    	this.reportingService=reportingService;
    	this.locationService=locationService;
        HorizontalLayout layoutRow = new HorizontalLayout();
        cmbLocation = this.createCmbLocation();
        dtPckrFrom = new DatePicker();
        dtPckrFrom.setLabel("From Date");
        dtPckrFrom.setWidth("min-content");
        dtPckrTo = new DatePicker();
        dtPckrTo.setLabel("To Date");
        dtPckrTo.setWidth("min-content");
        btnRefresh = new Button("Refresh",e->this.refreshReport());
        //btnRefresh.setText("Refresh");
        btnRefresh.setWidth("min-content");
        btnRefresh.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        tbHourReport=new Tab("Hour");
        tbDayReport=new Tab("Day");
        tbPeriodReport=new Tab("Period");
        tbsReporting = new Tabs(tbHourReport,tbDayReport,tbPeriodReport);
        tbsReporting.addSelectedChangeListener(event->this.setContent(event.getSelectedTab()));
        tabContent=new VerticalLayout();
        tabContent.setWidth("100%");
        VerticalLayout layoutColumn2 = new VerticalLayout();
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.add(tbsReporting);
        layoutColumn2.add(tabContent);
        //layoutColumn2.add(createGridHourCost());
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow.setAlignItems(Alignment.END);
        layoutRow.setJustifyContentMode(JustifyContentMode.START);
        getContent().add(layoutRow);
        layoutRow.add(cmbLocation);
        layoutRow.add(dtPckrFrom);
        layoutRow.add(dtPckrTo);
        layoutRow.add(btnRefresh);
        getContent().add(layoutColumn2);
        
    }
    
    private ComboBox<Location> createCmbLocation(){
    	cmbLocation=new ComboBox<Location>();
        cmbLocation.setLabel("Location");
        List<Location> locations=locationService.getLocations();
        cmbLocation.setItems(locations);
        cmbLocation.setItemLabelGenerator(item->((Location)item).toString());
    	return cmbLocation;
    }
    
    private Tabs createTbsReporting() {
    	//tbsReporting=new Tabs();
    	//tbsReporting.setWidth("100%");
    	//tbsReporting.add(createTbPeriodReport());
    	//tbsReporting.add(createTbDailyReport());
    	//tbsReporting.add(createTbHourlyReport());
    	//tbsReporting.addSelectedChangeListener(e->this.setContent(e.getSelectedTab()));
    	return tbsReporting;
    }
    
    private Tab createTbPeriodReport() {
    	Tab tbPeriodReport=new Tab("Period");
    	return tbPeriodReport;
    }
    
    private Tab createTbDailyReport() {
    	Tab tbDailyReport=new Tab("Daily");
    	return tbDailyReport;
    }
    
    private Tab createTbHourlyReport() {
    	Tab tbHourlyReport=new Tab("Hourly");
    	//VerticalLayout layoutColumn = new VerticalLayout();
    	//tbHourlyReport.add(layoutColumn);
    	//layoutColumn.setWidth("100%");
    	//layoutColumn.getStyle().set("flex-grow", "0");
    	//layoutColumn.add(createGridHourCost());
    	//tbHourlyReport.add(getGridHourCost());
    	return tbHourlyReport;
    }
    
    private Grid<IHourCost> getGridHourReport(){
    	if(gridHourReport==null) {
	    	gridHourReport = new Grid<>(IHourCost.class);
	    	gridHourReport.setWidth("100%");
	        gridHourReport.getStyle().set("flex-grow", "0");
	        gridHourReport.setColumns("keyDate","keyHour","hourConsumption","minQuartConsumption","maxQuartConsumption","averageQuartConsumption","price","averagePrice","hourCostSpot","margin","hourCostTotal");
    	}
    	return gridHourReport;
    }
    
    private Grid<IDayCost> getGridDayReport(){
    	if(gridDayReport==null) {
    		gridDayReport = new Grid<>(IDayCost.class);
	    	gridDayReport.setWidth("100%");
	        gridDayReport.getStyle().set("flex-grow", "0");
	        gridDayReport.setColumns("keyDate","dayConsumption","minHourConsumption","maxHourConsumption","averageHourConsumption","minHourPrice","maxHourPrice","averageHourPrice","dayCostEUR");
    	}
    	return gridDayReport;
    }
    
    private Grid<IPeriodCostEnergy> getGridPeriodReport(){
    	if(gridPeriodReport==null) {
    		gridPeriodReport = new Grid<>(IPeriodCostEnergy.class);
	    	gridPeriodReport.setWidth("100%");
	        gridPeriodReport.getStyle().set("flex-grow", "0");
	        gridPeriodReport.setColumns("contractId","daysInPeriod","periodConsumption","averageHourConsumption","minHourConsumption","maxHourConsumption","minHourPrice","maxHourPrice","spotCostEUR","energyCostEUR","transferCostEUR");
    	}
    	return gridPeriodReport;
    }
    
    private Grid<ITransferCost> getGridTransferCost(){
    	if(gridTransferCost==null) {
    		gridTransferCost = new Grid<>(ITransferCost.class);
    		gridTransferCost.setWidth("100%");
    		gridTransferCost.getStyle().set("flex-grow", "0");
    		gridTransferCost.setColumns("id","description","consumption","unitPriceCt","transferCostEUR");
    	}
    	return gridTransferCost;
    }
    
    private void setContent(Tab tab) {
    	tabContent.removeAll();
    	if(tab.equals(tbHourReport)) {
    		tabContent.add(getGridHourReport());
    	}
    	else if(tab.equals(tbDayReport)) {
    		tabContent.add(getGridDayReport());
    	}
    	else if(tab.equals(tbPeriodReport)) {
    		tabContent.add(getGridPeriodReport());
    		tabContent.add(getGridTransferCost());
    	}
    }
    
    private void refreshReport() {
    	if(!(cmbLocation.getValue()==null || dtPckrFrom.isEmpty() || dtPckrTo.isEmpty())) {
    		Location location=cmbLocation.getValue();
    		Date fromDate=Date.valueOf(dtPckrFrom.getValue());
    		Date toDate=Date.valueOf(dtPckrTo.getValue());
    		if(tbsReporting.getSelectedTab().equals(tbHourReport)) {
    			List<IHourCost> hourlyCost=reportingService.getHourlyCost(location.getId(), fromDate, toDate);
    			getGridHourReport().setItems(hourlyCost);
    		}
    		else if(tbsReporting.getSelectedTab().equals(tbDayReport)) {
    			List<IDayCost> dayCost=reportingService.getDailyCost(location.getId(), fromDate, toDate);
    			System.out.println("Got day costs: "+dayCost.size());
    			getGridDayReport().setItems(dayCost);
    		}
    		else if(tbsReporting.getSelectedTab().equals(tbPeriodReport)) {
    			IPeriodCostEnergy periodCostEnergy=reportingService.getPeriodCost(location.getId(), fromDate, toDate);
    			getGridPeriodReport().setItems(periodCostEnergy);
    			List<ITransferCost> transferCosts=reportingService.getTransferCost(location.getId(), fromDate, toDate);
    			getGridTransferCost().setItems(transferCosts);
    		}
    		this.setContent(tbsReporting.getSelectedTab());
    	}
    }


}

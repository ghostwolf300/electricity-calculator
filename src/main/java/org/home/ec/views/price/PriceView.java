package org.home.ec.views.price;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.upload.SucceededEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.home.ec.data.Consumption;
import org.home.ec.data.HourId;
import org.home.ec.data.Price;
import org.home.ec.data.SamplePerson;
import org.home.ec.services.PriceService;
import org.home.ec.services.SamplePersonService;
import org.home.ec.views.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("Price")
@Route(value = "price", layout = MainLayout.class)
@Uses(Icon.class)
public class PriceView extends Composite<VerticalLayout> implements ComponentEventListener<ClickEvent<Button>>{
	
	private Button btnRefresh;
	private Button btnUpdate;
	private Button btnUpdateFromFile;
	private Anchor ancDownload;
	private DatePicker dtPkrFromDate;
	private DatePicker dtPkrToDate;
	private Grid<Price> priceGrid;
	private final MemoryBuffer memoryBuffer = new MemoryBuffer();
    private Dialog fileDialog;
    private Upload uploadFile;
	
	@Autowired()
    private SamplePersonService samplePersonService;
	
	@Autowired
	private PriceService priceService;
	
    public PriceView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        dtPkrFromDate = new DatePicker();
        dtPkrToDate = new DatePicker();
        btnRefresh = new Button();
        btnUpdate = new Button();
        ancDownload=new Anchor();
        ancDownload.setId("download");
        ancDownload.add(new Button(new Icon(VaadinIcon.DOWNLOAD_ALT)));
        btnUpdateFromFile=new Button("Update File",e->fileDialog.open());
        
        VerticalLayout layoutColumn2 = new VerticalLayout();
        //Grid priceGrid = new Grid(SamplePerson.class);
        //priceGrid = new Grid<>();
     
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
        btnRefresh.addClickListener(this);
        btnRefresh.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnUpdate.setText("Update API");
        btnUpdate.setWidth("min-content");
        btnUpdate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnUpdate.addClickListener(this);
        btnUpdateFromFile.setWidth("min-content");
        btnUpdateFromFile.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        //priceGrid.setWidth("100%");
        //priceGrid.getStyle().set("flex-grow", "0");
        //setGridSampleData(priceGrid);
        getContent().add(layoutRow);
        layoutRow.add(dtPkrFromDate);
        layoutRow.add(dtPkrToDate);
        layoutRow.add(btnRefresh);
        layoutRow.add(btnUpdate);
        layoutRow.add(ancDownload);
        layoutRow.add(getFileUploadDialog(),btnUpdateFromFile);
        getContent().add(layoutColumn2);
        layoutColumn2.add(this.getPriceGrid());
    }
    
    public Grid<Price> getPriceGrid() {
    	if(priceGrid==null) {
    		priceGrid = new Grid<>(Price.class);
    		priceGrid.setWidth("100%");
    	    priceGrid.getStyle().set("flex-grow", "0");
    	    priceGrid.setColumns("id.keyDate","id.keyHour","price");
    	    priceGrid.setItems(getSamplePrices());
    	}
    	return priceGrid;
    }

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
    }
    
    private List<Price> getSamplePrices(){
    	List<Price> prices=Arrays.asList(
    			new Price("2024-01-20",0,14.145),
    			new Price("2024-01-20",1,12.56),
    			new Price("2024-01-20",3,10.020)
    			);
    	return prices;
    }
    
    public Dialog getFileUploadDialog() {
    	if(fileDialog==null) {
    		fileDialog=new Dialog();
    		fileDialog.setHeaderTitle("Upload consumption file");
    		VerticalLayout dialogLayout = new VerticalLayout();
    		fileDialog.add(dialogLayout);
    		
    		uploadFile=new Upload(memoryBuffer);
    		uploadFile.addSucceededListener(event -> this.handleUpload(event));
    		fileDialog.add(uploadFile);
    		
    		Button btnCancel = new Button("Cancel", e -> fileDialog.close());
    		fileDialog.getFooter().add(btnCancel);

    	}
    	return fileDialog;
    }
    
    private void handleUpload(SucceededEvent event) {
		InputStream fileData=memoryBuffer.getInputStream();
		String fileName=event.getFileName();
		long contentLength=event.getContentLength();
		String mimeType=event.getMIMEType();
		priceService.processPriceFile(fileData, fileName, contentLength, mimeType);
		fileDialog.close();
	}
    
    private void handleDownload() {
    	
    }

	@Override
	public void onComponentEvent(ClickEvent<Button> event) {
		if(event.getSource().equals(btnUpdate)) {
			System.out.println("Update database");
			if(!(dtPkrFromDate.isEmpty() || dtPkrToDate.isEmpty())) {
				priceService.updateData(Date.valueOf(dtPkrFromDate.getValue()),Date.valueOf(dtPkrToDate.getValue()));
				List<Price> prices=priceService.getPrices(Date.valueOf(dtPkrFromDate.getValue()),Date.valueOf(dtPkrToDate.getValue()));
				priceGrid.setItems(prices);
			}
		}
		else if(event.getSource().equals(btnRefresh)) {
			System.out.println("Refresh view");
			if(!(dtPkrFromDate.isEmpty() || dtPkrToDate.isEmpty())) {
				List<Price> prices=priceService.getPrices(Date.valueOf(dtPkrFromDate.getValue()),Date.valueOf(dtPkrToDate.getValue()));
				priceGrid.setItems(prices);
				ancDownload.setHref("/download/prices?fromDate="+dtPkrFromDate.getValue().toString()+"&toDate="+dtPkrToDate.getValue().toString());
			}
		}
	}
}

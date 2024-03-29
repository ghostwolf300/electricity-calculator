package org.home.ec.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.home.ec.views.about.AboutView;
import org.home.ec.views.consumption.ConsumptionView;
import org.home.ec.views.contract.ContractView;
import org.home.ec.views.data.DataView;
import org.home.ec.views.helloworld.HelloWorldView;
import org.home.ec.views.price.PriceView;
import org.home.ec.views.reporting.ReportingView;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Electricity Calculator");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        //nav.addItem(new SideNavItem("Hello World", HelloWorldView.class, LineAwesomeIcon.GLOBE_SOLID.create()));
        //nav.addItem(new SideNavItem("About", AboutView.class, LineAwesomeIcon.FILE.create()));
        nav.addItem(new SideNavItem("Contract", ContractView.class, LineAwesomeIcon.FILE_CONTRACT_SOLID.create()));
        nav.addItem(new SideNavItem("Consumption", ConsumptionView.class, LineAwesomeIcon.BOLT_SOLID.create()));
        nav.addItem(new SideNavItem("Price", PriceView.class, LineAwesomeIcon.EURO_SIGN_SOLID.create()));
        nav.addItem(new SideNavItem("Reporting", ReportingView.class, LineAwesomeIcon.CHART_BAR.create()));
        nav.addItem(new SideNavItem("Data", DataView.class, LineAwesomeIcon.DATABASE_SOLID.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}


package one.microstream.microstreamtraining.ui;

import com.vaadin.ui.Button;
import com.xdev.ui.XdevButton;
import com.xdev.ui.XdevGridLayout;
import com.xdev.ui.XdevView;
import com.xdev.ui.entitycomponent.table.XdevTable;

import one.microstream.microstreamtraining.dal.CustomerDAO;
import one.microstream.microstreamtraining.entities.Customer;

public class MainView extends XdevView {

	/**
	 * 
	 */
	public MainView() {
		super();
		this.initUI();
	}

	/**
	 * Event handler delegate method for the {@link XdevButton} {@link #btnSave}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnSave_buttonClick(final Button.ClickEvent event) {
		final Customer customer = new Customer();
		customer.setFirstname("Christian");
		customer.setLastname("Kümmel");
		customer.setId(10);
		customer.setMail("c.kuemmel@xdev-software.de");
		CustomerDAO.insert(customer);
	}

	/**
	 * Event handler delegate method for the {@link XdevButton} {@link #btnLoad}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnLoad_buttonClick(final Button.ClickEvent event) {
		this.table.getBeanContainerDataSource().addAll(CustomerDAO.findAll());
	}

	/*
	 * WARNING: Do NOT edit!<br>The content of this method is always regenerated by
	 * the UI designer.
	 */
	// <generated-code name="initUI">
	private void initUI() {
		this.gridLayout = new XdevGridLayout();
		this.btnSave = new XdevButton();
		this.btnLoad = new XdevButton();
		this.table = new XdevTable<>();
	
		this.btnSave.setCaption("Erzeugen");
		this.btnLoad.setCaption("Laden");
		this.table.setContainerDataSource(Customer.class, false);
		this.table.setVisibleColumns("firstname", "id", "lastname", "mail");
	
		this.gridLayout.setColumns(1);
		this.gridLayout.setRows(3);
		this.btnSave.setWidth(100, Unit.PERCENTAGE);
		this.btnSave.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.btnSave, 0, 0);
		this.btnLoad.setWidth(100, Unit.PERCENTAGE);
		this.btnLoad.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.btnLoad, 0, 1);
		this.table.setSizeFull();
		this.gridLayout.addComponent(this.table, 0, 2);
		this.gridLayout.setColumnExpandRatio(0, 10.0F);
		this.gridLayout.setRowExpandRatio(2, 100.0F);
		this.gridLayout.setSizeFull();
		this.setContent(this.gridLayout);
		this.setSizeFull();
	
		this.btnSave.addClickListener(event -> this.btnSave_buttonClick(event));
		this.btnLoad.addClickListener(event -> this.btnLoad_buttonClick(event));
	} // </generated-code>

	// <generated-code name="variables">
	private XdevButton btnSave, btnLoad;
	private XdevTable<Customer> table;
	private XdevGridLayout gridLayout;
	// </generated-code>

}

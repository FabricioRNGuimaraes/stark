package stark.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import stark.entity.Item;
import stark.service.IItemService;
import stark.service.impl.ItemServiceImpl;

@ManagedBean(name="itemBean")
@ViewScoped
public class ItemBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	private List<Item> itens = new ArrayList<Item>();
	private Item item = new Item();
	private IItemService service;
	private Boolean newEntity = false;
	
	public void initialize() {
		service = new ItemServiceImpl();
		itens = service.findAll();
	}

	public void onTabChange() {
		
//		initialize();
//		clean();
//		cleanItemBought();
	}
	
	public void save(ActionEvent event) {

		if(!newEntity && item.getId() == null) {

			addInfoGrowlMessage("Item " + item.getDescription() + " saved!");
			service.save(item);
		} else {
			
			addInfoGrowlMessage("Item " + item.getDescription() + " updated!");
			service.update(item);
		}
		itens.add(item);
	}

	public void remove(ActionEvent event) {
		
		service.remove(item);
		itens.remove(item);
		addInfoGrowlMessage("Item " + item.getDescription() + " removed!");
	}
	
	public void saveItemBought(ActionEvent event) {

//		if(!isEditEntityItemBought && itemBought.getId() == null) {
//
//			addInfoGrowlMessage("Values for " + itemBought.getItem().getDescription() + " saved!");
//			itemBoughtDAO.save(itemBought);
//			itensBought.add(itemBought);
//		} else {
//			
//			addInfoGrowlMessage("Values for " + itemBought.getItem().getDescription() + " updated!");
//			itemBoughtDAO.update(itemBought);
//		}
	}
	
	public void removeItemBought(ActionEvent event) {
		
//		itemBoughtDAO.remove(itemBought);
//		itensBought.remove(itemBought);
//		addInfoGrowlMessage("Values for " + itemBought.getItem().getDescription() + " removed!");
//		clean();
	}
	
	public void clean(ActionEvent ev) {
		clean();
	}
	
	private void clean() {
		
		item = new Item();
		newEntity = false;
	}
	
	public void cleanItemBought(ActionEvent ev) {
//		cleanItemBought();
	}
	
	private void cleanItemBought() {
		
//		itemBought = new ItemBought();
//		isEditEntityItemBought = false;
//		isViewEntityItemBought = false;
	}
	
	public void prepareEdit() {
		
		newEntity = true;
//		isViewEntity = false;
	}

//	public void prepareView() {
//		isViewEntity = true;
//		isEditEntity = false;
//	}

	public void prepareEditItemBought() {
		
//		isEditEntityItemBought = true;
//		isViewEntityItemBought = false;
	}

//	public void prepareViewItemBought() {
//		isViewEntityItemBought = true;
//		isEditEntityItemBought = false;
//	}
	
	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Boolean getNewEntity() {
		return newEntity;
	}

	public void setNewEntity(Boolean newEntity) {
		this.newEntity = newEntity;
	}
	
}
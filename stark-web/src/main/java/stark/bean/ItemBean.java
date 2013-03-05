package stark.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.hibernate.annotations.common.util.StringHelper;

import antlr.StringUtils;

import stark.dao.ItemBoughtDAO;
import stark.dao.ItemDAO;
import stark.entity.Item;
import stark.entity.ItemBought;

@ManagedBean(name="itemBean")
@ViewScoped
public class ItemBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	private List<Item> itens = new ArrayList<Item>();
	private Item item = new Item();

	private List<ItemBought> itensBought = new ArrayList<ItemBought>();
	private ItemBought itemBought = new ItemBought();
	
	private ItemDAO dao;
	private ItemBoughtDAO itemBoughtDAO;
	
	private Boolean isEditEntity = false;
//	private Boolean isViewEntity = false;
	
	private Boolean isEditEntityItemBought = false;
//	private Boolean isViewEntityItemBought = false;
	
	public void initialize() {

		dao = new ItemDAO();
		itens = dao.findAll();
		
		itemBoughtDAO = new ItemBoughtDAO();
		itensBought = itemBoughtDAO.findAll();
	}

	public void onTabChange() {
	
//		initialize();
		clean();
		cleanItemBought();
	}
	
	public void save(ActionEvent event) {

		if(!isEditEntity && item.getId() == null) {

			addInfoGrowlMessage("Item " + item.getDescription() + " saved!");
			dao.save(item);
			itens.add(item);
		} else {
			
			addInfoGrowlMessage("Item " + item.getDescription() + " updated!");
			dao.update(item);
		}
	}

	public void saveItemBought(ActionEvent event) {

		if(!isEditEntityItemBought && itemBought.getId() == null) {

			addInfoGrowlMessage("Values for " + itemBought.getItem().getDescription() + " saved!");
			itemBoughtDAO.save(itemBought);
			itensBought.add(itemBought);
		} else {
			
			addInfoGrowlMessage("Values for " + itemBought.getItem().getDescription() + " updated!");
			itemBoughtDAO.update(itemBought);
		}
	}
	
	public void remove(ActionEvent event) {
		
		dao.remove(item);
		itens.remove(item);
		addInfoGrowlMessage("Item " + item.getDescription() + " removed!");
//		clean();
	}
	
	public void removeItemBought(ActionEvent event) {
		
		itemBoughtDAO.remove(itemBought);
		itensBought.remove(itemBought);
		addInfoGrowlMessage("Values for " + itemBought.getItem().getDescription() + " removed!");
//		clean();
	}
	
	public void clean(ActionEvent ev) {
		clean();
	}
	
	private void clean() {
		
		item = new Item();
		isEditEntity = false;
//		isViewEntity = false;
	}
	
	public void cleanItemBought(ActionEvent ev) {
		cleanItemBought();
	}
	
	private void cleanItemBought() {
		
		itemBought = new ItemBought();
		isEditEntityItemBought = false;
//		isViewEntityItemBought = false;
	}
	
	public void prepareEdit() {
		
		isEditEntity = true;
//		isViewEntity = false;
	}

//	public void prepareView() {
//		isViewEntity = true;
//		isEditEntity = false;
//	}

	public void prepareEditItemBought() {
		
		isEditEntityItemBought = true;
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

	public Boolean getIsEditEntity() {
		return isEditEntity;
	}

	public void setIsEditEntity(Boolean isEditEntity) {
		this.isEditEntity = isEditEntity;
	}

//	public Boolean getIsViewEntity() {
//		return isViewEntity;
//	}
//
//	public void setIsViewEntity(Boolean isViewEntity) {
//		this.isViewEntity = isViewEntity;
//	}

	public List<ItemBought> getItensBought() {
		return itensBought;
	}

	public void setItensBought(List<ItemBought> itensBought) {
		this.itensBought = itensBought;
	}

	public ItemBought getItemBought() {
		return itemBought;
	}

	public void setItemBought(ItemBought itemBought) {
		this.itemBought = itemBought;
	}

	public Boolean getIsEditEntityItemBought() {
		return isEditEntityItemBought;
	}

	public void setIsEditEntityItemBought(Boolean isEditEntityItemBought) {
		this.isEditEntityItemBought = isEditEntityItemBought;
	}

//	public Boolean getIsViewEntityItemBought() {
//		return isViewEntityItemBought;
//	}
//
//	public void setIsViewEntityItemBought(Boolean isViewEntityItemBought) {
//		this.isViewEntityItemBought = isViewEntityItemBought;
//	}
	
}
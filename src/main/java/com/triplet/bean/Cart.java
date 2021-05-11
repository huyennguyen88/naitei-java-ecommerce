package com.triplet.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.triplet.model.User;
import com.triplet.utils.FormatUtils;

public class Cart {
	private List<Item> items;
	private BigDecimal cartPrice;
	private String formatCartPrice;
	private int itemQuantity;
	private Item lastedItem;
	private Ship ship;
	private User user;

	public Cart() {
		this.items = new ArrayList<Item>();
		this.cartPrice = new BigDecimal(0);
		this.formatCartPrice = "";
		this.itemQuantity = 0;
		this.lastedItem = null;
		this.ship = null;
		this.user = null;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public BigDecimal getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(BigDecimal cartPrice) {
		this.cartPrice = cartPrice;
	}

	public String getFormatCartPrice() {
		return formatCartPrice;
	}

	public void setFormatCartPrice(String formatCartPrice) {
		this.formatCartPrice = formatCartPrice;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public Item getLastedItem() {
		return lastedItem;
	}

	public void setLastedItem(Item lastedItem) {
		this.lastedItem = lastedItem;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void add(Item item) {
		// Check item exist
		boolean existed = false;
		for (Item i : this.items) {
			if (i.getId() == item.getId()) {
				i.setQuantity(i.getQuantity() + 1);
				i.setSumPrice(i.getSumPrice().add(item.getPrice()));
				i.setFormatSumPrice();
				existed = true;
				break;
			}
		}
		if (!existed) {
			items.add(item);
			this.itemQuantity = this.itemQuantity + 1;
		}
		this.cartPrice = this.cartPrice.add(item.getPrice());
		FormatUtils formatUtils = new FormatUtils();
		this.formatCartPrice = formatUtils.formatCurrency(this.cartPrice);
		this.lastedItem = item;
	}

	public void plus(int itemId) {
		for (Item i : items) {
			if (i.getId() == itemId) {
				i.setQuantity(i.getQuantity() + 1);
				i.setSumPrice(i.getSumPrice().add(i.getPrice()));
				i.setFormatSumPrice();
				this.cartPrice = this.cartPrice.add(i.getPrice());
				FormatUtils formatUtils = new FormatUtils();
				this.formatCartPrice = formatUtils.formatCurrency(this.cartPrice);
				break;
			}
		}
	}

	public void minus(int itemId) {
		for (Item i : items) {
			if (i.getId() == itemId) {
				i.setQuantity(i.getQuantity() - 1);
				i.setSumPrice(i.getSumPrice().subtract(i.getPrice()));
				i.setFormatSumPrice();
				if (i.getQuantity() == 0)
					this.itemQuantity = this.itemQuantity - 1;
				this.cartPrice = this.cartPrice.subtract(i.getPrice());
				FormatUtils formatUtils = new FormatUtils();
				this.formatCartPrice = formatUtils.formatCurrency(this.cartPrice);
				break;
			}
		}
	}

}

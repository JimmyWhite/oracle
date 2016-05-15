package oracle;

public class Product {
	int no;
	String name;
	String brand;
	String spec;
	String photo;
	float buy_price;
	float sell_price;
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public float getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(float buy_price) {
		this.buy_price = buy_price;
	}
	public float getSell_price() {
		return sell_price;
	}
	public void setSell_price(float sell_price) {
		this.sell_price = sell_price;
	}

}

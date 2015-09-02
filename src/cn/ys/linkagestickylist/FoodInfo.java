package cn.ys.linkagestickylist;

/**
 * 
 * @author ys
 * @date 2015-9-1
 */
public class FoodInfo extends FoodClass{
	
	private String foodInfoName;
	private FoodClass foodClass;
	public String getFoodInfoName() {
		return foodInfoName;
	}
	public void setFoodInfoName(String foodInfoName) {
		this.foodInfoName = foodInfoName;
	}
	public FoodClass getFoodClass() {
		return foodClass;
	}
	public void setFoodClass(FoodClass foodClass) {
		this.foodClass = foodClass;
	}
	
}

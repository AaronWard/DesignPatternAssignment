package cakeBuilder;

/**
 * A Cake class that uses the Builder pattern for easy and efficient object creation.
 * The class contains a Builder inner class
 * @author aaron
 *
 */
public class Cake {
	
	/**Initiliase variables **/
	private String type;
	private String shape;
	private String size;
	private String toppings;
	
	/**
	 * private constructor
	 * @param builder
	 */
	private Cake (Builder builder){
		this.type = builder.type;
		this.shape = builder.shape;
		this.size = builder.size;
		this.toppings = builder.toppings;
	}

	/**
	 * Builder class
	 * @author aaron
	 *
	 */
	public static class Builder{
		private String type;
		private String shape;
		private String size;
		private String toppings;
	
		public Builder type(final String type){
			this.type = type;
			return this;
		}
		
		public Builder shape (final String shape){
			this.shape = shape;
			return this;
		}
		
		public Builder size (final String size){
			this.size = size;
			return this;
		}
		
		public Builder toppings(final String toppings){
			this.toppings = toppings;
			return this;
		}
		
		public Cake build(){
			return new Cake(this);
		}
	}
	
	
	/********* Getters *********/
	
	/**
	 * Returns the type of cake
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * returns the shape
	 * @return
	 */
	public String getShape() {
		return shape;
	}

	/**
	 * returns the size of the cake
	 * @return
	 */
	public String getSize() {
		return size;
	}

	/**
	 * returns the cake toppings
	 * @return
	 */
	public String getToppings() {
		return toppings;
	}
}

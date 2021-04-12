package entities;

public class RepresentantPara extends User{

	public RepresentantPara(Builder builder) {
		super(builder);
	}
	
	public static class Builder extends User.Builder<Builder>{

		public Builder(String firstName, String lastName) {
			super(firstName, lastName);
		}
		
		@Override
		public RepresentantPara build() {
			return new RepresentantPara(this);
		}
		
	}
}

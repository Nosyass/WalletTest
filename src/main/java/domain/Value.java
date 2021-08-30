package domain;

import java.util.Objects;

/**
 * @author yassine.sahli
 */
public class Value {

	protected final double amount;

	public Value(double amount) {
		this.amount = amount;
	}

	public static Value from(double amount) {
		return new Value(amount);
	}
	

	/*Equals and hashCode was needed for unit test*/
	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Value other = (Value) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount);
	}
	
	
	

}

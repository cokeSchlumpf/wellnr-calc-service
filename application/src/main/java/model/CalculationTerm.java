package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import util.BaseObject;

/**
 * This class either include operation, left & right XOR a value. I know it's a
 * little bit dumb, but enough for this example.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "operation", "left", "right", "value" })
public class CalculationTerm extends BaseObject {

	@XmlElement(name = "operation")
	public final CalculationOperation operation;

	@XmlElement(name = "left")
	public final CalculationTerm left;

	@XmlElement(name = "right")
	public final CalculationTerm right;

	@XmlElement(name = "value")
	private final Integer value;

	/**
	 * Constructor for instantiation via JAXB
	 */
	@SuppressWarnings("unused")
	private CalculationTerm() {
		this(null, null, null);
	}

	public CalculationTerm(CalculationOperation operation, CalculationTerm left, CalculationTerm right) {
		this.operation = operation;
		this.left = left;
		this.right = right;
		this.value = null;
	}

	public CalculationTerm(int value) {
		this.operation = null;
		this.left = null;
		this.right = null;
		this.value = Integer.valueOf(value);
	}

}

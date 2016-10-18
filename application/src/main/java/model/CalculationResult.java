package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import util.BaseObject;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "result" })
public class CalculationResult extends BaseObject {

	@XmlElement(name = "result", required = true)
	public final int result;

	/**
	 * Constructor for instantiation via JAXB
	 */
	@SuppressWarnings("unused")
	private CalculationResult() {
		this(0);
	}

	public CalculationResult(int result) {
		this.result = result;
	}

}
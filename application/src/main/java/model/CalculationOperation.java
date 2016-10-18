package model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum CalculationOperation {

	@XmlEnumValue("ADD")
	ADD,

	@XmlEnumValue("SUB")
	SUB

}

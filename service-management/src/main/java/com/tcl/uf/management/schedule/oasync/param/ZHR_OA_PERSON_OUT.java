/**
 * ZHR_OA_PERSON_OUT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tcl.uf.management.schedule.oasync.param;

public class ZHR_OA_PERSON_OUT implements java.io.Serializable {
    private String p_ALL;

    private Date p_DATUM;

    private String p_PERNR;

    private String p_PWD;

    private ZTHR_PERSON_OUT[] IT_PERSON;

    public ZHR_OA_PERSON_OUT() {
    }

    public ZHR_OA_PERSON_OUT(
           String p_ALL,
           Date p_DATUM,
           String p_PERNR,
           String p_PWD,
           ZTHR_PERSON_OUT[] IT_PERSON) {
           this.p_ALL = p_ALL;
           this.p_DATUM = p_DATUM;
           this.p_PERNR = p_PERNR;
           this.p_PWD = p_PWD;
           this.IT_PERSON = IT_PERSON;
    }


    /**
     * Gets the p_ALL value for this ZHR_OA_PERSON_OUT.
     *
     * @return p_ALL
     */
    public String getP_ALL() {
        return p_ALL;
    }


    /**
     * Sets the p_ALL value for this ZHR_OA_PERSON_OUT.
     *
     * @param p_ALL
     */
    public void setP_ALL(String p_ALL) {
        this.p_ALL = p_ALL;
    }


    /**
     * Gets the p_DATUM value for this ZHR_OA_PERSON_OUT.
     *
     * @return p_DATUM
     */
    public Date getP_DATUM() {
        return p_DATUM;
    }


    /**
     * Sets the p_DATUM value for this ZHR_OA_PERSON_OUT.
     *
     * @param p_DATUM
     */
    public void setP_DATUM(Date p_DATUM) {
        this.p_DATUM = p_DATUM;
    }


    /**
     * Gets the p_PERNR value for this ZHR_OA_PERSON_OUT.
     *
     * @return p_PERNR
     */
    public String getP_PERNR() {
        return p_PERNR;
    }


    /**
     * Sets the p_PERNR value for this ZHR_OA_PERSON_OUT.
     *
     * @param p_PERNR
     */
    public void setP_PERNR(String p_PERNR) {
        this.p_PERNR = p_PERNR;
    }


    /**
     * Gets the p_PWD value for this ZHR_OA_PERSON_OUT.
     *
     * @return p_PWD
     */
    public String getP_PWD() {
        return p_PWD;
    }


    /**
     * Sets the p_PWD value for this ZHR_OA_PERSON_OUT.
     *
     * @param p_PWD
     */
    public void setP_PWD(String p_PWD) {
        this.p_PWD = p_PWD;
    }


    /**
     * Gets the IT_PERSON value for this ZHR_OA_PERSON_OUT.
     *
     * @return IT_PERSON
     */
    public ZTHR_PERSON_OUT[] getIT_PERSON() {
        return IT_PERSON;
    }


    /**
     * Sets the IT_PERSON value for this ZHR_OA_PERSON_OUT.
     *
     * @param IT_PERSON
     */
    public void setIT_PERSON(ZTHR_PERSON_OUT[] IT_PERSON) {
        this.IT_PERSON = IT_PERSON;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ZHR_OA_PERSON_OUT)) return false;
        ZHR_OA_PERSON_OUT other = (ZHR_OA_PERSON_OUT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.p_ALL==null && other.getP_ALL()==null) ||
             (this.p_ALL!=null &&
              this.p_ALL.equals(other.getP_ALL()))) &&
            ((this.p_DATUM==null && other.getP_DATUM()==null) ||
             (this.p_DATUM!=null &&
              this.p_DATUM.equals(other.getP_DATUM()))) &&
            ((this.p_PERNR==null && other.getP_PERNR()==null) ||
             (this.p_PERNR!=null &&
              this.p_PERNR.equals(other.getP_PERNR()))) &&
            ((this.p_PWD==null && other.getP_PWD()==null) ||
             (this.p_PWD!=null &&
              this.p_PWD.equals(other.getP_PWD()))) &&
            ((this.IT_PERSON==null && other.getIT_PERSON()==null) ||
             (this.IT_PERSON!=null &&
              java.util.Arrays.equals(this.IT_PERSON, other.getIT_PERSON())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getP_ALL() != null) {
            _hashCode += getP_ALL().hashCode();
        }
        if (getP_DATUM() != null) {
            _hashCode += getP_DATUM().hashCode();
        }
        if (getP_PERNR() != null) {
            _hashCode += getP_PERNR().hashCode();
        }
        if (getP_PWD() != null) {
            _hashCode += getP_PWD().hashCode();
        }
        if (getIT_PERSON() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIT_PERSON());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getIT_PERSON(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ZHR_OA_PERSON_OUT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", ">ZHR_OA_PERSON_OUT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_ALL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "P_ALL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_DATUM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "P_DATUM"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_PERNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "P_PERNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_PWD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "P_PWD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IT_PERSON");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IT_PERSON"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZTHR_PERSON_OUT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

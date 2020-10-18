/**
 * ZTHR_PERSON_OUT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tcl.uf.management.schedule.oasync.param;

public class ZTHR_PERSON_OUT implements java.io.Serializable {
    /* Personnel number */
    private String PERNR;

    /* Communication ID/Number */
    private String USRID;

    /* Last Name */
    private String NACHN;

    private String EMSEX;

    /* Date of Birth */
    private Date GBDAT;

    /* Organizational Unit */
    private String ORGEH;

    private String ORGEH_TEXT;

    private String PLANS_TEXT;

    private String STELL_TEXT;

    private String TEL_NO;

    private String EMAIL;

    private String STAT2_TXT;

    public ZTHR_PERSON_OUT() {
    }

    public ZTHR_PERSON_OUT(
           String PERNR,
           String USRID,
           String NACHN,
           String EMSEX,
           Date GBDAT,
           String ORGEH,
           String ORGEH_TEXT,
           String PLANS_TEXT,
           String STELL_TEXT,
           String TEL_NO,
           String EMAIL,
           String STAT2_TXT) {
           this.PERNR = PERNR;
           this.USRID = USRID;
           this.NACHN = NACHN;
           this.EMSEX = EMSEX;
           this.GBDAT = GBDAT;
           this.ORGEH = ORGEH;
           this.ORGEH_TEXT = ORGEH_TEXT;
           this.PLANS_TEXT = PLANS_TEXT;
           this.STELL_TEXT = STELL_TEXT;
           this.TEL_NO = TEL_NO;
           this.EMAIL = EMAIL;
           this.STAT2_TXT = STAT2_TXT;
    }


    /**
     * Gets the PERNR value for this ZTHR_PERSON_OUT.
     *
     * @return PERNR   * Personnel number
     */
    public String getPERNR() {
        return PERNR;
    }


    /**
     * Sets the PERNR value for this ZTHR_PERSON_OUT.
     *
     * @param PERNR   * Personnel number
     */
    public void setPERNR(String PERNR) {
        this.PERNR = PERNR;
    }


    /**
     * Gets the USRID value for this ZTHR_PERSON_OUT.
     *
     * @return USRID   * Communication ID/Number
     */
    public String getUSRID() {
        return USRID;
    }


    /**
     * Sets the USRID value for this ZTHR_PERSON_OUT.
     *
     * @param USRID   * Communication ID/Number
     */
    public void setUSRID(String USRID) {
        this.USRID = USRID;
    }


    /**
     * Gets the NACHN value for this ZTHR_PERSON_OUT.
     *
     * @return NACHN   * Last Name
     */
    public String getNACHN() {
        return NACHN;
    }


    /**
     * Sets the NACHN value for this ZTHR_PERSON_OUT.
     *
     * @param NACHN   * Last Name
     */
    public void setNACHN(String NACHN) {
        this.NACHN = NACHN;
    }


    /**
     * Gets the EMSEX value for this ZTHR_PERSON_OUT.
     *
     * @return EMSEX
     */
    public String getEMSEX() {
        return EMSEX;
    }


    /**
     * Sets the EMSEX value for this ZTHR_PERSON_OUT.
     *
     * @param EMSEX
     */
    public void setEMSEX(String EMSEX) {
        this.EMSEX = EMSEX;
    }


    /**
     * Gets the GBDAT value for this ZTHR_PERSON_OUT.
     *
     * @return GBDAT   * Date of Birth
     */
    public Date getGBDAT() {
        return GBDAT;
    }


    /**
     * Sets the GBDAT value for this ZTHR_PERSON_OUT.
     *
     * @param GBDAT   * Date of Birth
     */
    public void setGBDAT(Date GBDAT) {
        this.GBDAT = GBDAT;
    }


    /**
     * Gets the ORGEH value for this ZTHR_PERSON_OUT.
     *
     * @return ORGEH   * Organizational Unit
     */
    public String getORGEH() {
        return ORGEH;
    }


    /**
     * Sets the ORGEH value for this ZTHR_PERSON_OUT.
     *
     * @param ORGEH   * Organizational Unit
     */
    public void setORGEH(String ORGEH) {
        this.ORGEH = ORGEH;
    }


    /**
     * Gets the ORGEH_TEXT value for this ZTHR_PERSON_OUT.
     *
     * @return ORGEH_TEXT
     */
    public String getORGEH_TEXT() {
        return ORGEH_TEXT;
    }


    /**
     * Sets the ORGEH_TEXT value for this ZTHR_PERSON_OUT.
     *
     * @param ORGEH_TEXT
     */
    public void setORGEH_TEXT(String ORGEH_TEXT) {
        this.ORGEH_TEXT = ORGEH_TEXT;
    }


    /**
     * Gets the PLANS_TEXT value for this ZTHR_PERSON_OUT.
     *
     * @return PLANS_TEXT
     */
    public String getPLANS_TEXT() {
        return PLANS_TEXT;
    }


    /**
     * Sets the PLANS_TEXT value for this ZTHR_PERSON_OUT.
     *
     * @param PLANS_TEXT
     */
    public void setPLANS_TEXT(String PLANS_TEXT) {
        this.PLANS_TEXT = PLANS_TEXT;
    }


    /**
     * Gets the STELL_TEXT value for this ZTHR_PERSON_OUT.
     *
     * @return STELL_TEXT
     */
    public String getSTELL_TEXT() {
        return STELL_TEXT;
    }


    /**
     * Sets the STELL_TEXT value for this ZTHR_PERSON_OUT.
     *
     * @param STELL_TEXT
     */
    public void setSTELL_TEXT(String STELL_TEXT) {
        this.STELL_TEXT = STELL_TEXT;
    }


    /**
     * Gets the TEL_NO value for this ZTHR_PERSON_OUT.
     *
     * @return TEL_NO
     */
    public String getTEL_NO() {
        return TEL_NO;
    }


    /**
     * Sets the TEL_NO value for this ZTHR_PERSON_OUT.
     *
     * @param TEL_NO
     */
    public void setTEL_NO(String TEL_NO) {
        this.TEL_NO = TEL_NO;
    }


    /**
     * Gets the EMAIL value for this ZTHR_PERSON_OUT.
     *
     * @return EMAIL
     */
    public String getEMAIL() {
        return EMAIL;
    }


    /**
     * Sets the EMAIL value for this ZTHR_PERSON_OUT.
     *
     * @param EMAIL
     */
    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }


    /**
     * Gets the STAT2_TXT value for this ZTHR_PERSON_OUT.
     *
     * @return STAT2_TXT
     */
    public String getSTAT2_TXT() {
        return STAT2_TXT;
    }


    /**
     * Sets the STAT2_TXT value for this ZTHR_PERSON_OUT.
     *
     * @param STAT2_TXT
     */
    public void setSTAT2_TXT(String STAT2_TXT) {
        this.STAT2_TXT = STAT2_TXT;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ZTHR_PERSON_OUT)) return false;
        ZTHR_PERSON_OUT other = (ZTHR_PERSON_OUT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.PERNR==null && other.getPERNR()==null) ||
             (this.PERNR!=null &&
              this.PERNR.equals(other.getPERNR()))) &&
            ((this.USRID==null && other.getUSRID()==null) ||
             (this.USRID!=null &&
              this.USRID.equals(other.getUSRID()))) &&
            ((this.NACHN==null && other.getNACHN()==null) ||
             (this.NACHN!=null &&
              this.NACHN.equals(other.getNACHN()))) &&
            ((this.EMSEX==null && other.getEMSEX()==null) ||
             (this.EMSEX!=null &&
              this.EMSEX.equals(other.getEMSEX()))) &&
            ((this.GBDAT==null && other.getGBDAT()==null) ||
             (this.GBDAT!=null &&
              this.GBDAT.equals(other.getGBDAT()))) &&
            ((this.ORGEH==null && other.getORGEH()==null) ||
             (this.ORGEH!=null &&
              this.ORGEH.equals(other.getORGEH()))) &&
            ((this.ORGEH_TEXT==null && other.getORGEH_TEXT()==null) ||
             (this.ORGEH_TEXT!=null &&
              this.ORGEH_TEXT.equals(other.getORGEH_TEXT()))) &&
            ((this.PLANS_TEXT==null && other.getPLANS_TEXT()==null) ||
             (this.PLANS_TEXT!=null &&
              this.PLANS_TEXT.equals(other.getPLANS_TEXT()))) &&
            ((this.STELL_TEXT==null && other.getSTELL_TEXT()==null) ||
             (this.STELL_TEXT!=null &&
              this.STELL_TEXT.equals(other.getSTELL_TEXT()))) &&
            ((this.TEL_NO==null && other.getTEL_NO()==null) ||
             (this.TEL_NO!=null &&
              this.TEL_NO.equals(other.getTEL_NO()))) &&
            ((this.EMAIL==null && other.getEMAIL()==null) ||
             (this.EMAIL!=null &&
              this.EMAIL.equals(other.getEMAIL()))) &&
            ((this.STAT2_TXT==null && other.getSTAT2_TXT()==null) ||
             (this.STAT2_TXT!=null &&
              this.STAT2_TXT.equals(other.getSTAT2_TXT())));
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
        if (getPERNR() != null) {
            _hashCode += getPERNR().hashCode();
        }
        if (getUSRID() != null) {
            _hashCode += getUSRID().hashCode();
        }
        if (getNACHN() != null) {
            _hashCode += getNACHN().hashCode();
        }
        if (getEMSEX() != null) {
            _hashCode += getEMSEX().hashCode();
        }
        if (getGBDAT() != null) {
            _hashCode += getGBDAT().hashCode();
        }
        if (getORGEH() != null) {
            _hashCode += getORGEH().hashCode();
        }
        if (getORGEH_TEXT() != null) {
            _hashCode += getORGEH_TEXT().hashCode();
        }
        if (getPLANS_TEXT() != null) {
            _hashCode += getPLANS_TEXT().hashCode();
        }
        if (getSTELL_TEXT() != null) {
            _hashCode += getSTELL_TEXT().hashCode();
        }
        if (getTEL_NO() != null) {
            _hashCode += getTEL_NO().hashCode();
        }
        if (getEMAIL() != null) {
            _hashCode += getEMAIL().hashCode();
        }
        if (getSTAT2_TXT() != null) {
            _hashCode += getSTAT2_TXT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ZTHR_PERSON_OUT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZTHR_PERSON_OUT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USRID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "USRID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NACHN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NACHN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMSEX");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EMSEX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GBDAT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GBDAT"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORGEH");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ORGEH"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORGEH_TEXT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ORGEH_TEXT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PLANS_TEXT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PLANS_TEXT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STELL_TEXT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STELL_TEXT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TEL_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TEL_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMAIL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EMAIL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STAT2_TXT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STAT2_TXT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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

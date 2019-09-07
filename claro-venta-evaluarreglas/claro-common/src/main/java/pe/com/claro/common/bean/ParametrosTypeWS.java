package pe.com.claro.common.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para parametrosType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="parametrosType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="objetoOpcional" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="campo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="valor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parametrosType", propOrder = {
    "objetoOpcional"
})
public class ParametrosTypeWS {

    @XmlElement(required = true)
    protected List<ParametrosTypeWS.ObjetoOpcional> objetoOpcional;

    /**
     * Gets the value of the objetoOpcional property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objetoOpcional property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjetoOpcional().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParametrosType.ObjetoOpcional }
     * 
     * 
     */
    public List<ParametrosTypeWS.ObjetoOpcional> getObjetoOpcional() {
        if (objetoOpcional == null) {
            objetoOpcional = new ArrayList<>();
        }
        return this.objetoOpcional;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="campo" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="valor" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ObjetoOpcional {

        @XmlAttribute(name = "campo")
        protected String campo;
        @XmlAttribute(name = "valor")
        protected String valor;

        /**
         * Obtiene el valor de la propiedad campo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCampo() {
            return campo;
        }

        /**
         * Define el valor de la propiedad campo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCampo(String value) {
            this.campo = value;
        }

        /**
         * Obtiene el valor de la propiedad valor.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValor() {
            return valor;
        }

        /**
         * Define el valor de la propiedad valor.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValor(String value) {
            this.valor = value;
        }

    }

}

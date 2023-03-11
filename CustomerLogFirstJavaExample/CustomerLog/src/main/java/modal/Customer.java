package modal;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 *
 * @author Ebin
 */
@XmlRootElement(name = "customer")
@XmlType(propOrder = {"id", "firstName", "country", "city", "visitTimeDate",  "postalCode", "nationalId","lastName"})
public class Customer {

    private String id;
    private String firstName;
    private String country;
    private String city;
    private String visitTimeDate;
    private String postalCode;
    private String nationalId;
    private String lastName;

    public Customer() {
    }

    public Customer(String id, String firstName, String country, String city, String visitTimeDate, String postalCode,
            String nationalId, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.country = country;
        this.city = city;
        this.visitTimeDate = visitTimeDate;
        this.postalCode = postalCode;
        this.nationalId = nationalId;
        this.lastName = lastName;
    }

    @XmlElement
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlElement
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement
    public String getVisitTimeDate() {
        return visitTimeDate;
    }

    public void setVisitTimeDate(String visitTimeDate) {
        this.visitTimeDate = visitTimeDate;
    }

    @XmlElement
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @XmlElement
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    @XmlElement
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Customer {" + "id=" + id + ", firstName=" + firstName + ", country=" + country + ", city=" + city
                + ", visitTimeDate=" + visitTimeDate + ", postalCode=" + postalCode + ", nationalId=" + nationalId + ", lastName=" + lastName + '}';
    }
}

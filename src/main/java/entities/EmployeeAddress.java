package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Employee_address")
public class EmployeeAddress {
    @Id
    private int id;
    @Column(name = "address")
    private String address;

    public EmployeeAddress() {

    }

    public EmployeeAddress(int id, String address) {
        this.id = id;
        this.address = address;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

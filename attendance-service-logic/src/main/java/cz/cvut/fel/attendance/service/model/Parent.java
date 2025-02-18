package cz.cvut.fel.attendance.service.model;

import cz.cvut.fel.attendance.service.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parent")
public class Parent extends User {

    private String street;

    private String city;

    private int zip;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Child> children = new ArrayList<>();

    public Parent(String firstName, String lastName, String email, String phoneNumber, String password, String street, String city, int zip, Role role) {
        super(firstName, lastName, email, phoneNumber, password, role);
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    public void addChild(Child child) {
        this.children.add(child);
    }
}

package com.example.fundmanager.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "fundId")
public class Fund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fundId;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, name = "employee_id")
    private Long employeeId;
    /**
     * Investment funds are managed by a single fund manager
     *
     * Each fund has a set of positions
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", updatable = false, insertable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private FundManager manager;

    @OneToMany(mappedBy = "fund", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Position> positions;

    public Fund() {
    }

    public Fund(String name, FundManager manager, List<Position> positions) {
        this.name = name;
        this.manager = manager;
        this.positions = positions;
    }

    public Fund(Long fundId, String name, FundManager manager, List<Position> positions) {
        this.fundId = fundId;
        this.name = name;
        this.manager = manager;
        this.positions = positions;
    }

    public Fund(Long fundId, String name, Long employeeId) {
        this.fundId = fundId;
        this.name = name;
        this.employeeId = employeeId;
    }

    public Fund(String name, Long employeeId) {
        this.name = name;
        this.employeeId = employeeId;
    }

    public Long getFundId() {
        return fundId;
    }

    public void setFundId(Long fundId) {
        this.fundId = fundId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FundManager getManager() {
        return manager;
    }

    public void setManager(FundManager manager) {
        this.manager = manager;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "fundId=" + fundId +
                ", name='" + name + '\'' +
                ", manager=" + manager +
                ", positions=" + positions +
                '}';
    }
}

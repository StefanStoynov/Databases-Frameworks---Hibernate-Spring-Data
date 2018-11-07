package app.domein.entities.p01_gringotts;


import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "wizard_deposits")
public class WizardDeposits {

    private long id;
    private String firstName;
    private String lastName;
    private String notes;
    private int age;
    private String magicWandCreator;
    private int magicWandSize;
    private String depositGroup;
    private Date startDate;
    private BigDecimal depositAmount;
    private BigDecimal depositInterest;
    private BigDecimal depositCharge;
    private LocalDate depositExparationDate;
    private boolean isDepositExpired;


    public WizardDeposits() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "first_name",length = 50)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "last_name", length = 60,nullable = false)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name = "notes", columnDefinition = "text", length = 1000)
    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Column(name = "age", nullable = false)
    @Min(0)
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Column(name = "magic_wand_creator",length = 100)
    public String getMagicWandCreator() {
        return this.magicWandCreator;
    }

    public void setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
    }
    @Column(name ="magic_wand_size")
    @Min(1)
    public int getMagicWandSize() {
        return this.magicWandSize;
    }

    public void setMagicWandSize(int magicWandSize) {
        this.magicWandSize = magicWandSize;
    }
    @Column(name = "deposit_group", length = 20)
    public String getDepositGroup() {
        return this.depositGroup;
    }

    public void setDepositGroup(String depositGroup) {
        this.depositGroup = depositGroup;
    }
    @Column(name = "start_date")
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    @Column(name = "deposit_amount")
    public BigDecimal getDepositAmount() {
        return this.depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }
    @Column(name = "deposit_interest")
    public BigDecimal getDepositInterest() {
        return this.depositInterest;
    }

    public void setDepositInterest(BigDecimal depositInterest) {
        this.depositInterest = depositInterest;
    }
    @Column(name = "deposit_charge")
    public BigDecimal getDepositCharge() {
        return this.depositCharge;
    }

    public void setDepositCharge(BigDecimal depositCharge) {
        this.depositCharge = depositCharge;
    }
    @Column(name = "deposit_exparation_date")
    public LocalDate getDepositExparationDate() {
        return this.depositExparationDate;
    }

    public void setDepositExparationDate(LocalDate depositExparationDate) {
        this.depositExparationDate = depositExparationDate;
    }

    @Column(name= "is_deposit_expired")
    public boolean isDepositExpired() {
        return this.isDepositExpired;
    }

    public void setDepositExpired(boolean depositExpired) {
        isDepositExpired = depositExpired;
    }


}

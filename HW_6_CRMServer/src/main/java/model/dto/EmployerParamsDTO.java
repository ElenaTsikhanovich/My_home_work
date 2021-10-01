package model.dto;

public class EmployerParamsDTO {
    private String name;
    private Double salaryFrom;
    private Double salaryTo;

    public EmployerParamsDTO(){

    }

    public EmployerParamsDTO(String name, Double salaryFrom, Double salaryTo) {
        this.name = name;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(Double salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public Double getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(Double salaryTo) {
        this.salaryTo = salaryTo;
    }
}

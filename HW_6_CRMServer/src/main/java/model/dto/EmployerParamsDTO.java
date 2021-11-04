package model.dto;

public class EmployerParamsDTO {
    private String name;
    private Double salaryFrom;
    private Double salaryTo;
    private Integer page;
    private Integer limit;
    private Integer offset;

    public EmployerParamsDTO() {

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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}



package controller.utils;

public enum Params {
    NAME("name"),
    SALARY("salary"),
    DEPARTMENT("department"),
    POSITION("position"),
    ID("id"),
    LIMIT("limit"),
    PAGE("page"),
    SALARY_FROM("salaryFrom"),
    SALARY_TO("salaryTo"),
    LIST("list"),
    PARENT("parent");

    private String title;

    Params(String title) {
        this.title=title;
    }

    public String getTitle() {
        return title;
    }
}


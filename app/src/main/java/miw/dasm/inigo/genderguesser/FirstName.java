package miw.dasm.inigo.genderguesser;

public class FirstName {

    private String name;
    private String gender;
    private String description;

    public FirstName(String name, String gender, String description) {
        this.name = name;
        this.gender = gender;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FirstName{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

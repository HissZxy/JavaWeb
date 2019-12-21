import org.omg.CORBA.StringHolder;

public class Student {
    private Integer id;
    private Integer sn;
    private String name;
    private String qqMail;
    private Integer classId;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sn=" + sn +
                ", name='" + name + '\'' +
                ", qqMail='" + qqMail + '\'' +
                ", classId=" + classId +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQqMail() {
        return qqMail;
    }

    public void setQqMail(String qqMail) {
        this.qqMail = qqMail;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getId(){}
}

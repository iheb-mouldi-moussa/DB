package Tables;

public class AllTables {
    private String name;


    public AllTables(String name)
    {
        super();
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return String.format("%s", name);
    }

}

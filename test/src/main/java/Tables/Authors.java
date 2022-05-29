package Tables;

public class Authors {
    
    private int id;
    private String Firstname, Lastname, mail;

    public Authors(int id, String Firstname, String Lastname, String mail)
    {
        super();
        this.id = id;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.mail = mail;
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return this.Firstname;
	}

	public void setFirstname(String Firstname) {
		this.Firstname = Firstname;
    }

    public String getLastname()
    {
        return this.Lastname;
    }

    public void setLastname(String Lastname)
    {
        this.Lastname = Lastname;
    }

    public String getMail()
    {
        return this.mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }
    
    @Override
	public String toString() {
		return String
				.format("Author [id=%s,  Firstname=%s, Lastname=%s, mail=%s]",
						id, Firstname, Lastname, mail);
	}
}

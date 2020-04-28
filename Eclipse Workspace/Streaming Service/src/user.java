
public class user {
	
	private String username;
    private String password;

    public user(String uname, String pword)
    {
        username = uname;
        password = pword;
    }

    public void setUserName(String uname)
    {
        this.username = uname;
    }

    public String getUserName()
    {
        return this.username;
    }

    public void setPassword(String pword)
    {
        this.password = pword;
    }

    public String getPassword()
    {
        return this.password;
    }

}

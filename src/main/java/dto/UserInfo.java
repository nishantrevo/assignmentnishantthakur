package dto;

public class UserInfo {

    private String name;
    private String email;
    private String password;
    
    public String getName(){
        return name;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
    	return (name+email+password).hashCode();
    }
    
    @Override
    public boolean equals(Object other){
        if(this == other) return true;
        if (!(other instanceof UserInfo)) return false;
        UserInfo otherUserInfo = (UserInfo) other;
        return name.equals(otherUserInfo.name)
                && email.equals(otherUserInfo.email)
                && password.equals(otherUserInfo.password);
    }

    @Override
    public String toString(){
        return String.format("[%s, %s, %s]",name,email,password);
    }
}

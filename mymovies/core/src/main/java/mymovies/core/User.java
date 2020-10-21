package mymovies.core;


public class User implements Comparable<User> {

    String userName; 
    String password;
    MyMovies myMovs; 

    public User(String userName, String password){
        if (validUserName(userName) && validPassword(password)){
            this.userName = userName;
            this.password = password; 
            myMovs = new MyMovies();
        }
    }
    
    /**
     * brukes av konstruktør til å hente ut riktig mymoviesObjekt
     * @return MyMovies-objekt eid av this.user. 
     */
    public MyMovies getUsersMyMovies(){ 
        return this.myMovs;
    }

    private boolean validUserName(String userName){
       return userName.length()>=2;
    }

    private boolean validPassword(String password){
        return password.length()>=8;
    }

    public String getUserName(){
        return this.userName;
    }

    @Override
    public int compareTo(User o) {
        return this.getUserName().compareTo(o.getUserName());
    }











}
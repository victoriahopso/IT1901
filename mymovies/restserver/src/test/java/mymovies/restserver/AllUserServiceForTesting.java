package mymovies.restserver;

public class AllUserServiceForTesting extends AllUsersService {

  @Override
  protected String getFileName(){
    return "it-allusers.json";
  }
}
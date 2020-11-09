package mymovies.restserver;

@RunWith(SpringRunner.class)
@WebMvcTest(AllUsersController.class)
public class AllUsersControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private AllUsersService service;

  // write test cases here
}
package mymovies.integrationtests;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AllUsersAppIT {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private EmployeeRepository employeeRepository;
 
    // write test cases here
 
}
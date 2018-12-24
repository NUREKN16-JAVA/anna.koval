package ua.nure.kn.koval.usermanagement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class UserTest {
    User user;
    private static final String FIRST_NAME_ETALONE="Anna";
    private static final String LAST_NAME_ETALONE="Koval";
    private static final String FULL_NAME_ETALONE="Koval, Anna";

    private static final int YEAR_OF_BIRTH=1999;
    private static final int ETALONE_AGE=19;
    private static final int DAY_OF_BIRTH=24;
    private static final int MONTH_OF_BIRTH=Calendar.AUGUST;

    @Before
    public void setUp() throws Exception {
        user=new User();
    }

    @After
    public void tearDown() throws Exception {
    }
    //test getFullName
    @Test
    public void getFullName() {
        user.setFirstName(FIRST_NAME_ETALONE);
        user.setLastName(LAST_NAME_ETALONE);
        assertEquals(FULL_NAME_ETALONE, user.getFullName());
    }
    //test getAge
    @Test
    public void testGetAge(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE, user.getAge());
    }

    // when birthday was one year ago
    @Test
    public void testYearAgo(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH-1, MONTH_OF_BIRTH, DAY_OF_BIRTH);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE+1, user.getAge());
    }
    // when birthday will be next day
    @Test
    public void testDayBeforeBirth()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, Calendar.getInstance().get(Calendar.MONTH) , DAY_OF_BIRTH-1);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE-1, user.getAge());
    }
    // when birthday was two days ago
    @Test
    public void testTwoDayBeforeBirth()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH , DAY_OF_BIRTH+2);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE, user.getAge());
    }
}
package com.advertising.dashboard;

import com.advertising.dashboard.dao.UserDao;
import com.advertising.dashboard.exception.UserNotFoundException;
import com.advertising.dashboard.model.UserRole;
import com.advertising.dashboard.model.dto.UserDto;
import com.advertising.dashboard.model.entity.User;
import com.advertising.dashboard.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class UserServiceTest {
    private UserDto expectedUser;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        expectedUser = new UserDto("Admin Name", "admin@i.ua", passwordEncoder.encode("admin"), UserRole.ADMIN.toString(), true);
        userDao.deleteAll();
        userDao.save(new User("Admin Name", "admin@i.ua", passwordEncoder.encode("admin"), UserRole.ADMIN.toString(), true));
    }

    @Test
    public void deleteTest() throws UserNotFoundException {
        List<UserDto> all = userService.findAllActive();
        Assert.assertEquals(1, all.size());
        userService.delete(all.get(0).getId());
        Assert.assertEquals(0, userService.findAllActive().size());
    }

    @Test
    public void findAllTest() {
        List<UserDto> all = userService.findAllActive();
        Assert.assertFalse(all.isEmpty());
        UserDto userDto = all.get(0);
        assertFields(expectedUser, userDto);
    }

    @Test
    public void saveTest() {
        UserDto userDto = new UserDto("Adops Name", "adops@i.ua", passwordEncoder.encode("adop"), UserRole.ADOPS.toString(), true);
        UserDto savedDto = userService.save(userDto);
        Assert.assertEquals(savedDto.getEmail(), userDto.getEmail());
        Assert.assertEquals(savedDto.getActive(), userDto.getActive());
        Assert.assertEquals(savedDto.getName(), userDto.getName());
        Assert.assertEquals(savedDto.getUserRole(), userDto.getUserRole());
    }


    @Test
    public void updateTest() {
        List<UserDto> all = userService.findAllActive();
        Assert.assertFalse(all.isEmpty());
        UserDto userDto = all.get(0);
        UserDto updatedDto = new UserDto("Admin Updated", "admin2@i.ua", passwordEncoder.encode("adop"), UserRole.ADMIN.toString(), true);

        UserDto savedDto = userService.update(userDto.getId(), updatedDto);
        Assert.assertEquals(savedDto.getEmail(), updatedDto.getEmail());
        Assert.assertEquals(savedDto.getActive(), updatedDto.getActive());
        Assert.assertEquals(savedDto.getName(), updatedDto.getName());
        Assert.assertEquals(savedDto.getUserRole(), updatedDto.getUserRole());
    }

    @Test
    public void findByIdTest() throws UserNotFoundException {
        UserDto userDto = userService.findById(userService.findAllActive().get(0).getId());
        assertFields(expectedUser, userDto);
    }

    @Test(expected = UserNotFoundException.class)
    public void findByNotExistedIdTest() throws UserNotFoundException {
        userService.findById(-1);
    }

    @Test
    public void findByEmailTest() throws UserNotFoundException {
        UserDto userDto = userService.findByEmail(expectedUser.getEmail());
        assertFields(expectedUser, userDto);
    }

    private void assertFields(UserDto expectedDto, UserDto userDto) {
        Assert.assertEquals(expectedDto.getEmail(), userDto.getEmail());
        Assert.assertEquals(expectedDto.getActive(), userDto.getActive());
        Assert.assertEquals(expectedDto.getName(), userDto.getName());
        Assert.assertEquals(expectedDto.getUserRole(), userDto.getUserRole());
    }

    @Test
    public void uniqueEmailTest() {
        Assert.assertFalse(userService.isEmailUnique(expectedUser.getEmail()));
        Assert.assertTrue(userService.isEmailUnique("email@email"));
    }

    @Test
    public void testGetAdopsAndPublishers() {
        Assert.assertEquals(1, userService.findAllActive().size());
        Assert.assertEquals(0, userService.findByRoles(Arrays.asList(UserRole.ADOPS, UserRole.PUBLISHER)).size());
        UserDto publisher = new UserDto("Publisher", "pub@i.ua", passwordEncoder.encode("pub"), UserRole.PUBLISHER.toString(), true);
        UserDto adops = new UserDto("Admin Updated", "admin2@i.ua", passwordEncoder.encode("adop"), UserRole.ADOPS.toString(), true);

        userService.save(publisher);
        userService.save(adops);
        Assert.assertEquals(3, userService.findAllActive().size());
        Assert.assertEquals(2, userService.findByRoles(Arrays.asList(UserRole.ADOPS, UserRole.PUBLISHER)).size());

    }
}


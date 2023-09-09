package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GalleryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the UserManager class.
 */
public class UserManagerTest {
    @Test
    void add() throws GalleryException {
        UserManager userManager = new UserManager();

        UserManager userManagerMock = mock(UserManager.class);
        when(userManagerMock.validateUsername(any())).thenReturn(true);

        String name = "Ciko";
        String surname = "Cikic";
        String username = "ciki";
        String password = "123456";

        User addedUser = userManager.add(new User(username,password,name,surname));

        boolean isAdded = (userManager.findUserByUsername(username) != null);

        Assertions.assertTrue(isAdded);

        // Clean up by deleting the added user
        userManager.delete(addedUser.getId());
    }
}

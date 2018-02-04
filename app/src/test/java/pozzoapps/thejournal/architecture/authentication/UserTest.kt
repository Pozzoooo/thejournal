package pozzoapps.thejournal.architecture.authentication

import org.junit.Assert.assertEquals
import org.junit.Test

class UserTest {
  @Test
  fun shouldReturnCredentialsBasedOnUsernameAndPassword() {
    val user = User("username", "password")
    assertEquals("username:password", user.credentials())
  }
}

package pozzoapps.thejournal.architecture.authentication

/**
 * Represents a final user that has access to this app.
 */
data class User(val username: String, val password: String) {
  fun credentials(): String {
    return "$username:$password"
  }
}

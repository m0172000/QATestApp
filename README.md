Test 1: verifyElementsVisibility()
This test checks that all the important elements on the login page are visible, like the "Sign In with Discord" link, the email and password text fields, the login button, and the links for "Forgot Password" and "Sign Up". The test passes if all these elements are visible.

Test 2: testValidLogin()
This test checks if a user can successfully log in using correct credentials. It enters a valid email and password, clicks the login button, and checks that the user is taken to the right URL and sees the correct page title after logging in.

Test 3: TestLoginPageWithInvalidData()
This test checks what happens when a user tries to log in with wrong email or password. It uses different combinations of invalid data and ensures that the correct error message ("Please enter a correct username and password...") appears.

Test 4: Test_login_With_EmptyTextfields()
This test makes sure the login page gives the right error messages when the user tries to log in without entering an email, a password, or both. The test checks that specific error messages show up, like "Email: This field is required" or "Password: This field is required."

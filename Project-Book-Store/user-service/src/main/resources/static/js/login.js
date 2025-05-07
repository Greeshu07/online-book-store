function loginUser() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var credentials = {
        username: username,
        password: password
    };

    fetch("http://localhost:8082/users/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(credentials)
    })
    .then(function(response) {
        if (response.ok) {
            return response.text(); // login message like "Login successful"
        } else {
            throw new Error("Invalid username or password");
        }
    })
    .then(function(message) {
        document.getElementById("login-msg").textContent = message;

        // Store username in localStorage
        localStorage.setItem("username", username);

        // Fetch user profile to get the userId
        return fetch(`http://localhost:8082/users/${username}`);
    })
    .then(function(response) {
        if (!response.ok) {
            throw new Error("Failed to fetch user profile");
        }
        return response.json();
    })
    .then(function(user) {
        // Store userId in localStorage
        localStorage.setItem("userId", user.id);
        window.location.href = "user_dashboard.html"; // Redirect to dashboard
    })
    .catch(function(error) {
        document.getElementById("login-msg").textContent = error.message;
    });

    return false; // Prevent form submission
}

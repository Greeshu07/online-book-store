function loginUser() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const credentials = {
        username: username,
        password: password
    };

    fetch(`${CONFIG.USER_SERVICE_URL}/users/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(credentials)
    })
    .then(response => {
        if (response.ok) {
            return response.text(); // e.g., "Login successful"
        } else {
            throw new Error("Invalid username or password");
        }
    })
    .then(message => {
        document.getElementById("login-msg").textContent = message;

        // Store username in localStorage
        localStorage.setItem("username", username);

        // Fetch user profile to get the userId
        return fetch(`${CONFIG.USER_SERVICE_URL}/users/${username}`);
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Failed to fetch user profile");
        }
        return response.json();
    })
    .then(user => {
        // Store userId in localStorage
        localStorage.setItem("userId", user.id);
        window.location.href = "user_dashboard.html";
    })
    .catch(error => {
        document.getElementById("login-msg").textContent = error.message;
    });

    return false; // Prevent form submission
}

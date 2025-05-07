function registerUser(event) {
    event.preventDefault();

    const user = {
        name: document.getElementById("username").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };

    fetch("http://localhost:8082/users/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user)
    })
    .then(response => {
        if (!response.ok) throw new Error("Registration failed");
        return response.json();
    })
    .then(data => {
        document.getElementById("register-msg").textContent = "Registration successful!";
        localStorage.setItem("userId", data.id);
        setTimeout(() => {
            window.location.href = "user_dashboard.html";
        }, 1000);
    })
    .catch(err => {
        document.getElementById("register-msg").textContent = "Registration failed. Try again.";
    });
}

function register() {
  const username = document.getElementById("reg-username").value;
  const password = document.getElementById("reg-password").value;

  fetch(`${CONFIG.MANAGER_SERVICE_URL}/manager/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, password })
  })
  .then(res => res.json())
  .then(data => {
    document.getElementById("reg-response").innerText = "✅ Successfully Registered!";
  })
  .catch(err => {
    document.getElementById("reg-response").innerText = "❌ Registration Failed!";
  });
}

function login() {
  const username = document.getElementById("login-username").value;
  const password = document.getElementById("login-password").value;

  fetch(`${CONFIG.MANAGER_SERVICE_URL}/manager/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, password })
  })
  .then(response => response.text())
  .then(result => {
    if (result === "Login Successful") {
      window.location.href = "dashboard.html";
    } else {
      document.getElementById("login-response").innerText = "Invalid username or password.";
    }
  })
  .catch(error => {
    console.error("Error:", error);
    document.getElementById("login-response").innerText = "Something went wrong.";
  });
}

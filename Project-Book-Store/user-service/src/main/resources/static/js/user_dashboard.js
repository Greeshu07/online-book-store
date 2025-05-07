// Get the username from localStorage
const username = localStorage.getItem("username");

// Fetch all books
function getAllBooks() {
    fetch("http://localhost:8082/users/books")
        .then(response => response.json())
        .then(data => displayTable(data, "Book"))
        .catch(error => alert("Failed to fetch books: " + error));
}

// Fetch user profile by username
function getUserProfile() {
    fetch(`http://localhost:8082/users/${username}`)
        .then(response => response.json())
        .then(data => displaySingle(data, "User Profile"))
        .catch(error => alert("Failed to fetch user profile: " + error));
}

// Fetch orders using userId from user profile
function getUserOrders() {
    fetch(`http://localhost:8082/users/${username}`)
        .then(response => response.json())
        .then(user => {
            const userId = user.id;
            return fetch(`http://localhost:8082/users/${userId}/orders`);
        })
        .then(response => response.json())
        .then(orderData => displayTable(orderData, "Order"))
        .catch(error => {
            alert("Failed to fetch user orders: " + error);
        }); // ✅ This closing brace was missing
}

// Show the order form
function showPlaceOrderForm() {
    document.getElementById("order-form-section").style.display = "block";
    document.getElementById("data-table").style.display = "none";
}

// Place order via POST
function placeOrder(event) {
    event.preventDefault();

    const bookId = document.getElementById("bookId").value;
    const quantity = document.getElementById("quantity").value;

    fetch(`http://localhost:8082/users/${username}`)
        .then(response => response.json())
        .then(user => {
            const orderData = {
                userId: user.id,
                bookId: parseInt(bookId),
                quantity: parseInt(quantity)
            };

            return fetch(`http://localhost:8082/users/${user.id}/order`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(orderData)
            });
        })
        .then(response => {
            if (!response.ok) throw new Error("Order failed");
            return response.json();
        })
        .then(orderResponse => {
            alert(`Order placed! Total: ₹${orderResponse.totalPrice}`);
            document.getElementById("order-form-section").style.display = "none";
            getUserOrders();
        })
        .catch(error => {
            alert("Failed to place order: " + error.message);
        });
}


// Logout function
function logout() {
    localStorage.removeItem("username");
    localStorage.removeItem("userId");
    alert("Logged out successfully!");
    window.location.href = "login.html";
}

// Function to display tables
function displayTable(data, type) {
    const tableHead = document.getElementById("table-head");
    const tableBody = document.getElementById("table-body");

    tableHead.innerHTML = "";
    tableBody.innerHTML = "";

    if (!Array.isArray(data) || data.length === 0) {
        tableBody.innerHTML = `<tr><td colspan="5">No ${type.toLowerCase()} data found</td></tr>`;
        return;
    }

    const keys = Object.keys(data[0]);
    const headerRow = document.createElement("tr");

    keys.forEach(key => {
        const th = document.createElement("th");
        th.textContent = key.charAt(0).toUpperCase() + key.slice(1);
        headerRow.appendChild(th);
    });

    tableHead.appendChild(headerRow);

    data.forEach(item => {
        const row = document.createElement("tr");
        keys.forEach(key => {
            const td = document.createElement("td");
            td.textContent = item[key];
            row.appendChild(td);
        });
        tableBody.appendChild(row);
    });
}

// Function to display user profile
function displaySingle(data, title) {
    const tableHead = document.getElementById("table-head");
    const tableBody = document.getElementById("table-body");

    tableHead.innerHTML = `<tr><th colspan="2">${title}</th></tr>`;
    tableBody.innerHTML = "";

    for (let key in data) {
        const row = document.createElement("tr");
        const cellKey = document.createElement("td");
        const cellValue = document.createElement("td");

        cellKey.textContent = key.charAt(0).toUpperCase() + key.slice(1);
        cellValue.textContent = data[key];

        row.appendChild(cellKey);
        row.appendChild(cellValue);
        tableBody.appendChild(row);
    }
}

// Initial load

getAllBooks();

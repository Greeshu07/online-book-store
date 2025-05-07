// ----------------- BOOK FUNCTIONS -----------------

// Fetch and display all books
function getAllBooks() {
  fetch("http://localhost:8084/manager/books") // Backend URL
    .then(response => response.json())
    .then(data => displayTable(data, "books-table"))
    .catch(error => alert("Error fetching books: " + error));
}

// Add a new book
function addBook() {
  // Collect data from input fields
  const book = {
    title: document.getElementById("book-title").value,
    author: document.getElementById("book-author").value,
    price: parseFloat(document.getElementById("book-price").value),
    stock: parseInt(document.getElementById("book-stock").value)
  };

  fetch("http://localhost:8084/manager/books", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(book)
  })
    .then(response => response.json())
    .then(data => alert("Book added successfully!"))
    .catch(error => alert("Error adding book: " + error));
}

// Update a book by ID
function updateBook() {
  const bookId = document.getElementById("book-id").value;

  const book = {
    title: document.getElementById("book-title").value,
    author: document.getElementById("book-author").value,
    price: parseFloat(document.getElementById("book-price").value),
    stock: parseInt(document.getElementById("book-stock").value)
  };

  fetch(`http://localhost:8084/manager/books/${bookId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(book)
  })
    .then(response => response.json())
    .then(data => alert("Book updated successfully!"))
    .catch(error => alert("Error updating book: " + error));
}
//Patch By Feild

function patchBook() {
  const id = document.getElementById("patch-id").value;
  const book = {
    title: document.getElementById("patch-title").value || null,
    author: document.getElementById("patch-author").value || null,
    price: document.getElementById("patch-price").value || null,
    stock: document.getElementById("patch-stock").value || null
  };

  fetch(`/manager/books/${id}`, {
    method: "PATCH",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(book)
  })
    .then(res => {
      if (res.ok) return res.json();
      throw new Error();
    })
    .then(() => {
      alert("Book updated successfully!");
      getAllBooks();
    })
    .catch(() => alert("Error updating book"));
}


// ----------------- USER FUNCTIONS -----------------

// Fetch and display all users
function getAllUsers() {
  fetch("http://localhost:8084/manager/users")
    .then(response => response.json())
    .then(data => displayTable(data, "users-table"))
    .catch(error => alert("Error fetching users: " + error));
}

// ----------------- ORDER FUNCTIONS -----------------

// Fetch and display all orders
function getAllOrders() {
  fetch("http://localhost:8084/manager/orders")
    .then(response => response.json())
    .then(data => displayTable(data, "orders-table"))
    .catch(error => alert("Error fetching orders: " + error));
}

// ----------------- HELPER FUNCTION -----------------

// Display any data in a table (reusable for books, users, orders)
function displayTable(dataList, tableId) {
  const table = document.getElementById(tableId);
  table.innerHTML = ""; // Clear old data

  // If list is empty
  if (dataList.length === 0) {
    table.innerHTML = "<tr><td colspan='100%'>No data found</td></tr>";
    return;
  }

  // Create header row
  const headers = Object.keys(dataList[0]);
  const headerRow = document.createElement("tr");

  headers.forEach(header => {
    const th = document.createElement("th");
    th.textContent = header;
    headerRow.appendChild(th);
  });

  table.appendChild(headerRow);

  // Create data rows
  dataList.forEach(item => {
    const row = document.createElement("tr");
    headers.forEach(key => {
      const td = document.createElement("td");
      td.textContent = item[key];
      row.appendChild(td);
    });
    table.appendChild(row);
  });
}

// ----------------- LOGOUT -----------------

// Redirect to login page
function logout() {
  window.location.href = "login.html";
}


function showAddForm() {
  document.getElementById("book-form").classList.remove("hidden");
  document.getElementById("search-form").classList.add("hidden");
}

function showUpdateForm() {
  document.getElementById("book-form").classList.remove("hidden");
  document.getElementById("search-form").classList.add("hidden");
}

function showSearchForm() {
  document.getElementById("search-form").classList.remove("hidden");
  document.getElementById("book-form").classList.add("hidden");
}

// Add this to submit the book (Add or Update based on book-id)
function submitBook() {
  const bookId = document.getElementById("book-id").value;
  if (bookId === "") {
    addBook();
  } else {
    updateBook();
  }
}

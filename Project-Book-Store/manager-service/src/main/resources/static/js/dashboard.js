// ----------------- BOOK FUNCTIONS -----------------

function getAllBooks() {
  fetch(`${CONFIG.MANAGER_SERVICE_URL}/manager/books`)
    .then(response => response.json())
    .then(data => displayTable(data, "books-table"))
    .catch(error => alert("Error fetching books: " + error));
}

function addBook() {
  const book = {
    title: document.getElementById("book-title").value,
    author: document.getElementById("book-author").value,
    price: parseFloat(document.getElementById("book-price").value),
    stock: parseInt(document.getElementById("book-stock").value)
  };

  fetch(`${CONFIG.MANAGER_SERVICE_URL}/manager/books`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(book)
  })
    .then(response => response.json())
    .then(data => alert("Book added successfully!"))
    .catch(error => alert("Error adding book: " + error));
}

function updateBook() {
  const bookId = document.getElementById("book-id").value;

  const book = {
    title: document.getElementById("book-title").value,
    author: document.getElementById("book-author").value,
    price: parseFloat(document.getElementById("book-price").value),
    stock: parseInt(document.getElementById("book-stock").value)
  };

  fetch(`${CONFIG.MANAGER_SERVICE_URL}/manager/books/${bookId}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(book)
  })
    .then(response => response.json())
    .then(data => alert("Book updated successfully!"))
    .catch(error => alert("Error updating book: " + error));
}

function patchBook() {
  const id = document.getElementById("patch-id").value;
  const book = {
    title: document.getElementById("patch-title").value || null,
    author: document.getElementById("patch-author").value || null,
    price: document.getElementById("patch-price").value || null,
    stock: document.getElementById("patch-stock").value || null
  };

  fetch(`${CONFIG.MANAGER_SERVICE_URL}/manager/books/${id}`, {
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

function getAllUsers() {
  fetch(`${CONFIG.MANAGER_SERVICE_URL}/manager/users`)
    .then(response => response.json())
    .then(data => displayTable(data, "users-table"))
    .catch(error => alert("Error fetching users: " + error));
}

// ----------------- ORDER FUNCTIONS -----------------

function getAllOrders() {
  fetch(`${CONFIG.MANAGER_SERVICE_URL}/manager/orders`)
    .then(response => response.json())
    .then(data => displayTable(data, "orders-table"))
    .catch(error => alert("Error fetching orders: " + error));
}

// ----------------- HELPER FUNCTION -----------------

function displayTable(dataList, tableId) {
  const table = document.getElementById(tableId);
  table.innerHTML = "";

  if (dataList.length === 0) {
    table.innerHTML = "<tr><td colspan='100%'>No data found</td></tr>";
    return;
  }

  const headers = Object.keys(dataList[0]);
  const headerRow = document.createElement("tr");

  headers.forEach(header => {
    const th = document.createElement("th");
    th.textContent = header;
    headerRow.appendChild(th);
  });

  table.appendChild(headerRow);

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

function logout() {
  window.location.href = "login.html";
}

// ----------------- FORM VISIBILITY -----------------

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

// ----------------- ADD/UPDATE SUBMIT LOGIC -----------------

function submitBook() {
  const bookId = document.getElementById("book-id").value;
  if (bookId === "") {
    addBook();
  } else {
    updateBook();
  }
}
